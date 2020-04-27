package gamarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.*;

public class Client extends Application {
    private StartMenuGUI startMenu;
    private Grid grid;
    private Player player;
    private GridPane root;
    private int width = 800;
    private int height = 800;
    private PokemonCollection pokeCollection;
    private MoveCollection moveCollection;
    private Stage window;
    private boolean paused;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    /**
     * start begins the application and is the main controller of it
     */
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Pokemon: East Bay");
        window.setResizable(false);
        startMenu = new StartMenuGUI();
        startMenu.display();
        Scene scene = new Scene(gameInterface(startMenu.getNewUser(), startMenu.getUsername(), startMenu.getPassword()));
        window.setScene(scene);
        paused = false;

        ArrayList<String> input = new ArrayList<String>();
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e){
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add( code );
                    }
                });
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e){
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });
        new AnimationTimer() {
            private long lastUpdate;
            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }
            public void handle(long currentNanoTime) {
                long elapsedNanoSeconds = currentNanoTime - lastUpdate;
                double elapsedSeconds = elapsedNanoSeconds / 1000000000.0;
                if(elapsedSeconds >= .1) {
                    lastUpdate = currentNanoTime;
                    if (input.contains("W")) {
                        updateGUI("w");
                        encounterCheck();
                        if(paused){
                            this.stop();
                        }
                    } else if (input.contains("A")) {
                        updateGUI("a");
                        encounterCheck();
                        if(paused){
                            this.stop();
                        }
                    } else if (input.contains("S")) {
                        updateGUI("s");
                        encounterCheck();
                        if(paused){
                            this.stop();
                        }
                    } else if (input.contains("D")) {
                        updateGUI("d");
                        encounterCheck();
                        if(paused){
                            this.stop();
                        }
                    }else if(input.contains("E")){
                        System.out.println("menu selected");
                    }
                }
            }
        }.start();



        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                save();
                window.close();
                System.exit(0);
            }
        });

        window.show();
    }

    /**
     * gameInterface loads up the appropriate grid depending on whether the player is new or returning,
     * instantiates the player class and loads in their new or saved information, and creates the GUI.
     * @param newPlayer lets gameInterface know whether we have a new player
     * @param username is used to instantiate the player
     * @param password is usded to instantiate the player
     * @return returns the GUI
     */
    public GridPane gameInterface(boolean newPlayer, String username, String password){
        loadCollections();

        if(!newPlayer){
            player = new Player(false, username, password);
            grid = new Grid();
            grid.loadData(startMenu.getUsername(), false);
        }else{
            player = new Player(true, username, password);
            grid = new Grid();
            grid.loadData("new",false);
        }

        root = new GridPane();
        root.setStyle("-fx-background-color: #a3a3a3;");
        root.setPrefSize(width, height);
        root.setHgap(0.0);
        root.setVgap(0.0);

        TileGUI tile;
        for (int y = 0; y < grid.getYMax(); y++) {
            for (int x = 0; x < grid.getXMax(); x++) {
                tile = new TileGUI(grid.getTile(x,y));
                root.add(tile, x, y);
            }
        }
        updateGUI("a");
        updateGUI("d");
        return root;

    }

    /**
     * save saves the current players data and grid
     */
    public void save(){
        player.saveData();
        grid.save(player.getName(), false);
    }

    public void displayTeam(){
        //TO-DO
    }

    public void loadCollections () {
        moveCollection = new MoveCollection();
        pokeCollection = new PokemonCollection(moveCollection);
    }
    public void encouter(){
        System.out.println("Pokemon encountered!");
        Encounter aEncounter = new Encounter(player, pokeCollection);
        EncounterGUI encounterGUI = new EncounterGUI();

        // begin the wild Pokemon encounter music.
        Soundtrack.stopMusic();                             // stop the previous music that was playing. 
        Soundtrack.loadMusic("wild_encounter.wav");
        Soundtrack.startMusic();                            // start the wild encounter music.

        if(!paused){
            paused = true;
            window.setScene(new Scene(encounterGUI.display()));
        }
        System.out.println("TEST");

        //aEncounter.battle();

        Soundtrack.stopMusic();                             // stop the Wild_Encounter music, since the battle is over.  
        Soundtrack.loadMusic("in_game1.wav");               // load in the previous music that was playing.
        Soundtrack.startMusic();                            // As the soundtrack files get bigger, probably will use two music variables 
                                                            // to keep track of previous and current. 
     }

    /**
     * encounterCheck checks the tile the player moved on.
     * If the tile is grass, checks the probablity of encountering a pokemon,
     * and calls the encounter method if a pokemon is encounterd.
     */
    private void encounterCheck(){
         Random random = new Random();
         int rand = random.nextInt(10);
         int rand2 = random.nextInt(10);

         Tile tile = grid.getTile(grid.getPlayerPosition()[0], grid.getPlayerPosition()[1]);

         if(tile.getType() == Tile.Type.GRASS){
              if(rand == rand2){
                 encouter();
             }
         }

    }

    /**
     * updatesGUI updates the player sprite location on the gui
     * @param direction lets the method know in which direection to move the sprite
     */
    private void updateGUI(String direction){
        int location = grid.getPlayerPosition()[0] + (grid.getPlayerPosition()[1] * grid.getYMax());
        TileGUI player = (TileGUI)root.getChildren().get(location);
        player.removePlayer();
        int x = grid.getPlayerPosition()[0];
        int y = grid.getPlayerPosition()[1];
        switch (direction){
            case "w":
                if(grid.canMove(x, y-1)) {
                    grid.updateGrid("w");
                    location -= grid.getXMax() ;
                }
                break;
            case "a":
                if(grid.canMove(x-1, y)) {
                    grid.updateGrid("a");
                    location--;
                }
                break;
            case "s":
                if(grid.canMove(x, y+1)) {
                    grid.updateGrid("s");
                    location += grid.getXMax();
                }
                break;
            case "d":
                if(grid.canMove(x+1, y)) {
                    grid.updateGrid("d");
                    location += 1;
                }
                break;
        }
        TileGUI playerNew = (TileGUI)root.getChildren().get(location);
        playerNew.renderPlayer();
    }

}