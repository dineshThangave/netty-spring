package com.example.httpserver.kafka;


import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.example.httpserver.controller.request.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeSerializer implements Serializer<Employee> {
	  @Override public void configure(Map<String, ?> map, boolean b) {
	  }
	  @Override public byte[] serialize(String arg0, Employee arg1) {
	    byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(arg1).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	  }
	  @Override public void close() {
	  }
	}
