package com.everytime.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@RequestMapping("/create")
	public Map<String, Object> create() {
		Map<String, Object> result = new HashMap<>();
		
		
		
		return result;
	}
}
