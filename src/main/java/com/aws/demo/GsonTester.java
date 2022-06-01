package com.aws.demo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

    public class GsonTester {
        @RequestMapping("/test")
        public void main(String args[]) {
            Gson gson = new Gson();
            Student student = new Student();
            student.setAge(26);
            student.setName("Maxsu");

            String jsonString = gson.toJson(student);
            System.out.println(jsonString);
        }
    class Student {
        private String name;
        private int age;
        public Student(){}

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String toString() {
            return "[name:"+name+",age:"+ age+ "]";
        }
    }
}
