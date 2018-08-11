package com.example.demo;
import com.example.demo.board.model.ItemVO;
import com.example.demo.board.model.UserVO;
import dao.DeptDao;
import model.Dept;
import org.apache.ibatis.type.MappedTypes;
import org.apache.tomcat.jni.FileInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@MapperScan("com.example.demo.board.mapper")
@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);



    }
    @RequestMapping(value="selectall",method = RequestMethod.GET)
    public String selectAll(){

        DeptDao deptdao = new DeptDao();

        for(Dept d : deptdao.selectAll())
            System.out.println(d.getItem_key());

        return "hi";
    }



}
