package gamarket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Represents a team of Pokemon, a team will be able to hold at most 6 Pokemon. */
public class Team {
    
	/** The array to hold the Pokemon team. */
    private Pokemon[] myTeam;
    
    /** The number of Pokemons in the Player's Team */
    private int numOfPokesInTeam;
    
    /** To gain access to the MoveCollection database */ 
    private MoveCollection mc;
    
    /**
     * The PokemonCollection constructor.
     * A team can hold at-most 6 Pokemon.
     * A player will start out with a Pikachu Pokemon in its team.  
     */
    Team(MoveCollection mc)
    {
        myTeam = new Pokemon[6];
        numOfPokesInTeam = 0;
        this.mc = mc;
    }
    
    /** It displays all the Pokemon in the player's team with each Pokemon's: level and name.
     * @return nothing.
     */
    public void displayTeam()
	{
		for(int i = 0; i < 6; i++)
		{
			if(myTeam[i] == null)
			{
				break; 
			}
    		System.out.println((i+1) + ". " + "Level " + myTeam[i].getIdentStats().getLevel() + " " + myTeam[i].getIdentStats().getName()); 
		}
	}

    public Pokemon getPokemonAtIndex(int index) {
		return myTeam[index];
	}

    /**
     * What it does: each Pokemon will have a numbered index/position, you can switch them around.
     * @param prevSpot this must be the old index/position of the Pokemon.
     * @param newSpot this must be the new index/position of the Pokemon.
     */
    public void switchPokemonInTeam(int prevSpot, int newSpot)
    {
        Pokemon temp = new Pokemon();
        temp = myTeam[--prevSpot];
        myTeam[prevSpot] = myTeam[--newSpot];
        myTeam[newSpot] = temp;
    }
    
    /**
     * What it does: saves the current Player's team of Pokemons to a file.
     * @return nothing.
     */
    public void saveTeam(String user, Boolean... test)
    {
        // create a file object.
        String filename;
        if(test.length > 0 && test[0]==true){
            filename = "./databaseFiles/pkmnCollectionFiles/poketeamFiles/" + user+ "_poketeam.txt";
        }else{
            filename ="./pokemon/databaseFiles/pkmnCollectionFiles/poketeamFiles/" + user+ "_poketeam.txt";
        }
        File newFile = new File(filename);
                
        // create a file writer object to write data to a file.
        FileWriter fw;
        try {
                // proceed to write data to the output file.        
                fw = new FileWriter(newFile, false);
                for(int i = 0; i < 6; i++)
                {
                    if(myTeam[i] == null)
                    {
                        break;
                    }
                    fw.write(myTeam[i].toString("file"));    // "file" to preserve the read-in file data format of commas separation.
                }
                fw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
    }
    
    /**
     * What it does: sets the Player's team to empty and sets numOfPokesInTeam to zero.
     * @return nothing.
     */
    public void clear()
    {
        for(int i = 0; i < 6; i++)
        {
            if(myTeam[i] == null)
            {
                break;
            }
            myTeam[i].setMoves(null, null, null, null);
            myTeam[i] = null;
        }
        numOfPokesInTeam = 0;
    }
    
    /**
     * What it does: gets the total number of current Pokemons in Player's team.
     * @return this number of current Pokemons in Player's team.
     */
    public int getNumOfPokesInTeam()
    {
        return numOfPokesInTeam;
    }
    
    /**
     * What it does: Adds the Pokemon into the Player's team array.
     * @param myPoke this should have the Pokemon object with all its stats.
     * @return nothing.
     */
    public void addPokemon(Pokemon myPoke)
    {
        myTeam[numOfPokesInTeam++] = myPoke;
    }
    
    /**
     * What it does: reads in the Player's Pokemons from the save file.
     * @param filename this should be the save file that contains the Player's saved Pokemons.
     * @return nothing.
     */
    public void loadTeam(String user, Boolean... test)
    {
        String filename = "";
        if(test.length > 0 && test[0]==true){
            filename = "./databaseFiles/pkmnCollectionFiles/poketeamFiles/" + user+ "_poketeam.txt";
        }else{
            filename ="./pokemon/databaseFiles/pkmnCollectionFiles/poketeamFiles/" + user+ "_poketeam.txt";
        }
        // create a buffered reader to read in text.
        BufferedReader br;
        
        try {
            // to read in data from the file.
                br = new BufferedReader(new FileReader(filename));
                String line = br.readLine();
                     
               /** read line by line with each pokemon: id, name, gender, type, level,
                    HP, Attack, Defense, Sp. Atk, Sp. Def, Speed separated by commas.
               **/
                while(line != null && !line.isEmpty())
                {
                    String[] pokeData = line.split(",");
                   
                    // this will read in Identification Stats: ID, name, gender, type
                    Stats.IdentifyStats myIS = new Stats().new IdentifyStats(Integer.parseInt(pokeData[0]),
                                                                              pokeData[1],
                                                                              pokeData[2],
                                                                              pokeData[3],
                                                                              Integer.parseInt(pokeData[4]));
                    
                    // this will read in Offensive Stats: attack, sp. attack, speed
                    Stats.OffensiveStats myOS = new Stats().new OffensiveStats(Integer.parseInt(pokeData[5]),
                                                                               Integer.parseInt(pokeData[6]),
                                                                               Integer.parseInt(pokeData[7]));
                   
                    // this will read in Defensive Stats: health, defense, sp. defense
                    Stats.DefensiveStats myDS = new Stats().new DefensiveStats(Integer.parseInt(pokeData[8]),
                                                                               Integer.parseInt(pokeData[9]),
                                                                               Integer.parseInt(pokeData[10]));
                    
                    
                    
                    
                    // create the Pokemon object with all the stats we just read in: Identification, Offensive, Defensive stats.
                    Pokemon myPoke = new Pokemon(myIS, myOS, myDS);
                   
                    
                    // load in the Pokemon's move-set.
                    Move m1 = mc.searchMove(pokeData[11].trim());
                    Move m2 = mc.searchMove(pokeData[12].trim());
                    Move m3 = mc.searchMove(pokeData[13].trim());
                    Move m4 = mc.searchMove(pokeData[14].trim());
                    
                    // add the move-set to the Pokemon's default move-set.
                    myPoke.setMoves(m1, m2, m3, m4);
                    
                    // add the pokemon object to the Player's Team.
                    addPokemon(myPoke);      
                
                    line = br.readLine();
                }
                // close the buffered reader.
                br.close();
                }catch(IOException e) {
                       e.printStackTrace();
                }
    }
    
    /**
     * What it does: you can view a specified Pokemon's move-set. 
     * @param myPoke this should be the Pokemon you want to see the moves for. 
     */
    public void viewPokemonMoves(String myPoke)
    {
    	for(int i = 0; i < numOfPokesInTeam; i++)
    	{
    		if(myTeam[i].getIdentStats().getName().toUpperCase().equals(myPoke.toUpperCase())) 
    		{
    			System.out.println(myTeam[i].getIdentStats().getName() + "'s Move-Set:");
    			myTeam[i].printPokemonMoves();
    		}
    	}
    }
    /**
     * returns true if the player has any non fainted pokemon
     */
	public boolean hasActivePokemon() {
		boolean hasActive = false;
		for(int i = 0; i < this.numOfPokesInTeam; i++) {
			if(myTeam[i].hasPokemonFainted() == false) {
				hasActive = true;
			}
		}
		return hasActive;
	}
    /**
     * returns teams first non-fainted pokemon 
     */
	public Pokemon getActivePokemon() {
        Pokemon activePokemon = myTeam[0];
        for(int i = 0; i < this.numOfPokesInTeam; i++) {
            if(myTeam[i].hasPokemonFainted() == false) {
                activePokemon = myTeam[i];
                break;
            }
        }
        return activePokemon;
	}
}



