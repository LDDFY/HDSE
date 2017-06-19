package com.zzuli.controller;

import com.zzuli.Constants;
import com.zzuli.search.Pager;
import com.zzuli.search.SearchEntity;
import com.zzuli.service.SearchService;
import com.zzuli.vo.WeiBo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LDDFY
 * 查询controller
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("index.do")
    public ModelAndView searchResult() {
        ModelAndView mv = new ModelAndView("search/index");
        List<Map<String, String>> searchCode = getSearchCode();
        mv.addObject("code", searchCode);
        return mv;
    }

    @RequestMapping("search.do")
    public ModelAndView searchPage(HttpServletRequest request, SearchEntity entity, Pager pager) {
        ModelAndView mv = new ModelAndView("search/searchPage");
        List<Map<String, String>> searchCode = getSearchCode();
        String content=entity.getKey();
        if(StringUtils.isBlank(content)){
            mv.setViewName("redirect:index.do");
        }else{
            pager =searchService.getPageInfo(entity,pager);
            mv.addObject("pager", pager);
            mv.addObject("code", searchCode);
            mv.addObject("searchCode", entity);
        }
        return mv;
    }

    @RequestMapping("detial.do")
    public ModelAndView detial(String id){
        ModelAndView mv = new ModelAndView("search/detial");
        WeiBo weiBo=searchService.getEntityById("id",id,"data");
        mv.addObject("data",weiBo);
        return mv;
    }

    /**
     * 查询搜索条件信息
     * @return
     */
    private List<Map<String, String>> getSearchCode() {
        List<Map<String, String>> searchCode = new ArrayList<Map<String, String>>();
        for (String key : Constants.searchCodeMap.keySet()) {
            Map<String, String> temp = new HashMap<String, String>();
            temp.put("key", key);
            temp.put("value", Constants.getSearchCodeMap(key));
            searchCode.add(temp);
        }
        return searchCode;
    }
}
