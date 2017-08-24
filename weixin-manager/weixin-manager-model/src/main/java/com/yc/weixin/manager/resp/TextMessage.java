package com.yc.weixin.manager.resp;


import com.yc.weixin.manager.base.BaseResponseMessage;

/**
 * 类名: TextMessage </br>
 * 描述: 响应文本消息 </br>
 * 开发人员： souvc </br>
 * 创建时间：  2015-9-30 </br>
 * 发布版本：V1.0  </br>
 */
public class TextMessage extends BaseResponseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
