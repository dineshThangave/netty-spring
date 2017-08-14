package com.example.httpserver.protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


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
              System.out.println(empFromFile);
           } catch (IOException e) {
                   e.printStackTrace();
           }
		
	}

}
