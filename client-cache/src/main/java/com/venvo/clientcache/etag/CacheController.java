package com.venvo.clientcache.etag;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author venvo
 * @date 2021-09-02 21:50
 * @description
 * @modified By
 * @version: jdk1.8
 */

@RestController
@RequestMapping("/cache")
public class CacheController {

    private MyFile file = MyFile.getInstance();
    @RequestMapping("/cc")
    public ResponseEntity<String> lastreq(@RequestHeader(value="IF-Modified-Since",required = false) Date ifModifiedSince) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

        long now = System.currentTimeMillis() / 1000 *1000;
        // 系统当前时间

        System.out.println(now);
        // 缓存时间
        long maxAge = 20;

        HttpHeaders headers = new HttpHeaders();

        if (null != ifModifiedSince && ifModifiedSince.getTime() == file.getLastModified() ) {

            System.out.println(304);

        }

        headers.add("Date", simpleDateFormat.format(new Date(now)));
        headers.add("Expires", simpleDateFormat.format(new Date(now + maxAge * 1000)));
        headers.add("Cache-Control", "max-age="+maxAge);
        headers.add("Last-Modified", simpleDateFormat.format(new Date(file.getLastModified())));

        String body = "<a href =''>hi点我</a>";
        return new ResponseEntity<>(body,headers,HttpStatus.OK);
    }

    @RequestMapping("/")
    public ResponseEntity<String> last(@RequestHeader(value = "IF-Modified-Since", required = false) Date ifModifiedSince) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

        long now = System.currentTimeMillis() / 1000 * 1000;

        HttpHeaders headers = new HttpHeaders();

        String body = "<a href =''>hi点我</a>";

        String ETag = getMd5(body);

        headers.add("Date", simpleDateFormat.format(new Date(now)));
        headers.add("ETag", ETag);

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    /**
     * 字符串转md5
     *
     * @param msg
     * @return
     */
    private String getMd5(String msg) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        md5.update(msg.getBytes());
        byte[] digest = md5.digest();

        StringBuffer buf = null;
        buf = new StringBuffer(digest.length * 2);
        //遍历
        for (int i = 0; i < digest.length; i++) {
            if (((int) digest[i] & 0xff) < 0x10) { //(int) b[i] & 0xff 转换成无符号整型
                buf.append("0");
            }
            //Long.toHexString( 无符号长整数的十六进制字符串表示
            buf.append(Long.toHexString((int) digest[i] & 0xff));
        }
        return buf.toString();
    }
}
