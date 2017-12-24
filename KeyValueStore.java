package be.idocument;
import java.util.ArrayList;


public class KeyValueStore {
	private ArrayList<KeyValue> list = new ArrayList<KeyValue>();
	public KeyValueStore() {
		
	}
	public void addKeyValue(String key, String value) {
		//if key is already in store add the value to the KeyValue
		Boolean keyFound = false;
		for(KeyValue kv : list) {
		    if (key.equals(kv.getKey())) {
			//System.out.println("We got a match "+kv.toString());
			kv.add(value);
			keyFound=true;
			break;}
		    else {}}
		//else make new KeyValue and add to the store
		if (keyFound == false ){
			//System.out.println("adding key value");
			KeyValue newKV = new KeyValue(key,value);
			list.add(newKV);
		}}
	
	public KeyValue getKeyValueForKey(String key) {
		for(KeyValue kv : list) {
		    if (key.equals(kv.getKey())) {
			//System.out.println("We got a match "+kv.toString());
		    	return kv;}
		}
		KeyValue emptyKv = new KeyValue("","");
		return emptyKv ;}
	
	
	public ArrayList<KeyValue> getAllKeyValues() {
		return list;
		}
}
	

