package com.xiaobu.base.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/30 15:31
 * @description V1.0
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;
    protected String redirect(String path) {
        return "redirect:" + path;
    }


    /**
     * @author xiaobu
     * @date 2018/12/3 14:57
     * @return org.springframework.data.domain.Pageable
     * @descprition   获取分页对象 前端用的bootStrap table
     * sortName 排序字段名  sortOrder 排序方式 pageNumber 页码（要减1） jpa的页码从0开始的
     * pageSize 一页数据大小   searchText 自带的查询的文本的name 
     * @version 1.0
     */
    protected Pageable getPageable(){
        int page=0;
        int size=10;
        Sort sort=Sort.by(Sort.Order.asc("id"));
        String sortName = request.getParameter("sortName");
        String sortOrder = request.getParameter("sortOrder");
        if(StringUtils.isNotBlank(sortName)&&StringUtils.isNotBlank(sortOrder)){
            if("desc".equalsIgnoreCase(sortOrder)){
                sort = new Sort(Sort.Direction.DESC, sortName);
            }else{
                sort = new Sort(Sort.Direction.ASC, sortName);
            }
        }
        if(!StringUtils.isEmpty(request.getParameter("pageNumber"))){
            page = Integer.parseInt(request.getParameter("pageNumber")) - 1;
            size = Integer.parseInt(request.getParameter("pageSize"));
        }
        // TODO: 2018/12/3  Sort 不能为null
        return PageRequest.of(page, size, sort);
    }




   /* @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
    }*/

}
