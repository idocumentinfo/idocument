package be.idocument;

public class Synonym {
	private static String word;
	private String synonym;
	public Synonym (String word1, String word2) {
		setWord(word1);
		synonym = word2;
}
	public String getSynonym () {
		return synonym;
	}
	public static String getWord() {
		return word;
	}
	private static void setWord(String word) {
		Synonym.word = word;
	}
}
