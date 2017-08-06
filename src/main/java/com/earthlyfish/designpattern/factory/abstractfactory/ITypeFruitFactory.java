package com.earthlyfish.designpattern.factory.abstractfactory;

import com.earthlyfish.designpattern.factory.product.ForeignFruit;
import com.earthlyfish.designpattern.factory.product.LocalFruit;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public interface ITypeFruitFactory {

    LocalFruit getLocalFruit();

    ForeignFruit getForeignFruit();
}
