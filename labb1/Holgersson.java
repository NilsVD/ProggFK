package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<TextProcessor> list = new ArrayList<TextProcessor>();
		
		
		TextProcessor p1 = new SingleWordCounter("nils");
		TextProcessor p2 = new SingleWordCounter("norge");  
		
		TextProcessor p3 = new MultiWordCounter(REGIONS);
		
		list.add(p1);
		list.add(p2);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			p1.process(word);
			p2.process(word);
			p3.process(word);
		}

		s.close();

		//p1.report();
		//p2.report();
		p3.report();
	}
}
