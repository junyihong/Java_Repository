
package com.study.springboot;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MyController {

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "FileUpload (2)";
	}
	
	@RequestMapping("/uploadForm")
	public String uploadForm() {
		return "fileForm";
	}
	
	@RequestMapping("/uploadOk")
	public @ResponseBody String uploadOk(HttpServletRequest request)
	{
		int size = 1024 * 1024 * 10; // 10MB
		String file = "";
		String oriFile = "";
		
		JSONObject obj = new JSONObject();
		
		try {
			// 변경 : 절대 경로를 사용합니다.
			String path = new File ("C:\\upload").getAbsolutePath();
//			String path = ResourceUtils
//						.getFile("classpath:static/upload/").toPath().toString();
			System.out.println(path);
			
            MultipartRequest multi = new MultipartRequest(request, path, size,
                    "UTF-8", new DefaultFileRenamePolicy());
			System.out.println("111111"); // 이는 디버깅을 위한 것이며 디버깅 코드의 일부이다.
			// 이런 종류의 출력문은 실제 프로덕션 코드에서는 제거하는 것이 일반적이다.
			Enumeration files = multi.getFileNames();
			String str = (String)files.nextElement();
			
			file = multi.getFilesystemName(str);
			oriFile = multi.getOriginalFileName(str);
			
			obj.put("success", 1);	// 간단히 사용 가능 autoboxing 기능이 제공
			obj.put("desc", "업로드 성공");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("success", 0);
			obj.put("desc", "업로드 실패");
		}
		return obj.toJSONString();
	
	}
}