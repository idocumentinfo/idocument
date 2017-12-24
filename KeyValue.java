package be.idocument;
import java.util.ArrayList;
public class KeyValue {
	final private String storedKey; 
	final private ArrayList<String> storedValues=new ArrayList<String>();
	
	public KeyValue(String key, String value)  {
		storedKey = key;
		storedValues.add(value);
		
	}
	public String toString()
	{String output = storedKey+storedValues;
	return output;
	}
	public String getKey()
	{
	return storedKey;
	}
	public ArrayList<String> getStoredValuesKey()
	{
	return storedValues;
	}
	//adds a value to the key
	public void add(String value) {
	storedValues.add(value);}

}
