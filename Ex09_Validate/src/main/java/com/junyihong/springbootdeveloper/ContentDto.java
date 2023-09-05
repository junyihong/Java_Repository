package com.junyihong.springbootdeveloper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContentDto {
    private int id;
    
    @NotNull(message="writer is null.")
    // writer 프로퍼티가 null이 아니어야 합니다.
    // null인 경우에는 "writer is null" 메시지가 발생
    
    @NotEmpty(message="writer is empty.")
    // 비어있는 경우에는 "writer is empty" 메시지가 발생
    
    @Size(min=3, max=10, message="writer min 3, max 10.")
    // writer 프로퍼티의 길이는 3이상 10이하여야 한다.
    
    private String writer;
    
    @NotNull(message="content is null.")
    @NotEmpty(message="content is empty.")
    private String content;
}
