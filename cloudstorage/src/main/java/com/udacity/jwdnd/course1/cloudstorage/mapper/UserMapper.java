package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.data.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//repostiory
//add, delete, update, find

/**
 * CREATE TABLE IF NOT EXISTS USERS (
 *   userid INT PRIMARY KEY auto_increment,
 *   username VARCHAR(20),
 *   salt VARCHAR,
 *   password VARCHAR,
 *   firstname VARCHAR(20),
 *   lastname VARCHAR(20)
 * );
 */

@Mapper
public interface UserMapper {

    static final String GET_QUERY = "SELECT * FROM ";
    static final String INSERT_QUERY = "INSERT INTO ";
    static final String DELETE_QUERY = "DELETE FROM ";
    static final String TABLE = "USERS ";
    static final String WHERE_CONDITION = "WHERE ";

    @Select(GET_QUERY + TABLE + WHERE_CONDITION + "username = #{username}" )
    User getUser(String username);

    @Insert(INSERT_QUERY + TABLE + " (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Delete(DELETE_QUERY + TABLE + WHERE_CONDITION + " userid = #{userId}")
    int delete(Integer userId);

    @Delete(DELETE_QUERY + TABLE)
    int deleteAll();

    @Select(GET_QUERY + TABLE)
    List<User> getAllUser();

}
