package com.yc.weixin.manager.validate;

import com.yc.weixin.manager.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/weixin")
public class WeixinController {
    private static final Logger log = LoggerFactory.getLogger(WeixinController.class);

    /**
     * 确认请求来自微信服务器
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = { "signature" }, method = RequestMethod.GET)
    public void signature(
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr,
            HttpServletResponse response) throws IOException {
        boolean bool = SignUtil.checkSignature(signature, timestamp, nonce);
        PrintWriter writer = response.getWriter();
        if (bool) {// 验证成功返回ehcostr
            writer.print(echostr);
            log.info("验证成功。");
        } else {
            writer.print("error");
            log.error("验证失败。");
        }
        writer.flush();
        writer.close();
        writer = null;
    }
}
