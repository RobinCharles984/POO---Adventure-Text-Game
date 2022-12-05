package Game;

import DAO.PlayerDAO;

public class Places {
    PlayerDAO playerDAO;

    public Places(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    //Metodos
    public void townGate(Player player, Monster monster) {//Metodo que Inicia o Jogo
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Você está no portão do vilarejo.");
        System.out.println("Um guarda está em sua frente.");
        System.out.println("");
        System.out.println("O que você fará?");
        System.out.println("");
        System.out.println("1: Conversar com o guarda");
        System.out.println("2: Atacar o guarda");
        System.out.println("3: Se afastar");
        System.out.println("\n------------------------------------------------------------------\n");

        player.choice = player.myScanner.nextInt();

        if (player.choice == 1) {
            if (player.silverRing == 1 && player.oldPingent == 1) {
                player.ending();
            } else if(player.silverRing == 1 && player.oldPingent == 0){
                System.out.println("Guarda: Você novamente " + player.playerName + "?!");
                System.out.println("Guarda: Um simples anel de prata? Quer que eu faça o que com isso?");
                System.out.println("Guarda: Ah... Entendo. Se quiser então provar que é um aventureiro e bom, vá até a caverna ao oeste.");
                System.out.println("Guarda: Se conseguir trazer o pingente da Maga Mera que foi roubado, deixarei você entrar!");
                player.enterScanner.nextLine();
                townGate(player, monster);
            } else {
                System.out.println("Guarda: Você, forasteiro. Então seu nome é " + player.playerName
                        + "? \nPerdão mas não deixamos pessoas do seu tipo entrarem aqui");
                player.enterScanner.nextLine();
                townGate(player, monster);
            }

        } else if (player.choice == 2) {
            if(player.silverRing == 0) {
                player.playerHP = player.playerHP - 1;
                System.out.println(
                        "Guard: Não brinque comigo.\n\nO guarda te da um capote que você desiste.\n(Você recebeu 1 de dano)\n");
                System.out.println("Seu HP: " + player.playerHP);
                player.enterScanner.nextLine();
                townGate(player, monster);
            }else {
                System.out.println("Guarda: Tente qualquer graçinha novamente e eu te mato aqui mesmo!");
                player.enterScanner.nextLine();
                townGate(player, monster);
            }
        } else if (player.choice == 3) {
            crossRoad(player, monster);
        } else {
            townGate(player, monster);
        }
    }

    public void crossRoad(Player player, Monster monster) {

        playerDAO.updatePlayer(player.id, player.playerWeapon, player.maxHP, player.playerHP, player.silverRing, player.oldPingent);

        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Você esta em um cruzamento. Ao Sul está o vilarejo.\n\n");
        System.out.println("1: Ir para o Norte");
        System.out.println("2: Ir para o Leste");
        System.out.println("3: Ir para o Sul");
        System.out.println("4: Ir para o Oeste");
        System.out.println("\n------------------------------------------------------------------\n");

        player.choice = player.myScanner.nextInt();

        if (player.choice == 1) {
            north(player, monster);
        } else if (player.choice == 2) {
            east(player, monster);
        } else if (player.choice == 3) {
            townGate(player, monster);
        } else if (player.choice == 4) {
            west(player, monster);
        } else {
            crossRoad(player, monster);
        }
    }

    public void north(Player player, Monster monster) {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Tem um rio limpo. Você bebe da agua e descansa na beirada.");
        System.out.println("Seu HP foi restaurado.");
        player.playerHP = player.maxHP;
        System.out.println("Your HP: " + player.playerHP);
        System.out.println("\n\n1: Voltar para o Cruzamento");
        System.out.println("\n------------------------------------------------------------------\n");

        player.choice = player.myScanner.nextInt();

        if (player.choice == 1) {
            crossRoad(player, monster);
        } else {
            north(player, monster);
        }
    }

    public void east(Player player, Monster monster) {
        System.out.println("\n------------------------------------------------------------------\n");
        System.out.println("Você anda pela floresta e encontra uma Espada de Duas Mãos! Você pega a espada.");
        player.playerWeapon = "Espada de Duas Mãos";
        System.out.println("Sua Arma: " + player.playerWeapon);
        System.out.println("\n\n1: Voltar para o Cruzamento");
        System.out.println("\n------------------------------------------------------------------\n");

        player.choice = player.myScanner.nextInt();

        if (player.choice == 1) {
            crossRoad(player, monster);
        } else {
            east(player, monster);
        }
    }

    public void west(Player player, Monster monster) {
        if(!monster.alreadyDefeated) {
            System.out.println("\n------------------------------------------------------------------\n");
            System.out.println("Você encontrou um Goblin!\n");
            System.out.println("1: Lutar");
            System.out.println("2: Fugir");
            System.out.println("\n------------------------------------------------------------------\n");

            player.choice = player.myScanner.nextInt();

            if (player.choice == 1) {
                player.fight(this, monster);
            } else if (player.choice == 2) {
                crossRoad(player, monster);
            } else {
                west(player, monster);
            }
        }
        else {
            System.out.println("\n------------------------------------------------------------------\n");
            System.out.println("Você ve o que antes era um Goblin vivo");
            System.out.println("Tem uma caverna logo a sua frente\n");
            System.out.println("1: Voltar para o Cruzamento");
            System.out.println("2: Entrar na Caverna");
            System.out.println("\n------------------------------------------------------------------\n");

            player.choice = player.myScanner.nextInt();

            if (player.choice == 1) {
                crossRoad(player, monster);
            } else if (player.choice == 2) {
                cave(player, player.boss);
            } else {
                west(player, monster);
            }
        }
    }

    public void cave(Player player, Boss boss) {
        if(!boss.alreadyDefeated) {
            System.out.println("\n------------------------------------------------------------------\n");
            System.out.println("Você entra na caverna e ela está iluminada por tochas!");
            System.out.println("Andando mais um pouco você encontra um Goblin Master! Ele parecia já estar espeando por você");
            System.out.println("Goblin Master: O rango hoje vai ser do bom!\n");
            System.out.println("1: Lutar");
            System.out.println("2: Fugir");
            System.out.println("\n------------------------------------------------------------------\n");

            player.choice = player.myScanner.nextInt();

            if (player.choice == 1) {
                player.fightBoss(this);
            } else if (player.choice == 2) {
                crossRoad(player, boss);
            } else {
                west(player, boss);
            }
        }
        else {
            System.out.println("\n------------------------------------------------------------------\n");
            System.out.println("Você entra na caverna e ela está iluminada por tochas!");
            System.out.println("Andando mais um pouco você encontra os restos do Goblin Master. Mas parece que alguma coisa passou por ali.");
            System.out.println("Você olha pra trás e ve uma silhueta na entrada da caverna te espionando, logo ela foge.\n");
            System.out.println("1: Voltar para o cruzamento");
            System.out.println("\n------------------------------------------------------------------\n");

            player.choice = player.myScanner.nextInt();

            if (player.choice == 1) {
                crossRoad(player, boss);
            } else {
                west(player, boss);
            }
        }
    }
}
