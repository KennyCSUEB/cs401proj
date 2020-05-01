package gamarket;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class EncounterGUI extends Client implements EventHandler<ActionEvent> {
    private Button move1, move2, move3, move4, exit;
    private Stage window;
    private boolean done;

    private Button fight, bag, pokemon, run, switchPokemon; 
    
    GridPane gp;
    HBox battleMenu;
    HBox fightMenu;
    private Text text;
    HBox textBox;
    static Pokemon wildPokemon;
    Pokemon playerPokemon;
    String wildPokePath, playerPokePath; 
    Encounter aEncounter; 
    String attackerStatus; 
    String input; 
    static boolean wasMove1Used;
    static boolean wasMove2Used; 
    static boolean wasMove3Used; 
    static boolean wasMove4Used; 
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    ImageView enemyPoke, playerPoke; 
    Image image1, image2; 
    Stage testWindow;
    Scene testScene; 
    boolean runStatus;

    public EncounterGUI(Pokemon wildPokemon, Pokemon playerPokemon, Encounter aEncounter, Stage window, Scene scene)
    {
        this.wildPokemon = wildPokemon;
        this.playerPokemon = playerPokemon;  
        this.aEncounter = aEncounter; 
        this.testWindow = window;
        this.testScene = scene; 
        runStatus = false; 
    }

    public GridPane display() {
        
       // window = new Stage();
       // window.setResizable(false);
        gp = new GridPane();
        //HBox hbox = new HBox();
        //hbox.setAlignment(Pos.BOTTOM_LEFT);
        //gp.setPadding(new Insets(0, 0, 50, 125));
        //gp.setPrefSize(1250, 750);
        
        Soundtrack.stopMusic();
       // Soundtrack.loadMusic("wild_music1.wav");
        Soundtrack.startMusic();

        battleMenu = new HBox();
        fightMenu = new HBox();
        textBox = new HBox();
        

        // Display the wild pokemon, temporary set-up, will use a function to set wildPokePath.  
        if(wildPokemon.getIdentStats().getName().equals("Flareon"))
        {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/0/0c/Flareon-AttackAnimation-XY-3.gif/revision/latest?cb=20160822140919";
        }
        else if (wildPokemon.getIdentStats().getName().equals("Poliwrath"))
        {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/0/03/Poliwrath_XY.gif/revision/latest?cb=20150201054011";
        }
        else if (wildPokemon.getIdentStats().getName().equals("Gengar"))
        {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/7/7c/Gengar_Shiny_XY.gif/revision/latest?cb=20140901010939";
        }
        else if (wildPokemon.getIdentStats().getName().equals("Pikachu"))
        {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/1/18/Pikachu-F_SS.gif/revision/latest?cb=20200107220953";
        }
        else if (wildPokemon.getIdentStats().getName().equals("Sandslash"))
        {
            wildPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/2/24/Sandslash_XY.gif/revision/latest?cb=20150201055633";
        }
        else {} // do nothing 
        
        // Display player's pokemon, temporary set-up, will use a function to set playerPokePath. 
        if(playerPokemon.getIdentStats().getName().equals("Flareon"))
        {
            playerPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/0/0c/Flareon-AttackAnimation-XY-3.gif/revision/latest?cb=20160822140919";
        }
        else if (playerPokemon.getIdentStats().getName().equals("Poliwrath"))
        {
            playerPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/3/3f/Poliwrath_BW.gif/revision/latest?cb=20120628004615";
        }
        else if (playerPokemon.getIdentStats().getName().equals("Gengar"))
        {
            playerPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/7/7c/Gengar_Shiny_XY.gif/revision/latest?cb=20140901010939";
        }
        else if (playerPokemon.getIdentStats().getName().equals("Pikachu"))
        {
            playerPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/1/18/Pikachu-F_SS.gif/revision/latest?cb=20200107220953";
        }
        else if (playerPokemon.getIdentStats().getName().equals("Sandslash"))
        {
            playerPokePath = "https://vignette.wikia.nocookie.net/pokemon/images/2/24/Sandslash_XY.gif/revision/latest?cb=20150201055633";
        }
        else {} // do nothing 
     
        // Background source
        File file = new File("C:\\Users\\etern\\Desktop\\background2.jpg");
        String path3 = file.toURI().toString();
        
      //  Image image = new Image(wildPokePath);
      //  Image image2 = new Image(playerPokePath);
       // image1 = new Image(wildPokePath);
        

      
        // Background image stuff
        Image image3 = new Image(path3);

        // Wild Pokemon image
        enemyPoke = new ImageView();
        enemyPoke.setImage(this.getImage(wildPokePath));
       
        // Player Pokemon image
        playerPoke = new ImageView();
        playerPoke.setImage(this.getImage(playerPokePath));

        gp.setAlignment(Pos.CENTER);
        gp.add(enemyPoke, 20, 0);
        gp.setHgap(30);
        gp.setVgap(10);
        gp.add(playerPoke, 6, 10);


        // background
        ImageView background = new ImageView(image3);
        enemyPoke.setFitHeight(475);
        enemyPoke.setFitWidth(475);
        playerPoke.setFitHeight(175);
        playerPoke.setFitWidth(175);
        background.setFitHeight(300);
        background.setFitWidth(300);

        
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);

        gp.setBackground(new Background(new BackgroundImage(image3,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        

        // BUttons
        fight = new Button("Fight");
        bag = new Button("Bag");
        pokemon = new Button("Pokemon");
        run = new Button("Run");

        // 
        fight.setOnAction(this::handle);
        bag.setOnAction(this::handle);
        pokemon.setOnAction(this::handle);
        run.setOnAction(this::handle);

        // Sync the Player's pokemon moves into the buttons. 
        move1 = new Button(playerPokemon.getMove(0).getMoveName());
        move1.setOnAction(this::handle);
        move2 = new Button(playerPokemon.getMove(1).getMoveName());
        move2.setOnAction(this::handle);
        move3 = new Button(playerPokemon.getMove(2).getMoveName());
        move3.setOnAction(this::handle);
        move4 = new Button(playerPokemon.getMove(3).getMoveName());
        move4.setOnAction(this::handle);


        battleMenu.getChildren().addAll(fight, bag, pokemon, run);
        fightMenu.getChildren().addAll(move1,move2,move3,move4);
        gp.add(battleMenu, 6, 12);
        gp.add(fightMenu, 6, 12);
      
        fightMenu.setVisible(false);
        choiceBox.setVisible(false);
        battleMenu.setVisible(true);
        
        switchPokemon = new Button("Switch");
        switchPokemon.setOnAction(e -> getChoice(choiceBox));
        gp.add(choiceBox, 6, 12);
        gp.add(switchPokemon, 5, 12);
        switchPokemon.setVisible(false);

        text = new Text();
        text.setText("A wild " + wildPokemon.getIdentStats().getName() + " appears!"
                     + "\nPlayer: Lets go " + playerPokemon.getIdentStats().getName() + "!");
        text.setFont(Font.font("Old-town fantasy", FontWeight.BOLD, FontPosture.REGULAR, 32));
        textBox.getChildren().add(text);
        textBox.setStyle("-fx-border-color: black;");
        gp.add(textBox, 6, 20);
      

        done = false;
        
        return gp; 
        
    }

    public Image getImage(String source)
    {
        Image img = new Image(source);
        return img;  
    }

    public void getChoice(ChoiceBox<String> choiceBox)
    {
       // text.setText(choiceBox.getValue());
       String imageSource; 
       if(choiceBox.getValue().equals("Pikachu"))
       {
            imageSource = "https://vignette.wikia.nocookie.net/pokemon/images/1/18/Pikachu-F_SS.gif/revision/latest?cb=20200107220953";
            playerPoke.setImage(this.getImage(imageSource));
       }
       else if(choiceBox.getValue().equals("Poliwrath"))
       {
            imageSource = "https://vignette.wikia.nocookie.net/pokemon/images/0/03/Poliwrath_XY.gif/revision/latest?cb=20150201054011";
            playerPoke.setImage(this.getImage(imageSource));
       }
       else if(choiceBox.getValue().equals("Sandslash"))
       {
            imageSource = "https://vignette.wikia.nocookie.net/pokemon/images/2/24/Sandslash_XY.gif/revision/latest?cb=20150201055633";
            playerPoke.setImage(this.getImage(imageSource));
       }
       else if(choiceBox.getValue().equals("Flareon"))
       {
            imageSource = "https://vignette.wikia.nocookie.net/pokemon/images/0/0c/Flareon-AttackAnimation-XY-3.gif/revision/latest?cb=20160822140919";
            playerPoke.setImage(this.getImage(imageSource));
       }
       else if(choiceBox.getValue().equals("Gengar"))
       {
            imageSource = "https://vignette.wikia.nocookie.net/pokemon/images/7/7c/Gengar_Shiny_XY.gif/revision/latest?cb=20140901010939";
            playerPoke.setImage(this.getImage(imageSource));
       }
       fightMenu.setVisible(true);
       choiceBox.setVisible(false);
       switchPokemon.setVisible(false);
    }

    @Override
    public void handle(ActionEvent event) {

        if(event.getSource() == fight)
        {
            battleMenu.setVisible(false);
            String bla = "Displaying " + playerPokemon.getIdentStats().getName() + "'s moves.";
            text.setText(bla + "\n" + aEncounter.getAttackStatus());
            fightMenu.setVisible(true);                     // display pokemon moves for fight input
            wasMove1Used = false; 
            wasMove2Used = false;
            wasMove3Used = false;
            wasMove4Used = false;  
        }   
        else if(event.getSource() == bag)
        {
            System.out.println("bag button clicked.");
            battleMenu.setVisible(false);
            fightMenu.setVisible(false);
            choiceBox.setVisible(false);
            text.setText(aEncounter.displayItems()
                         + "\n" + aEncounter.bag("1"));
            
            if(wildPokemon.getIdentStats().getName().equals(wildPokemon.getIdentStats().getName()))
            {
                battleMenu.setVisible(true);
            }


            // System.out.println(displayItems());
            // System.out.println(bag(getInput()));
        }
        else if(event.getSource() == pokemon)
        {
            System.out.println("pokemon button clicked.");
            battleMenu.setVisible(false);
            fightMenu.setVisible(false);
            choiceBox.setVisible(true);
            switchPokemon.setVisible(true);
            /*  this.thePlayer.getPokeTeam().displayTeam();
                    inputString = getInput();
                    this.attacker = false;
                    System.out.println(getAttackStatus());
                    System.out.println(switchPokemon(inputString));
                    System.out.println(displayPokemonMoves());
                    System.out.println(fight(getInput()));*/
            
            //  this.thePlayer.getPokeTeam().displayTeam();
            int teamSize = aEncounter.getPlayerTeam().getNumOfPokesInTeam();          
            if(teamSize >= 0) // temporary should be teamSize > 1
            {
                for(int i = 0; i < teamSize; i++) // temproary should be int i = 1 
                {
                    choiceBox.getItems().add(aEncounter.getPlayerTeam().getPokemonAtIndex(i).getIdentStats().getName());
                }
            }

            // this.attacker = false 
            aEncounter.setAttacker(false);

            // System.out.println(getAttackStatus());
            String temp = aEncounter.getAttackStatus();

            // System.out.println(switchPokemon(inputString));



        }
        else if(event.getSource() == run)
        {
            System.out.println("Run button clicked.");
            String runOutcome = aEncounter.run();
            text.setText(runOutcome);
            if(runOutcome.equals("Run away was unsuccessful")) {
                 text.setText(runOutcome + "\n" + aEncounter.getAttackStatus());
                 battleMenu.setVisible(false);
                 fightMenu.setVisible(true);
                 choiceBox.setVisible(false);
                 
            }  

            // if run was successful, exit the battle. 
            if(!(runOutcome.equals("Run away was unsuccessful")))
            {
               // code here to switch back into previous screen. 
                // testWindow.setScene(testScene);
               // testWindow.show();
             //  window.close();
            }
        }
        else {} // do nothing


        if(event.getSource() == move1)
        {
            // move 1 yea...
            System.out.println("Electro Ball clicked.");
            input = "0";
            wasMove1Used = true; 
            text.setText(aEncounter.fight("0"));
            

            if(aEncounter.getAttacker())
            {
                attackerStatus = " \n You are attacking.";
            }
            else 
            {
                attackerStatus = "You are defending.";
            }
         //   text.setText(aEncounter.fight(playerPokemon.getMove(0).getMoveName())
          //               + "\n" + attackerStatus);
            fightMenu.setVisible(false);
            battleMenu.setVisible(true);




            /*
            File file = new File("C:\\Users\\etern\\Desktop\\test.gif");
            String path = file.toURI().toString();
            Image image = new Image(path);
            ImageView iv = new ImageView(image);
            //iv.setBlendMode(BlendMode.SOFT_LIGHT);
            //iv.setBlendMode(BlendMode.);


            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(3));
            transition.setFromX(225);
            transition.setFromY(425);
            transition.setToX(885);
            transition.setToY(225);
            transition.setAutoReverse(true);
            transition.setNode(iv);
            transition.play();
            gp.getChildren().add(iv);

            //iv.setBlendMode(BlendMode.SCREEN);
            */

        }
        else if(event.getSource() == move2)
        {
            // move 2 yea...
            System.out.println("Thunder clicked.");
            input = "1";
            wasMove2Used = true; 

            text.setText(aEncounter.fight("1"));
            
            battleMenu.setVisible(true);
            fightMenu.setVisible(false);
          //  text.setText(aEncounter.fight(playerPokemon.getMove(1).getMoveName()));
        }
        else if(event.getSource() == move3)
        {
            // move 3 yea...
            System.out.println("Quick Attack clicked.");
            text.setText(aEncounter.fight("2"));
            
            battleMenu.setVisible(true);
            fightMenu.setVisible(false);
          //  text.setText(aEncounter.fight(playerPokemon.getMove(2).getMoveName()));
        }
        else if(event.getSource() == move4)
        {
            // move 4 yea...
            System.out.println("Slam clicked.");
            text.setText(aEncounter.fight("3"));
            
            battleMenu.setVisible(true);
            fightMenu.setVisible(false);
         //   text.setText(aEncounter.fight(playerPokemon.getMove(3).getMoveName()));
        }
        else {} // do nothing
    }

    public void displayMovesMenu()
    {
        battleMenu.setVisible(false);
        fightMenu.setVisible(true);
    }

    public void stText(String myText)
    {
        text.setText(myText);
    }

    public void setWildPokemon(Pokemon wildPoke) {
        wildPokemon = wildPoke; 
    }

    public boolean getDone() {
        return done;
    }

    public boolean getMove1UsedStatus()
    {
        return EncounterGUI.wasMove1Used;
    }

    public boolean getMove2UsedStatus()
    {
        return EncounterGUI.wasMove2Used;
    }
    public boolean getMove3UsedStatus()
    {
        return EncounterGUI.wasMove3Used;
    }
    public boolean getMove4UsedStatus()
    {
        return EncounterGUI.wasMove4Used;
    }
    public boolean getRunStatus()
    {
        return this.runStatus; 
    }
}