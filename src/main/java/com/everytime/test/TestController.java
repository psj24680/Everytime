package com.everytime.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {

	@ResponseBody
	@RequestMapping("/1")
	public String test1() {
		return "Hello World!";
	}
	
	@ResponseBody
	@RequestMapping("/2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("1", "aaa");
		map.put("2", "bbb");
		map.put("3", "ccc");
		
		return map;
	}
	
	@RequestMapping("/3")
	public String test3() {
		return "test/test";
	}
}
