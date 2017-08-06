package com.earthlyfish.designpattern.factory.product;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public class Apple implements Fruit {

    public Apple() {
        System.out.println("I am a Apple!");
    }

    @Override public void taste() {
        System.out.println("I am a Apple!so good!");
    }
}
