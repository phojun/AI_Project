package com.example.demo.vo;

public class ColorTestVO {
	
	int red,green,blue,no;
	String personalType;
	
	public ColorTestVO(int red, int green, int blue, String personalType, int no) {
		super();
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setPersonalType(personalType);
		setNo(no);
	}
	
	@Override
	public String toString() {
		return "ColorTestVO [red=" + red + ", green=" + green + ", blue=" + blue + ", personalType=" + personalType
				+ "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}

	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	public String getPersonalType() {
		return personalType;
	}
	public void setPersonalType(String personalType) {
		this.personalType = personalType;
	}
	
	

}
