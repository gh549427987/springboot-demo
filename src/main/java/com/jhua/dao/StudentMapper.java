package com.jhua.dao;

import com.jhua.pojo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //表示这是一个mapper类
//@Repository //弹幕说这里加不加都可以
public interface StudentMapper {

    List<Student> queryStudentList();

}
