import Character.Enemy.Enemy;
import Character.Player.Player;
import Exceptions.CommandNotFoundException;

public class GameElements {
    //Atributos
    public String command;

    //Metodos
    public void commandFunction(String cmd, Player player, Enemy enemy) throws CommandNotFoundException {
        switch (cmd)
        {
            case "atacar":

                break;

            case "defender":
                break;

            case "fugir":
                break;

            case "usar":
                break;

            case "equipar":
                break;

            case "observar":
                break;

            case "inventario":
                System.out.println();
                break;

            case "continuar":
                System.out.println();
                break;

            case default:
                throw new CommandNotFoundException("Comando não encontrado! Tente novamente");
        }
    }
}
