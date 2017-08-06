package com.earthlyfish.designpattern.factory.product;

/**
 * Created by wangyoupeng on 2017/6/28.
 */
public class WaterMelon implements LocalFruit {
    public WaterMelon() {
        System.out.println("I am a WaterMelon!");
    }

    @Override public void taste() {
        System.out.println("I am a WaterMelon!so good!");
    }
}
