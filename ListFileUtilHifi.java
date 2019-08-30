package be.idocument;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import be.idocument.BusinessTerm;
import be.idocument.iDocument;
import be.ixordocs.doccle.DoccleUtil;
import be.ixordocs.doccle.DocumentType;


public class ListFileUtilHifi {
	private static DoccleUtil doccleUtil = new DoccleUtil();	
	private static String inputDirectory =  "/Users/johanplatteau/pdfdocs/hifi/input/"; 
	private static String outputDirectory =  "/Users/johanplatteau/pdfdocs/hifi/merged/"; 
	private static String Periode = "08-2019";
	private static String name = "FICHE SALAIRE 08/2019";
	
	
	public static void main(String[] args) {
		System.out.println("Change the periode static variable!!!!!!");
		DocumentType documentType = doccleUtil.getDocumentTypeInput();
		
        System.out.println("The documentType you entered is : " + documentType.getNameNl());
        if (documentType.getNameNl().compareTo("Receivers")==0){ 
        	listFilesAndCreateDoccleReceivers(inputDirectory);	
        }    
        else { 
        mergeFiles(inputDirectory,outputDirectory,documentType);
        listFilesAndCreateDoccleDocuments(outputDirectory,documentType);
        System.out.println("Check the periode static variable!!!!!!");
        System.out.println("Check the periode static variable!!!!!!");
        System.out.println("Check the periode static variable!!!!!!");
        System.out.println("Check the periode static variable!!!!!!");
        System.out.println("Check the periode static variable!!!!!!");
        System.out.println("Check the periode static variable!!!!!!");
        System.out.println("Check the periode static variable!!!!!!");

	}

}
	private static String getDocumentTypeInput(){
		   Scanner un = new Scanner(System.in); //  un is just a random variable you can choose any other variable name if you want
	       System.out.println("Enter the document type");
		       System.out.println("1-Loonadministratie");
		       //System.out.println("2-Individuele rekening");
		       System.out.println("Document datum aanpassen");

	       String documentTypeInt = un.next(); 
	       String documentType ="";
	       switch (documentTypeInt) {
		   case "1":  documentType = "Loonadministratie";
		   case "2":  documentType = "Loonadministratie";
	       break;   
	       }
	       System.out.println("The documentType you entered is : " + documentType);
	       un.close();
	       return documentType;
	}	
	private static String getSender()
	   { return "hifi";
	   }
 private static String getReceiverParameter(iDocument idoc, DocumentType docType){
     String receiver;
	 //als certificat de renumeration
	 if (docType.getNameNl().compareTo("Certificat de renumeration")==0){ 
  		 BusinessTerm bt = new BusinessTerm ("Rijksregisternummer",11,"number");
		 int start = idoc.getStartPosition(bt);
		 String scannerText = idoc.toString();
		 Scanner scanner = new Scanner(scannerText);
		    String line = scanner.nextLine();
		    line = line + scanner.nextLine();
		    //line = line + scanner.nextLine();
		    System.out.println("line = " +line);
		    scanner.close();
		    iDocument sd2 = new iDocument(line);
		    String value = sd2.getValueByPosition(1, 90);
		    //.replaceAll("[\\D.]", "");
		    value =value.replaceAll("[\\D.]", "");
		    value = value.replace("Emploi", "");
		    String mirror=new StringBuilder(value).reverse().toString();
		    System.out.println("value "+mirror);
		    receiver = mirror;
     }
	 else {
	 BusinessTerm bt = new BusinessTerm("Rijksregisternummer",13,"number");
	 receiver = idoc.getValue(bt);
	 System.out.println("Document RR = "+receiver);
	 
	if (receiver.compareTo("no value found") ==0) {
	//Zoek naar een paar keywords zoals "Assimilé" of "I/A"
	 
	
		 BusinessTerm bt2 = new BusinessTerm("Titel",2,"number");
		 
	     int start = idoc.getStartPosition(bt2);
	     System.out.println("start = "+start);
	     String scannerText = idoc.toString().substring(start);
	 	//System.out.println("scannertext = " +scannerText);
	 	
	    Scanner scanner = new Scanner(scannerText);
	    String line = scanner.nextLine();
	     line = scanner.nextLine();
	     line = scanner.nextLine();
	         	receiver = line.replace(" ", "");
	         	//System.out.println("found receiver "+receiver);
	         	if (receiver.compareTo("Assimilé") ==0){
	         		Scanner scanner2 = new Scanner(scannerText);
	        	    String line2 = scanner.nextLine();
	        	     line2 = scanner.nextLine();
	        	     //line = scanner.nextLine();
	        	         	receiver = line2.replace(" ", "");
	        	         	//System.out.println("found receiver "+receiver);
	    	
	         	}
	         	if (receiver.compareTo("I00") == 0){
	         		Scanner scanner2 = new Scanner(scannerText);
	        	    String line2 = scanner.nextLine();
	        	     line2 = scanner.nextLine();
	        	     //line = scanner.nextLine();
	        	         	receiver = line2.replace(" ", "");
	        	         	//System.out.println("found receiver "+receiver);
	         	}
	         	if (receiver.compareTo("II00") == 0){
	         		Scanner scanner2 = new Scanner(scannerText);
	        	    String line2 = scanner.nextLine();
	        	     line2 = scanner.nextLine();
	        	     //line = scanner.nextLine();
	        	         	receiver = line2.replace(" ", "");
	        	         	//System.out.println("found receiver "+receiver);
	         	}
	         	if (receiver.compareTo("IA00") == 0){
	         		Scanner scanner2 = new Scanner(scannerText);
	        	    String line2 = scanner.nextLine();
	        	     line2 = scanner.nextLine();
	        	     //line = scanner.nextLine();
	        	         	receiver = line2.replace(" ", "");
	        	         	//System.out.println("found receiver "+receiver);
	         	}
	         	
	}
		 //else {returnDate = doccleUtil.getDoccleDate(returnDate);
		 //} 
		}
    	return receiver;
    	}  
 private static String getDocumentDate(iDocument idoc){
	 BusinessTerm bt = new BusinessTerm ("Datum",8,"number");
 String returnDate = idoc.getValue(bt);
 //System.out.println("Document date = "+returnDate);
 if (returnDate.compareTo("no value found") ==0) {returnDate = doccleUtil.getNowInDoccleDate();}
 else {returnDate = doccleUtil.getDoccleDate(returnDate);
 }
	 return  returnDate;
	 }
 public static void listFilesAndCreateDoccleReceivers(String directoryName){
	   
	   File directory = new File(directoryName);
      String content = new String();
      content = "eMail;salutation;firstName;lastName;language;companyNumber;nationalNumber;token1;token2"+'\n';
      String doccleContent = doccleUtil.getDoccleReceiversHeader(getSender());
      //get all the files from a directory
      File[] fList = directory.listFiles();
      Arrays.sort(fList);
      for (File file : fList){
          if (file.isFile()){
          	String ext = getFileExtension(file);
          	//thread only csv files
          	if (ext.compareTo("csv")==0) {
          			 String line = "";
          		     String cvsSplitBy = ";";
          		        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          		            while ((line = br.readLine()) != null) {
          		                // use comma as separator	
          		            	   String[] lineString = line.split(cvsSplitBy);
          		            	   System.out.println(lineString[0]);
          		            	   //don't convert first line
          		            	   if (lineString[0].compareTo("work_info.email")!=0) {
          		                 String receiver = lineString[4];
          		               receiver = receiver.replace(" ", "");
          		            		System.out.println("creating new receiver "+ receiver);
          		            		//content = "eMail;salutation;firstName;lastName;language;companyNumber;nationalNumber;token1;token2"+'\n';
          		            		content = content + lineString[0]+";;"+lineString[1]+";"+lineString[2]+";fr;;"+receiver+";"+receiver+";"+doccleUtil.generateToken(receiver)+'\n';
          		                //String createDoccleReceiverWithEmail(String receiver, String token1,String email,String unlinkedNotification, 
          		        		    //String firstName, String lastName, String language) {

          		                doccleContent=doccleContent + doccleUtil.createDoccleReceiverWithEmail(
          		                		receiver,
          		                		doccleUtil.generateToken(receiver),
          		                		lineString[0],
          		                		"true",
          		                		lineString[1],
          		                		lineString[2],
          		                		lineString[3]);
          		            	   
          		            	   }
          		            }
          		            }
          		        catch (IOException e) {
          		            e.printStackTrace();
          		        }}}}
    //save csv file
      doccleUtil.saveFile(directoryName, "receiverBatch.csv",content);
      //add doccle footer
      doccleContent=doccleContent+"</receivers></batch>";
    //save doccle file
      doccleUtil.saveFile(directoryName,"/receivers.xml",doccleContent);
            
      }  
	public static void listFilesAndCreateDoccleDocuments(String directoryName, DocumentType documentType){
		   File directory = new File(directoryName);
	        String content = new String();
	        content = "receiver;creationDate;documentReference;documentType"+'\n';        
	        String doccleContent = doccleUtil.getDoccleDocumentsHeader(getSender());
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	        Arrays.sort(fList);
	        for (File file : fList){
	            if (file.isFile()){
	            		if(doccleUtil.isPdf(file)){
	            	    System.out.println("managing file "+file.getName());
	            		iDocument idoc = new iDocument(file);
	            		//System.out.println(idoc.toString());
	            		String fileName = file.getName();
	            		String rec = getReceiverParameter(idoc,documentType);
	            		System.out.println("receiver = "+rec);
	            		String documentId = Periode + "_"+rec;
	            		String date = getDocumentDate(idoc);
	            		System.out.println(rec + " "+date);
	            		
	            		String documentName = name;
	            	    
	                doccleContent = doccleContent+ doccleUtil.createDoccleDocument(documentId,rec,date,documentType.getDoccleName(),file,documentName,documentName);     
	            }
	          }
	        }
	      //save csv file
	       // doccleUtil.saveFile(directoryName, "output.csv",content);
	        //add doccle footer
	        doccleContent=doccleContent+"</documents></batch>";
	      //save doccle file
	        doccleUtil.saveFile(directoryName,"/documents.xml",doccleContent);    
	        } 
	private static void mergeFiles( String inputDirectory, String outputDirectory,DocumentType docType) {
		//get files in correct order by adding leading zeros
		File directory = new File(inputDirectory);		
		renameFiles(directory);
		//start merging*/
		KeyValueStore store = createKeyValueStore(inputDirectory,docType);   
		ArrayList<KeyValue> kvList = store.getAllKeyValues();
		   for (KeyValue kv : kvList){			   
			   ArrayList<String> files = kv.getStoredValuesKey();
			   PDFMergerUtility merger = new PDFMergerUtility();
			   String outputFileName = "";
		       for (String fileName : files)
			      { 
			      try {
			    	  File file1 = doccleUtil.getFileByName(inputDirectory,fileName);
			    	  merger.addSource(file1);
			    	  outputFileName = file1.getName().substring(0,10);
			    	  outputFileName = "merged" + outputFileName + kv.getKey();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    }
		       //save the merged file
		       OutputStream bout2;
			try {
				bout2 = new BufferedOutputStream(new FileOutputStream(outputDirectory + outputFileName+".pdf"));
				merger.setDestinationStream(bout2);
		        try {
					merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
				} catch (IOException e) {
					e.printStackTrace();
				}}
			 catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    	     }
	   }
	private static KeyValueStore createKeyValueStore(String inputDir, DocumentType docType) {
		   File directory = new File(inputDir);
		   KeyValueStore store= new KeyValueStore();
		   File[] fList = directory.listFiles();
		   String previousSplitKey = "";
		   for (File file : fList){
	            if (file.isFile()){
	            
	            	if (doccleUtil.isPdf(file)) {	           
	            iDocument id = new iDocument(file);	
	            System.out.println("file is "+file.getName());
          String currentSplitKey = getSplitKey(id,docType);     
          if (currentSplitKey.compareTo("") != 0) {
         	    if (currentSplitKey.compareTo("no value found") != 0) 
         	      { store.addKeyValue(currentSplitKey,file.getName());
         	        previousSplitKey = currentSplitKey;}                
         	      else store.addKeyValue(previousSplitKey, file.getName());}
          System.out.println("printing key" + currentSplitKey);
          System.out.println("previous key" + previousSplitKey);

	            }
	         }
		   }
		   return store;
	   }
	private static void renameFiles(File input)
	  {int maxLength = getHighestSuffixLength(input);
		  DoccleUtil doccleUtil = new DoccleUtil();
		  File[] fList = input.listFiles();
		   for (File file : fList){
	            if (file.isFile()){
	            	if (doccleUtil.isPdf(file)){
	            		int suffix = getSuffix(file.getName());
	            		int length = String.valueOf(suffix).length();
	            		int leadZero = maxLength-length;
	            		String suffixWithLeadingZeros = getLeadingZeros(leadZero)+suffix;
	                replaceSuffix(file,suffixWithLeadingZeros);
	            		}
	            		
	            	}
	            }
	  }
	 private static String getLeadingZeros(int leadZero) {
			// TODO Auto-generated method stub
			int i = 0;
			String leadingZeros = "";
	        while(i<leadZero){
	            leadingZeros = leadingZeros + "0";
	            i++;
	       }
	        return leadingZeros;
		}
	  
			private static int getHighestSuffixLength(File inputDirectory)  {
				int highest = new Integer("0");
				DoccleUtil doccleUtil = new DoccleUtil();
				//File directory = new File(inputDirectory);
				File[] fList = inputDirectory.listFiles();
				   for (File file : fList){
			            if (file.isFile()){
			            	if (doccleUtil.isPdf(file)) {
			            		int suffix = getSuffix(file.getName());
			            		if (suffix > highest) {highest = suffix;
			            		}
			            		
			            	}
			            }
				   }
				   int length = String.valueOf(highest).length();
				   return length;
			 }
			  private static int getSuffix(String fileName) {
				  int start = fileName.indexOf("pagina");
				  int end = fileName.indexOf(".pdf");
				  String suffix = fileName.substring(start+6,end);
				 
				  return Integer.parseInt(suffix);
			  }
			  private static void replaceSuffix(File file, String newSuffix) {
				  String fileName = file.getName();
				  int start = fileName.indexOf("pagina");
				  int end = fileName.indexOf(".pdf");
				  String suffix = fileName.substring(start,end);
				  String out = fileName.replaceFirst(suffix, "pagina"+newSuffix);
					//New File
					File newfile =new File("/Users/johanplatteau/pdfdocs/hifi/input/"+out);
					file.renameTo(newfile);
					System.out.println(newfile.getName());
			  }
			  private static String getFileExtension(File file) {
				    String name = file.getName();
				    try {
				        return name.substring(name.lastIndexOf(".") + 1);
				    } catch (Exception e) {
				        return "";
				    }
				}
			  private static String getSplitKey(iDocument idoc,DocumentType docType){ 
					return getReceiverParameter(idoc, docType);
					}
} 
