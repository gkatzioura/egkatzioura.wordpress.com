package com.gkatzioura.sqstesting;

import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gkatziourasemmanouil on 24/02/16.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/write",method = RequestMethod.POST)
    public void write(HttpServletRequest servletRequest,HttpServletResponse servletResponse) throws IOException {

        InputStream inputStream = servletRequest.getInputStream();

        String message = IOUtils.toString(inputStream);

        messageService.sendMessage(message);
    }

}

