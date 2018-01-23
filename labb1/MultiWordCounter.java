package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
	private Map<String,Integer> wordmap = new HashMap<String,Integer>();
	
	
	
	public MultiWordCounter(String[] words){
		int n = words.length;
		for(int i=0; i<n ; i++ ){
			wordmap.put(words[i], 0);
			
		}
	}

	@Override
	public void process(String w) {
		if (wordmap.containsKey(w)) {
				wordmap.put(w, wordmap.get(w)+1);				
			}
		}
		
	

	@Override
	public void  report() {
		System.out.println(wordmap.toString());
		
		
	}
	
	
	
	
	
	
	

}
