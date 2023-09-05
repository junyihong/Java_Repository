package com.junyihong.springbootdeveloper.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyUserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<MyUserDTO> list() {
		String query = "SELECT * FROM MYUSER";
		List<MyUserDTO> list = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(MyUserDTO.class));
		
		// for(UserDTO my : list) {
		// System.out.pringln(my);
		// }
		
		return list;
	}
}
