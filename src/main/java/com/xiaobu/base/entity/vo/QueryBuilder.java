package com.xiaobu.base.entity.vo;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xiaobu
 * @date 2018/11/19 13:48
 * @descprition   分页查询对象
 * @version 1.0
 */
public class QueryBuilder {

	/**
	 * 如果需要排序填写排序字段，比如按id排序,那么 order = "id"
	 */
	private String order=null;

	/**
	 * 排序方式 true是正序，false是倒序
	 */
	private boolean isAsc=false;

	/**
	 * 跳转
	 */
	private int page=1;

	/**
	 * 每页显示记录数
	 */
	private int pageSize = 10;
	/**
	 * 是否模糊查询，默认是
	 */
	private boolean isLike = true;

	/**
	 * map条件 
	 * map.key就是查询条件的字段名
	 * map.value就是值
	 */
	private HashMap<String, String> map = new HashMap<String, String>();
	
	/**
	 * list条件 
	 * list就是查询条件的语句
	 * list就是查询条件（"id like %000%"或"id = '000123'"等等）
	 * 注意，使用此种方式前，一定要首先验证参数是否合法，以免用户恶意使用sql注入攻击
	 */
	
	private List<String> list = new ArrayList<String>();

	/**
	 * 默认空构造函数
	 */
	public QueryBuilder() {

	}

	/**
	 * 具有参数的构造函数
	 * @param order
	 * @param isAsc
	 * @param page
	 * @param pageSize
	 */
	public QueryBuilder(String order,boolean isAsc,int page,int pageSize) {
		this.order = order;
		this.isAsc = isAsc;
		this.page = page;
		this.pageSize = pageSize;
	}
	
	/**
	 * 具有参数的构造函数
	 * @param page
	 * @param pageSize
	 */
	public QueryBuilder(int page,int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}


	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean getIsAsc() {
		return isAsc;
	}

	public void setIsAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	/**
	 * 为防止sql注入攻击，该map已自动把所有的键值的单引号变成了成对的单引号
	 * @param map
	 */
	public void setMap(HashMap<String, String> map) {
		HashMap<String, String> map1 = new HashMap<String, String>();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key =  StringEscapeUtils.escapeSql(entry.getKey());
			String value =  StringEscapeUtils.escapeSql(entry.getValue());
			map1.put(key,value);

		}
		this.map = map1;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

}

