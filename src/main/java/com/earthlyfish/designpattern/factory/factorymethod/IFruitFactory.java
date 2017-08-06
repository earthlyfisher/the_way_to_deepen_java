package com.earthlyfish.designpattern.factory.factorymethod;

import com.earthlyfish.designpattern.factory.product.Fruit;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public interface IFruitFactory {

    Fruit getApple();

    Fruit getPeer();
}
