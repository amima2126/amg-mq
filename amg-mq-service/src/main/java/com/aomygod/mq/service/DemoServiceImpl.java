package com.aomygod.mq.service;

import org.springframework.stereotype.Service;

import com.aomygod.mq.service.DemoService;

@Service("demoServiceImpl")
public class DemoServiceImpl implements DemoService {

	public void test() {
		System.out.println("test");
	}


}
