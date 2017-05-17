package com.digit.PictureMgr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.domain.UserVO;
import com.dgit.service.UserService;
import com.dgit.util.MediaUtils;
import com.dgit.util.UploadFileUtil;

@Controller

public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	private String innerUploadPath = "resources/upload";
	
	/*servlet-context.xml의 bean의 id값으로 주입 받아짐*/
	@Resource(name="uploadPath") 
	private String uploadPath;
	@Inject
	private UserService service;
	
	
	@RequestMapping(value="/multiUploadForm", method=RequestMethod.GET)
	public String uploadMultiForm(){
		logger.info("multi upload form get---------------------------");
		return "outerMultiUploadForm";
	}//uploadMultiForm
	
	@RequestMapping(value="/multiUploadForm", method=RequestMethod.POST)
	public String uploadMultiFormResult(List<MultipartFile> files, String uid, Model model) throws Exception{
		logger.info("------------------------------------");
		for (MultipartFile file : files) {
			logger.info(file.getOriginalFilename());
			logger.info(file.getSize() + "");
			logger.info(file.getContentType());
			
		}
		
		
		
		
		
		
	
			
		ArrayList<String> fileNames = new ArrayList<String>();
		List<String> sel = null;
		for (MultipartFile file : files) {
			UUID uuid = UUID.randomUUID();
			
			String savedName2 = uuid.toString() + "_" + file.getOriginalFilename();
			
			sel = service.getAttach(uid); 
			
			File target = new File(uploadPath, savedName2);
			FileCopyUtils.copy(file.getBytes(), target);
			
			String thumbFile = UploadFileUtil.makeThumbnail(uploadPath, savedName2);
			UserVO vo = new UserVO();
			vo.setFullname(thumbFile);
			vo.setUid(uid);
			service.addAttach(vo);
			logger.info(thumbFile+">>>>>>>>>>저장된 아이디값>>>>>>>>>>>>>>>>>>>>>");
			sel.add(thumbFile);
			
			
		}
		
		model.addAttribute("fullList",sel);
		logger.info(sel+"<<<<<<<<<<<<<<<<셀");
		model.addAttribute("fileNames", fileNames);
		
		return "outerMultiUploadResult";
	}//uploadMultiFormResult
	
	/*외부 이미지 파일 보이도록 하는 메소드*/
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fullList){
		InputStream inS = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("[displayFile] fileName : "+fullList);
		
//		파일확장자만 뽑기
		String format = fullList.substring(fullList.lastIndexOf(".")+1);
		
//		패키지 com.dgit.util의 메소드를 불러와 
//		HttpHeaders에 주입
		MediaType mType = MediaUtils.getMediaType(format);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mType); //png, jpg, gif ...
		
		try {
			inS = new FileInputStream(uploadPath + "/" + fullList);
			
//			IOUtils.toByteArray(inS); : 바이트배열로 만들어주는 메소드
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(inS), headers, HttpStatus.CREATED);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				inS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	
//	//////////////테스트
	/*이미지 파일 지우기*/
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String filename) throws IOException {
		ResponseEntity<String> entity = null;
		
		System.out.println("--------------------------------");
//		현재파일(썸네일파일) : /2017/04/24/s_xxx.png
		logger.info("delete file name 호허: " + filename);
		
		try{
			/*본 파일 지우기*/
			 String original_b = filename.substring(2);
			 new File(uploadPath+"/"+original_b).delete();
			
			/*썸네일파일지우기*/
			new File(uploadPath+"/" + filename).delete();
			/* 데이터베이스 상으로 지우기*/
			service.delAttach(filename);
			
			entity = new ResponseEntity<String>("delete SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//deleteFile
	
}
