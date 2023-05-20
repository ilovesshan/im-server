package com.ilovesshan.im.core.netty;


import com.alibaba.fastjson.JSON;
import com.ilovesshan.im.core.constants.ImConstants;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Configuration
@ChannelHandler.Sharable
public class CoordinationSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public Map<String, Channel> cmap = new HashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("与客户端建立连接，通道开启！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("与客户端断开连接，通道关闭！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //接收的消息
        Map map = JSON.parseObject(msg.text(), Map.class);
        Integer type = Integer.parseInt(map.get("type").toString());
        switch (type) {
            case ImConstants.LOGOUT:
                // 下线
                handleLogout(map, ctx);
                break;
            case ImConstants.LOGIN:
                // 上线
                handleLogin(map, ctx);
                break;
            case ImConstants.SEND_MESSAGE:
                // 发送消息
                handleSendMessage(map, ctx);
                break;
            default:
        }
        log.debug("收到客户端{}的数据：{}", ctx.channel().id(), msg.text());
    }


    private void handleLogout(Map map, ChannelHandlerContext ctx) {
        String uid = map.get("uid").toString();
        log.debug("uid用户下线：{}", uid);
        cmap.remove("user" + uid);
    }

    private void handleSendMessage(Map map, ChannelHandlerContext ctx) {
        String uid = map.get("uid").toString();
        String toId = map.get("toId").toString();
        String message = map.get("message").toString();
        log.debug("{}向{}发送消息：{}", uid, toId, message);

        // 消息推送
        Channel channel = cmap.get("user" + toId);
        if (channel != null) {
            Map<String, Object> pushResponse = new HashMap<>();
            pushResponse.put("type", String.valueOf(ImConstants.SEND_MESSAGE));
            pushResponse.put("fid", uid);
            pushResponse.put("uuid", UUID.randomUUID().toString());
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(pushResponse)));
        }
    }

    private void handleLogin(Map map, ChannelHandlerContext ctx) {
        String uid = map.get("uid").toString();
        cmap.put("user" + uid, ctx.channel());
        log.debug(uid + "登录");
    }
}