package modeloDaoX;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeansX.BeansUsuario;
import modeloConnectionX.ConectaBDX;


public class DaoUsuario {
    ConectaBDX conexaoBD = new ConectaBDX();
    BeansUsuario beansUsuario = new BeansUsuario();
     
    public void salvar(BeansUsuario beansUsuario){
         conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("insert into usuarios(usuario, senha, permissao)values(?,?,?)");
            pst.setString(1, beansUsuario.getUsuario()); 
            pst.setString(2, beansUsuario.getSenha());
            pst.setInt(3, beansUsuario.getPermissao());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dados Inseridos com Sucesso!", "Atenção!", 1);
            }
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao inserir dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
            conexaoBD.desconectaBD();
        }
     } 

    public BeansUsuario buscaUsuario(BeansUsuario beansUsuario){
        //abre a conexão
        conexaoBD.conectaBD();
        
        
        //executa a pesquisa no banco
        conexaoBD.executaSql("select * from usuarios where usuario like '%" + beansUsuario.getPesquisar()+"%'");
        try {
            conexaoBD.rs.first();
            beansUsuario.setUsuario(conexaoBD.rs.getString("usuario"));
            beansUsuario.setSenha(conexaoBD.rs.getString("senha"));
            beansUsuario.setPermissao(conexaoBD.rs.getInt("permissao"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
            conexaoBD.desconectaBD();
        }
          
      
        return beansUsuario;
    }
    public void editar(BeansUsuario beansUsuario){
        
        
        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("update usuarios set usuario=?, senha=?, permissao=? where usuario= ?");
            pst.setString(1, beansUsuario.getUsuario()); 
            pst.setString(2, beansUsuario.getSenha());
            pst.setInt(3, beansUsuario.getPermissao());
            pst.setString(4, beansUsuario.getUsuario());
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
    public void excluir(BeansUsuario beansUsuario){
        conexaoBD.conectaBD();
        
        try{
            PreparedStatement pst = conexaoBD.con.prepareStatement("delete from usuarios where usuario=?");
            pst.setString(1, beansUsuario.getUsuario());
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
