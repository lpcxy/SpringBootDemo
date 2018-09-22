package com.liping.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liping.domain.IamUser;

@Mapper
public interface IamUserMapper
{
	@Select("select * from t_iam_user where name = #{name}")
	IamUser query(@Param("name")String name);
	
	@Insert("insert into t_iam_user values(#{user.id}, #{user.name}, #{user.password})")
	void insert(@Param("user")IamUser user);

	@Update("update t_iam_user set password = #{password} where name = #{name}")
	void update(@Param("name")String name, @Param("password")String password);
	
	@Delete("delete from t_iam_user where name = #{name}")
	void delete(@Param("name")String name);
}
