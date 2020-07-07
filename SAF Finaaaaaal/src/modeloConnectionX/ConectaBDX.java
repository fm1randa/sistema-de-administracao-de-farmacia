package modeloConnectionX;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectaBDX {
    
    public Statement stm;
    public ResultSet rs;
    public Connection con;
    private String driver = "com.mysql.jdbc.Driver";
    private String caminho = "jdbc:mysql://localhost:3306/farmacia?useTimezone=true&serverTimezone=UTC";
    private String usuario = "root";
    private String senha = "root";
    
    //conecta ao banco de dados
    public void conectaBD(){
        
        try {
            System.setProperty("jdbc.Drivers", driver);
        
            con = DriverManager.getConnection(caminho,usuario, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar se conectar ao BD" + ex);
        }
    }
    
    public void conexaoBD(){ /* metodo para conectar */
       
        try { /*usado para tentar se conectar com o banco */
            System.setProperty("jdbc.Drivers", driver); /* atribui a propriedade do drive de conexao */
            con = DriverManager.getConnection(/*url*/caminho, /*user*/usuario,/*password*/senha);/* recebe a conexão */
            //JOptionPane.showMessageDialog(null,"Conectado ao BD com sucesso");
        } catch (SQLException ex) { /* captura o erro e exibe */
            Logger.getLogger(ConectaBDX.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Erro ao conectar ao BD:" + ex);
        }
    }
    
    public void desconectaBD(){
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao tentar se conectar ao BD" + ex);
        }
    }
    public void executaSql(String sql){
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao executar o sql.\n" + ex.getMessage());
        }
        
    }
}