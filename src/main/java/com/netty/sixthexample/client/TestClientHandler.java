package com.netty.sixthexample.client;

import com.netty.sixthexample.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //　随机生成0,1,2
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;

        if (0 == randomInt) {

            myMessage = MyDataInfo.MyMessage
                    .newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder()
                            .setName("饶进阳")
                            .setAge(27)
                            .setAddress("上海")
                            .build())
                    .build();

        } else if (1 == randomInt) {

            myMessage = MyDataInfo.MyMessage
                    .newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("一只狗")
                            .setAge(2)
                            .build())
                    .build();

        } else {

            myMessage = MyDataInfo.MyMessage
                    .newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setName("一只猫")
                            .setCity("苏州")
                            .build())
                    .build();

        }


        ctx.channel().writeAndFlush(myMessage);

    }
}
