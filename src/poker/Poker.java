package poker;

import players.Player;

public class Poker {
    public static void main(String[] args) {
        Table table = new Table();
        Player player1 = new Player(table);
        Player player2 = new Player(table);
        Player player3 = new Player(table);
        table.addPlayer(player1);
        table.addPlayer(player2);
        table.addPlayer(player3);
        table.deal();
        table.placeCards(3);
        table.placeCards(1);
        table.placeCards(1);
        System.out.println(player1.toString() + player1.getCardsInPlay().toString());
        System.out.println(player2.toString() + player2.getCardsInPlay().toString());
        System.out.println(player3.toString() + player3.getCardsInPlay().toString());
        System.out.println(table.findWinner().toString());
    }
}
