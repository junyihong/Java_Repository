package com.junyihong.springbootdeveloper;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	 @RequestMapping("/")
	 public @ResponseBody String root() throws Exception{
	    return "Valid Annotation 4";
	 }
	@RequestMapping("/insertForm")
	public String insert1() {
		return "createPage";
	}
	@RequestMapping("/create")
    public String insert2(@ModelAttribute("dto") @Valid ContentDto contentDto,
                            BindingResult result) {
        String page = "createDonePage";
        
		// 유효성 검증을 수행합니다.
		
//		ContentValidator validator = new ContentValidator();
//		validator.validate(contentdto, result);
		// ContentValidator 객체를 사용하여 contentDto를 유효성 검증합니다.
		
        if (result.hasErrors()) {
            if (result.getFieldError("writer") != null)
                System.out.println("1: "+ result.getFieldError("writer").getDefaultMessage());
            
            if (result.getFieldError("content") != null)
                System.out.println("2: "+ result.getFieldError("content").getDefaultMessage());
            page = "createPage";
        }
        
        return page;
    }	// page 변수를 반환하여 요청에 대한 응답으로 사용.
		// 유효성 검증에 실패하지 않았다면 "createDonePage"가 반환됩니다.
		// 실패한 경우에는 "createPage" 가 반환됩니다.
	
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(new ContentValidator());
//    }
//    @InitBinder
//    : 검증에 사용할 클래스를 이 부분에서 의존성 주입을 함
}

