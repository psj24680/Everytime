package com.everytime.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class APIRestController {

	public List<String> callAPI() {
		List<String> schoolNameList = new ArrayList<>();

		String key = "4b18c9c07295ede3beb8730178d80acc";
		String result = "";

		try {

			String apiURL = "https://www.career.go.kr/cnet/openapi/getOpenApi?"
					+ "apiKey=" + key
					+ "&svcType=api"
					+ "&svcCode=SCHOOL"
					+ "&contentType=json"
					+ "&gubun=univ_list"
					+ "&perPage=500";
			URL url = new URL(apiURL);

			BufferedReader bf;

			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			result = bf.readLine();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject dataSearch = (JSONObject) jsonObject.get("dataSearch");
			JSONObject content = (JSONObject) dataSearch.get("content");

			System.out.println("캠퍼스명: " + content.get("campusName"));
			System.out.println("학교명: " + content.get("schoolName"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return schoolNameList;
	}
}
