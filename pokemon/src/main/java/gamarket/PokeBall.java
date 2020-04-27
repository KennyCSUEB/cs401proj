package gamarket;

public class PokeBall extends Item {
    private double catchRate = 70;
    private String type = "Pokeball";
    public PokeBall () {
        this.description = "Use to catch Pokemon";
        this.cost = 1.50;
        this.type = "Pokeball";
    }

    public String getType () {
        return this.type;
    }
    public double getCatchRate () {
        return this.catchRate;
    }

    public boolean throwBall (Pokemon targetPokemon, Team playerTeam) {
        int catchVal = generateRandomInt(0, 100);
        if(catchVal < catchRate) {
            playerTeam.addPokemon(targetPokemon);
            return true;
        }
        else {
            return false;
        }
    }
    private int generateRandomInt (int min, int max) {
        return (int) (Math.random() * ((max-min))+min);
    }
}