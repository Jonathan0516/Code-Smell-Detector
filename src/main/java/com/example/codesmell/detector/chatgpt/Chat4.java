package com.example.codesmell.detector.chatgpt;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import org.junit.Before;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * 描述： 测试类
 *
 * @author https:www.unfbx.com
 * 2023-02-28
 */
public class Chat4 {

    public OpenAiClient v2;


    @Before
    public void before() {
        //代理可以不设置
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
//        client = new OpenAiStreamClient("sk-**********************",
//                60,
//                60,
//                60,
//                proxy);
        //推荐这种构造方式
        v2 = OpenAiClient.builder()
                .connectTimeout(300)
                .readTimeout(300)
                .writeTimeout(300)
//                .apiKey("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")
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build();
    }

    public String chat(String output) {
        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content(output).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        before();
        ChatCompletionResponse chatCompletionResponse = v2.chatCompletion(chatCompletion);
        List<Message> response = new ArrayList<>();
        chatCompletionResponse.getChoices().forEach(e -> {
            response.add(e.getMessage());
        });
        String str = response.toString();
        return str;
    }
}


