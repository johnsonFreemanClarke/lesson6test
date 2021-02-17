package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.data.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.data.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    //CRUD operations
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid= #{userId}")
    List<Credential> getAll(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid= #{credentialId}")
    Credential getCredByCredId(Integer credentialId);

    @Select("SELECT key FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    String getKey(int credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password}")
    int update(Credential credential);

    @Delete("DELETE FROM CREDENTIAL WHERE credentialid = #{credentialId}")
    int delete(Integer credentialid);

    @Delete("DELETE CREDENTIALS")
    int deleteAll();

}

//    private int credentialId;
//    private String url;
//    private String username;
//    private String key;
//    private String password;
//    private int userId;