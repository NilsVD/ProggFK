package textproc;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

public class GeneralWordCounter implements TextProcessor {	
	private Map<String,Integer> generalWordMap = new TreeMap<String,Integer>();
	private Set<String> stopwords = new HashSet<String>();
	private Set<String> commonWords = new HashSet<String>();	//Mängd med ord som förekommer >200ggr
	
	public GeneralWordCounter(Set<String> stopwords) {
		this.stopwords = stopwords;
	}
	
	@Override
	public void process(String w) {
		if (generalWordMap.containsKey(w)) {
			generalWordMap.put(w, generalWordMap.get(w)+1);
			if(generalWordMap.get(w) >= 200) {
				commonWords.add(w);
			}
		} else if (!stopwords.contains(w)) {
			generalWordMap.put(w, 1);
		}
	}
	
	@Override
	public void report() {
		// Uppgift D7:
		//System.out.print("Ord som förekommer fler än 200 ggr: " + commonWords.toString());
		Set<Map.Entry<String, Integer>> wordset = generalWordMap.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordset);
		wordList.sort(new WordCountComparator());
		for(int i = 0; i < 5; i++) {
			System.out.println(wordList.get(i).toString() + " ");
		}
 		
	}public Set<Map.Entry<String, Integer>> getWords(){
		return generalWordMap.entrySet();
	}
	
	
}
