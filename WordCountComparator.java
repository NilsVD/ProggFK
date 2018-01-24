package textproc;
import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>>{

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		int n = 0;
		n = o2.getValue() - o1.getValue();
		if (n == 0) {
			n = o1.getKey().compareTo(o2.getKey());
		} 
		return n;
	}
}


