
package gamarket;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage window;
    private Scene mainScene;
    protected static SceneController sceneController;
    private String activeScene = "";
    protected SceneController(){}

    public static synchronized SceneController getInstance(Stage window){
        if(sceneController == null){
            sceneController = new SceneController();
            sceneController.setWindow(window);
            sceneController.setMainScene(window);
        }
        return sceneController;
    }

    public void setMainScene(Stage window) {
        this.mainScene = window.getScene();
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public void trainerCardScene(Player player){
        this.activeScene = "trainerCard";
        TrainerCard card = TrainerCard.getInstance();
        card.setSceneController(window);
        card.setPlayer(player);
        Scene scene = new Scene(card.display());

        this.window.setScene(scene);
        this.window.show();
    }

    public void returnScene(){
        this.activeScene = "main";
        this.window.setScene(mainScene);
        this.window.show();
    }
    public String getActiveScene () {
        return this.activeScene;
    }

    public void encounterScene(Pokemon wildPoke, Pokemon playerPoke, Encounter aEncounter){
        this.activeScene = "encounter";
        EncounterGUI encounterGUI = EncounterGUI.getInstance();
        encounterGUI.setSceneController(this.window);
        encounterGUI.setData(wildPoke, playerPoke, aEncounter);
        Scene scene = new Scene(encounterGUI.display());
        this.window.setScene(scene);
        this.window.setFullScreen(true);
        this.window.show();

    }

    public void poketeamScene(Team team, Bag bag, String... sceneType){
        PoketeamGUI poketeamGUI = PoketeamGUI.getInstance();
        poketeamGUI.setSceneController(this.window);
        poketeamGUI.setTeam(team);
        poketeamGUI.setBag(bag);
        Scene scene;
        if(sceneType.length > 0){
            team.getPokemonAtIndex(0).getDefensiveStats().setHPCurrent(200);
            scene = new Scene(poketeamGUI.display(sceneType));
        }else{
            scene = new Scene(poketeamGUI.display());
        }


        this.window.setScene(scene);
        this.window.show();
    }

    public void bagScene(Bag bag, Team team){
        BagGUI bagGUI = BagGUI.getInstance();
        bagGUI.setSceneController(this.window);
        bagGUI.setBag(bag);
        bagGUI.setTeam(team);
        Scene scene = new Scene(bagGUI.display());

        this.window.setScene(scene);
        this.window.show();

    }
}
