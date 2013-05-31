package org.nextplus.htb2.bind;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.WebRequest;

public class WebBindingInitializer implements 
		org.springframework.web.bind.support.WebBindingInitializer {
	
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(df, true));
//		binder.registerCustomEditor(File.class, new FileEditor(fileUploadService));
	}
	
//	private FileUploadService fileUploadService;
//	
//	@Autowired
//	public void setFileUploadService(FileUploadService fileUploadService) {
//		this.fileUploadService = fileUploadService;
//	}

}
