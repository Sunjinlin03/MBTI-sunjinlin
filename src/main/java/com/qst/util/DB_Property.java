package com.qst.util;

public class DB_Property {

	private String aon;// and or nor
	private String col;// 列
	private String eq;// = ！
	private Object value;

	public String getAon() {
		return aon;
	}

	public void setAon(String aon) {
		this.aon = aon;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getEq() {
		return eq;
	}

	public void setEq(String eq) {
		this.eq = eq;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public DB_Property(String aon, String col, String eq, Object value) {
		super();
		this.aon = aon;
		this.col = col;
		this.eq = eq;
		this.value = value;
	}

	public DB_Property() {
		// TODO Auto-generated constructor stub
	}

}
