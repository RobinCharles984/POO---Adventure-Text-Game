public class Main {
    public static void main(String[] args) {
        //Creating Player, Monster and Places Objects
        Player player = new Player();
        Monster monster = new Monster();
        Places places = new Places();

        player.playerSetUp(monster);
        places.townGate(player, monster);
    }
}
