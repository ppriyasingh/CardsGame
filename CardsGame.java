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
    int id;
    ArrayList<Card> handyCards;

    Player(int id) {
        this.id=id;
        this.handyCards= new ArrayList<Card>();
    }
}

interface Deck {
    void addCard(Card card);
    Card getCard();
    void shuffleDeck();
    void printDeck();
}

class Game implements Deck{
    ArrayList<Player> players;
    ArrayList<Card>  deck;
    static int pCount; //can be replaced by this.players.size()
    Player winPlayer =null;

    Game() {
        this.pCount= 1;
        this.players = new ArrayList<Player> ();

        deck = new ArrayList<Card>(52);
        for(Suits suit: Suits.values()) {
            for(Denominations denomination: Denominations.values()) {
                // System.out.print("suit: "+suit+"denomination: "+denomination); 
                deck.add(new Card(suit, denomination));
            }
        }           // cardsCount=52;
    }

    public void addCard(Card card) {    //add card to the deck
        deck.add(card);
    }
    public Card getCard() { //get card from the deck and returns
        return deck.remove(0);
    }
    public void shuffleDeck() { //shuffle the deck
        Collections.shuffle(deck);
        System.out.println("Just Shuffled your deck, now you are good to go... ");
    }
    public void printDeck() { // print all cards present in the deck
        for(Card c: deck) {
            System.out.println(printCard(c));
        }
    }

    void addPlayer() {
        players.add(new Player(pCount++));
        System.out.println("A new Player with ID "+(pCount-1)+" joined the game! Cheers!!");
    }
    Player removePlayer() {
        return players.remove(0);
    }

    Player removePlayer(int deletePlayer) {
        for(int i=0; i<pCount; i++) {
            if(players.get(i).id==deletePlayer) {
                return players.remove(i);
            }
        }
        if(players.size()>0) {
            return players.remove(0);
        } else return null;
    }

    void startGame() {
        if(players.size() <2) {
            System.out.println("Oops! No Players found to play with, have to Add atleast two players!"); return;
        }
        System.out.println(" ***** THE GAME HAS BEGUN *****");
        System.out.println(" Go ahead and show off your luck!!!");

        Player potentialWinner= players.get(1);
        for(int i=0; i< players.size(); i++) {
            Player tempPlayer= players.get(i);
            tempPlayer.handyCards.add(getCard());
            players.set(i, tempPlayer);    //check if needed ????

            if(i!=0) {
                if((tempPlayer.handyCards.get(0).denomination.getVal() < potentialWinner.handyCards.get(0).denomination.getVal()) ||
                (tempPlayer.handyCards.get(0).denomination.getVal() == potentialWinner.handyCards.get(0).denomination.getVal() && 
                (tempPlayer.handyCards.get(0).suit.getVal() < potentialWinner.handyCards.get(0).suit.getVal()))) {
                    potentialWinner= tempPlayer;
                }
            }
        }
        winPlayer= potentialWinner;   //ready to expose the winner to others
    }

    int getWinner() {
        if(players.size() < 1) return 0;
        else if(pCount==1) return players.get(0).id;
        else return winPlayer.id;
    }

    void printCardsForEachPlayer() {
        for(Player p: players) {
            System.out.println("Cards for player "+p.id+" are "+printCard(p.handyCards.get(0)));
        }
    }
    void finishGame() {
        for(Player p: players) {
            for(Card card: p.handyCards)
                addCard(card);
        }
        System.out.print("Game ended! See ya soon!!");
    }

    public static String printCard(Card c) {
        return "[ "+c.suit.toString()+ " "+c.denomination.toString()+" ]";
    }
}
/* id of the class has to be "Main" only if the class is public. */
public class Main {
    
	public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        Game g= new Game();
        int checker= 0;
		do {
            // int b=sc.nextInt();
            String msg= "\n\n Please enter the respective number to perform your intended task: \n"
                                +"1. Begin the Game\n"
                                +"2. Shuffle the Deck\n"
                                +"3. Print the Deck\n"
                                +"4. Reveal the winner of the Game\n"
                                +"5. Add Player to the Game\n"
                                +"6. Remove Player from the Game\n"
                                +"7. Print Cards for each player holding\n"
                                +"8. Finish off the Game\n"
                                +"9. Do you wanna quit, press 9 \n";

            System.out.println(msg);
            checker= sc.nextInt();

            switch(checker) {
                case 1: g.startGame();
                    break;
                case 2: g.shuffleDeck();
                    break;
                case 3: g.printDeck();
                    break;
                case 4: System.out.print("Yay! Winner of the GAME is with id: "+g.getWinner());
                    break;
                case 5: g.addPlayer();
                    break;
                case 6: Player p= g.removePlayer();
                    System.out.println("Oh no! Player "+p.id+" has left the game!");
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
