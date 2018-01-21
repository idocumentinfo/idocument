package be.idocument;

public class BusinessTerm {
private String businessName;
private int termLength;
// type of the value
// number = numeric values
// text = string values
private String termType;
	public BusinessTerm(String name, int length, String type) {
		businessName = name;
		termLength = length;
		termType = type;
				
}
public String getName(){
 return businessName;
 }
public int getLength(){
	 return termLength;
	 }
public String getType(){
	 return termType;
	 }
}
