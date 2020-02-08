package com.arjjs.ccm.tool.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.arjjs.ccm.tool.freemaker.HtmlGenerator;
import com.arjjs.ccm.tool.pdf.exception.DocumentGeneratingException;

/**
 * pdf 生成
 * 
 * @ClassName: PdfGenerator
 * @Description:pdf 生成
 
 */
public class PdfDocumentGenerator {
	private final static Logger logger = Logger.getLogger(PdfDocumentGenerator.class);

	private final static HtmlGenerator htmlGenerator;
	static {
		htmlGenerator = new HtmlGenerator();
	}

	/**
	 * 使用模板,模板数据,生成pdf
	 * 
	 * @Title: generate
	 * @Description: 使用模板,模板数据,生成pdf
	 * @param template
	 *            classpath中路径模板路径
	 * @param documentVo
	 *            模板数据
	 * @param outputFile
	 *            生成pdf的路径
	 
	 * @throws DocumentGeneratingException
	 */
	@SuppressWarnings("static-access")
	public boolean generate(String template, Map<String, Object> documentVo,
			String outputFile ,String path ) throws DocumentGeneratingException {
		Map<String, Object> variables = new HashMap<String, Object>();
         
		try {
			variables = documentVo;
			String htmlContent = this.htmlGenerator.generate(template,
					variables ,path);
			this.generate(htmlContent, outputFile);
		
			
			
		} catch (Exception e) {
					
			throw new DocumentGeneratingException( e);
		}

		return true;
	}
   
	
	
	
	/**
	 * Output a pdf to the specified outputstream
	 * 
	 * @param htmlContent
	 *            the htmlstr
	 * @param out
	 *            the specified outputstream
	 * @throws Exception
	 */
	public void generate(String htmlContent, String outputFile)
			throws Exception {
		
		
		OutputStream out = null;
		ITextRenderer iTextRenderer = null;

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(htmlContent
					.getBytes("UTF-8")));
			File f = new File(outputFile);
			if (f != null && !f.getParentFile().exists()) {
				f.getParentFile().mkdir();
			}
			out = new FileOutputStream(outputFile);

			iTextRenderer = (ITextRenderer) ITextRendererObjectFactory
					.getObjectPool().borrowObject();//获取对象池中对象

			try {
				iTextRenderer.setDocument(doc, null);
				iTextRenderer.layout();
				iTextRenderer.createPDF(out);
				
			} catch (Exception e) {
				ITextRendererObjectFactory.getObjectPool().invalidateObject(
						iTextRenderer);
				iTextRenderer = null;
				throw e;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null)
				out.close();

			if (iTextRenderer != null) {
				try {
					ITextRendererObjectFactory.getObjectPool().returnObject(
							iTextRenderer);
				} catch (Exception ex) {
					logger.error("Cannot return object from pool.", ex);
				}
			}
			
			
		}
	}
	
	

	/**
	 * 使用模板,模板数据,生成pdf  返回流
	 * 
	 * @Title: generate
	 * @Description: 使用模板,模板数据,生成pdf
	 * @param template
	 *            classpath中路径模板路径
	 * @param documentVo
	 *            模板数据
	 * @param outputFile
	 *            生成pdf的路径
	 
	 * @throws DocumentGeneratingException
	 */
	@SuppressWarnings("static-access")
	public void generate(String template, Map<String, Object> documentVo,
			String path,HttpServletResponse response ) throws DocumentGeneratingException {
		Map<String, Object> variables = new HashMap<String, Object>();
		ByteArrayInputStream inputStream=null;
		try {
			variables = documentVo;
			String htmlContent = this.htmlGenerator.generate(template,
					variables ,path);
			
			 inputStream=this.generate(htmlContent);
			
			
		} catch (Exception e) {
					
			throw new DocumentGeneratingException( e);
		}
		response.setContentType("application/x-download");
        response.addHeader("Content-Disposition","attachment;filename=" + System.currentTimeMillis()+".pdf");
        ServletOutputStream out=null;
		try {
			out = response.getOutputStream();
			
			 int b = 0;  
	            byte[] buffer = new byte[512];  
	            while (b != -1){  
	                b = inputStream.read(buffer);  
	                //4.写到输出流(out)中  
	                out.write(buffer,0,b);  
	            }  
		} catch (IOException e) {
			throw new DocumentGeneratingException( e);
		}finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new DocumentGeneratingException( e);
				}	
			}
			if(out!=null) {					
			try {																			
		         out.close();	
			} catch (IOException e) {
				throw new DocumentGeneratingException( e);
			}
			}
		}		          		            
		
	}
    
	/**
	 * Output a pdf to the specified outputstream
	 * 
	 * @param htmlContent
	 *            the htmlstr
	 * @param out
	 *            the specified outputstream
	 * @throws Exception
	 */
	public ByteArrayInputStream generate(String htmlContent)
			throws Exception {
		
		ByteArrayOutputStream out=null;
		ByteArrayInputStream inputStream=null;
		ITextRenderer iTextRenderer = null;

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(htmlContent
					.getBytes("UTF-8")));
			
			out = new ByteArrayOutputStream();
	       
	       
			//out = new FileOutputStream(outputFile);

			iTextRenderer = (ITextRenderer) ITextRendererObjectFactory
					.getObjectPool().borrowObject();//获取对象池中对象

			try {
				iTextRenderer.setDocument(doc, null);
				iTextRenderer.layout();
				iTextRenderer.createPDF(out);
				  
			
				inputStream=new ByteArrayInputStream(out.toByteArray());
			
			} catch (Exception e) {
				ITextRendererObjectFactory.getObjectPool().invalidateObject(
						iTextRenderer);
				iTextRenderer = null;
				throw e;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			
			if (out != null)
				out.close();
			
			if (iTextRenderer != null) {
				try {
					ITextRendererObjectFactory.getObjectPool().returnObject(
							iTextRenderer);
				} catch (Exception ex) {
					logger.error("Cannot return object from pool.", ex);
				}
			}				
		}
		return inputStream;
	}

}