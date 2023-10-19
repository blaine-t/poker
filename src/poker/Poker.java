package poker;

public class Poker {
    public static void main(String[] args) {
        Table table = new Table();
        System.out.println(table.draw().toString());
    }
}
