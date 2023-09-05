package com.junyihong.spring_boot.dao;

import java.util.List;

import com.junyihong.spring_boot.dto.SimpleBbsDto;

public interface ISimpleBbsDao {
	
	public List<SimpleBbsDto> listDao();
	// 리스트보기
	
	public SimpleBbsDto viewDao(String id);
	// 개별 뷰 보기
	
	public int writeDao(String writer, String title, String content);
	// 글 작성을 위한 insert
	
	public int deleteDao(String id);

}
