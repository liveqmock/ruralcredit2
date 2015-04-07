package com.creditease.rc.domain;

import java.io.Serializable;
/**
 * 
 * @author zhangman
 *
 */
public class CodeTable implements Serializable {

	private Integer codaTableId;
	private String section;
	private String codeKey;
	private String codeVlue;
	private String codeNote;
	private String sequence;
	private String available;
	private Integer parentId;
	/**
	 * 
	 * @param section 
	 * @param codeKey 
	 * @param codeVlue 
	 * @param codeNote 
	 * @param sequence 
	 */
	public CodeTable(String section, String codeKey,
			String codeVlue, String codeNote, String sequence) {
		this.section = section;
		this.codeKey = codeKey;
		this.codeVlue = codeVlue;
		this.codeNote = codeNote;
		this.sequence = sequence;
	}
	/**
	 * 
	 */
	public CodeTable() {
		super();
	}

	public Integer getCodaTableId() {
		return codaTableId;
	}
	public void setCodaTableId(Integer codaTableId) {
		this.codaTableId = codaTableId;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public String getCodeVlue() {
		return codeVlue;
	}
	public void setCodeVlue(String codeVlue) {
		this.codeVlue = codeVlue;
	}
	public String getCodeNote() {
		return codeNote;
	}
	public void setCodeNote(String codeNote) {
		this.codeNote = codeNote;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
