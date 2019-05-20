package com.admin.dao;

import com.admin.model.User;
import com.admin.vo.Location;
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
	
	/*************************************新增用户************************************/
	@Insert({"insert into users (account,name,passWord,cardNumber,state,registerTime) " +
			         "values (#{account},#{name},#{passWord},#{cardNumber},#{state},#{registerTime})"})
	int addUser(User user);
	
	/*************************************删除用户************************************/
	@Delete({"delete from users where id = #{id}"})
	int deleteUserById(@Param("id") int id);
	
	@Delete({"delete from users where cardNumber = #{cardNumber}"})
	int deleteUserByCardNumber(@Param("cardNumber") String cardNumber);

	@Delete({"delete from users where account = #{account}"})
	int deleteUserByAccount(@Param("account")String account);
	
	/*************************************获取用户************************************/
	@Select({"select count(*) from users"})
	int getUserCount();
	
	@Select({"select * from users"})
	List<User> getUser();
	
	@Select({"select * from users where cardNumber = #{cardNumber}"})
	User getUserByCardNumber(@Param("cardNumber") String cardNumber);
	
	@Select({"select * from users where id = #{id}"})
	User getUserById(@Param("id")int id);
	
	@Select({"select * from users where account = #{account}"})
	User getUserByAccount(@Param("account")String account);
	
	@Select({"select * from users where name like concat(concat('%',#{name}),'%')"})
	List<User> getUsersByName(@Param("name")String name);
	
	@Select({"select * from users where registerTime between #{leftDate} and #{rightDate}"})
	List<User> getUsersByDate(@Param("leftDate")String leftDate,@Param("rightDate")String rightDate);
	
	/*************************************修改用户************************************/
	
	@Update({"update users set passWord = #{passWord} where id = #{id}"})
	int modifyUserPassWord(@Param("id")int id,@Param("passWord")String passWord);
	
	@Update({"update users set cardNumber = #{cardNumber} where id = #{id}"})
	int modifyUserCardNumber(@Param("id")int id,@Param("cardNumber")String cardNumber);
	
	@Update({"update users set name = #{name} where id = #{id}"})
	int modifyUserName(@Param("id")int id,@Param("name")String name);

	@Update({"update users set state = #{state} where id = #{id}"})
	int modifyUserState(@Param("id")int id,@Param("state")String state);

	@Update({"update users set account = #{account} where id = #{id}"})
	int modifyUserAccount(@Param("id")int id,@Param("account")String account);
}
