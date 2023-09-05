package com.junyihong.spring_boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.junyihong.spring_boot.dto.SimpleBbsDto;

@Mapper
public interface ISimpleBbsDao {
	public List<SimpleBbsDto> listDao();
	public SimpleBbsDto viewDao(String id);
	public int writeDao(String writer, String title, String content);
	public int deleteDao(@Param("_id") String id);
	// @Param 어노테이션을 사용하여 매개변수의 이름을 지정합니다.
	// _id라는 이름으로 전달된 id 값을 사용하여 데이터를 삭제하는 기능
}
