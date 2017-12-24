package be.idocument;

public class BusinessTerm {
private String businessName;
private int termLength;
// type of the value
// number = numeric values
// text = string values
private String termType;
	public BusinessTerm(String name, int length, String type) {
		if (name.compareTo("Rijksregisternummer") ==0)
		{businessName = name;
		termLength = length;
		termType = type;
		}
	    if (name.compareTo("Datum") ==0)
		{businessName = name;
		termLength = length;
		termType = type;
		}
		if (name.compareTo("Maand van de berekening") ==0)
		{businessName = name;
		termLength = length;
		termType = type;
	    }
		if (name.compareTo("Factuurnummer") ==0)
		{businessName = name;
		termLength = length;
		termType = type;
		}		
		if (name.compareTo("Factuurdatum") ==0)
		{businessName = name;
		termLength = length;
		termType = type;
		}		
		if (name.compareTo("Vervaldatum") ==0)
		{businessName = name;
		termLength = length;
		termType = type;
		}
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
