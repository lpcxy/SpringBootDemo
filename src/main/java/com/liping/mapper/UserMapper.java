package com.liping.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liping.domain.User;

@Mapper
public interface UserMapper
{
	@Select("select * from t_user where id = #{id}")
	User queryById(@Param("id")String id);
	
	@Insert("insert into t_user(id, name, age) values(#{user.id}, #{user.name}, #{user.age})")
	void insert(@Param("user")User user);
	
	@Update("update t_user set age = #{user.age}, name = #{user.name} where id = #{user.id}")
	void update(@Param("user")User user);
	
	@Delete("delete from t_user where id = #{id}")
	void delete(@Param("id")String id);
}
