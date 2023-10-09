package views_controllers;

/**
 * Play TicTacToe the computer that can have different AIs to beat you. 
 * Select the Options menus to begin a new game, switch strategies for 
 * the computer player (BOT or AI), and to switch between the two views.
 * 
 * This class represents an event-driven program with a graphical user 
 * interface as a controller between the view and the model. It has 
 * event handlers to mediate between the view and the model.
 * 
 * This controller employs the Observer design pattern that updates two 
 * views every time the state of the Tic Tac Toe game changes:
 * 
 *  1) whenever you make a move by clicking a button or an area of either view
 *  2) whenever the computer AI makes a move
 *  3) whenever there is a win or a tie
 *    
 * You can also select two different strategies to play against from the menus
 * 
 * @author Rick Mercer and YOUR NAME
 */
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.IntermediateAI;
import model.OurObserver;
import model.RandomAI;
import model.TicTacToeGame;
import model.TicTacToeStrategy;

public class TicTacToeGUI extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private TicTacToeGame theGame;

  private OurObserver currentView;
  private OurObserver textAreaView;
  private OurObserver buttonView;
  private OurObserver drawingView;
  private String lastView = "button";
  
  private TicTacToeStrategy currentStrategy = new RandomAI();
  // TBA:
  // private OurObserver buttonView;
  // private OurObserver drawingView;

  private BorderPane window;
  public static final int width = 254;
  public static final int height = 360;
  private MenuBar menuBar = new MenuBar();

  public void start(Stage stage) {
    stage.setTitle("Tic Tac Toe");
    window = new BorderPane();
    Scene scene = new Scene(window, width, height);
    initializeGameForTheFirstTime();
    createMenuBar();
   
    
    
    
    window.setTop(menuBar);
    

  
    textAreaView = new TextAreaView(theGame);
    buttonView = new ButtonView(theGame);
    drawingView = new DrawingView(theGame);
    theGame.addObserver(textAreaView);
    theGame.addObserver(buttonView);
    theGame.addObserver(drawingView);
    setViewTo(buttonView);
    stage.setScene(scene);
    stage.show();
  }
  
  public void newGame() {
	  
	  theGame = new TicTacToeGame();
	  theGame.setComputerPlayerStrategy(currentStrategy);
	  
	  textAreaView = new TextAreaView(theGame);
	  buttonView = new ButtonView(theGame);
	  drawingView = new DrawingView(theGame);
	  
	  theGame.addObserver(textAreaView);
      theGame.addObserver(buttonView);
	  theGame.addObserver(drawingView);
	  
	  if (lastView.equals("text")) {
		  setViewTo(textAreaView);
	  }
	  else if (lastView.equals("button")) {
		  setViewTo(buttonView);
	  }
	  else if (lastView.equals("drawing")) {
		  setViewTo(drawingView);
	  }
	  

	  
  }

  public void createMenuBar() {
	  Menu options = new Menu("Options");

      // Create menu items for the options menu
      MenuItem newGame = new Menu("New Game");
      
      Menu strategies = new Menu("Strategies");
      MenuItem randomStrategy= new MenuItem("Random");
      MenuItem intermediateStrategy = new MenuItem("Intermediate");
      
      
      Menu views = new Menu("Views");
      MenuItem buttonViewItem = new MenuItem("Button View");
      MenuItem textViewItem = new MenuItem("Text View");
      MenuItem drawViewItem = new MenuItem("Draw View");

      // Add menu items to their respective menus
      strategies.getItems().addAll(randomStrategy, intermediateStrategy);
      views.getItems().addAll(buttonViewItem, textViewItem, drawViewItem);

      options.getItems().addAll(newGame, strategies, views);
      
      menuBar.getMenus().addAll(options);
      
      newGame.setOnAction(e -> {
          newGame();
       });
      
      randomStrategy.setOnAction(e -> {
    	 currentStrategy = new RandomAI();
    	 theGame.computerPlayer.setStrategy(new RandomAI());
      });
      
      intermediateStrategy.setOnAction(e -> {
    	  currentStrategy = new IntermediateAI();
          theGame.computerPlayer.setStrategy(new IntermediateAI());
       });
      
      
      buttonViewItem.setOnAction(e -> {
    	  lastView = "button";
          setViewTo(buttonView);
       });
      
      drawViewItem.setOnAction(e -> {
    	  lastView = "drawing";
          setViewTo(drawingView);
       });
      
      textViewItem.setOnAction(e -> {
    	  lastView = "text";
          setViewTo(textAreaView);
       });
      
      

      
  }
  /**
   * Set the game to the default of an empty board and the random AI.
   */
  public void initializeGameForTheFirstTime() {
    theGame = new TicTacToeGame();
    // This event driven program will always have
    // a computer player who takes the second turn
    
    
    theGame.setComputerPlayerStrategy(currentStrategy);
  }

  private void setViewTo(OurObserver newView) {
    window.setCenter(null);
    currentView = newView;
    window.setCenter((Node) currentView);
  }

}