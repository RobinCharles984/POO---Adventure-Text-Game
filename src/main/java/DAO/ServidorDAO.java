package DAO;

import java.sql.SQLException;

public class ServidorDAO extends ConnectionDAO {

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    private int nUsuarios;

    //INSERT
    public boolean updateServidor(int id, int id_servidor) {
        connectToDB();
        String sql = "UPDATE servidor SET nUsuarios =? where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, id_servidor);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
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

}
