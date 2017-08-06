package com.earthlyfish.spring.bean;


import com.earthlyfish.spring.stereotype.*;

@Controller
@RequestMapping("/studs")
public class StuController {

    @Qualifier
    private StuService stuService;
    
    @Autowired
    private StuDao stuDao;

    @RequestMapping(value="/id",method= RequestMethod.GET)
    public String findStuds(String id) {
        return "";
    }

    public void printTest() {
        stuService.printTest();
    }
    
    @Override
    public String toString() {
        return "StuController [stuService=" + stuService + ", stuDao=" + stuDao + "]";
    }
}
