import java.util.ArrayList;
public class Deck {

private ArrayList<Card> Deck  = new ArrayList<Card>();

    

    public Deck(){

            //Creating First Suit

            for(int i = 1; i < 14; i++){
                Card dummy = new Card(1, i);
                Deck.add(dummy);

                //Creating Second Suit

            }

            for(int i = 1; i < 14; i++){
                Card dummy = new Card(2, i);
                Deck.add(dummy);

                //Creating Third Suit

            }

            for(int i = 1; i < 14; i++){
                Card dummy = new Card(3, i);
                Deck.add(dummy);

                //Creating Fourth Suit

            }

            for(int i = 1; i < 14; i++){ 
                Card dummy = new Card(4, i);
                Deck.add(dummy);

            }

        

    }

    

    public ArrayList<Card> getDeck(){
    	return Deck;

    }


}


