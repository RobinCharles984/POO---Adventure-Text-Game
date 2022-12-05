package Game;

import DAO.PlayerDAO;
import DAO.ServidorDAO;
import DAO.UsuarioDAO;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Objetos do BD
        ServidorDAO servidorDAO = new ServidorDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PlayerDAO playerDAO = new PlayerDAO();

        //Entrando com dados do usuario
        System.out.println("Entre com uma conta");
        System.out.println("Coloque seu nome: ");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println("Coloque sua senha: ");
        String senha = scanner.nextLine();

        //criando os personagens
        Player player = new Player();
        Monster monster = new Monster();
        Places places = new Places(playerDAO);

        //definir o id com base no nome e senha do usuario
        int id = usuarioDAO.autenticacao(nome, senha);
        player.id = id;

        if(id == 0){
            //se o id é zero, ele n existe, por isso, vai criar um personagem no servidor 1
            usuarioDAO.insertUsuario(nome, senha, 1);

            //aqui a gente vai atualizar os dados do servidor, o id do jogador atual é a quantidade de usuarios
            servidorDAO.updateServidor(usuarioDAO.autenticacao(nome, senha), 1);

            //Cria player
            System.out.println("Voce não tinha um usuário até agora, agora tem!");
            player.playerSetUp(monster, false);
            playerDAO.insertPlayer(player);
        }
        else {
            //se nao existe, ele pode continuar de onde parou
            player.playerSetUp(monster, true);
            playerDAO.autenticacao(id, player);
        }

        System.out.println("Coloque um nome de um player para seguir e ver o avanço: ");
        String nomeBuscado = scanner.nextLine();
        usuarioDAO.usuarioSeguir(usuarioDAO.acharID(player.playerName), usuarioDAO.acharID(nomeBuscado));
        playerDAO.avancoPlayer(nomeBuscado);

        //aqui ele vai pra town gate, pensei em colocar isso lá no id = 0 e no else na crossroad
        places.townGate(player, monster);
    }
}
