package com.earthlyfish.designpattern.factory.factorymethod;

import com.earthlyfish.designpattern.factory.product.Apple;
import com.earthlyfish.designpattern.factory.product.Fruit;
import com.earthlyfish.designpattern.factory.product.Peer;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public class FruitFactory implements IFruitFactory {

    @Override
    public Fruit getApple() {
        return new Apple();
    }


    @Override
    public Fruit getPeer() {
        return new Peer();
    }
}
