package com.netty.thirdexample.client;

import com.netty.secondexample.client.MyClientInitializer;
import com.netty.thirdexample.MyChatServerInitiailzer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyChatClinet {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channelFuture = bootstrap.connect("localhost", 8899).sync().channel();


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for(;;) {
                channelFuture.writeAndFlush(br.readLine() + "\n");
            }


        } finally {

            eventLoopGroup.shutdownGracefully();

        }

    }

}
