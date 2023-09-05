package com.junyihong.spring_boot.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMyUserDao {
	List<MyUserDTO> list();	// 테이블의 내용을 select하기 위한 메서드를 정의한다.
}

// NAC (NETWORK ACCESS CONTROL)
// NAC는 네트워크에 접속하는 내부 PC의 MAC주소를 IP 관리 시스템에 등록한 후 일관된 보안 관리 기능을 제공하는 보안 솔루션이다.

// SIEM (SECURITY INFORMATION EVENT MANAGEMENT)
// 시스템의 로그와 이벤트를 통합하여 관리하는 빅데이터 기반의 보안 솔루션이다.