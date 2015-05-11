/**
 * @author 许彬
 */
package com.lt.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.lt.model.DisplayCursorModel;

/**
 * @author 许彬
 *
 */
public class GridUtil {
	
	public static List<HashMap> formatTree(List<HashMap> list) {
		HashMap<Object, Object> root = new HashMap<Object, Object>();
		HashMap node = new HashMap();
		List<HashMap> treelist = new ArrayList<HashMap>();// 拼凑好的json格式的数据
		List<HashMap> parentnodes = new ArrayList<HashMap>();// parentnodes存放所有的父节点

		if (list != null && list.size() > 0) {
			root = list.get(0);
			// 循环遍历oracle树查询的所有节点
			for (int i = 1; i < list.size(); i++) {
				node = list.get(i);
				if (/*node.getPid().equals(root.getId())*/ MapUtils.getString(node, "PID").equals(MapUtils.getString(root, "ID"))) {
					// 为tree root 增加子节点
					parentnodes.add(node);
					/*root.getChildren().add(node);*/
					if(CollectionUtils.isEmpty((List)MapUtils.getObject(root, "children"))){
						root.put("children", new ArrayList<HashMap>());
					}
					((List<HashMap>)MapUtils.getObject(root, "children")).add(node);
				} else {// 获取root子节点的孩子节点
					getChildrenNodes(parentnodes, node);
					parentnodes.add(node);
				}
			}
		}
		treelist.add(root);
		return treelist;

	}
	
	private static void getChildrenNodes(List<HashMap> parentnodes,
			HashMap node) {
		// 循环遍历所有父节点和node进行匹配，确定父子关系
		for (int i = parentnodes.size() - 1; i >= 0; i--) {

			HashMap pnode = parentnodes.get(i);
			// 如果是父子关系，为父节点增加子节点，退出for循环
			if (/*pnode.getId().equals(node.getPid())*/MapUtils.getString(pnode, "ID").equals(MapUtils.getString(node, "PID"))) {
				/*pnode.setState("closed");*/// 关闭二级树
				/*pnode.put("state", "closed");*/
				/*pnode.getChildren().add(node);*/
				if(CollectionUtils.isEmpty((List)MapUtils.getObject(pnode, "children"))){
					pnode.put("children", new ArrayList<HashMap>());
				}
				((List<HashMap>)MapUtils.getObject(pnode, "children")).add(node);
				return;
			} else {
				// 如果不是父子关系，删除父节点栈里当前的节点，
				// 继续此次循环，直到确定父子关系或不存在退出for循环
				parentnodes.remove(i);
			}
		}
	}
	
	/**
	 * 将DISPLAY CURSOR结果集处理为displayCursorModel对象
	 * @param data
	 * @return
	 */
	public static DisplayCursorModel parseList2DisplayCursorModel(List data){
		DisplayCursorModel displayCursorModel = new DisplayCursorModel();
		Pattern p = Pattern.compile("\\|[^\\|]*");
		Matcher a;
		
		for (int i = 0 ; i < data.size(); i++) {
			String tag = MapUtils.getString((Map) data.get(i), "PLAN_TABLE_OUTPUT");
			if(tag.startsWith("Plan hash value")){
				displayCursorModel.setHeaderindex(i);
				displayCursorModel.setExplainplanindex(i + 3);
			}
			
			if(tag.startsWith("Predicate Information")){
				displayCursorModel.setPredicateInformationindex(i);
				displayCursorModel.setExplainplanindexafter(i - 3);
			}
		}
		
		for (int i = displayCursorModel.getExplainplanindex(); i <= displayCursorModel.getExplainplanindexafter(); i++) {
			String pto = MapUtils.getString((Map) data.get(i), "PLAN_TABLE_OUTPUT");
			if(pto.startsWith("-----")){
				continue;
			}
			a = p.matcher(pto.replaceAll("\\|$", ""));
			if(i == displayCursorModel.getExplainplanindex()){
				 while(a.find()){
					 displayCursorModel.getExplainplanHeader().add(a.group(0).toUpperCase().replaceAll("\\|", "").trim());
				 }
				 continue;
			}
			
			int j = 0;
			HashMap epMap = new HashMap();
			while(a.find()){
				String key = displayCursorModel.getExplainplanHeader().get(j);
				String value = a.group(0).replaceAll("\\|", "");
				if("OPERATION".equals(key)){
					epMap.put(key, value.replaceAll("\\s", "&nbsp;"));
				}else{
					epMap.put(key, value.trim());
				}
				
				j++;
			}
			
			a.reset();
			displayCursorModel.getExplainplan().add(epMap);
		}
		
		return displayCursorModel;
	}

}
