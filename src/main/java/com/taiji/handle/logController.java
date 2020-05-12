package com.taiji.handle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuyj
 * @Title: logController
 * @create 2019-10-21 9:30
 * @ProjectName log-look
 * @Description: TODO
 */
@RestController
public class logController {

    @GetMapping("/loglook")
    private ModelAndView log(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("log");
        return mav;
    }
}
