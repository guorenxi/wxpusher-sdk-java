package com.smjcco.wxpusher.client.sdk.demo.controller;

import com.smjcco.wxpusher.client.sdk.WxPusher;
import com.smjcco.wxpusher.client.sdk.bean.Message;
import com.smjcco.wxpusher.client.sdk.bean.Result;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：发送消息的演示
 * 作者：zjiecode
 * 时间：2019-10-05
 */
@RestController
@RequestMapping("/demo/send")
public class SendController {

    @Value("${wxpusher.biz.apptoken}")
    private String appToken;

    private String sign = "本消息来自WxPusher演示程序 http://wxpusher.zjiecode.com/demo";

    /**
     * 发送普通文本
     */
    @GetMapping("/text/{uid}")
    public Result sendText(@PathVariable("uid") String uid) {
        Message message = new Message();
        message.setContentType(Message.CONTENT_TYPE_TEXT);
        message.setUid(uid);
        message.setAppToken(appToken);
        message.setContent("WxPusher演示消息，这里是一条普通的演示消息\n\n\n" + sign);
        return WxPusher.getDefaultWxPusher().send(message);
    }

    /**
     * 发送html文本
     */
    @GetMapping("/html/{uid}")
    public Result sendHtml(@PathVariable("uid") String uid) {
        Message message = new Message();
        message.setContentType(Message.CONTENT_TYPE_HTML);
        message.setUid(uid);
        message.setAppToken(appToken);
        message.setContent("WxPusher演示消息，这是一个html消息<br />标题：<span style='color:red;'>这是标题</span><br />状态：<span style='color:green;'>成功</span>"
                + "<br /><br /><br />" + sign);
        return WxPusher.getDefaultWxPusher().send(message);
    }


    /**
     * 发送markdown
     */
    @GetMapping("/markdown/{uid}")
    public Result sendMarkdown(@PathVariable("uid") String uid) {
        Message message = new Message();
        message.setContentType(Message.CONTENT_TYPE_MD);
        message.setUid(uid);
        message.setAppToken(appToken);
        message.setContent("WxPusher演示消息，这是一个Markdown消息\n# 目录\n- 什么是Wxpusher\n- Wxpusher可好用了\n## 发送状态：_成功_"
                + "\n\n<br /><br /><br />" + sign);
        return WxPusher.getDefaultWxPusher().send(message);
    }

    /**
     * 发送markdown
     */
    @GetMapping("/custom/{uid}")
    public Result sendCustom(@PathVariable("uid") String uid, String content) {
        Message message = new Message();
        message.setContentType(Message.CONTENT_TYPE_TEXT);
        message.setUid(uid);
        message.setAppToken(appToken);
        message.setContent(content + "\n\n\n" + sign);
        return WxPusher.getDefaultWxPusher().send(message);
    }

}
