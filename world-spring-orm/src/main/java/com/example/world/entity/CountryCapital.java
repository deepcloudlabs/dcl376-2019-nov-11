package com.example.world.entity;

public class CountryCapital {
	private String code;
	private String name;
	private Long cityId;
	private String capital;

	public CountryCapital() {
	}
	
	public CountryCapital(String code, String name, Long cityId, String capital) {
		this.code = code;
		this.name = name;
		this.cityId = cityId;
		this.capital = capital;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	@Override
	public String toString() {
		return "CountryCapital [code=" + code + ", name=" + name + ", cityId=" + cityId + ", capital=" + capital + "]";
	}

}
