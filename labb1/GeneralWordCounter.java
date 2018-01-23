package textproc;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class GeneralWordCounter implements TextProcessor {	
	private Map<String,Integer> generalWordMap = new HashMap<String,Integer>();
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
		System.out.print("Ord som förekommer fler än 200 ggr: " + commonWords.toString());
	}
}
