package com.earthlyfish.spring.bean;


import com.earthlyfish.spring.stereotype.Qualifier;
import com.earthlyfish.spring.stereotype.Service;

@Service("stuService")
public class StuService {

    @Qualifier
    private StuDao stuDao;

    public void printTest() {
        System.err.println("service test!.......................");
    }

}
