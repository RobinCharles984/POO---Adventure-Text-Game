package DAO;
import java.sql.SQLException;

public class UsuarioDAO extends ConnectionDAO{

    public int autenticacao(String nome, String senha) {
        connectToDB();
        String sql = "select * from usuario where nome_usuario = ? and senha_usuario = ?";
        int i = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Nome = " + rs.getString("nome_usuario"));
                System.out.println("Senha = " + rs.getString("senha_usuario"));
                System.out.println("--------------------------------");
                i = rs.getInt("id_usuario");
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

    public int acharID(String nome) {
        connectToDB();
        String sql = "select * from player where playerName = ?";
        int i = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            while (rs.next()) {
                i = rs.getInt("id");
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

    public void usuarioSeguir(int id1, int id2) {
        if(id1 == 0 || id2 == 0){
            System.out.println("Usuario inexistente!");
        }else {
            connectToDB();
            String sql = "insert into seguidor_de (id_seguido, id_seguidor) values(?,?)";
            try {
                pst = con.prepareStatement(sql);
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.execute();
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
        }
    }
    public void insertUsuario(String nome, String senha, int id_servidor) {

        connectToDB();
        String sql = "INSERT INTO usuario (nome_usuario, senha_usuario, id_servidor) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, senha);
            pst.setInt(3, id_servidor);
            pst.execute();
        } catch (SQLException exc) {
            System.out.println("Erro insert: " + exc.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro insert finally: " + exc.getMessage());
            }
        }
    }
}