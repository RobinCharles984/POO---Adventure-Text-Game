import Character.Enemy.Boss;
import Character.Enemy.Enemy;
import Character.Player.Player;

import java.util.*;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        //Variaveis Auxiliares
        boolean[] placeAlreadyVisited = new boolean[10];

        //GameElemets
        GameElements gameElements = new GameElements();

        //Gerando Inimigos por código
        Enemy michelin = new Enemy();
        michelin.name = "Michelin";
        michelin.setHp(20);
        michelin.setDef(2);
        michelin.setDmg(3);

        Enemy goblin = new Enemy();
        goblin.name = "Goblin";
        goblin.setHp(13);
        goblin.setDef(1);
        goblin.setDmg(5);

        //Gerando Boss
        Boss boss = new Boss();
        boss.name = "Unknown";
        boss.setHp(50);
        boss.setDef(15);
        boss.setDmg(10);

        //Gerando Player
        Player player = new Player();
        System.out.println("Entre com seu nome: ");
        Scanner scanner = new Scanner(System.in);//Entrando com nome do Player
        player.name = scanner.nextLine();
        System.out.println("Seja Bem Vindo, " + player.name + "!");
        Random generator = new Random();
        player.setHp(generator.nextInt(15, 30));
        player.setDef(generator.nextInt(0, 15));
        player.setDmg(generator.nextInt(2, 8));

        //Game Design
        for (int i = 0; i < placeAlreadyVisited.length; i++) { //Começando todos os cenários encontrados como falso
            placeAlreadyVisited[i] = false;
        }
        player.room = 0;//Definindo spawn do player
        goblin.room = 4;
        michelin.room = 6;
        boss.room = 9;

        //Game em Loop
        while(gameElements.command != "sair")
        {
            switch (player.room)
            {
                case 0:
                    if(placeAlreadyVisited[0] == false)
                    {
                        System.out.println(player.name + ", você está em um calabouço.");
                        System.out.println("Você foi jogado nele por um crime que não cometeu.");
                        System.out.println("Seu objetivo é sair deste calabouço vivo, tenha cuidado!");
                        System.out.println("Pois neste lugar tem criaturas perigosas que são jogadas para eliminar as vítimas presas nele.");
                        System.out.println("Boa Sorte");
                        System.out.println("Você acorda no calabouço. Você sente um cheiro horrível vindo desse lugar.");
                        System.out.println("Ao seu lado tem uma espada enferrujada, provavelmente dos guardas, que querem ver algum espetáculo hoje.");
                    }
                    placeAlreadyVisited[0] = true;
                    System.out.println("Tem um caminho na sua frente");
                    System.out.println("O que deseja fazer?");
                    scanner = new Scanner(System.in);
                    gameElements.command = scanner.nextLine();
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
            }
        }
    }
}
