package boardGame;

public class Square {
	private String curntSquare;

	/*Default constructor, when called, will invoke the 
	 * constructor, which takes in a string, and passes it
	 * a string that says "Safe"
	 */
	public Square() {
		this("Safe");
	}
	
	
	//constructor that takes in a string and sets it to curntSquare
	public Square(String curntSquare) {
		this.curntSquare = curntSquare;
	}
	
	
	//overrode toString() function
	public String toString() {
		return curntSquare;
	}
	
	
	/*This method is to remove the "Adventurer" from the current square.
	 * In Wumpus World, its set in a way that it will remove "Adventurer" before
	 * going to the next "Adventurer" location placing him/her.
	 */
	public void removeAdven(Square curntSquare) {
		this.curntSquare = curntSquare.toString().replaceAll("Adventurer", "");
	}
	
	
	/* This is a setter method. If the square contains string "Safe" it 
	 *will write over the square, except if a the "Player" is trying to move in,
	 *and if the square  contains another string besides "Safe", it will catenate
	 *the new string onto what is currently in the square. 
	 */
	public void setSquare(String curntSquare) {
		if(this.curntSquare.indexOf("Safe") != -1 && curntSquare.indexOf("Adventurer") != -1) {
			this.curntSquare += curntSquare;
		}
		else if((this.curntSquare.indexOf("Pit") != -1 || this.curntSquare.indexOf("Wumpus") != -1) && (curntSquare.indexOf("Stench") != -1 || curntSquare.indexOf("Breeze") != -1)){
			curntSquare = "";
			this.curntSquare += curntSquare;
		
		}
		else if(this.curntSquare.indexOf("Safe") != -1) {
			this.curntSquare = curntSquare;
		}
		else
		this.curntSquare += curntSquare;
	}

}

