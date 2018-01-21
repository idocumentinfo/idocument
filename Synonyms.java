package be.idocument;
import java.util.ArrayList;

public class Synonyms {
	private KeyValueStore kvs;
	public Synonyms() {
	kvs = new KeyValueStore();
	populate();
	}
	
	public ArrayList<String> getAllSynonyms(String input) {
    	
    KeyValue kv = kvs.getKeyValueForKey(input);
    return kv.getStoredValuesKey();
		}

	private void populate() {
		// TODO Auto-generated method stub
   
    	    kvs.addKeyValue("Rijksregisternummer", "Rijksregisternum");
     	kvs.addKeyValue("Rijksregisternummer", "Rijksregisternr");
     	kvs.addKeyValue("Rijksregisternummer", "Rijksregisternummer");
     	kvs.addKeyValue("Rijksregisternummer", "rijksregisternum");
     	kvs.addKeyValue("Rijksregisternummer", "rijksregisternr");
     	kvs.addKeyValue("Rijksregisternummer", "rijksregisternummer");
     	kvs.addKeyValue("Rijksregisternummer", "Nationaal Identificatienummer");
     	kvs.addKeyValue("Rijksregisternummer", "N° d'identification national");
     	kvs.addKeyValue("Rijksregisternummer", "Rijksregisternr");
     	kvs.addKeyValue("Rijksregisternummer", "RR/NN :");
     	kvs.addKeyValue("Rijksregisternummer", "RR/NN");
     	kvs.addKeyValue("Datum", "Datum :");
     	kvs.addKeyValue("Datum", "Date :");
     	kvs.addKeyValue("Maand van de berekening", "Maand v/d berek: ");
     	kvs.addKeyValue("Maand van de berekening", "Mois du calcul: ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE Nr. 281.10 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE N° 281.10 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE N° 281.17 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE Nr. 281.17 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE N° 281.20 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE Nr. 281.20 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE N° 281.18 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE Nr. 281.18 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE N° 281.13 - ");
     	kvs.addKeyValue("Maand van de berekening", "FICHE Nr. 281.13 - ");
     	kvs.addKeyValue("Maand van de berekening", "PREMIE ");
     	kvs.addKeyValue("Maand van de berekening", "ATTESTATION D’EMPLOI - ");
     	kvs.addKeyValue("Maand van de berekening", "PRIME ");
     	kvs.addKeyValue("Maand van de berekening","TEWERKSTELLINGSATTEST- ");
     	kvs.addKeyValue("Maand van de berekening","December ");
     	kvs.addKeyValue("Maand van de berekening","Décembre ");
     	kvs.addKeyValue("Maand van de berekening","VARIABLE PAY YEAR OF REFERENCE ");
     	kvs.addKeyValue("Jaar van de berekening", "van de premie ");
     	kvs.addKeyValue("Factuurnummer", "Factuurnummer ");
     	kvs.addKeyValue("Factuurnummer", "cbc:ID");
     	kvs.addKeyValue("Factuurnummer", "document_number");
     	kvs.addKeyValue("Factuurdatum", "Factuurdatum ");
     	kvs.addKeyValue("Factuurdatum", "cbc:IssueDate");
     	kvs.addKeyValue("Factuurdatum", "date_document");
     	kvs.addKeyValue("Vervaldatum", "Vervaldatum ");
     	kvs.addKeyValue("Vervaldatum", "cbc:PaymentDueDate");
     	kvs.addKeyValue("Vervaldatum", "planned_payment_date");
     	kvs.addKeyValue("Bestelbon", "Bestelbonnr: ");
     	kvs.addKeyValue("Bestelbon", "Bestelbonnummer ");
     	kvs.addKeyValue("Bestelbon", "Bestelbon: ");
     	kvs.addKeyValue("Bestelbon", "ponumber");
     	//kvs.addKeyValue("Totaal bedrag", "Totaal ");
     	kvs.addKeyValue("Totaal bedrag", "Totaal (excl. btw) ");
     	kvs.addKeyValue("Totaal incl BTW","inv_total_gross_amount");
     	kvs.addKeyValue("Totaal BTW","invoice_total_of_vat");
     	
     	kvs.addKeyValue("Factuurlijn", "detail");
     	kvs.addKeyValue("Klantnaam", "customer_name");
     	kvs.addKeyValue("Klantstraat", "adrs02");
     	kvs.addKeyValue("Klantpostcode","post02");
     	kvs.addKeyValue("Klantland","land02");
     	//kvs.addKeyValue("Klantid","");

     	

     	
     
     	

     	
     	
	}
}
