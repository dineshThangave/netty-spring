package com.example.httpserver.protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.example.httpserver.protobuf.EmployeeProto.Department;
import com.example.httpserver.protobuf.EmployeeProto.Employee;
import com.example.httpserver.protobuf.EmployeeProto.Employee.Builder;


public class ProtoBufferHandler {
 
	public void insertValues(String firstname,String lastName,int id,double salary){
		  Builder empbuild = EmployeeProto.Employee.newBuilder();
		  empbuild.setFirstname(firstname);
		  empbuild.setLastname(lastName);
		  empbuild.setId(id);
		  empbuild.setSalary(salary);
		  Department dept1 = EmployeeProto.Department.newBuilder().setDeptid(1)
                  .setDeptname("Finance").build();
          Department dept2 = EmployeeProto.Department.newBuilder().setDeptid(2)
                  .setDeptname("Admin").build();
          empbuild.addDept(dept1);
          empbuild.addDept(dept2);
          Employee emp = empbuild.build();
          
          try {
              // write
              FileOutputStream output = new FileOutputStream("Employee.ser");
              emp.writeTo(output);
              output.close();

              // read
              Employee empFromFile = Employee.parseFrom(new FileInputStream("Employee.ser"));
              
        
              //sending to kafka producer queue - serializer 
              
              Properties props=new Properties();
              props.put("bootstrap.servers","localhost:8082");
              props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
              props.put("value.serializer", "com.example.httpserver.kafka.EmployeeSerializer");
              try (Producer<String, Employee> producer = new KafkaProducer<>(props)) {
            	   producer.send(new ProducerRecord<String, Employee>("Employee_obj", empFromFile));
            	   System.out.println("Message " + empFromFile.toString() + " sent !!");
            	} catch (Exception e) {
            	   e.printStackTrace();
            	}
              System.out.println(empFromFile);
              
              //de-serializer 
              props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
              props.put("value.deserializer", "com.example.httpserver.kafka.EmployeeDeserializer");
              
              try (KafkaConsumer<String, Employee> consumer = new KafkaConsumer<>(props)) {
            	    consumer.subscribe(Collections.singletonList("Employee_obj"));
            	    while (true) {
            	        ConsumerRecords<String, Employee> messages = consumer.poll(100);
            	        for (ConsumerRecord<String, Employee> message : messages) {
            	          System.out.println("Message received " + message.value().toString());
            	        }
            	    }
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
              
              
           } catch (IOException e) {
                   e.printStackTrace();
           }
		
	}

}
