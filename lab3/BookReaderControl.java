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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;
import textproc.WordCountComparator;

public class BookReaderControl extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
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
		
		HBox hbox = new HBox();
		Button AlphButton = new Button ("Alphabetic");
		Button FreqButton = new Button ("Frequency");
		TextField search = new TextField("");
		search.setPromptText("Search...");
		Button FindButton = new Button ("Find");
		hbox.getChildren().addAll(AlphButton, FreqButton, search, FindButton);
		root.setBottom(hbox);
		Alert alert = new Alert(AlertType.WARNING, "The word can not be found");
		
		FreqButton.setOnAction(event -> words.sort((e1,e2) -> e2.getValue() - e1.getValue() ));
		AlphButton.setOnAction(event -> words.sort((e1,e2) -> e1.getKey().compareTo(e2.getKey()) ));
		FindButton.setOnAction(event -> {
			String searchWord = search.getText().toLowerCase().trim();
			for(int i=0 ; i<words.size();i++) {
				if(words.get(i).getKey().equals(searchWord)){
					listView.scrollTo(words.get(i));	
				//System.out.println("Hittad");
				} else if (i == words.size()-1) {
					alert.showAndWait();
				}
			}
		});
		search.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER) {
				FindButton.fire();
			}
		});

		root.setCenter(listView);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}