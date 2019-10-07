package com.kvinod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kvinod.entity.Shipper;

public interface ShipperDao {

	@Select("SELECT SHIPPER_ID as id, COMPANY_NAME as name, PHONE as phone FROM SHIPPERS WHERE SHIPPER_ID = #{id}")
	public Shipper selectShipper(Integer id);

	@Insert("INSERT INTO SHIPPERS (SHIPPER_ID, COMPANY_NAME, PHONE) VALUES (#{id}, #{name}, #{phone})")
	// @Options(useGeneratedKeys=true, keyProperty="id")
    public void insertShipper(Shipper shipper);

	@Select("select * from shippers")
	@Results({ 
		@Result(property = "id", column = "shipper_id", id = true),
		@Result(property = "name", column = "company_name") 
	})
	public List<Shipper> findAll();
	
	@Update("update shippers set company_name=#{name}, phone=#{phone} where shipper_id=#{id}")
	public void updateShipper(Shipper shipper);
	
	@Delete("delete from shippers where shipper_id=#{id}")
	public void deleteShipper(Integer id);

}
