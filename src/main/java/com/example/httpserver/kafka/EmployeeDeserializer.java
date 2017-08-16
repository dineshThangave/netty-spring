package com.example.httpserver.kafka;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.example.httpserver.controller.request.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
public class EmployeeDeserializer implements Deserializer {
	  @Override 
	  public void close() {
	  }
	 
	  @Override
	  public Employee deserialize(String arg0, byte[] arg1) {
	    ObjectMapper mapper = new ObjectMapper();
	    Employee user = null;
	    try {
	      user = mapper.readValue(arg1, Employee.class);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return user;
	  }
	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}
	}