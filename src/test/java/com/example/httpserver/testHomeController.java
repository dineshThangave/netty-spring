package com.example.httpserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.example.httpserver.controller.EmployeeController;
import com.example.httpserver.controller.request.Employee;
import com.example.httpserver.pojo.response.Helloworld;
import com.example.httpserver.server.NettyServer;
import com.example.httpserver.testHomeController.TestConfig;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class testHomeController {

	@Configuration
	public static class TestConfig {

		@Bean
		public NettyServer nettyServer() {
			return new NettyServer();
		}

		@Bean
		public EmployeeController homeController() {
			return new EmployeeController();
		}

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Autowired
	NettyServer		server;
	@Autowired
	RestOperations	restOps;

	@Before
	public void init() {
		server.start();
	}

	@Test
	public void testHelloworld() {
		Helloworld resp = restOps.getForObject(buildUrl("hello/world"), Helloworld.class);
		assertNotNull(resp);
		assertTrue(StringUtils.hasText(resp.getMessage()));
	}

//	@Test
//	public void testPOST() {
//		int id = 44;
//		String name = "NAME";
//		Article resp = restOps.postForObject(
//				buildUrl("hello"),
//				new Article(id, name),
//				Article.class);
//		assertNotNull(resp);
//		assertTrue(resp.getId().equals(id));
//		assertTrue(resp.getName().equals(name));
//	}
//
//	@Test
//	public void testPOSTbyList() {
//		int id = 44;
//		String name = "NAME";
//		
//		@SuppressWarnings("unchecked")
//		List<Object> resp = restOps.postForObject(
//				buildUrl("hello?multi=true"),
//				Collections.singletonList(new Article(id, name)),
//				List.class);
//
//		assertEquals(1, resp.size());
//	}

	public String buildUrl(String target) {
		return String.format("http://localhost:%d/%s/%s",
				server.getPort(), server.getRootResourcePath(), target);
	}

}
