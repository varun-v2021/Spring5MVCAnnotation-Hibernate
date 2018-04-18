# Spring5MVCAnnotation-Hibernate

Install and Run on Tomcat 9 Server

Increase server timeout to 60 seconds

Select "Publish module contexts to separate XML files" in Server options

# SQL Scripts

CREATE DATABASE mysql;

CREATE TABLE employee (
      id int(11), 
      first_name varchar(20), 
      last_name varchar(20), 
      salary int(11));
      
INSERT INTO employee
     (id, first_name, last_name, salary) 
  VALUES 
     (int(11), varchar(20), varchar(20), int(11));
     
CREATE TABLE employee (
      id int(11), 
      first_name varchar(20), 
      last_name varchar(20), 
      salary int(11));
      
create table ADDRESS (
   ADDRESS_ID BIGINT NOT NULL AUTO_INCREMENT,
   ADDRESS_STREET VARCHAR(30) NOT NULL,
   ADDRESS_CITY  VARCHAR(30) NOT NULL,
   ADDRESS_STATE  VARCHAR(30) NOT NULL,
   ADDRESS_ZIPCODE  VARCHAR(30) NOT NULL,
   PRIMARY KEY (ADDRESS_ID)
);
 
create table STUDENT (
   STUDENT_ID BIGINT NOT NULL AUTO_INCREMENT,
   STUDENT_NAME VARCHAR(30) NOT NULL,
   STUDENT_ADDRESS  BIGINT NOT NULL,
   PRIMARY KEY (student_id),
   CONSTRAINT student_address FOREIGN KEY (STUDENT_ADDRESS) REFERENCES ADDRESS (ADDRESS_ID)
);
     
# URLs
# POST:   http://localhost:8080/Spring5MVCAnnotation/employee/add

#Sample data
{
"firstName" : "ABCD",
"lastName" : "EFGH",
"salary" : 1000
}

# GET:   http://localhost:8080/Spring5MVCAnnotation/employee/get/1
		
         http://localhost:8080/Spring5MVCAnnotation/employee/get/exception

# Reference:

http://www.javainterviewpoint.com/hibernate-one-to-one-bidirectional-mapping-foreign-key/

https://howtodoinjava.com/spring5/webmvc/spring-dispatcherservlet-tutorial/

https://stackoverflow.com/questions/16909742/spring-4-x-3-x-web-mvc-rest-api-and-json2-post-requests-how-to-get-it-right-o

https://dzone.com/tutorials/java/hibernate/hibernate-example/hibernate-mapping-one-to-one-using-annotations-1.html

http://websystique.com/hibernate/hibernate-one-to-one-unidirectional-with-foreign-key-associations-annotation-example/

https://www.concretepage.com/spring/spring-mvc/spring-handlerinterceptor-annotation-example-webmvcconfigureradapter
