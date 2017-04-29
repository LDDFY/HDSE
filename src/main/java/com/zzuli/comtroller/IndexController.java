package com.zzuli.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LDDFY
 */
@Controller
public class IndexController {

    @RequestMapping("goIndex.html")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("/test/index");
        System.out.println("----------->");
        return mv;
    }
}
