package com.junyihong.springbootdeveloper;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContentValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0);		// 검증할 객체의 클래스 타입 정보
	} // 지정된 클래스가 유효성 검증을 지원하는지 확인합니다.
	// arg0로 전달된 클래스가 ContentDto 클래스로 할당 가능한지 확인합니다.
	
	@Override
	public void validate(Object obj, Errors errors) {
		// 실제 유효성 검증 로직을 수행합니다.
		
		ContentDto dto = (ContentDto)obj;
		// obj 매개변수는 ContentDto로 캐스팅된 객체입니다.
		
//		String sWriter = dto.getWriter();
//		
//		if(sWriter == null || sWriter.trim().isEmpty()) {	
//			// 값이 null이거나 빈 문자열인지, 공백을 제거한 후 isEmpty() 메서드로 확인합니다.
//			System.out.println("Writer is null or empty.");
//			errors.rejectValue("writer", "trouble");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty.");
		// 필드가 비어있거나 공백 문자로만 이루어져 있는 경우에
		// "writer" : 유효성 검사를 수행할 필드의 이름, errors 오류를 저장하는 객체
		// 추가할 오류 메시지 writer is empty.
		String sWriter = dto.getWriter();
		
		if(sWriter.length() < 3) {	// 문자열의 길이가 3보다 작을 경우를 검사
			errors.rejectValue("writer", "writer is too short.");
		}
		
//		String sContent = dto.getContent();
//		if(sContent == null || sContent.trim().isEmpty()) {
//			System.out.println("Content is null or empty.");
//			errors.rejectValue("content", "trouble");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty.");
		
	}
}
