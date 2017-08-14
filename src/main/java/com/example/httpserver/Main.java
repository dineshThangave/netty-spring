package com.example.httpserver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.example.httpserver.controller.EmployeeController;
import com.example.httpserver.server.NettyServer;

public class Main {

	public static void main(String[] args)
			throws Exception {

		ApplicationContext ac = new ClassPathXmlApplicationContext("root-context.xml");
		Assert.notNull(ac);
		Assert.notNull(ac.getBean(EmployeeController.class));

		NettyServer netty = ac.getBean(NettyServer.class);

		netty.start();

	}
}
