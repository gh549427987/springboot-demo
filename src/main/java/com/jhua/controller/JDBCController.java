package com.jhua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/jdbcController")
    public List<Map<String, Object>> userList() {
        String sql = "select * from mybatis.student";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);

        return list_maps;
    }

}
