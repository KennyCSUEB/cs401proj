package gamarket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents all the moves in the Pokemon universe. 
 * A Pokemon will be able to hold at most 4 moves in its move-set. 
 */
public class MoveCollection {
	
	/** The current number of MOVEs in the array */
	private int numMoves; 
	
	/** The array to contain all the MOVEs in the Pokemon Universe*/
	private Move moveArray[]; 
	
	/**
	 * The MoveCollection constructor.
	 * It sets the numMoves to zero. 
	 * It allocates a new array of Move type to 7. 
	 * 
	 */
	public MoveCollection(boolean... test)
	{
		numMoves = 0; 
		moveArray = new Move[7];
		loadData();

		if(test.length >0 && test[0] == true) {
			loadData(true);
		}
		else {
			loadData();
		}

	}
	
	/**
	 * The addOrModifyMove fucntion of type void.
	 * The function adds a new move to the move collection, if the new move does not exist.
	 * If it does exist, the move information will get updated. 
	 * @param name This is the Move's name. Should have the Move's name(Tackle). 
	 * @param type This is the Move's type. Should have the Move's type(Normal). 
	 * @param category This is the Move's category. Should have the Move's category(Physical).
	 * @param damage This is the Move's damage. Should have the Move's damage(60). 
	 */
	public void addOrModifyMove(String name, String type, String category, String damage)
	{
		try {	// running time error prevention.   
				// double the size of the array if the current array is full. 
			if(numMoves == moveArray.length-1)
			{
				moveArray = Arrays.copyOf(moveArray, moveArray.length * 2);
			}
				
			// change the Strings back into type Integer. 
			int myDmg = Integer.parseInt(damage);
			boolean moveExists = false; 
				
			// traverse the array to see if the MOVE already "exists" in the collection.
			for(int i = 0; i < numMoves && moveExists != true; i++)
				{
					// if both MOVEs are equal, then the MOVE "exists". 
					if(moveArray[i].getMoveName().compareToIgnoreCase(name) == 0)
					{
						// proceed to modify the move-type, move-type-category, move-damage. 
						moveArray[i].setMoveType(type);
						moveArray[i].setMoveCategory(category);
						moveArray[i].setMoveDmg(myDmg);
						moveExists = true; 
					}
				}

				// if the new MOVE does NOT already exist in the collection, then add the new MOVE to the array. 
				if(moveExists == false) 
				{
					Move newMove = new Move(name, type, category, myDmg);
					moveArray[numMoves++] = newMove;
				}
				}catch(NumberFormatException e)
				{
					System.out.println("Error has occurred from MoveCollection() function...");
				}
			}
		
	/**
	 * The function loadData of type void.
	 * The function loads in a given data text file (movedata.txt).
	 * Each line in the text file will have data: Move's name, Move's type, Move's category, and Move's damage separated by commas. 
	 * @param filename This is the name of the file that is being read (movedata.txt). 
	 */
	public void loadData(boolean... test) 
	{

		String filename;
		if(test.length > 0 && test[0]==true){
            filename = "./databaseFiles/movedata.txt";
        }else{
            filename = "./pokemon/databaseFiles/movedata.txt";
        }
		// create a buffered reader to read in text.
	    BufferedReader br;
	      
	    try {
	         // to read in data from the file.
	         br = new BufferedReader(new FileReader(filename));
	         String line = br.readLine();
	          
	         // read line by line with each token: move-name, move-type, move-type-category, move-damage separated by commas.
	         while(line != null && !line.isEmpty())
	         {
	              String[] moveData = line.split(",");
	              addOrModifyMove(moveData[0], moveData[1], moveData[2], moveData[3]);
	              line = br.readLine();
             }
	          
	         // close the buffered reader.
	         br.close();
	       }catch(IOException e) {
	           e.printStackTrace();
	       }
    }

	/**
	 * The function printAllMoves of type void.
	 * The function prints out all the moves available in the Pokemon universe. 
	 */
	public void printAllMoves()
	{
		System.out.println("Move's Name / Move's Type / Move's Category / Move's Damage");
		for(int i = 0; i < numMoves; i++)
		{
			System.out.println("Move #" + i + " " + moveArray[i].toString());
		}
	}
	
	/**
	 * The function getNumMoves of type int.
	 * The function prints out the current number of moves that exist in the Pokemon universe.
	 * @return this total number of moves.  
	 */
	public int getNumMoves()
	{
		return numMoves;
	}
	
	/**
	 * The function getMoveAtIndex of type Move class.
	 * What it does: it obtains the move at the specified index in the moveArray.
	 * @param i this should be the specified index/location of the move. 
	 * @return the move at this specified index. 
	 */
	public Move getMoveAtIndex(int i)
	{
		return moveArray[i];
	}
	
	/**
	 * The function searchMove of type Move class.
	 * What it does: traverse the move collection array to locate the position of the move,
	 * which the move is indicated by the move's name(findMove).
	 * @param findMove this should be the move to be located. 
	 * @return this move.
	 */
	public Move searchMove(String findMove)
	{
		int position = 0;
		for(int i = 0; i < numMoves; i++)
		{
			if(moveArray[i].getMoveName().toUpperCase().equals(findMove.toUpperCase()))
			{
				position = i; 
			}
		}
		return moveArray[position];
	}
} 
