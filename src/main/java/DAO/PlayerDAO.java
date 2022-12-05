package DAO;
import Game.Player;
import java.sql.SQLException;

public class PlayerDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    public int autenticacao (int id, Player player) {
        connectToDB();
        String sql = "select * from player where id = ?";
        int i = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                player.playerName = rs.getString("playerName");
                player.playerWeapon = rs.getString("playerWeapon");
                player.maxHP = rs.getInt("maxHP");
                player.playerHP = rs.getInt("playerHP");
                player.silverRing = rs.getInt("silverRing");
                player.oldPingent = rs.getInt("oldPingent");
            }
        } catch (SQLException exc) {
            System.out.println("Erro usuarioDAO: " + exc.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro usuarioDAO FINALLY: " + exc.getMessage());
            }
        }
        return i;
    }

    //INSERT
    public boolean insertPlayer(Player player) {
        connectToDB();
        String sql = "INSERT INTO player (playerName, playerWeapon, maxHP, playerHP, silverRing, oldPingent) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, player.playerName);
            pst.setString(2, player.playerWeapon);
            pst.setInt(3,player.maxHP);
            pst.setInt(4,player.playerHP);
            pst.setInt(5,player.silverRing);
            pst.setInt(6,player.oldPingent);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro insert: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public void avancoPlayer(String nome) {
        connectToDB();
        String sql = "select * from player where playerName = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Avanco de: " + rs.getString("playerName"));
                System.out.println("Arma: " + rs.getString("playerWeapon"));
                System.out.println("Vida: "+ rs.getInt("playerHP") + "/" + rs.getInt("maxHP"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public void updatePlayer(int id, String playerWeapon, int maxHP, int playerHP, int silverRing, int oldPingent) {
        connectToDB();
        String sql = "update player set playerWeapon = ?, maxHP = ?, playerHP = ?, silverRing = ?, oldPingent =? where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, playerWeapon);
            pst.setInt(2,maxHP);
            pst.setInt(3,playerHP);
            pst.setInt(4,silverRing);
            pst.setInt(5,oldPingent);
            pst.setInt(6,id);
            pst.execute();
            System.out.println("Progresso atualizado!" + id);
        } catch (SQLException ex) {
            System.out.println("Erro updatePLayer = " + ex.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: updatePLayer" + exc.getMessage());
            }
        }
    }

}