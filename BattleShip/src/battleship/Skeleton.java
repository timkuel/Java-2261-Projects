package battleship;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class Skeleton extends Application{
	
	
	@Override
	public void start(Stage primaryStage) {
		//put borderpane in scene, and put scene in a stage
		Scene scene = new Scene(getBPane());
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}
	
	/*
	 * Method that creates a border pane, puts hbox at top, vbox at left
	 * and gpane in center
	 */
	private BorderPane getBPane() {
		BorderPane bPane = new BorderPane();
				
		bPane.setTop(getHBox());
		bPane.setLeft(getVBox());
		bPane.setCenter(getGPane());
		
		return bPane;
	}
	
	/*
	 * Creates an hBox
	 */
	private HBox getHBox(){
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(10,10,10,10));
		
		Label text = new Label("Sink My Battleships");
		text.setTextFill(Color.web("blue"));
		
		hBox.setBackground(new Background(new BackgroundImage(new Image("http://www.howmanypeopledied.net/wp-content/uploads/2010/10/Bismark-World-War-II-11.jpg", 100, 50, true,true), BackgroundRepeat.REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, 
				BackgroundSize.DEFAULT)));
		
		hBox.getChildren().add(text);
		
		return hBox;
	}
	
	/*
	 * Creates vBox
	 */
	private VBox getVBox() {
		int hits = 0, miss = 0, sunk = 0;
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets (10,10,10,10));
		
		Label text = new Label("Hits:\n" + hits + "\n\nMisses:\n" + miss + "\n\nSunk:\n" + sunk);
		text.setTextFill(Color.web("white"));
		
		vBox.setBackground(new Background(new BackgroundImage(new Image("http://static3.businessinsider.com/image/51eeb94a69bedd831300000f-1200/the-seasparrow-missile-also-factors-into-lethality-with-its-ability-to-fly-four-times-the-speed-of-sound-turn-on-a-dime-and-intercept-anti-ship-missiles-more-than-30-miles-out.jpg", 80, 200, true,true), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, 
				BackgroundSize.DEFAULT)));
		
		vBox.getChildren().add(text);
		
		return vBox;
	}
	
	/*
	 * Creates 10x10 Gridpane of buttons
	 */
	private GridPane getGPane() {
		int numRows = 10, numCols = 10;
		Integer buttonClick = 0;
		GridPane gPane = new GridPane();
		
		//Sets the row constraints
		for(int row = 0; row < numRows; row ++) {
			RowConstraints rc = new RowConstraints();
			rc.setVgrow(Priority.ALWAYS);
			gPane.getRowConstraints().add(rc);
		}
		
		//Sets the column constraints
		for(int col = 0; col < numCols; col++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHgrow(Priority.ALWAYS);
			gPane.getColumnConstraints().add(cc);
		}
		
		gPane.setBackground(new Background(new BackgroundImage(new Image("http://www.abovephotography.com.au/Earth's-Artwork/originals/EarthArt-801503.jpg"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, 
				BackgroundSize.DEFAULT)));
		
		Button bt;
		
		
		//Populating the gpane
		for(int i = 0; i < numCols; i++) {
			for(int j = 0; j < numRows; j++) {
				bt = new Button(buttonClick.toString());
				bt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				
				gPane.add(bt, i, j);
			}
		}
		
		
		return gPane;
	}
}
