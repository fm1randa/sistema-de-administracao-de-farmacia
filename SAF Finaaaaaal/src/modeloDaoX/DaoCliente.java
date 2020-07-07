package modeloDaoX;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeansX.BeansCliente;
import modeloConnectionX.ConectaBDX;


public class DaoCliente {
    ConectaBDX conexaoBD = new ConectaBDX();
    BeansCliente beansCliente = new BeansCliente();
     
    public void salvar(BeansCliente beansCliente){
         conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("insert into clientes(nome_cliente, cpf_cliente, email_cliente, tel_cliente)values(?,?,?,?)");
            pst.setString(1, beansCliente.getNome()); 
            pst.setString(2, beansCliente.getCpf());
            pst.setString(3, beansCliente.getEmail());
            pst.setString(4, beansCliente.getTelefone());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!", "Atenção!", 1);
            }
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao inserir dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
            conexaoBD.desconectaBD();
        }
     } 

    public BeansCliente buscaCliente(BeansCliente beansCliente){
        //abre a conexão
        conexaoBD.conectaBD();
        
        
        //executa a pesquisa no banco
        conexaoBD.executaSql("select * from clientes where nome_cliente like '%" + beansCliente.getPesquisar()+"%'");
        try {
            conexaoBD.rs.first();
            beansCliente.setCodigo(conexaoBD.rs.getInt("cod_cliente"));
            beansCliente.setNome(conexaoBD.rs.getString("nome_cliente"));
            beansCliente.setCpf(conexaoBD.rs.getString("cpf_cliente"));
            beansCliente.setTelefone(conexaoBD.rs.getString("tel_cliente"));
            beansCliente.setEmail(conexaoBD.rs.getString("email_cliente"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
            conexaoBD.desconectaBD();
        }
          
      
        return beansCliente;
    }
    public void editar(BeansCliente beansCliente){
        
        
        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("update clientes set nome_cliente=?, cpf_cliente=?, email_cliente=?, tel_cliente=? where cod_cliente =?");
            pst.setString(1, beansCliente.getNome());
            pst.setString(2, beansCliente.getCpf());
            pst.setString(3, beansCliente.getEmail());
            pst.setString(4, beansCliente.getTelefone());
            pst.setInt(5, beansCliente.getCodigo());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados editados com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
        
        // fecha a conexão
        conexaoBD.desconectaBD();
        }
    }
    public void excluir(BeansCliente beansCliente){
        conexaoBD.conectaBD();
        
        try{
            PreparedStatement pst = conexaoBD.con.prepareStatement("delete from clientes where cod_cliente=?");
            pst.setInt(1, beansCliente.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!", "Atenção!", 1);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados: \n" + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
            conexaoBD.desconectaBD();
        }
    }
}
