package htmltopdf;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.exceptions.RuntimeWorkerException;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext; 


public class ItextHtmlToPDFExample {
	static StringBuilder htmlString = new StringBuilder(); 
	
	public void geturlHtml(String urlToRead){

    	URL url = null;
    	try {
			url = new URL(urlToRead);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		List<String> lines = new ArrayList<String>(); 
		String readLine = null;
		try {
			
			InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr); 
			
			while ((readLine = br.readLine()) != null) {  
				lines.add(readLine);
			}
			for (String line : lines) {  
				//System.out.println("> " + line);
				htmlString.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

	 /**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException 
	    { 
		 //url에서 html 읽어오기
		// gethtml getHtml = new gethtml();
		 //System.out.println(getHtml.getOpenStreamHTML("http://kostat.go.kr/incomeNcpi/cpi/cpi_td/4/1/index.action?bmode=priceinfo&gubun=020"));
	        //System.out.println(getHtml.getHttpHTML("http://kostat.go.kr/incomeNcpi/cpi/cpi_td/4/1/index.action?bmode=priceinfo&gubun=020"));
		 //getHtml.geturlHtml("http://kostat.go.kr/incomeNcpi/cpi/cpi_td/4/1/index.action?bmode=priceinfo&gubun=020");
		 
		 
	        try 


	        { 
	               OutputStream file = new FileOutputStream(new File("HTMLtoPDF.pdf")); 

	            Document document = new Document(); 
	            PdfWriter writer = PdfWriter.getInstance(document, file); 

	            ///////////////////////////////// Pdf 속성 설정.
	            PdfPageEvent event = new PdfPageEvent(); 
	            writer.setBoxSize("boxName", new Rectangle(36, 54, 559, 788));
	            writer.setPageEvent(event);



	            ///////////////////////////// HTML Parsing
	       //   new ItextHtmlToPDFExample().geturlHtml("http://kostat.go.kr/incomeNcpi/cpi/cpi_td/4/1/index.action?bmode=priceinfo&gubun=020");

	        

/**
	            htmlString.append(new String("<html><body> This is HMTL to PDF conversion Example<table border='2' align='center'> ")); 
	            htmlString.append(new String("<tr><td>JavaCodeGeeks</td><td><a href='examples.javacodegeeks.com'>JavaCodeGeeks</a> </td></tr>"));                
	            htmlString.append(new String("<tr> <td> Google Here </td> <td><a href='www.google.com'>Google</a> </td> </tr></table></body></html>")); 
	**/         
	           


	            document.open(); 
	            XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

	            CSSResolver cssResolver = new StyleAttrCSSResolver();

	            CssFile cssFile = helper.getCSS(new FileInputStream("E:\\workspace\\htmltopdf\\style.css"));

	            cssResolver.addCss(cssFile);
	            
	            
	            //////////////폰트설정
	            
	            XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);

	            fontProvider.register("E:\\workspace\\htmltopdf\\궁서체.TTF", "gungser"); // MalgunGothic은 alias, 

	            fontProvider.register("E:\\workspace\\htmltopdf\\NanumPen.ttf", "nanum"); // MalgunGothic은 alias,
	            CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

	             

	            HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);

	            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	            
	            
	            ///////////////////////////////////pipeline
	            
	            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);

	            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);

	            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

	             

	            XMLWorker worker = new XMLWorker(css, true);

	            XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
	            
	            
	            
	            ///////////////////////////////////////// 파일저장
	       //   FileOutputStream fos = new FileOutputStream("test.txt");
	         // fos.write(htmlString.toString().getBytes());

	          //  InputStream is = new ByteArrayInputStream(htmlString.toString().getBytes()); 

	         //   XMLWorkerHelper.getInstance().parseXHtml(writer, document, is); 
	         // XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("test1.html"), Charset.forName("cp1252"));

	
	            String Readd = null;
	            Scanner in = new Scanner(new File("dashboard.htm"),"UTF-8");
	            while (in.hasNextLine()) {
	            	
					Readd += in.nextLine();
				}
				
	            
	           StringReader strReader = new StringReader(Readd);
	            System.out.println(Readd);
	            try{
	            xmlParser.parse(new ByteArrayInputStream(Readd.getBytes()));
	            document.close();
	            }catch(RuntimeWorkerException e) {
	            	System.out.println(e);
	            }
	           
	            System.out.println("Program End");


	            file.close(); 



	        } 



	        catch (Exception e) 


	        { 


	            e.printStackTrace(); 

	        } 


	    } 


}
