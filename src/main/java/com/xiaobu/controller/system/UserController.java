package com.xiaobu.controller.system;

import com.alibaba.fastjson.JSON;
import com.xiaobu.base.controller.BaseController;
import com.xiaobu.base.util.JsonResult;
import com.xiaobu.entity.Role;
import com.xiaobu.entity.User;
import com.xiaobu.service.RoleService;
import com.xiaobu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;


	/**
	 * @author xiaobu
	 * @date 2018/12/3 17:16
	 * @return java.lang.String
	 * @descprition
     * value = { "/", "/index" } 表示两种都行
	 * @version 1.0
	 */
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/user/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public String list() {
		String searchText = request.getParameter("searchText");
        List<User> users =null;
		if(StringUtils.isNotBlank(searchText)){
            users = userService.findListLikeByParam("nickName",searchText);
		}
        if(users==null){
            Page<User> page= userService.findByPageable(getPageable());
            System.out.println("page.getContent().get(0).getBirthday() = " + page.getContent().get(0).getBirthday());
            return JSON.toJSONString(page);
        }
       return JSON.toJSONString(userService.findByPageable(users,getPageable(),users.size()));
	}


    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public  Page<User> getList(){
        String searchText = request.getParameter("searchText");
        List<User> users =null;
        if(StringUtils.isNotBlank(searchText)){
            users = userService.findListLikeByParam("nickName",searchText);
        }
        if(users==null){
            Page<User> page= userService.findByPageable(getPageable());
            System.out.println("page.getContent() = " + page.getContent());
            return page;
        }
        return userService.findByPageable(users,getPageable(),users.size());
    }

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/user/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, ModelMap map) {
		User user = userService.findById(id);
		map.put("user", user);
		return "admin/user/form";
	}

	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(User user, ModelMap map){
		try {
			userService.saveOrUpdate(user);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id, ModelMap map) {
		try {
			userService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable Integer id, ModelMap map) {
		User user = userService.findById(id);
		map.put("user", user);

		Set<Role> set = user.getRoles();
		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : set) {
			roleIds.add(role.getId());
		}
		map.put("roleIds", roleIds);

		List<Role> roles = roleService.findAll();
		map.put("roles", roles);
		return "admin/user/grant";
	}

	@ResponseBody
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
	public JsonResult grant(@PathVariable Integer id, String[] roleIds, ModelMap map) {
		try {
			userService.grant(id,roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}
