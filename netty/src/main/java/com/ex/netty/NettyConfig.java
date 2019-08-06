package com.ex.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
* @Author: Ben
* @Date: 2019-05-07
* @Description: 存储整个工程的全局配置
*/
public class NettyConfig {
    /**
     * 存储每一个客户端接入进来的channel对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
