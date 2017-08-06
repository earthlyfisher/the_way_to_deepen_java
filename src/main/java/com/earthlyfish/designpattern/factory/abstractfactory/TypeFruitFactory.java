package com.earthlyfish.designpattern.factory.abstractfactory;

import com.earthlyfish.designpattern.factory.product.Durian;
import com.earthlyfish.designpattern.factory.product.ForeignFruit;
import com.earthlyfish.designpattern.factory.product.LocalFruit;
import com.earthlyfish.designpattern.factory.product.WaterMelon;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public class TypeFruitFactory implements ITypeFruitFactory {

    @Override public LocalFruit getLocalFruit() {
        return new WaterMelon();
    }

    @Override public ForeignFruit getForeignFruit() {
        return new Durian();
    }
}
