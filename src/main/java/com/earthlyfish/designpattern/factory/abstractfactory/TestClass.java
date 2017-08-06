package com.earthlyfish.designpattern.factory.abstractfactory;

import com.earthlyfish.designpattern.factory.product.ForeignFruit;
import com.earthlyfish.designpattern.factory.product.LocalFruit;

/**
 * Created by wangyoupeng on 2017/6/28.
 * 抽象工厂是对于一族产品族的抽象.
 */
public class TestClass {
    public static void main(String[] args) {
        ITypeFruitFactory factory = new TypeFruitFactory();
        LocalFruit localFruit = factory.getLocalFruit();
        localFruit.taste();
        ForeignFruit foreignFruit = factory.getForeignFruit();
        foreignFruit.taste();
    }
}
