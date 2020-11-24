package com.controller.test;

import com.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DataDictionaryService dataDictionaryService;

    @RequestMapping("/get")
    @ResponseBody
    public Object getMapDataDictionary(){
        return  dataDictionaryService.getMapDataDictionary();
    }
}
