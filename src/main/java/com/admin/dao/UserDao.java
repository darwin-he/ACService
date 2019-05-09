package com.admin.dao;

import com.admin.model.User;
import com.admin.ov.Location;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/2 0:38
 */
@Mapper
@Component
public interface UserDao {
	
	/*************************************用户归属************************************/
	@Select({"select province,city,county,community from admins where adminCount = '00000000000'"})
	Location getLocation();
	
	/*************************************新增用户************************************/
	@Insert({"insert into users (userCount,userName,passWord,userCard,province,city,county,community,state,registerTime) " +
			         "values (#{userCount},#{userName},#{passWord},#{userCard},#{province},#{city},#{county},#{community},#{state},#{registerTime})"})
	int addUser(User user);
	
	/*************************************删除用户************************************/
	@Delete({"delete from users where userId = #{userId}"})
	int deleteUserByUserId(@Param("userId") int userId);
	
	@Delete({"delete from users where userCard = #{userCard}"})
	int deleteUserByUserCard(@Param("userCard") String userCard);

	@Delete({"delete from users where userCount = #{userCount}"})
	int deleteUserByUserCount(@Param("userCount")String userCount);
	
	/*************************************获取用户************************************/
	@Select({"select * from users"})
	List<User> getUser();
	
	@Select({"select * from users where userCard = #{userCard}"})
	User getUserByUserCard(@Param("userCard") String userCard);
	
	@Select({"select * from users where userId = #{userId}"})
	User getUserByUserId(@Param("userId")int userId);
	
	@Select({"select * from users where userCount = #{userCount}"})
	User getUserByUserCount(@Param("userCount")String userCount);
	
	@Select({"select * from users where userName like concat(concat('%',#{userName}),'%')"})
	List<User> getUsersByUserName(@Param("userName")String userName);
	
	@Select({"select * from users where registerTime between #{leftDate} and #{rightDate}"})
	List<User> getUsersByDate(@Param("leftDate")String leftDate,@Param("rightDate")String rightDate);
	
	@Select({"select * from users where county = #{county} and community = #{community}"})
	List<User> getUsersByLocation(@Param("province")String province,@Param("city")String city,
	                              @Param("county")String county,@Param("community")String community);
	
	/*************************************修改用户************************************/
	@Update({"update users set userCount = #{userCount},userName = #{userName},passWord = #{passWord}," +
			         "userCard = #{userCard},province = #{province},city = #{city}," +
			         "county = #{county},community = #{community},state = #{state} where userId = #{userId}"})
	int modifyUser(User user);
	
	@Update({"update users set passWord = #{passWord} where userId = #{userId}"})
	int modifyUserPassWord(@Param("userId")int userId,@Param("passWord")String passWord);
	
	@Update({"update users set userCard = #{userCard} where userId = #{userId}"})
	int modifyUserCard(@Param("userId")int userId,@Param("userCard")String userCard);
	
	@Update({"update users set userName = #{userName} where userId = #{userId}"})
	int modifyUserName(@Param("userId")int userId,@Param("userName")String userName);

	@Update({"update users set state = #{state} where userId = #{userId}"})
	int modifyUserState(@Param("userId")int userId,@Param("state")char state);

	@Update({"update users set userCount = #{userCount} where userId = #{userId}"})
	int modifyUserCount(@Param("userId")int userId,@Param("userCount")String userCount);
	
	@Update({"update users set province = #{province},city = #{city},county = #{county},community = #{community} where userId = #{userId}"})
	int modifyUserLocation(@Param("userId")int userId,
	                       @Param("province")String province,@Param("city")String city,
	                       @Param("county")String county,@Param("community")String community);
}
