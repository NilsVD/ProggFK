package lab3;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;

public class BookReaderControl extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, 500,500);
		primaryStage.setTitle("Book reader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Scanner swScan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		
		while (swScan.hasNext()) {
			stopwords.add(swScan.next());
		}
		swScan.close();
		GeneralWordCounter wordcount = new GeneralWordCounter(stopwords);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			wordcount.process(word);
		}
		s.close();
		
		//Set<Map.Entry<String, Integer>> generalWords = wordcount.getWords();
		
		ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(wordcount.getWords());
		ListView<Map.Entry<String, Integer>> listView = new ListView<Map.Entry<String, Integer>>(words);
		
		root.setCenter(listView);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}