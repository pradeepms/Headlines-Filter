package com.example.daylifesearch;

import java.util.HashMap;
import java.util.Map;

public class SignatureClass  {
	
	public String signatureCall(String query) {
		
		
		String accesskey = "";
		String sharedsecret = "";
		String server = "freeapi.daylife.com";
		String version = "4.10";
		
		//initialize the daypi client
		SignatureClient client = new SignatureClient(accesskey, sharedsecret, server, version);
		
		//create the input params
		Map<String, String> input = new HashMap<String, String>();
		input.put("query", query);
		input.put("limit", "20");
		
		//make the API call
		String doc = client.call("search_getRelatedArticles", input);
		
		//parse and use the xml dom returned
		
		System.out.println("Within signatureCall"+doc);
		return doc.toString();
	}

}
