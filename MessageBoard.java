import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
 *@author Adib Hasan
 *@version 1.8.0
 */
public class MessageBoard extends Application {

    private ObservableList<String> doList = FXCollections.observableArrayList();
    private ListView<String> root = new ListView<String>(doList);
    private int i = 1, j = 1;
    private Button submit = new Button("Submit");
    private Button remove = new Button("Remove");
    private TextArea message = new TextArea();
    private TextField name = new TextField();
    private Label label1 = new Label("Either name or message is missing!");
    private Label label2 = new Label("Please Try Again.");
    private Label label3 = new Label();
    private Stage stage;
    private Label label4 = new Label();
    private Label label5 = new Label("Total messages: 0");
    private Label label6 = new Label("Removed messages: 0");
    private Label label7 = new Label();
    private Image image = new Image("Stop.jpg");
    private Image image1 = new Image("where.gif");
    private Button cancel = new Button("Cancel");
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        BorderPane pane = new BorderPane();
        pane.setCenter(getVBox());
        pane.setBottom(getHBox());
        //Event-handler of "Submit" button in lambda expression
        submit.setOnAction(e -> {
            if (name.getText() != null && !name.getText().isEmpty()
                && message.getText() != null && !message.getText().isEmpty()) {
                label5.setText("Total messages: " + i);
                i++;
                String s = "Adib's CS1331 Messenge Board" + " " + label5.getText() + " " + label6.getText();
                setStageTitle(s);
                doList.add(0, "\n" + label4.getText() + "\n" + "\n" + name.getText() + " : " + message.getText());
                name.clear();
                message.clear();
            } else {
                Stage newStage = new Stage();
                newStage.getIcons().add(new Image("Logo.png"));
                newStage.setTitle("Warning!");
                newStage.setScene(new Scene(getNewVBox(), 400, 300));
                newStage.show();
            }
        });
        //Event-handler of "Remove" button in lambda expression
        remove.setOnAction(e -> {
            int index = root.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                root.getItems().remove(index);
                label6.setText("Removed messages: " + j);
                j++;
                String q = "Adib's CS1331 Messenge Board" + " " + label5.getText() + " " + label6.getText();
                setStageTitle(q);
            }
        });
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 800, 800);
        primaryStage.getIcons().add(new Image("Logo.png"));
        primaryStage.setTitle("Adib's CS1331 Messenge Board" + " " + label5.getText() + " " + label6.getText());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     This method gives HBox pane for the Scene.
     @return HBox
     */
    public HBox getHBox() {
        HBox hBox = new HBox(15);
        hBox.setPrefWidth(800);
        hBox.setPrefHeight(250);
        hBox.setAlignment(Pos.CENTER);
        name.setPromptText("Enter your name.");
        name.setPrefColumnCount(15);
        message.setPrefColumnCount(15);
        message.setPromptText("Enter your message.");
        message.setMaxSize(400, 20);
        message.setWrapText(true);
        Label name1 = new Label("Name:");
        Label message1 = new Label("Message:");
        name1.setStyle("-fx-font-weight: bold; -fx-text-fill: red; -fx-font-size: 15");
        message1.setStyle("-fx-font-weight: bold; -fx-text-fill: red; -fx-font-size: 15");
        hBox.setStyle("-fx-background-color: beige");
        hBox.setStyle("-fx-background-image: url('logo2.png');-fx-background-size: 800 240");
        hBox.getChildren().addAll(name1, name, message1, message, submit, remove);
        return hBox;
    }
    /**
     This method gives VBox pane for the Scene.
     @return VBox
     */
    public VBox getVBox() {
        root.setPrefWidth(400);
        root.setPrefHeight(600);
        VBox vBox = new VBox(root);
        vBox.setPrefWidth(400);
        vBox.setPrefHeight(500);
        vBox.setStyle("-fx-background-color: BEIGE");
        root.setCellFactory((ListView<String> l) -> new ColorCell());
        label4.setText(timeStamp());
        vBox.setPadding(new Insets(15, 5, 5, 5));
        return vBox;
    }
   /**
    This method gives time information of the sent message.
    @return time as String format
    */
    public String timeStamp() {
        String at = "Time:";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeOnly = new SimpleDateFormat("HH:mm:ss");
        return String.format("%s %4s", at, timeOnly.format(cal.getTime()));
    }
    /**
     This method gives VBox pane for second Scene.
     @return VBox
     */
    public VBox getNewVBox() {
        VBox newVBox = new VBox(20);
        newVBox.setStyle("-fx-background-color: BEIGE");
        //newVBox.setPadding(new Insets(15, 5, 5, 5));
        newVBox.setAlignment(Pos.CENTER);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        label3.setGraphic(imageView);
        newVBox.getChildren().addAll(label3, new Label("", getNewHBox()), label2);
        return newVBox;
    }
   /**
    This method gives VBox pane for second Scene.
    @return VBox
    */
    public HBox getNewHBox() {
        HBox newHBox = new HBox(10);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(20);
        imageView1.setFitWidth(20);
        label7.setGraphic(imageView1);
        newHBox.getChildren().addAll(label1, label7);
        return newHBox;
    }
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support.
     *@param args as argument
     */
    public static void main(String[] args) {
        launch(args);
    }
   /**
    This static class makes the color for Listview items.
    */
    static class ColorCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setStyle(null);
            } else {
                setText(item);
                setStyle(getIndex() % 2 == 0
                    ? "-fx-text-fill: blue; -fx-font-size: 12; -fx-font-style: italic"
                    : "-fx-text-fill: red;-fx-font-size: 12; -fx-font-style: italic");
            }
        }
    }
   /**
    This method makes new title for the Stage.
    @param newTitle as a String
    */
    public void setStageTitle(String newTitle) {
        stage.setTitle(newTitle);
    }
}

