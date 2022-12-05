package Game;

import DAO.PlayerDAO;
import Game.Boss;

import java.util.Random;
import java.util.Scanner;

public class Player extends Thread{
    //Atributos Externos
    Boss boss = new Boss();

    //Atributos
    Scanner myScanner = new Scanner(System.in);
    Scanner enterScanner = new Scanner(System.in);
    public int playerHP;
    public int maxHP;
    public String playerName;
    public String playerWeapon;
    public int choice;
    public int silverRing;
    public int oldPingent;
    public int id;

    //Metodos
    public void playerSetUp(Monster monster, Boolean cadastrado) {//Metodo a ser Chamado antes de Iniciar o jogo
        monster.monsterHP = new Random().nextInt(8, 13);
        boss.monsterHP = new Random().nextInt(25, 40);

        if(!cadastrado) {
            playerHP = new Random().nextInt(10, 25);
            maxHP = playerHP;

            playerWeapon = "Adaga";
            System.out.println("Seu HP: " + playerHP);
            System.out.println("Sua Arma: " + playerWeapon);

            System.out.println("Digite o nome de seu Aventureiro(a):");
            playerName = myScanner.nextLine();

            System.out.println("Saudações " + playerName + ", vamos começar!");
        }
    }

    public void fight(Places places, Monster monster) {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Seu HP: " + playerHP);
        System.out.println("Goblin HP: " + monster.monsterHP);
        System.out.println("\n1: Atacar");
        System.out.println("2: Fugir");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            attack(places, monster);
        } else if (choice == 2) {
            places.crossRoad(this, monster);
        } else {
            fight(places, monster);
        }

    }

    public void fightBoss(Places places) {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Seu HP: " + playerHP);
        System.out.println("Goblin Master HP: " + boss.monsterHP);
        System.out.println("\n1: Atacar");
        System.out.println("2: Fugir");
        System.out.println("\n------------------------------------------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            attackBoss(places);
        } else if (choice == 2) {
            places.crossRoad(this, boss);
        } else {
            fightBoss(places);
        }
    }

    public void attackBoss(Places places) {
        int playerDamage = 0;

        if (playerWeapon.equals("Adaga")) {
            playerDamage = new java.util.Random().nextInt(5);
        } else if (playerWeapon.equals("Espada de Duas Mãos")) {
            playerDamage = new java.util.Random().nextInt(13);
        }

        System.out.println("Você atacou e deu " + playerDamage + " de dano!");

        boss.monsterHP = boss.monsterHP - playerDamage;

        System.out.println("Goblin HP: " + boss.monsterHP);

        if (boss.monsterHP < 1) {
            winBoss(places);
        } else if (boss.monsterHP > 0) {
            int monsterDamage = 0;

            monsterDamage = new java.util.Random().nextInt(10);

            System.out.println("O Goblin atacou e deu " + monsterDamage + " de dano!");

            playerHP = playerHP - monsterDamage;

            System.out.println("Seu HP: " + playerHP);

            if (playerHP < 1) {
                dead();
            } else if (playerHP > 0) {
                fightBoss(places);
            }
        }

    }

    public void attack(Places places, Monster monster) {
        int playerDamage = 0;

        if (playerWeapon.equals("Adaga")) {
            playerDamage = new java.util.Random().nextInt(3);
        } else if (playerWeapon.equals("Espada de Duas Mãos")) {
            playerDamage = new java.util.Random().nextInt(6);
        }

        System.out.println("Você atacou e deu " + playerDamage + " de dano!");

        monster.monsterHP = monster.monsterHP - playerDamage;

        System.out.println("Goblin HP: " + monster.monsterHP);

        if (monster.monsterHP < 1) {
            win(places, monster);
        } else if (monster.monsterHP > 0) {
            int monsterDamage = 0;

            monsterDamage = new java.util.Random().nextInt(6);

            System.out.println("O Goblin atacou e deu " + monsterDamage + " de dano!");

            playerHP = playerHP - monsterDamage;

            System.out.println("Seu HP: " + playerHP);

            if (playerHP < 1) {
                dead();
            } else if (playerHP > 0) {
                fight(places, monster);
            }
        }

    }

    public void dead() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Você morreu!!!");
        System.out.println("\n\nGAME OVER");
        System.out.println("\n------------------------------------------------------------------\n");

    }

    public void win(Places places, Monster monster) {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Você matou o Goblin!");
        System.out.println("Ele soltou um anél!");
        System.out.println("Você agora obtém um Anel de Prata!\n");
        System.out.println("Você agora enxerga uma caverna a sua frente.\n\n");
        System.out.println("1: Voltar para o Cruzamento");
        System.out.println("2: Entra na Caverna");
        System.out.println("\n------------------------------------------------------------------\n");

        silverRing = 1;
        monster.alreadyDefeated = true;

        choice = myScanner.nextInt();
        if (choice == 1) {
            places.crossRoad(this, monster);
        } else if (choice == 2){
            places.cave(this, boss);
        } else
            win(places, monster);

    }

    public void winBoss(Places places) {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Você matou o Goblin Master! Ele morre lentamente gritando enquanto sua barriga gorda se abre ensanguentada.");
        System.out.println("Ele soltou um pingente!");
        System.out.println("Você agora obtém um Pingente Velho!\n");
        System.out.println("1: Voltar para o Cruzamento");
        System.out.println("\n------------------------------------------------------------------\n");

        oldPingent = 1;
        boss.alreadyDefeated = true;

        choice = myScanner.nextInt();
        if (choice == 1) {
            places.crossRoad(this, boss);
        } else if (choice == 2){
            places.cave(this, boss);
        } else
            winBoss(places);

    }

    public void ending() {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Guarda: Eu não acredito, você realmente enfrentou o Carniça: Vou sentir saudades daquele Goblin desgraçado.");
        System.out.println("Guarda: ...Espera um segundo. Esse pingente não é da Mera, é do Capitão do Bando da Águia!");
        System.out.println("Guarda: Isso é problema, Mera perdeu o pingente por lá, mas este é do Capitão Grifyn...");
        System.out.println("Guarda: Ele só pode ter ido desafiar o Carniça, mas nunca que Grifyn perderia.");
        System.out.println("Guarda: Tem problema solto no ar, tinha mais alguém lá.");
        System.out.println("Guarda: Você pode entrar, mas terá outra missão!");
        System.out.println("Guarda: Fale com Mera na Prefeitura. Diga que eu, o Comandante Bower está ordenando dois grupos...");
        System.out.println("Bower: Um de patrulha para procurar o Capitão Grifyn.");
        System.out.println("Bower: ...E o outro de Extermínio!");
        System.out.println("\n\n           CONTINUA                    ");
        System.out.println("\n------------------------------------------------------------------\n");
    }
}