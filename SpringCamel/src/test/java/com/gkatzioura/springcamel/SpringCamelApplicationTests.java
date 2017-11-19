package com.gkatzioura.springcamel;

import com.gkatzioura.springcamel.routes.TimerRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class SpringCamelApplicationTests {

    @EndpointInject(uri = MOCK_RESULT)
    private MockEndpoint resultEndpoint;

	@Autowired
    private CamelContext camelContext;

    @EndpointInject(uri = MOCK_TIMER)
    private ProducerTemplate producer;

    private static final String MOCK_RESULT = "mock:result";
    private static final String MOCK_TIMER = "direct:mock-timer";

    @Before
	public void setup() throws Exception {

        camelContext.getRouteDefinition(TimerRoute.ROUTE_NAME)
                .autoStartup(true)
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        replaceFromWith(MOCK_TIMER);
                        interceptSendToEndpoint("log*")
                                .skipSendToOriginalEndpoint()
                                .to(MOCK_RESULT);
                    }
                });
    }

	@Test
	public void contextLoads() throws Exception {

        resultEndpoint.expectedMessageCount(1);
        producer.sendBody("A message");
        resultEndpoint.assertIsSatisfied();
    }

}
