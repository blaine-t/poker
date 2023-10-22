package poker;

import players.Player;

public class Poker {
    public static void main(String[] args) {
        Table table = new Table();
        Player player1 = new Player(table);
        Player player2 = new Player(table);
        table.addPlayer(player1);
        table.addPlayer(player2);
        table.placeCards(3);
        table.placeCards(1);
        table.placeCards(1);
        table.deal();
    }
}
