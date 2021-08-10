package com.venvo.springboot.controller;

import com.venvo.springboot.config.WxConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import weixin.popular.api.MenuAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLImageMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.TokenManager;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author venvo
 * @date 2021-08-08 20:51
 * @description
 * @modified By
 * @version: jdk1.8
 */
@RestController
@RequestMapping("/WXService")
public class WXServiceController {

    Logger logger = LoggerFactory.getLogger(WXServiceController.class);

    // 重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();

    @Autowired
    WxConfig wxConfig;




    @RequestMapping("/createMenu")
    @ResponseBody
    public BaseResult createMenu() {

        String menuString = "{\r\n" +
                "     \"button\":[\r\n" +
                "     {    \r\n" +
                "          \"type\":\"click\",\r\n" +
                "          \"name\":\"今日歌曲\",\r\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\r\n" +
                "      },\r\n" +
                "      {\r\n" +
                "           \"name\":\"菜单\",\r\n" +
                "           \"sub_button\":[\r\n" +
                "           {    \r\n" +
                "               \"type\":\"view\",\r\n" +
                "               \"name\":\"搜索\",\r\n" +
                "               \"url\":\"http://www.soso.com/\"\r\n" +
                "            },\r\n" +
                "            {\r\n" +
                "                 \"type\":\"miniprogram\",\r\n" +
                "                 \"name\":\"wxa\",\r\n" +
                "                 \"url\":\"http://mp.weixin.qq.com\",\r\n" +
                "                 \"appid\":\"wx286b93c14bbf93aa\",\r\n" +
                "                 \"pagepath\":\"pages/lunar/index\"\r\n" +
                "             },\r\n" +
                "            {\r\n" +
                "               \"type\":\"click\",\r\n" +
                "               \"name\":\"赞一下我们\",\r\n" +
                "               \"key\":\"V1001_GOOD\"\r\n" +
                "            }]\r\n" +
                "       }]\r\n" +
                " }";

        String menuString2 = "{\r\n" +
                "    \"button\": [\r\n" +
                "        {\r\n" +
                "            \"name\": \"扫码\",\r\n" +
                "            \"sub_button\": [\r\n" +
                "                {\r\n" +
                "                    \"type\": \"scancode_waitmsg\",\r\n" +
                "                    \"name\": \"扫码带提示\",\r\n" +
                "                    \"key\": \"rselfmenu_0_0\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                },\r\n" +
                "                {\r\n" +
                "                    \"type\": \"scancode_push\",\r\n" +
                "                    \"name\": \"扫码推事件\",\r\n" +
                "                    \"key\": \"rselfmenu_0_1\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                }\r\n" +
                "            ]\r\n" +
                "        },\r\n" +
                "        {\r\n" +
                "            \"name\": \"发图\",\r\n" +
                "            \"sub_button\": [\r\n" +
                "                {\r\n" +
                "                    \"type\": \"pic_sysphoto\",\r\n" +
                "                    \"name\": \"系统拍照发图\",\r\n" +
                "                    \"key\": \"rselfmenu_1_0\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                },\r\n" +
                "                {\r\n" +
                "                    \"type\": \"pic_photo_or_album\",\r\n" +
                "                    \"name\": \"拍照或者相册发图\",\r\n" +
                "                    \"key\": \"rselfmenu_1_1\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                },\r\n" +
                "                {\r\n" +
                "                    \"type\": \"pic_weixin\",\r\n" +
                "                    \"name\": \"微信相册发图\",\r\n" +
                "                    \"key\": \"rselfmenu_1_2\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                }\r\n" +
                "            ]\r\n" +
                "        },\r\n" +
                "        {\r\n" +
                "            \"name\": \"发送位置\",\r\n" +
                "            \"type\": \"location_select\",\r\n" +
                "            \"key\": \"rselfmenu_2_0\"\r\n" +
                "        }\r\n" +
                "    ]\r\n" +
                "}";

        BaseResult result = MenuAPI.menuCreate(TokenManager.getDefaultToken(), menuString2);
        return result;
    }

    @RequestMapping("/signature")
    @ResponseBody
    public void signature(@RequestParam Map<String, String> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServletInputStream inputStream = request.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();

        //算出来的签名
        String signature = param.get("signature");
        String echostr = param.get("echostr");
        String timestamp = param.get("timestamp");
        String nonce = param.get("nonce");
        // 对称加密  本地
        String token = wxConfig.getTokenString();

        if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp)) {
            outputStreamWrite(outputStream, "faild request");
            return;
        }

        if (echostr != null) {
            outputStreamWrite(outputStream, echostr);
            return;
        }

        // 验证请求签名
        if (!signature.equals(SignatureUtil.generateEventMessageSignature(token, timestamp, nonce))) {
            System.out.println("The request signature is invalid");
            return;
        }

        if (inputStream != null) {
            // 转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);

            logger.info("eventMessage:" + ToStringBuilder.reflectionToString(eventMessage));
            String key = eventMessage.getFromUserName() + "__" + eventMessage.getToUserName() + "__" + eventMessage.getMsgId() + "__" + eventMessage.getCreateTime();


            if (expireKey.exists(key)) {
                // 重复通知不作处理
                System.err.println("重复通知不作处理");
                return;
            } else {
                expireKey.add(key);
            }


            logger.info("token="+TokenManager.getDefaultToken());

            XMLTextMessage xmlTextMessage2 = new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(),
                    "请先<a href='http://venvo.nat300.top/profile/my'>完善一下信息</a>");
            xmlTextMessage2.outputStreamWrite(outputStream);


//			XMLImageMessage xmlImageMessage = new XMLImageMessage(eventMessage.getFromUserName(),eventMessage.getToUserName() , "3bhMcjmw8SMbpYH3FbU5JwtTXF2osbmrnM-b7hHWkVfnP3bFVVItln9IQz0s4Nek");
//
//			xmlImageMessage.outputStreamWrite(outputStream);
            return;
        }
    }


    private boolean outputStreamWrite(OutputStream outputStream, String text) {
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
