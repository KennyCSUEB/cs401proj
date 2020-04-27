package gamarket;

/**
 * Represents a Pokemon in the Pokemon universe. A Pokemon can have many traits: identify status, offensive status,
 * and defensive status.  
 */
public class Pokemon{
	
	/** To hold the id, name, gender, type, and level of the Pokemon */
	private Stats.IdentifyStats IS; 
	
	/** To hold the attack, sp. attack, and speed of the Pokemon */
	private Stats.OffensiveStats OS;
	
	/** To hold the health, defense, and sp. defense of the Pokemon */
	private Stats.DefensiveStats DS;
	
	/** To hold the move-set of the Pokemon */
	private Move[] moveSet;
	
	/** 
	 * A constructor that changes the identity, offensive, and defensive status of this Pokemon. 
	 * @param IS This is the Pokemon identity status. Should have id, name, gender, type, and level.
	 * @param OS This is the Pokemon offensive status. Should have attack, special attack, and speed.
	 * @param DS This is the Pokemon defensive status. Should have health, defense, and special defense.
	 */
	Pokemon(Stats.IdentifyStats IS, Stats.OffensiveStats OS, Stats.DefensiveStats DS) 
	{
		this.IS = IS;
		this.OS = OS;
		this.DS = DS; 
		moveSet = new Move[4];
	}
	
	/**
	 * A constructor that changes the identity, offensive, and defensive status of this Pokemon.
	 * Sets all the private class variables: IS, OS, and DS to null. 
	 */
	Pokemon()
	{
		this.IS = null;
		this.OS = null;
		this.DS = null; 
		this.moveSet = null;
	}
	/**
	 * @return tells whether pokemon has fainted
	 */
	public boolean hasPokemonFainted() {
		return this.DS.getHPCurrent() == 0;
	}
	/**
	 * Changes the identity status of this Pokemon.
	 * @param IS The Pokemon's new identity status. Should have id, name, gender, type, and level. 
	 */
	public void setIdentStats(Stats.IdentifyStats IS) { this.IS = IS; }
	
	/**
	 * Changes the offensive status of this Pokemon.
	 * @param OS The Pokemon's new offensive status. Should have attack, special attack, and speed. 
	 */
	public void setOffeniveStats(Stats.OffensiveStats OS) { this.OS = OS; }
	
	/**
	 * Changes the defensive status of this Pokemon.
	 * @param DS The Pokemon's new defensive status. Should have health, defense, and special defense. 
	 */
	public void setDefensiveStats(Stats.DefensiveStats DS) { this.DS = DS; }

	/**
	 * Gets id, name, gender, type, and level of this Pokemon. 
	 * @return this Pokemon's identity status. 
	 */
	public Stats.IdentifyStats getIdentStats() { return IS; }
	
	/**
	 * Gets attack, special attack, and speed of this Pokemon.
	 * @return this Pokemon's offensive status.
	 */
	public Stats.OffensiveStats getOffeniveStats() { return OS; }
	
	/**
	 * Gets health, defense, and special defense of this Pokemon. 
	 * @return this Pokemon's defensive status. 
	 */
	public Stats.DefensiveStats getDefensiveStats() { return DS; }
	
	/**
	 * It creates a variable temp of type String to hold the Pokemon's identity, offensive, 
	 * and defensive status. It converts integer data into type String for temp able to contain. 
	 * @param format specifies how the String data should either be: output to screen or output to file.  
	 * @return this Pokemon's id, name, gender, type, level, attack, special attack, speed, health
	 * , defense, and special defense.  
	 */
	public String toString(String format)
	{
		String temp = "";
		
		if(format.equals("other"))
		{
			temp = "Pokemon ID: " + Integer.toString(this.IS.getID()) + " / " 
					   + "Name: " + this.IS.getName() + " / "
					   + "Gender: " + this.IS.getGender() + " / "
					   + "Type: " + this.IS.getType() + " / "
				       + "Level: " + Integer.toString(this.IS.getLevel()) + " / " 
				       + "Attack: " + Integer.toString(this.OS.getATK()) + " / "
				       + "Sp. Attack: " + Integer.toString(this.OS.getSPATK()) + " / "
				       + "Speed: " + Integer.toString(this.OS.getSpeed()) + " / "
				       + "Health: " + Integer.toString(this.DS.getHP()) + " / "
				       + "Defense: " + Integer.toString(this.DS.getDEF()) + " / "
					   + "Sp. Defense: " + Integer.toString(this.DS.getSPDEF()) + "\n";
		}
		if(format.equals("file"))	//  need to preserve the read-in data format: data1,data2,...,dataN.
		{
			temp = Integer.toString(this.IS.getID()) + ","
				   + this.IS.getName() + ","
				   + this.IS.getGender() + ","
				   + this.IS.getType() + ","
				   + Integer.toString(this.IS.getLevel()) + "," 
				   + Integer.toString(this.OS.getATK()) + ","
				   + Integer.toString(this.OS.getSPATK()) + ","
				   + Integer.toString(this.OS.getSpeed()) + ","
			       + Integer.toString(this.DS.getHP()) + ","
			       + Integer.toString(this.DS.getDEF()) + ","
				   + Integer.toString(this.DS.getSPDEF()) + ","
				   + moveSet[0].getMoveName() + ","
				   + moveSet[1].getMoveName() + ","
				   + moveSet[2].getMoveName() + ","
				   + moveSet[3].getMoveName();
		}
		return temp; 
	}
	
	/**
	 * What it does: Return a string of the Pokemon's move-set of four moves. 
	 * @return String of Pokemon moves. 
	 */
	public String printPokemonMoves()
	{
		String result = "";
		for(int i = 0; i < 4; i++)
		{
			if(moveSet[i] == null)
			{
				result+="";
				continue;
			}
			result += "Move #" + Integer.toString(i) + " " + moveSet[i].getMoveName() + "\n";
		}
		return result; 
	}
	/**
	 * getter for the pokemons moveset
	 */
	public Move[] getPokemonMoveSet () {
		return this.moveSet;
	}
	/**
	 * Returns move at the index 0-3
	 * @param index
	 * @return move at the given index
	 */
	public Move getMove(int index) {
		return this.moveSet[index];
	}
	/**
	 * What it does: It allows to change one Pokemon's current move to a new move. 
	 * @param newMove this should be the Pokemon's new move.
	 * @param oldMove this should be the Pokemon's old move.
	 * @param mc this is needed to obtain data from the MoveCollection database. 
	 */
	public void changeMove(String newMove, String oldMove, MoveCollection mc)
	{
		for(int i = 0; i < 4; i++)
		{
			if(moveSet[i].getMoveName().toUpperCase().equals(oldMove.toUpperCase()))
			{	
				moveSet[i] = mc.searchMove(newMove);
			}
		}
	}
	
	/**
	 * What it does: It sets the Pokemon's move-set with four moves. 
	 * @param m1 this is the Pokemon's move number 1.
	 * @param m2 this is the Pokemon's move number 2.
	 * @param m3 this is the Pokemon's move number 3.
	 * @param m4 this is the Pokemon's move number 4. 
	 * @return nothing. 
	 */
	public void setMoves(Move m1, Move m2, Move m3, Move m4)
	{
		moveSet[0] = m1;
		moveSet[1] = m2; 
		moveSet[2] = m3; 
		moveSet[3] = m4; 
	}
} 
