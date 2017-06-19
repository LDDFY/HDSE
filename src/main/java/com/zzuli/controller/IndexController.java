package com.zzuli.controller;

import com.zzuli.service.IndexService;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LDDFY
 *         创建索引controller
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("createIndex.do")
    public @ResponseBody String createIndex() {
        boolean flag = false;
        try {
            System.out.println("------------>开始创建索引！");
            flag = indexService.createIndex();
            System.out.println(flag);
            System.out.println("------------>创建索引结束！");
            if (flag) {
                return JSON.toString( "索引创建成功！");
            } else {
                return JSON.toString( "索引创建失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toString( "索引创建失败！");
        }
    }

}
