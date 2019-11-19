//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Program 6 HelloFX
// Files: Main.java
// Course: Fall 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Deppeler
// Lecture Number: 001
//
// Description of Program: This program implements graphs to manage packages with different
// operations/functions.
//
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main Class to run the java FX program
 * 
 * @author Jacob Brevard
 *
 */
public class Main extends Application {
  // Fields for the height and Width of the window
  private static final int WINDOW_WIDTH = 300;
  private static final int WINDOW_HEIGHT = 200;
  private static final String APP_TITLE = "Program 6 HelloFX";

  @Override
  /**
   * This method sets up the scene on the stage and then displays it.
   * 
   */
  public void start(Stage primaryStage) throws Exception {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();

    // Calls the methods to get the Top, Left, Right, Bottom, and Center Components and then sets
    // these in the pane
    root.setTop(getTopLabel(root));
    root.setBottom(getBottomButton(root));
    root.setLeft(getLeftComboBox(root));
    root.setRight(getRightColorPicker(root));
    root.setCenter(getCenterImage(root));

    // Creates a new scene with the specified width and height and uses the created border pane as
    // the template
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * This method formats and styles the item in for the top panel.
   * 
   * @param root - The passed in border pane
   * 
   * @return - The item for the pane
   */
  public Label getTopLabel(BorderPane root) {
    // Creates the new label
    Label topLabel = new Label("CS400 MyFirstJavaFX");
    topLabel.setPrefHeight(100);
    topLabel.prefWidthProperty().bind(root.widthProperty());

    // define max font size you need
    final double MAX_FONT_SIZE = 30.0;

    // set to Label
    topLabel.setFont(new Font(MAX_FONT_SIZE));

    topLabel.setStyle("-fx-border-style: dotted; -fx-border-width: 0 0 1 0; -fx-font-weigth:bold;");
    topLabel.setAlignment(Pos.BASELINE_CENTER);
    return topLabel;
  }

  /**
   * This method formats and styles the item in for the bottom panel.
   * 
   * @param root - The passed in border pane
   * 
   * @return - The item for the pane
   */
  public Button getBottomButton(BorderPane root) {
    // Create a vertical box for Right
    Button bottomButton = new Button("Done");
    bottomButton.setPrefHeight(60);
    bottomButton.prefWidthProperty().bind(root.widthProperty());
    bottomButton
        .setStyle("-fx-border-style: dotted; -fx-border-width: 0 0 1 0; -fx-font-weigth:bold;");
    bottomButton.setAlignment(Pos.BASELINE_CENTER);
    return bottomButton;
  }

  /**
   * This method formats and styles the item in for the left panel.
   * 
   * @param root - The passed in border pane
   * 
   * @return - The item for the pane
   */
  public ComboBox<String> getLeftComboBox(BorderPane root) {
    // Creates a combo box to select a fruit
    ComboBox<String> comboBox = new ComboBox<String>();
    comboBox.getItems().add("Apple");
    comboBox.getItems().add("Orange");
    comboBox.getItems().add("Mango");
    comboBox.getItems().add("Peach");
    comboBox.getItems().add("Pear");

    comboBox.setPrefWidth(100);
    comboBox.prefHeightProperty().bind(root.heightProperty().subtract(120));
    comboBox.setStyle("-fx-border-style: dotted; -fx-border-width: 0 0 1 0; -fx-font-weigth:bold;");
    return comboBox;
  }

  /**
   * This method formats and styles the item in for the center panel.
   * 
   * @param root - The passed in border pane
   * 
   * @return - The item for the pane
   */
  public ImageView getCenterImage(BorderPane root) {
    // Imports a photo of me
    FileInputStream input = null;
    try {
      input = new FileInputStream(
          "C:\\Users\\footb\\eclipse-workspace\\CS400 Program 6 HelloFX\\Jacob Brevard Professional Photo.jpg");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Image image = new Image(input);
    ImageView imageView = new ImageView(image);

    imageView.setFitHeight(450);
    imageView.setFitWidth(300);

    return imageView;
  }

  /**
   * This method formats and styles the item in for the right panel.
   * 
   * @param root - The passed in border pane
   * 
   * @return - The item for the pane
   */
  public ColorPicker getRightColorPicker(BorderPane root) {
    // Creates a combo box to select a fruit
    ColorPicker rightColorPicker = new ColorPicker();

    rightColorPicker.setPrefWidth(100);
    rightColorPicker.prefHeightProperty().bind(root.heightProperty().subtract(120));
    rightColorPicker
        .setStyle("-fx-border-style: dotted; -fx-border-width: 0 0 1 0; -fx-font-weigth:bold;");

    return rightColorPicker;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
