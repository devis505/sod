package com.webdivas.sod.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webdivas.sod.responce.core.IResponceCreator;

@Controller
public class MainController {

    @Autowired
    IResponceCreator responce;

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public void jsonResponce(@RequestParam("json") String json, HttpServletResponse response) throws IOException {

	logger.info("Получили запрос: " + json);
	String send = "";

	if (json != null) {
	    responce.setRequestParams(json);
	    responce.loadData();

	    send = responce.getResponceParamsToString();

	    response.setCharacterEncoding("UTF-8");
	    response.setHeader("Content-Type", "application/json; charset=UTF-8");
	}

	logger.info("Отправили ответ: " + send);
	
	response.getWriter().print(send);
    }

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
}
