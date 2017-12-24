package be.idocument;


import java.io.File;
/*copyright Johan Platteau, granted full license to Perspektiv Consult*/
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class iDocument {
private String text;
private Scanner scanner; 
private Synonyms synonyms;

public iDocument (File file) {
	
    if (isPdf(file))
	   {PDDocument document;
	   synonyms = new Synonyms();
	try {
		document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
	    text = pdfStripper.getText(document);
	    document.close();
	} catch (InvalidPasswordException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}  
  }
}
public String getValue(BusinessTerm keyword) {	
	ArrayList<String> syn = synonyms.getAllSynonyms(keyword.getName());
	String returnValue ="";
	for (String kw : syn){		
		int start = text.indexOf(kw.toString()); 
		//System.out.println("start = "+start +"of synoniem "+kw);
		if (start>-1) {
			//create a scanner of the text cut of at the index	
		    String scannerText = text.substring(start);
		    scanner = new Scanner(scannerText);		    
		    String line = scanner.nextLine();
		    scanner.close();
		    int i = 0;
		    int numbersInValue = 0;
		    while (numbersInValue < keyword.getLength())
		    { char c = line.charAt(i);
		    System.out.println(c);
		       if (keyword.getType().compareTo("number") ==0)
		    	     {int cInt = Character.getNumericValue(c);
		            if (cInt>-1) {
		    	         if (cInt<10) {
		    		       returnValue= returnValue + c;	    		       
		    		       numbersInValue = numbersInValue+1;
		    		       System.out.println("numbersInValue "+numbersInValue);	    		       
		    		    };
		    		    }
                  }
		       else returnValue= returnValue + c;
		       numbersInValue = numbersInValue+1;
		       System.out.println(returnValue);
		       i = i + 1;}
		    return returnValue;     
		}
		
}		
	return returnValue;	
}
public String toString() {
	return text;
}
public int getStartPosition(BusinessTerm keyword) {
	ArrayList<String> syn = synonyms.getAllSynonyms(keyword.getName());
	int returnValue =-1;
	for (String kw : syn){		
		int start = text.indexOf(kw.toString());
		//System.out.println("looking for " + kw + "at pos "+start);
		if (start>-1) {returnValue=start+kw.toString().length();}
}
	return returnValue;
}
public String getValueByPosition(int start,int numberOfWords) {
	String returnValue ="";
	String scannerText = text.substring(start);
	int wordcount = 1;
    scanner = new Scanner(scannerText);
    String line = scanner.nextLine();
    
    scanner.close();
    for (int i = 0; i < line.length(); i++)
     { char c = line.charAt(i);
       if (c == ' ' ) {
    	     //next word
    	     wordcount = wordcount +1;}
        if (wordcount <= numberOfWords) {
    		       returnValue= returnValue + c;
        }
        else break;    
      }
	return returnValue;
}
private boolean isPdf(File file) {
    String name = file.getName();
    try {
        String ext = name.substring(name.lastIndexOf(".") + 1);
        if (ext.compareTo("pdf") == 0) {return true;
    } }catch (Exception e) {
        return false;
    }
    return false;
}

}
