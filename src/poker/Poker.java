package poker;

public class Poker {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Table table = new Table();
        table.addPlayer(player1);
        table.addPlayer(player2);
        table.placeCards(3);
        table.placeCards(1);
        table.placeCards(1);
        table.deal();
    }
}
