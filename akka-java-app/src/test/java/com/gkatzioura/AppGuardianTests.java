package com.gkatzioura;

import akka.actor.testkit.typed.javadsl.LogCapturing;
import akka.actor.testkit.typed.javadsl.LoggingTestKit;
import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.testkit.typed.javadsl.TestProbe;
import akka.actor.typed.ActorRef;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class AppGuardianTests {

	@ClassRule
	public static final TestKitJunitResource testKit = new TestKitJunitResource();

	@Rule
	public final LogCapturing logCapturing = new LogCapturing();

	@Test
	public void testReceiveMessage() {
		ActorRef<AppGuardian.GuardianMessage> underTest = testKit.spawn(AppGuardian.create(), "app-guardian");

		LoggingTestKit.info("Message received: hello")
				.expect(
						testKit.system(),
						() -> {
							underTest.tell(new AppGuardian.MessageToGuardian("hello"));
							return null;
						});
	}

}