class Solution {
    class Card{
        String suit;
        String denomination;
        
        public Card(String suit, String denom) {
            this.suit = suit;
            this.denomination = denom;
        }
    }

    public enum Suits{
        SPADES(1), HEART(2), CLUB(3), DIAMONDS(4);
    }
    public enum Denominations {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14); 
    }
    class Deck {
        ArrayList<Card> cards;
        // static int cardsCount;
        // String[] = ["SPADES", "HEART", "CLUB", "DIAMONDS"];
        // String[] denom= ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"];

        public Deck(){
            // Excluding jokers
            this.cards = new ArrayList<Card>(52);
            for(Suits suit: Suits.values()) {
                for(Denominations denomination: Denominations.values()) {
                    this.cards.add(new Card(suit, denomination));
                }
            }           // cardsCount=52;
        }
        
        public void addCard(Card card) {
            this.cards.add(card);
        }
        public void addCard(String name, String denom) { //for more yet to be done
            this.cards.add(new Card(name, denom));
        }
        public Card getCard() { //for more yet to be done
            return this.cards.remove();
        }
        // public Card getCards(){
        //     Collections.shuffle(this.cards);
        // }        
        public Card shuffleCards(){
            Collections.shuffle(this.cards);
        }
        public void printCards(){
            for(int i=0; i<cards; i++) {
                System.out.print(cards[i]+ " ");
            } System.out.println();
        }
    }

    class Player{
        String name;
        ArrayList<Card> handyCards;

        Player(name){
            this.name=name;
            this.handyCards= new ArrayList<Card>();
        }
    }

    class Game {
        ArrayList<Player> players;
        Deck cardsBundle;
        static int pCount;
        Player winPlayer;

        constructor(int p, int hCards=0) {
            this.pCount=p;
            for(int i=0; i<p; i++){
                this.players.add(new Player("P"+(i+1), hCards));
            }
            this.cardsBundle = new Deck();
        }

        void addPlayer() {
            this.players.add(new Player("P"+(pCount+1)));
        }

        Player removePlayer(String deletePlayer) {
            for(int i=0; i<this.pCount; i++) {
                if(this.players.get(i).equals(deletePlayer)) this.players.remove(i);
            }
        }

        void startGame() {
            Player tempWinner= this.players.get(0);
            for(int i=0; i<pCount; i++){
                Player tempPlayer= this.players.get(i);
                tempPlayer.handyCards= cardsBundle.remove(0);
                this.players.add(i, tempPlayer);
                if(handyCards.Denominations.    )
            }
        }
        // void distributeCards(){ }

        Player findWinner() {
            if(pCount==1) return players.get(0);
        }
    }

	public static void main (String[] args) {
		Scanner a=new Scanner(System.in);
		int b=a.nextInt();
        
	}
}