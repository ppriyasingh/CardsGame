/* package codechef; // don't place package name! */

import java.util.*;
import java.util.Collections;
import java.lang.*;
import java.io.*;

enum Suits {
    SPADES(1), HEART(2), CLUB(3), DIAMONDS(4);

    private Suits(final int val) {
        this.val = val;
    }

    private int val;

    public int getVal() {
        return val;
    }
}
 enum Denominations {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14); 
    
    private Denominations(final int val) {
        this.val = val;
    }

    private int val;

    public int getVal() {
        return val;
    }
}

class Card {
    Suits suit;
    Denominations denomination;
    
    public Card(Suits suit, Denominations denom) {
        this.suit = suit;
        this.denomination = denom;
    }
}

class Player{
    String name;
    ArrayList<Card> handyCards;

    Player(String name){
        this.name=name;
        this.handyCards= new ArrayList<Card>();
    }
}

class Deck {
    ArrayList<Card> cards;

    public Deck() {
        // Excluding jokers
        this.cards = new ArrayList<Card>(52);
        for(Suits suit: Suits.values()) {
            for(Denominations denomination: Denominations.values()) {
                this.cards.add(new Card(suit, denomination));
            }
        }           // cardsCount=52;
    }
    
    public void addCard(Card card) {    //add card to the deck
        this.cards.add(card);
    }
    // public void addCard(String name, String denom) { //for more yet to be done
    //     this.cards.add(new Card(name, denom));
    // }
    public Card getCard() { //get card from the deck
        //for more yet to be done
        return this.cards.remove(0);
    }
    // public Card getCards(){
    //     Collections.shuffle(this.cards);
    // }        
    public void shuffleDeck(){ //shuffle the deck
        Collections.shuffle(this.cards);
        System.out.println("Just Shuffled your deck, now you are good to go... ");
    }
    public void printDeck(){ // print all cards present in the deck
        // for(int i=0; i<cards; i++) { System.out.print(cards[i]+ " ");  } 
        System.out.println("Here is your Deck: "+ this.cards);
    }
}

class Game {
    ArrayList<Player> players;
    Deck deck;
    static int pCount; //can be replaced by this.players.size()
    Player winPlayer =null;

    Game() {
        this.pCount= 0;
        this.players = new ArrayList<Player> ();
        // for(int i=0; i<p; i++){
        //     this.players.add(new Player("P"+(i+1)));
        // }
        this.deck = new Deck();
    }

    void addPlayer() {
        this.players.add(new Player("P"+(pCount+1)));
        System.out.println("A new Player P"+(pCount+1)+" joined the game! Cheers!!");
    }
    Player removePlayer() {
        return this.players.remove(0);
    }

    Player removePlayer(String deletePlayer) {
        for(int i=0; i<this.pCount; i++) {
            if(this.players.get(i).name.equals(deletePlayer)) {
                return this.players.remove(i);
            }
        }
        if(this.players.size()>0) {
            return this.players.remove(0);
        } else return null;
    }

    void startGame() {
        if(this.players.size() <2) {
            System.out.println("Oops! No Players found to play with, have to Add atleast two players!"); return;
        }
        System.out.println(" ***** THE GAME HAS BEGUN *****");
        System.out.println(" Go ahead and show off your luck!!!");

        Player potentialWinner= this.players.get(0);
        for(int i=0; i< this.pCount; i++) {
            Player tempPlayer= this.players.get(i);
            tempPlayer.handyCards.add(deck.getCard());
            this.players.set(i, tempPlayer);    //check if needed ????

            if(i!=0) {
                if((tempPlayer.handyCards.get(0).denomination.getVal() <potentialWinner.handyCards.get(0).denomination.getVal()) ||
                (tempPlayer.handyCards.get(0).denomination.getVal() ==potentialWinner.handyCards.get(0).denomination.getVal() && 
                (tempPlayer.handyCards.get(0).suit.getVal() <potentialWinner.handyCards.get(0).suit.getVal()))) {
                    potentialWinner= tempPlayer;
                }
            }
        }
        this.winPlayer= potentialWinner;   //ready to expose the winner to others
    }

    Player getWinner() {
        if(this.players.size()<1) return null;
        else if(pCount==1) return this.players.get(0);
        else return this.winPlayer;
    }

    void printCardsForEachPlayer() {
        for(Player p: this.players) {
            System.out.print("Cards for player "+p.name+" are "+p.handyCards);
        }
    }
    void finishGame() {
        for(Player p: this.players) {
            for(Card card: p.handyCards)
                this.deck.addCard(card);
        }
        System.out.print("Game ended! See ya soon!!");
    }
}
/* Name of the class has to be "Main" only if the class is public. */
public class Codechef {
    
	public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        Game g= new Game();
        int checker= 0;
		do {
            // int b=sc.nextInt();
            String msg= " Please enter the respective number to perform your intended task: \n"
                                +"1. Begin the Game\n"
                                +"2. Shuffle the Deck\n"
                                +"3. Print the Deck\n"
                                +"4. Reveal the winner of the Game\n"
                                +"5. Add Player to the Game\n"
                                +" 6. Remove Player from the Game\n"
                                +"7. Print Cards for each player holding\n"
                                +"8. Finish off the Game\n"
                                +"9. Do you wanna quit, press 9 \n";

            System.out.println(msg);
            checker= sc.nextInt();

            switch(checker) {
                case 1: g.startGame();
                    break;
                case 2: g.deck.shuffleDeck();
                    break;
                case 3: g.deck.printDeck();
                    break;
                case 4: System.out.print("Yay! Winner of the GAME is: "+g.getWinner());
                    break;
                case 5: g.addPlayer();
                    break;
                case 6: Player p= g.removePlayer();
                    System.out.println("Oh no! Player "+p.name+" has left the game!");
                    break;
                case 7: g.printCardsForEachPlayer();
                    break;
                case 8: g.finishGame();
                    break;
                default: System.out.println("Watch Out! Enter a valid key from the options provided!");
            }
        } while(checker != 9);
	}
}
