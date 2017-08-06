package com.earthlyfish.designpattern.factory.factorymethod;

import com.earthlyfish.designpattern.factory.product.Fruit;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public class TestClass {
    public static void main(String[] args) {
        IFruitFactory factory = new FruitFactory();
        Fruit apple = factory.getApple();
        Fruit peer = factory.getPeer();
        apple.taste();
        peer.taste();
    }
}
