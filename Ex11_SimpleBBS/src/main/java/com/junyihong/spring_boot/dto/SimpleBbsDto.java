package com.junyihong.spring_boot.dto;

import lombok.Data;

@Data
public class SimpleBbsDto {
	private int id;
	private String writer;
	private String title;
	private String content;
}
