package gamarket;

import java.util.Scanner;

/**
 * Encounter class represents a battle between player and wild pokemon
 */
public class Encounter {
    private Pokemon wildPokemon;
    private Pokemon activePlayerPokemon;
    private boolean battling;
    private boolean attacker;
    private Player thePlayer; 
    private PokemonCollection collection;
    private Scanner scan = new Scanner(System.in);
    
    /**
     * Encounter generates arandom pokemon from the Pokemon collection and 
     * takes information from the player class
     * @param player Loaded player provided by the client
     * @param pc Pokemon Collection Passed by the client
     */
    Encounter (Player player, PokemonCollection pc) {
        this.collection = pc;
        this.thePlayer = player;
        this.activePlayerPokemon = getPlayerActivePokemon();
        this.wildPokemon = generateWildPokemon();
        this.battling = true; 
        
        this.attacker = true;
    }
    /**
     * Gets the first non-fainted pokemon from the players team
     * @return The first active Pokemon
     */
    private Pokemon getPlayerActivePokemon() {
        return this.thePlayer.getPokeTeam().getActivePokemon();
    }

    /**
     * Generates a Pokemon at random from the Pokemon Collection
     * @return Randomly generated Pokemon
     */
    private Pokemon generateWildPokemon() {
        int randomInt =  generateRandomInt(0,  this.collection.getNumPokes() );
        Pokemon generated =  PokemonCollection.getPokemonAtIndex(randomInt);
        return generated;
    }

    /**
     * Currently runs the encounter will have to be replaced when we go
     * to GUI
     */
    public void battle() {
        System.out.println("battling a wild " + this.wildPokemon.getIdentStats().getName());
        System.out.println("go " + this.activePlayerPokemon.getIdentStats().getName());
        String inputString;
        while(battling) {
            System.out.println("Type fight, bag, pokemon, or run");
            inputString = "";
            inputString = getInput();
            switch(inputString) {
                case  "fight" :
                    System.out.println(getAttackStatus());
                    System.out.println(displayPokemonMoves());
                    inputString = getInput();
                    System.out.println(fight(inputString));
                    System.out.println(this.attacker ? " \n You are attacking":"You are deffending");
                    System.out.println(displayPokemonMoves());
                    inputString = getInput();
                    System.out.println(fight(inputString));
                    break;
                case "bag" :
                    System.out.println(displayItems());
                    System.out.println(bag(getInput()));
                    break;
                case "pokemon":
                    this.thePlayer.getPokeTeam().displayTeam();
                    inputString = getInput();
                    this.attacker = false;
                    System.out.println(getAttackStatus());
                    System.out.println(switchPokemon(inputString));
                    System.out.println(displayPokemonMoves());
                    System.out.println(fight(getInput()));
                    break;
                case "run":
                    String runOutcome =  run();
                    System.out.println(runOutcome);
                    if(runOutcome.equals("Run away was unsuccessful")) {
                        this.attacker = false;
                        System.out.println(getAttackStatus());
                        System.out.println(displayPokemonMoves());
                        System.out.println(fight(getInput()));
                    }
                    break;
                case "b":
                    break;
                default:

            }
        }
    }
    /**
     * @return String of whether the player is attacking 
     */
    public String getAttackStatus () {
        return this.attacker ? " \n You are attacking":"You are deffending";
    }
    /**
     * handles when the player chooses the fight option
     * displays the active pokemon's move 
     * then asks for the player move
     * then resolves the attack
     */
    public String fight(String inputString) {
        String result = "";//this.attacker ? " \n You are attacking":"You are deffending";
        //System.out.println(result);
        // result += "\n" + 
        // String inputString = getInput();
        if(inputString.equals("b")) {
            return "returning to main menu"; 
        }
        int moveNum = Integer.parseInt(inputString);
        if(moveNum > 3) {
            return fight(getInput());
        }
        Move attackMove;
        Move deffMove;
        Pokemon attacker;
        Pokemon deffender;
        if(this.attacker) {
            attacker = this.activePlayerPokemon;
            deffender = this.wildPokemon;
            attackMove = attacker.getMove(moveNum); 
            deffMove =  deffender.getMove(generateRandomInt(0, 3));
        }
        else { 
            attacker = this.wildPokemon;
            deffender = this.activePlayerPokemon;
            attackMove = attacker.getMove(generateRandomInt(0, 3));
            deffMove =  deffender.getMove(moveNum);
        }
        
        
        result += resolveAttack(attackMove, deffMove, attacker, deffender);
        if(deffender.hasPokemonFainted()) {
            result += fainted();
        }

        this.attacker = !this.attacker;
        return result;
    }

    /**
     * Makes a string of the pokemon moves 
     * @return String tells active pokemon's name 
     * then their moves. Each move is separated by a \n
     */
    public String displayPokemonMoves() {
        String result = "";
        result += "Displaying " + this.activePlayerPokemon.getIdentStats().getName() + "'s moves \n";
        result += this.activePlayerPokemon.printPokemonMoves();
        return result;
    }

    /**
     * Handels what happens from an attack. Makes changes to the pokemon and 
     * return a string of the results
     * @param attackMove Move done by the attacking pokemon
     * @param defMove    Move done by the deffending pokemon
     * @param attacker   Attacking Pokemon using attack stats for damage calc
     * @param defender   Defender Pokemon will use the deff stats for the damage
     *                   calc
     * @return string telling the outcome of the round
     */
    private String resolveAttack(Move attackMove, Move defMove, Pokemon attacker , Pokemon defender) {
        
        String resultString = attacker.getIdentStats().getName() + " attacks with " + 
        attackMove.getMoveName() + ". " + defender.getIdentStats().getName() + " defends itself with " + defMove.getMoveName() +". \n";
        int damage;
        int attackVal;
        int defVal;
        if(attackMove.getMoveTypeCat().equals("physical")) {
            attackVal = attackMove.getMoveDamage() + attacker.getOffeniveStats().getATK();
        }
        else {
            attackVal = attackMove.getMoveDamage() + attacker.getOffeniveStats().getSPATK();
        }
        if(defMove.getMoveTypeCat().equals("physical")) {
            defVal = defMove.getMoveDamage() + defender.getDefensiveStats().getDEF();
        }
        else {
            defVal = defMove.getMoveDamage() + defender.getDefensiveStats().getSPDEF();
        }
        damage = attackVal - defVal;

        if(damage > 0){
            defender.getDefensiveStats().takeDamage(damage);
        }
        else {
            damage = 0;
        }
        
        resultString += "attack hit for "  + Integer.toString(damage);
        
        resultString += "\n" + defender.getIdentStats().getName() + " " + Integer.toString(defender.getDefensiveStats().getHPCurrent()) + " hp";
        
        return resultString;
    }

    /**
     * Handles when player or wild pokemon faints
     * @return returns a string of outcome
     */
    public String fainted () {
        String result = "";
        if(this.wildPokemon.hasPokemonFainted()) {
            result =  this.wildPokemon.getIdentStats().getName() + "has fainted";
            this.battling = false;
        }
        if(this.activePlayerPokemon.hasPokemonFainted()) {
            result = this.activePlayerPokemon.getIdentStats().getName() + "has fainted.";
            if(this.thePlayer.getPokeTeam().hasActivePokemon()) {
                result += switchPokemon(getInput());
            }
            else {
                result += "out of pokemon.";
                this.battling = false;
                return result;
            }
            
        }
        return result;
    }

    /**
     * handles when player selects bag option 
     * right now displays fake bag
     * and is hard coded to throw a pokeball
     * TODO IMPLEMENT player.bag
     */
    public String bag(String inputString) {
        if(inputString.equals("b")) {
            return "retruning to main menu"; 
        }
        Item ball = new PokeBall();
        
        return useItem(ball);
    }
    /**
     * displays items in players bag
     */
    private String displayItems() {
        //Bag playerBag this.player.getBag(); TODO make getBag Function
        //String bagString = playerBag.toString || itemsString();
        String result = "";
        String bagString =  "PokeBall 9, Potion 3";
        String[] itemStrings = bagString.split(",");
        for(int index = 0; index < itemStrings.length; index++) {
            result += index + " " + itemStrings[index];
        }
        return result;
    }
    /**
     * asks the player for console input 
     * TODO REPLACE THESE WITH INPUT FROM THE GUI
     * @return Line string of the players input
     */
    public String getInput() {
        // Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        if(input.equals("")) {
            return getInput();
        }
        //scan.nextLine();
        //scan.close();
        return input;
    }
    /**
     * Handles if the player want to switch out their current pokemon
     * for one in the team. 
     * @return string telling result of the switch
     */
    public String switchPokemon (String input) {
        String result = "";
        Team team = this.thePlayer.getPokeTeam();
        if(input.equals("b")) {
            return "return to main menu";
        }

        int convetedInput = Integer.parseInt(input);
        if(convetedInput > team.getNumOfPokesInTeam()) {
            return switchPokemon(getInput());
        }

        Pokemon pokeatTeamIndex = team.getPokemonAtIndex(convetedInput - 1);
        if(pokeatTeamIndex.hasPokemonFainted()) {
            return "That pokemon has is knocked out, returning to main menu";
        }

        this.activePlayerPokemon = pokeatTeamIndex;
        result = this.activePlayerPokemon.getIdentStats().getName() + "has been switched in.";
        return result;
    }
    
    /**
     * Uses the item selected and applies it to the wild pokemon if its a 
     * pokeball or to the active pokemon if it is a potion.
     * @param item item that player selected from their bag
     * @return string of what happened after the item was used
     */
    public String useItem (Item item) {
        //choosenItem.use();
        //Item choosenItem = bag.getItemAtIndex(Integer.parseInt(choosenItemStr));
        String itemString = item.getType();
        switch (itemString) {
            case "Pokeball":
                PokeBall ball = (PokeBall) item;
                boolean throwSucceed = ball.throwBall(this.wildPokemon, this.thePlayer.getPokeTeam());
                if(throwSucceed) {
                    
                    this.battling = false;
                    return "Caught wild " + this.wildPokemon.getIdentStats().getName();
                }
                else {
                    return this.wildPokemon.getIdentStats().getName() + " was not caught";
                    
                }
            case "Potion":
                Potion potion = (Potion) item;
                potion.use(this.activePlayerPokemon);
                return this.wildPokemon.getIdentStats().getName() + "healed to " + Integer.toString(this.activePlayerPokemon.getDefensiveStats().getHPCurrent()) + " hp";
            default:
                return "unregonized item";
        }
        //System.out.println("Using item " + choosenItemStr);
    }
    /**
     * Handles player trying to run away currently has 80% of being successful;
     * @return String telling outcome of runaway 
     */
    public String run () {
        int runAwayChance = 80;
        int ran = generateRandomInt(0, 100);
        if(ran < runAwayChance) {
            battling = false;
            return "Ran away succesful";
        }
        else {
            return "Run away was unsuccessful";
        }
    }
    /**
     * @return wild Pokemon of the encounter
     */
    public Pokemon getWildPokemon () {
        return this.wildPokemon;
    } 

    /**
     * generates random integer
     * @param min val of rand int
     * @param max val of rand int
     * @return int between min - max
     */
    public int generateRandomInt (int min, int max) {
        return (int) (Math.random() * ((max-min))+min);
    }
}