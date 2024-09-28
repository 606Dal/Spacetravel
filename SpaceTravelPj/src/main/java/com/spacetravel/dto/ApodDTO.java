package com.spacetravel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Nasa - api - Apod
 */
public class ApodDTO {

	@JsonProperty("copyright")
	private String apodCopyright;
	@JsonProperty("date")
	private String apodDate;
	@JsonProperty("explanation")
	private String apodExplanation; // 사진에 대한 설명
	@JsonProperty("hdurl")
	private String apodHdurl;
	@JsonProperty("media_type")
	private String apodMedia_type;
	@JsonProperty("title")
	private String apodTitle;
	@JsonProperty("url")
	private String apodUrl;
	
	public ApodDTO() {}

	public ApodDTO(String apodCopyright, String apodDate, String apodExplanation, String apodHdurl,
			String apodMedia_type, String apodTitle, String apodUrl) {
		super();
		this.apodCopyright = apodCopyright;
		this.apodDate = apodDate;
		this.apodExplanation = apodExplanation;
		this.apodHdurl = apodHdurl;
		this.apodMedia_type = apodMedia_type;
		this.apodTitle = apodTitle;
		this.apodUrl = apodUrl;
	}

	public String getApodCopyright() {
		return apodCopyright;
	}

	public void setApodCopyright(String apodCopyright) {
		this.apodCopyright = apodCopyright;
	}

	public String getApodDate() {
		return apodDate;
	}

	public void setApodDate(String apodDate) {
		this.apodDate = apodDate;
	}

	public String getApodExplanation() {
		return apodExplanation;
	}

	public void setApodExplanation(String apodExplanation) {
		this.apodExplanation = apodExplanation;
	}

	public String getApodHdurl() {
		return apodHdurl;
	}

	public void setApodHdurl(String apodHdurl) {
		this.apodHdurl = apodHdurl;
	}

	public String getApodMedia_type() {
		return apodMedia_type;
	}

	public void setApodMedia_type(String apodMedia_type) {
		this.apodMedia_type = apodMedia_type;
	}

	public String getApodTitle() {
		return apodTitle;
	}

	public void setApodTitle(String apodTitle) {
		this.apodTitle = apodTitle;
	}

	public String getApodUrl() {
		return apodUrl;
	}

	public void setApodUrl(String apodUrl) {
		this.apodUrl = apodUrl;
	}

	@Override
	public String toString() {
		return "ApodDTO [apodCopyright=" + apodCopyright + ", apodDate=" + apodDate
				+ ", apodHdurl=" + apodHdurl + ", apodMedia_type=" + apodMedia_type + ", apodTitle="
				+ apodTitle + ", apodUrl=" + apodUrl + "]";
	}
	
}
