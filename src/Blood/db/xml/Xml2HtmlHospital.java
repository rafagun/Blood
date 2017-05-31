package Blood.db.xml;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Xml2HtmlHospital {
	public static void simpleTransform(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File("src\\data\\XSLThospitals.xslt")));
			transformer.transform(new StreamSource(new File("src\\data\\NewFile.xml")),new StreamResult(new File("src\\data\\XSLThospitals.htlm")));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
