package gamarket;

/**
 * Represents the Pokemon's status in sub-categories: identity, offensive, and defensive. 
 * The identity status represents the id, name, gender, type, and level.
 * The offensive status represents the attack, special attack, and speed.
 * The defensive status represents the health, defense, and special defense. 
 */
public class Stats {
	
	    /**
	    * Represents the id, name, gender, type, and level of the Pokemon. 
	    */
		public class IdentifyStats {
		
		/** 
		 * The id of the Pokemon. 
		 */
		private int ID;
		
		/**
		 * The name of the Pokemon .
		 */
		private String name; 
		
		/**
		 * Male or Female of the Pokemon. 
		 */
		private String gender; 
		
		/**
		 * The type of the Pokemon (fire, electric, ghost, fighting, etc). 
		 */
		private String type;
		
		/**
		 * The level of the Pokemon. 
		 */
		private int level; 
	
		/**
		 * The IdentifyStats constructor. 
		 * It changes the id, name, gender, type, and level of the Pokemon. 
		 * @param id This is the new Pokemon id. Should have the Pokemon id.
		 * @param name This is the new Pokemon name. Should have the Pokemon name. 
		 * @param gender This is the new Pokemon gender. Should have the Pokemon gender(male/female). 
		 * @param type This is the new Pokemon type. Should have the Pokemon type(fire/water/electric/etc). 
		 * @param level This is the new Pokemon level. Should have the Pokemon level. 
		 */
		public IdentifyStats(int id, String name, String gender, String type, int level)
		{
			this.ID = id;
			this.name = name;
			this.gender = gender;
			this.type = type;
			this.level = level; 
		}
		
		/**
		 * The public function setID of type void.
		 * It changes the Pokemon's id. 
		 * @param thisID This is the new Pokemon's id. Should have the Pokemon's id. 
		 */
		public void setID(int thisID) { this.ID = thisID; }
		
		/**
		 * The public function setName of type void.
		 * It changes the Pokemon's name.
		 * @param thisName This is the new Pokemon's name. Should have the Pokemon's name. 
		 */
		public void setName(String thisName) { this.name = thisName; }
	
		/**
		 * The public function setGender of type void.
		 * It changes the Pokemon's gender.
		 * @param thisGender This is the new Pokemon's gender. Should have the Pokemon's gender.
		 */
		public void setGender(String thisGender) { this.gender = thisGender; }
	
		/** 
		 * The public function setType of type void. 
		 * It changes the Pokemon's type.
		 * @param thisType This is the new Pokemon's type. Should have the Pokemon's type. 
		 */
		public void setType(String thisType) { this.type = thisType; }

		/**
		 * The public function setLevel of type void.
		 * It changes the Pokemon's level.
		 * @param thisLevel This is the new Pokemon's level. Should have the Pokemon's level. 
		 */
		public void setLevel(int thisLevel)	{ this.level = thisLevel; }	
		
		/**
		 * The public function getID of type int.
		 * It returns the Pokemon's id. 
		 * @return this Pokemon's id as an integer.
		 */
		public int getID() { return ID; }
		
		/**
		 * The public function getName of type String.
		 * It returns the Pokemon's name as a String. 
		 * @return this Pokemon's name.
		 */
		public String getName() { return name; }
		
		/**
		 * The public function getGender of type String.
		 * It returns the Pokemon's gender(male/female) as a String. 
		 * @return this Pokemon's gender.
		 */
		public String getGender() { return gender; }
		
		/**
		 * The public function getType of type String.
		 * It returns the Pokemon's type(water/fire/electric/etc) as a String. 
		 * @return this Pokemon's type. 
		 */
		public String getType() { return type; }
		
		/**
		 * The public function getLevel of type int.
		 * It returns the Pokemon's level as an integer. 
		 * @return this Pokemon's level.
		 */
		public int getLevel() { return level; }
	}
	
	/**
	 * Represents the Pokemon offensive status: attack, special attack, and speed. 
	 */
	public class OffensiveStats {
		
		/**
		 * The attack stats of the Pokemon.  
		 */
		private int atk;
		
		/**
		 * The special attack stats of the Pokemon.
		 */
		private int spatk;

		/**
		 * The speed stats of the Pokemon. 
		 */
		private int spd;
		
		/**
		 * The OffensiveStats constructor.
		 * It changes the new Pokemon offensive status: attack, special attack, and speed. 
		 * @param atk This is the new Pokemon attack. Should have the Pokemon attack.
		 * @param spatk This is the new Pokemon special attack. Should have the Pokemon special attack.
		 * @param spd This is the new Pokemon speed. Should have the Pokemon speed. 
		 */
		public OffensiveStats(int atk, int spatk, int spd)
		{
			this.atk = atk;
			this.spatk = spatk;
			this.spd = spd; 
		}
		
		/**
		 * The function setSpeed of type void.
		 * It changes the Pokemon's speed.
		 * @param thisSPD This is the Pokemon's speed. Should have the Pokemon's speed. 
		 */
		public void setSpeed(int thisSPD) { this.spd = thisSPD; }
		
		/**
		 * The function setATK of type void.
		 * It changes the Pokemon's attack.
		 * @param thisATK This is the Pokemon's attack. Should have the Pokemon's attack.
		 */
		public void setATK(int thisATK)	{ this.atk = thisATK; }
		
		/**
		 * The function setSPAtk of type void.
		 * It changes the Pokemon's special attack.
		 * @param thisSPAtk This is the Pokemon's special attack. Should have the Pokemon's special attack. 
		 */
		public void setSPAtk(int thisSPAtk) { this.spatk = thisSPAtk; }
		
		/**
		 * The function getSpeed of type int.
		 * It returns the Pokemon's speed as an integer.  
		 * @return this Pokemon's speed.  
		 */
		public int getSpeed(){ return spd; }
		
		/**
		 * The function getATK of type int.
		 * It returns the Pokemon's attack as an integer.
		 * @return this Pokemon's attack.  
		 */
		public int getATK()	{ return atk; }
		
		/**
		 * The function getSPATK of type int.
		 * It returns the Pokemon's special attack as an integer.
		 * @return this Pokemon's special attack.  
		 */
		public int getSPATK() { return spatk; }
	}
	
	/**
	 * Represents the Pokemon's defensive status: health, defense, and special defense. 
	 */
	public class DefensiveStats {
		
		/**
		 * The Pokemon's health.
		 */
		private int hpMax;
		private int hpCurrent; 
		/**
		 * The Pokemon's defense.
		 */
		private int def;
		
		/**
		 * The Pokemon's special defense. 
		 */
		private int spdef;
		
		/**
		 * The DefensiveStats constructor. 
		 * It changes the Pokemon's defensive status: health, defense, and special defense. 
		 * @param hp This is the new Pokemon's health. Should have the Pokemon's health. 
		 * @param def This is the new Pokemon's defense. Should have the Pokemon's defense.
		 * @param spdef This is the new Pokemon's special defense. Should have the Pokemon's special defense. 
		 */
		public DefensiveStats(int hp, int def, int spdef)
		{
			this.hpMax = hp;
			this.hpCurrent = hp;
			this.def = def;
			this.spdef = spdef; 
		}
		
	    /**
	     * The function setHP of type void.
	     * This changes the Pokemon's health.
	     * @param thisHP This is the Pokemon's health. Should have the Pokemon's health. 
	     */
		/**
		 * Sets the max hp of the pokemon
		 * @param thisHP the new max hp
		 */
		 public void setHPMax(int thisHP) {	this.hpMax = thisHP; }
		/**
		 * The sets the current hp of the pokemon
		 * Will cap at max hp
		 * @param newHPCurrent
		 */
		public void setHPCurrent (int newHPCurrent) { 
			this.hpCurrent = newHPCurrent;
			if(this.hpCurrent > this.hpMax) {
				this.hpCurrent = this.hpMax;
			} 
		} 
		/**
		 * Has the pokemon take damage
		 * @param damage
		 */
		public void takeDamage (int damage) {
			this.hpCurrent -= damage;
			if(this.hpCurrent < 0) {
				this.hpCurrent = 0;
			}
		}
		/**
		 * The function setDEF of type void.
		 * This changes the Pokemon's defense. 
		 * @param thisDEF This is the Pokemon's defense. Should have the Pokemon's defense.
		 */
		public void setDEF(int thisDEF) { this.def = thisDEF; }
		
		/**
		 * The function setSPDef of type void.
		 * This changes the Pokemon's special defense. 
		 * @param thisSPDef This is the Pokemon's special defense. Should have the Pokemon's special defense. 
		 */
		public void setSPDef(int thisSPDef) { this.spdef = thisSPDef; }
		
		/**
		 * The function getHP of type int.
		 * It returns the Pokemon's health as an integer. 
		 * @return this Pokemon's health.
		 */
		public int getHP() { return this.hpMax; }
		/**
		 * @return current HP value
		 */
		public int getHPCurrent() { return this.hpCurrent; }

		/**
		 * The function getDEF of type int. 
		 * It returns the Pokemon's defense as an integer. 
		 * @return this Pokemon's defense. 
		 */
		public int getDEF() { return def; }
		
		/**
		 * The function getSPDEF of type int.
		 * It returns the Pokemon's special defense as an integer. 
		 * @return this Pokemon's special defense. 
		 */
		public int getSPDEF() { return spdef; }
	}
}
