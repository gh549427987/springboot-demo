package com.jhua.controller;

import com.jhua.dao.StudentMapper;
import com.jhua.pojo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentMapper studentMapper;


    @GetMapping("/jdbcController")
    public List<Map<String, Object>> userList() {
        String sql = "select * from mybatis.student";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);

        return list_maps;
    }

    @GetMapping("/student")
    public List<Student> student() {

        List<Student> students = studentMapper.queryStudentList();
        return students;
    }

    @GetMapping("/r")
    public List<Student> r() {

        List<Student> students = studentMapper.queryStudentList();
        System.out.println("Level - R !!");
        return students;
    }

    @GetMapping("/sr")
    public List<Student> sr() {

        List<Student> students = studentMapper.queryStudentList();
        System.out.println("Level - SR !!");
        return students;
    }

    @GetMapping("/ssr")
    public List<Student> ssr() {

        List<Student> students = studentMapper.queryStudentList();
        System.out.println("Level - SSR !!");
        return students;
    }



}
