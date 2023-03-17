package com.example.codesmell.detector.controller;

import com.example.codesmell.detector.chatgpt.Chat4;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContentController {
    private final Object lock = new Object();
    private boolean isParamReady = false;
    @RequestMapping(value="/post", method= RequestMethod.POST)
    public String post(String smellName, String codeContent){
        String des = "This code has a code smell problem:"+smellName+"Please give me your modified code directly";
        String intro = "Here is the code:";

        Chat4 c4 = new Chat4();
        String str = c4.chat(des+intro+codeContent);
        int contentStart = str.indexOf("content=") + 8; // 8 是 "content=" 的长度
        int contentEnd = str.indexOf(")]");
        String content = str.substring(contentStart, contentEnd);
        return content;
    }
}
