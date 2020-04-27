package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application implements Initializable {

    @FXML
    private ImageView imageview;


    public static void main(String args[]) {
        launch(args);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image i = new Image(new File("flareon.gif").toURI().toString());
        imageview.setImage(i);
    }



    @Override
    public void start(Stage stage) throws FileNotFoundException {
      /*
        //Creating an image
        Image image = new Image(new FileInputStream("C:\\Users\\etern\\Desktop\\flareon.gif"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 500);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    */
    }
}

