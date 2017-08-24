package com.yc.weixin.manager.base;

/**
 * 类名: BaseMessage </br>
 * 描述: 请求消息的基类 </br>
 * 开发人员： souvc</br>
 * 创建时间：  Sep 29, 2015 </br>
 * 发布版本：V1.0  </br>
 */
public class BaseRequestMessage extends BaseMessage {

    // 消息id，64位整型
    private long MsgId;

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
