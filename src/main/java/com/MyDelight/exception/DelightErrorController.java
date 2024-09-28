package com.MyDelight.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DelightErrorController implements ErrorController{
	
	@RequestMapping("/error")
    @ResponseBody
    public void handleError(HttpServletRequest request) {
		//logger.error(CommonConstants.INVALID_URL_LOG);
        throw new DelightException("Invalid URL please try with valid URL...");
    }
}
