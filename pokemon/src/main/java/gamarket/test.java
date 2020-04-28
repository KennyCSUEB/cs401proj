package gamarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class test extends Application {
    private int w = 800;
    private int h = 800;
    private tileTest grid[][] = new tileTest[50][50];
    private int size = w/20;

    private GridPane createContent(){
        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #a3a3a3;");
        root.setPrefSize(w,h);

        root.setHgap(1.0);
        root.setVgap(1.0);

        tileTest tile;

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                tile = new tileTest(x, y);
                System.out.println(tile.toString());
                grid[x][y] = tile;
                root.add(tile.getIv(), x, y);
                // gc.drawImage(grid[x][x].border);
            }
        }
        return root;
    }

     class tileTest extends StackPane {
      //  private int x, y;
        //private Rectangle border = new Rectangle(size, size);
        private ImageView iv;

        public tileTest(int x, int y) {
            /*
            this.x = x;
            this.y = y;
            */

            File file = new File("./pokemon/imgs/grass.png");
            Image image = new Image(file.toURI().toString());
            iv = new ImageView(image);

            iv.setFitHeight(size);
            iv.setFitWidth(size);
            getChildren().addAll(iv);

        }

        public ImageView getIv(){
            return iv;
        }

    }

    class playerTest extends StackPane{
        private ImageView iv;
        private int x;
        private int y;

        public playerTest(int x, int y) {
            this.x = x;
            this.y = y;

            File file = new File("./pokemon/imgs/player.png");
            Image image = new Image(file.toURI().toString());
            iv = new ImageView(image);

            iv.setFitHeight(size);
            iv.setFitWidth(size);
            getChildren().addAll(iv);

        }

        public ImageView getIv(){
            return iv;
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pokemon East Bay");
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas

                if (input.contains("W")) {

                } else if(input.contains("A")) {


                }else if (input.contains("S")) {

                }else if(input.contains("D")) {

                }
            }
        }.start();



        primaryStage.show();

        //Pane root = new Pane();
        //Canvas canvas = new Canvas(w,h);
        //root.getChildren().addAll(canvas);

        //this right here actually draws the shits
        /*
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill( Color.RED );
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        gc.fillText( "Hello, World!", 60, 50 );
        gc.strokeText( "Hello, World!", 60, 50 );
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.PINK);
        gc.fillRect(100,100,300,300);
        */

    }

    /*
    class Tile  extends StackPane {
        private boolean isPermeable;
        private Rectangle x = new Rectangle(20,20);;

        /**
         * note if new type is added make changes to toString and setPermeable

        public enum Type {
            GRASS, ROAD, HOUSE, TREE, CUTTABLE_TREE, WATER, UNSURFABLE, WHIRLPOOL, UNRECOGNIZED
        }

        private Type tileType;

        Tile(Type tType){
            this.tileType = tType;
            setPermeable(tType);
            switch(tileType){
                case ROAD:
                    this.x.setFill(Color.GRAY);
                    break;
                case GRASS:
                    this.x.setFill(Color.GREEN);
                    break;
                case WATER:
                    this.x.setFill(Color.BLUE);
                    break;
                case UNRECOGNIZED:
                    this.x.setFill(Color.PINK);
                    break;
                default:
                    this.x.setFill(Color.YELLOW);
                    break;
            }
            x.setX(1);

        }
    */



}
