package be.idocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDocument {
	private Document parsedDocument;
	private Synonyms synonyms;
	public XMLDocument (File file) {
		synonyms = new Synonyms();
		if (isXml(file)) {
		       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
   	           DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				parsedDocument = db.parse(file);
       		} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
}
}
}
	public String getValue(BusinessTerm keyword){	
		ArrayList<String> syn = synonyms.getAllSynonyms(keyword.getName());
		String possibleValue ="";
		for (String kw : syn){	
		
			NodeList nodeList = parsedDocument.getElementsByTagName(kw.toString());	
			//System.out.println(nodeList);
			if (nodeList.item(0) == null) {}
			else {possibleValue = nodeList.item(0).getTextContent();
			System.out.println("return is "+possibleValue + " " +nodeList.item(0));
			}
		}
		String returnValue = possibleValue;
		return returnValue;	
	}
	public NodeList getComplexValue(BusinessTerm keyword){	
		ArrayList<String> syn = synonyms.getAllSynonyms(keyword.getName());
		NodeList returnValue = (NodeList) parsedDocument;
		for (String kw : syn){	
			NodeList nodeList = parsedDocument.getElementsByTagName(kw.toString());	
			
			if (nodeList.item(0) == null) {}
			else returnValue = nodeList;
			}
	return returnValue;	
	}
	
	private boolean isXml(File file) {
	    String name = file.getName();
	    try {
	        String ext = name.substring(name.lastIndexOf(".") + 1);
	        if (ext.compareTo("xml") == 0) {return true;
	    }
	        if (ext.compareTo("XML") == 0) {return true;
		    }}catch (Exception e) {
	        return false;
	    }
	    return false;
	}
}