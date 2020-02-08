package com.arjjs.ccm.tool.exception;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * 异类处理类
 * 
 * @author yuedongyang
 * @date 2018年8月2日 下午2:09:37
 */
@ControllerAdvice
public class ExceptionAdvice implements ResponseBodyAdvice<Object>{
	private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2,
			Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest request, ServerHttpResponse response) {
		 ServletServerHttpRequest ssReq = (ServletServerHttpRequest)request;
	        ServletServerHttpResponse ssResp = (ServletServerHttpResponse)response;
	        if(ssReq == null || ssResp == null
	                || ssReq.getServletRequest() == null
	                || ssResp.getServletResponse() == null) {
	            return body;
	        }
	        
	        // 对于未添加跨域消息头的响应进行处理
	        HttpServletRequest req = ssReq.getServletRequest();
	        HttpServletResponse resp = ssResp.getServletResponse();
	        String originHeader = "Access-Control-Allow-Origin";
	        if(!resp.containsHeader(originHeader)) {
	            String origin = req.getHeader("Origin");
	            if(origin == null) {
	                String referer = req.getHeader("Referer");
	                if(referer != null) {
	                    origin = referer.substring(0, referer.indexOf("/", 7));
	                }
	            }
	            resp.setHeader("Access-Control-Allow-Origin", origin);
	        }
	        
	        String credentialHeader = "Access-Control-Allow-Credentials";
	        if(!resp.containsHeader(credentialHeader)) {
	            resp.setHeader(credentialHeader, "true");
	        }
	        return body;
	}

	@Override
	public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * MethodArgumentNotValidException
	 * 
	 * @author yuedongyang
	 * @date 2018年8月2日 下午2:09:37
	 */

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public MessageEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		// 获取BindingResult对象
		BindingResult result = e.getBindingResult();
		// 获取第一条校验消息
		String message = "";
		if (result != null && result.getAllErrors().size() != 0) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				message += fieldError.getField() + " " + fieldError.getDefaultMessage() + ";";
			}
		} else {
			message = "Method Valid Exception Advice Error!";
			logger.error(message, e);
		}
		System.out.println(message);
		return new MessageEntity(message);
	}

	/**
	 * @author yuedongyang  
	 * @date 2018年8月9日 下午1:59:18  
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServerErrorException.class)
	@ResponseBody
	public MessageEntity handleServerErrorException(
            Exception e) {
		System.out.println(e.getMessage());
		return new MessageEntity(e.getMessage());
	}
	 

	/**
	 * 上传文件过大异常
	 * 
	 * @author yuedongyang
	 * @date 2018年8月16日 下午7:09:44
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public MessageEntity handleMaxUploadSizeExceededException(Exception e) {
		System.out.println(e.getMessage());
		return new MessageEntity(e.getMessage());
	}
	/**
	 * 文件不存在异常
	 * 
	 * @author yuedongyang
	 * @date 2018年8月16日 下午7:09:44
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseBody
	public MessageEntity handleFileNotFoundException(Exception e) {
		return new MessageEntity("没有该文件或目录！");
	}

//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public MessageEntity handleAuthorizationException(Exception e) {
		System.out.println(e.getMessage());
		return new MessageEntity(e.getMessage());
	}
	
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public MessageEntity handleException(Exception e) {
//		System.out.println(e.getMessage());
//		return new MessageEntity("SERVER ERROR !");
//	}
	

}
