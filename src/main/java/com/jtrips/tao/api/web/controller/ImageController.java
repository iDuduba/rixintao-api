package com.jtrips.tao.api.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jtrips.tao.api.enums.RespCodeEnum;
import com.jtrips.tao.api.exception.ImageUploadException;
import com.jtrips.tao.api.res.CommonResponse;
import com.jtrips.tao.api.res.ImageUploadResponse;

@RestController
@RequestMapping("/images")
public class ImageController extends BaseController {
	
	@Value("${api.upload.path}")
	private String uploadPath;

	
	// curl -F "uploadfile=@/home/duduba/abc.jpg" http://localhost:8080/api/images/upload
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ImageUploadResponse uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
		
		String filename = uploadfile.getOriginalFilename();
		if(filename.startsWith("abc")) {
			throw new ImageUploadException(filename);
		}
		
		String fid = UUID.randomUUID().toString();
		String filepath = Paths.get(uploadPath, fid).toString();
		BufferedOutputStream stream;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		    stream.write(uploadfile.getBytes());
		    stream.close();
		} catch (IOException e) {
			throw new ImageUploadException(e.getLocalizedMessage());
		}

		ImageUploadResponse response = new ImageUploadResponse();
		response.setResponse(RespCodeEnum.SUCCESS);		
		response.setImageId(fid);
		
        return response;
	}
	
//	@ExceptionHandler(ImageUploadException.class)
//	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
//    public CommonResponse myError(HttpServletRequest request, Exception exception) {
//	  CommonResponse error = new CommonResponse();
//        error.setRespCode(""+HttpStatus.BAD_REQUEST.value());
//        error.setRespMsg(exception.getLocalizedMessage());
//        return error;
//    }
//

}
