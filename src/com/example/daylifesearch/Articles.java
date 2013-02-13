package com.example.daylifesearch;

public class Articles {
	public String headLine;
	public String URL;
	
	
	public void setHeadline(String headline){
		this.headLine=headline;
		System.out.println("Inside Articles "+headLine);
	}
	
	public String getHeadline(){
		return headLine;
	}
	
	
	public void setURL(String Url){
		this.URL=Url;
		System.out.println("Inside Articles "+URL);
	}
	
	public String getURL(){
		return URL;
	}
}
