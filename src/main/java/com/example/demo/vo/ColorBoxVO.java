package com.example.demo.vo;

public class ColorBoxVO {
	private String id,pColor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPColor() {
		return pColor;
	}

	public void setPColor(String pColor) {
		this.pColor = pColor;
	}

	@Override
	public String toString() {
		return "ColorBoxVO [id=" + id + ", pColor=" + pColor + "]";
	}
	

}
