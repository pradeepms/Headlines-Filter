package com.example.daylifesearch;

import java.util.HashMap;
import java.util.Map;

public class SignatureClass  {
	
	public String signatureCall(String query) {
		
		
		String accesskey = "55b84e19f67f128dc4a0cd74cc413471";
		String sharedsecret = "6f06fa944d3f9b184f508045863b86d6";
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
