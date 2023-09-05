package com.junyihong.spring_boot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.junyihong.spring_boot.dto.SimpleBbsDto;

@Repository
public class SimpleBbsDao implements ISimpleBbsDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<SimpleBbsDto>listDao() {
		System.out.println("listDao()");
		
		String query = "SELECT * FROM SIMPLE_BBS ORDER BY ID DESC";
		List<SimpleBbsDto> dtos = template.query(
				query, new BeanPropertyRowMapper<> (SimpleBbsDto.class));
				
				return dtos;
	}

	@Override
	public SimpleBbsDto viewDao(String id) {
		System.out.println("viewDao()");
		
		String query = "SELECT * FROM SIMPLE_BBS WHERE ID = " + id;
		SimpleBbsDto dto = template.queryForObject (
				query, new BeanPropertyRowMapper<>(SimpleBbsDto.class));
		return dto;
	}
	
	@Override
	public int writeDao(final String writer, final String title, final String content) {
		System.out.println("writeDao()");
		
		String query = "INSERT INTO SIMPLE_BBS (ID, WRITER, TITLE, CONTENT) " +
						" VALUES (SIMPLE_BBS_SEQ.NEXTVAL, ?, ?, ?)";
		return template.update(query, writer, title, content);
	}
	
	@Override
	public int deleteDao(final String id) {
		System.out.println("deleteDao()");
		
		String query = "DELETE FROM SIMPLE_BBS WHERE ID = ?";
		return template.update(query, Integer.parseInt(id));
	}
	
}
