
public class Card {

	//Instance Variables

	    private int suit;
	    private int value;

	  //Default Constructor

	    private Card(){
	    	suit = 0;
	    	value = 0;

	    }

	    //Card Constructor

	    public Card(int suit, int value){
	        this.suit = suit;
	        this.value = value;

	    }

	    public int getValue(){
	        return value;

	    }

	    public int getSuit(){
	        return suit;

	    }


	    

	    }



