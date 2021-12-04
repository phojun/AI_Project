package com.example.demo.vo;

public class ColorTestVO {
	
	int red,green,blue;
	String personalType;
	
	public ColorTestVO(int red, int green, int blue, String personalType) {
		super();
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setPersonalType(personalType);
	}
	
	@Override
	public String toString() {
		return "ColorTestVO [red=" + red + ", green=" + green + ", blue=" + blue + ", personalType=" + personalType
				+ "]";
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
