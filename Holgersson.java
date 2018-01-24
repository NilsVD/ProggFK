package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();	//Tidpunkt 1
		ArrayList<TextProcessor> list = new ArrayList<TextProcessor>();
		
		Scanner swScan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		
		while (swScan.hasNext()) {
			stopwords.add(swScan.next());
		}
		
		/*TextProcessor p1 = new SingleWordCounter("nils");
		TextProcessor p2 = new SingleWordCounter("norge");  
		
		TextProcessor p3 = new MultiWordCounter(REGIONS);*/
		TextProcessor p4 = new GeneralWordCounter(stopwords);
		
		//list.add(p1);
		//list.add(p2);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			/*p1.process(word);
			p2.process(word);
			p3.process(word);*/
			p4.process(word);
			
		}

		s.close();

		/*p1.report();
		p2.report();
		p3.report();*/
		p4.report();
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 +  " ms");
	}
}