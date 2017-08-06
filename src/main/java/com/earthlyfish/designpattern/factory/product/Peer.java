package com.earthlyfish.designpattern.factory.product;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public class Peer implements Fruit {
    public Peer() {
        System.out.println("I am a Peer!");
    }

    @Override public void taste() {
        System.out.println("I am a Peer!so good!");
    }
}
