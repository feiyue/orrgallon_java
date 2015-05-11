/**
 * @author 许彬
 */
package com.lt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 许彬
 *
 */
public class DisplayCursorModel implements Serializable {
	
	private List header = new ArrayList();
	private List explainplan = new ArrayList();
	private List predicateInformation = new ArrayList();
	
	private List<String> explainplanHeader = new ArrayList<String>();
	private int headerindex = 0;
	private int explainplanindex = 0;
	private int explainplanindexafter = 0;
	
	public List<String> getExplainplanHeader() {
		return explainplanHeader;
	}
	public void setExplainplanHeader(List<String> explainplanHeader) {
		this.explainplanHeader = explainplanHeader;
	}
	
	public int getExplainplanindexafter() {
		return explainplanindexafter;
	}
	public void setExplainplanindexafter(int explainplanindexafter) {
		this.explainplanindexafter = explainplanindexafter;
	}
	private int predicateInformationindex = 0;
	
	public int getHeaderindex() {
		return headerindex;
	}
	public void setHeaderindex(int headerindex) {
		this.headerindex = headerindex;
	}
	public int getExplainplanindex() {
		return explainplanindex;
	}
	public void setExplainplanindex(int explainplanindex) {
		this.explainplanindex = explainplanindex;
	}
	public int getPredicateInformationindex() {
		return predicateInformationindex;
	}
	public void setPredicateInformationindex(int predicateInformationindex) {
		this.predicateInformationindex = predicateInformationindex;
	}
	public List getHeader() {
		return header;
	}
	public void setHeader(List header) {
		this.header = header;
	}
	public List getExplainplan() {
		return explainplan;
	}
	public void setExplainplan(List explainplan) {
		this.explainplan = explainplan;
	}
	public List getPredicateInformation() {
		return predicateInformation;
	}
	public void setPredicateInformation(List predicateInformation) {
		this.predicateInformation = predicateInformation;
	}

}
