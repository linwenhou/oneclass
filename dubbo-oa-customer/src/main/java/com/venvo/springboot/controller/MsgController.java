package com.venvo.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.venvo.springboot.config.WxConfig;
import com.venvo.springboot.filter.WxAuthFilter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.yaml.snakeyaml.reader.StreamReader;
import weixin.popular.api.MessageAPI;
import weixin.popular.api.QrcodeAPI;
import weixin.popular.api.WxopenAPI;
import weixin.popular.bean.message.templatemessage.TemplateMessage;
import weixin.popular.bean.message.templatemessage.TemplateMessageResult;
import weixin.popular.bean.qrcode.QrcodeTicket;
import weixin.popular.support.TokenManager;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @author venvo
 * @date 2021-08-10 16:01
 * @description
 * @modified By
 * @version: jdk1.8
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    private static final Logger logger = LoggerFactory.getLogger(MsgController.class);

    @Autowired
    WxConfig wxConf;

    @RequestMapping("")
    public Object list(@RequestParam Map<String, String> param, HttpServletRequest request, HttpServletResponse resp) throws Exception {
//        TemplateMessage templateMessage = new TemplateMessage();
//        templateMessage.setUrl("http://venvo.nat300.top/profile/my");
//        templateMessage.setTemplate_id("lua56GGORSj9hSF4IyReXzKWsY6fXzmGvZPuvWU4Zj8");
//        templateMessage.setTouser("o1N5g6B18-zqbZgymV2g8A_Y9AG4");
//        TemplateMessageResult messageTemplateSend = MessageAPI.messageTemplateSend(TokenManager.getDefaultToken(), templateMessage);
//        return messageTemplateSend;


        QrcodeTicket qrcodeCreateTemp = QrcodeAPI.qrcodeCreateTemp(TokenManager.getDefaultToken(), 604800, "user_id=10000");
        System.out.println("qrcodeCreateTemp:" + ToStringBuilder.reflectionToString(qrcodeCreateTemp));
        BufferedImage showqrcode = QrcodeAPI.showqrcode(qrcodeCreateTemp.getTicket());

        ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
        ImageIO.write(showqrcode, "png", os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
        byte b[]=os.toByteArray();//从流中获取数据数组。




        resp.getOutputStream().write(b);

        return qrcodeCreateTemp;




    }
}
