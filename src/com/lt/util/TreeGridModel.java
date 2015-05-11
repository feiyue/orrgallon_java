/**
 * @author 许彬
 */
package com.lt.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 许彬
 *
 */
public class TreeGridModel implements Serializable {
	
	private String id;
	private String pid;
	private List<TreeGridModel> children = new ArrayList<TreeGridModel>();
	private String state;
	private HashMap attribute = new HashMap();
	
	public HashMap getAttribute() {
		return attribute;
	}
	public void setAttribute(HashMap attribute) {
		this.attribute = attribute;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<TreeGridModel> getChildren() {
		return children;
	}
	public void setChildren(List<TreeGridModel> children) {
		this.children = children;
	}

}
