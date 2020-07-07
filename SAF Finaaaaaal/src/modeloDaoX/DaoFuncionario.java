package modeloDaoX;

//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeansX.BeansFuncionario;
import modeloConnectionX.ConectaBDX;

public class DaoFuncionario {

    ConectaBDX conexaoBD = new ConectaBDX();
    BeansFuncionario beansfuncionario = new BeansFuncionario();

    public void salvar(BeansFuncionario beansfuncionario) {
        conexaoBD.conectaBD();
        try {

            PreparedStatement pst = conexaoBD.con.prepareStatement("insert into enderecos(rua, numero, bairro, cidade, estado, comp, cep, cod_end2) values(?,?,?,?,?,?,?,?)");
            pst.setString(1, beansfuncionario.getRua());
            pst.setInt(2, beansfuncionario.getNumero());
            pst.setString(3, beansfuncionario.getBairro());
            pst.setString(4, beansfuncionario.getCidade());
            pst.setString(5, beansfuncionario.getEstado());
            pst.setString(6, beansfuncionario.getComplemento());
            pst.setInt(7, beansfuncionario.getCep());
            pst.setInt(8, beansfuncionario.getCodigoend2());
            pst.execute();

            pst = (PreparedStatement) conexaoBD.con.prepareStatement("insert into funcionarios(nomefunc, CPF, telefone, cargo, RG, nascimento, usuario, senha, Cod_func)values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, beansfuncionario.getNome());
            pst.setString(2, beansfuncionario.getCPF());
            pst.setString(3, beansfuncionario.getTelefone());
            pst.setString(4, beansfuncionario.getCargo());
            pst.setString(5, beansfuncionario.getRG());
            pst.setDate(6, beansfuncionario.getNascimento());
            pst.setString(7, beansfuncionario.getUsuario());
            pst.setString(8, beansfuncionario.getSenha());
            pst.setInt(9, beansfuncionario.getCod_func());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!", "Atenção!", 1);
        } catch (SQLIntegrityConstraintViolationException cp) {
            int numcerto = 0;
            //JOptionPane.showMessageDialog(null, "CPF já cadastrado!\nTentando excluir possíveis dados de informações do paciente inseridos...", "Atenção!", 1);
            JOptionPane.showMessageDialog(null, "CPF já cadastrado!\n"+cp, "Atenção!", 1);
            try {
                conexaoBD.executaSql("select cod_end from funcionarios order by cod_end desc");
                PreparedStatement pst = conexaoBD.con.prepareStatement("delete from funcionarios where Cod_func =" + beansfuncionario.getCod_func());
                pst.execute();
                //JOptionPane.showMessageDialog(null, "Sucesso ao excluir dados do paciente, nenhum dado foi inserido.", "Atenção!", 1);
                try {
                    //JOptionPane.showMessageDialog(null, "Tentando setar auto increment da tabela paciente...", "Atenção!", 1);
                    conexaoBD.rs.first();
                    PreparedStatement pst2 = conexaoBD.con.prepareStatement("ALTER TABLE funcionarios AUTO_INCREMENT = ?");
                    //JOptionPane.showMessageDialog(null, "ULTIMO COD END:" + conexaoBD.rs.getInt("cod_end"));
                    numcerto = conexaoBD.rs.getInt("cod_end") + 1;
                    //JOptionPane.showMessageDialog(null, "NUMCERTO PACIENTE:" + numcerto);
                    pst2.setInt(1, numcerto);
                    pst2.execute();
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, "Erro ao setar auto increment! \n" + err, "Atenção!", 1);
                }
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Nenhum dado foi encontrado para excluir ou ocorreu algum problema! \n" + ex1, "Atenção!", 1);
            }
            try {
                conexaoBD.executaSql("select cod_end from enderecos order by cod_end desc");
                //JOptionPane.showMessageDialog(null, "Tentando excluir possíveis dados de endereço do paciente inseridos...", "Atenção!", 1);
                PreparedStatement pst = conexaoBD.con.prepareStatement("delete from enderecos where cod_end2 =" + beansfuncionario.getCodigoend2());
                pst.execute();
                //JOptionPane.showMessageDialog(null, "Sucesso ao excluir dados do paciente, nenhum dado foi inserido.", "Atenção!", 1);
                try {
                    //JOptionPane.showMessageDialog(null, "Tentando setar auto increment da tabela enderecos...", "Atenção!", 1);
                    conexaoBD.rs.first();
                    PreparedStatement pst2 = conexaoBD.con.prepareStatement("ALTER TABLE enderecos AUTO_INCREMENT = ?");
                    //JOptionPane.showMessageDialog(null, "ULTIMO COD END:" + conexaoBD.rs.getInt("cod_end"));
                    //JOptionPane.showMessageDialog(null, "NUMCERTO ENDERECO:" + numcerto);
                    pst2.setInt(1, numcerto);
                    pst2.execute();
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, "Erro ao setar auto increment! \n" + err, "Atenção!", 1);
                }
            } catch (SQLException ex2) {
                JOptionPane.showMessageDialog(null, "Nenhum dado foi encontrado para excluir ou ocorreu algum problema! \n" + ex2, "Atenção!", 1);
            }

        } catch (SQLException ex) {
            //conexaoBD.executaSql("select * from funcionarios where nome_pac ='" + beansfuncionario.getNome()+ "' and nasc_pac ='"+beansfuncionario.getNascimento()+"'");
            int numcerto = 0;
            try {
                //conexaoBD.rs.first();
                conexaoBD.executaSql("select cod_end from funcionarios order by cod_end desc");
                //JOptionPane.showMessageDialog(null, "Erro ao inserir dados! \n" + ex + "\nTentando excluir possíveis dados de informações do paciente inseridos...", "Atenção!", 1);
                JOptionPane.showMessageDialog(null, "Erro ao inserir dados! \n" + ex, "Atenção!", 1);
                PreparedStatement pst = conexaoBD.con.prepareStatement("delete from funcionarios where Cod_func =" + beansfuncionario.getCod_func());
                pst.execute();
                //JOptionPane.showMessageDialog(null, "Sucesso ao excluir dados do paciente, nenhum dado foi inserido.", "Atenção!", 1);
                try {
                    //JOptionPane.showMessageDialog(null, "Tentando setar auto increment da tabela paciente...", "Atenção!", 1);
                    conexaoBD.rs.first();
                    PreparedStatement pst2 = conexaoBD.con.prepareStatement("ALTER TABLE funcionarios AUTO_INCREMENT = ?");
                    //JOptionPane.showMessageDialog(null, "ULTIMO COD END:" + conexaoBD.rs.getInt("cod_end"));
                    numcerto = conexaoBD.rs.getInt("cod_end") + 1;
                    //JOptionPane.showMessageDialog(null, "NUMCERTO da tabela PACIENTES:" + numcerto);
                    pst2.setInt(1, numcerto);
                    pst2.execute();
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, "Erro ao setar auto increment! \n" + err, "Atenção!", 1);
                }
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Nenhum dado foi encontrado para excluir ou ocorreu algum problema! \n" + ex1, "Atenção!", 1);
            }
            try {
                conexaoBD.executaSql("select cod_end from enderecos order by cod_end desc");
                //JOptionPane.showMessageDialog(null, "Tentando excluir possíveis dados de endereço do paciente inseridos...", "Atenção!", 1);
                PreparedStatement pst = conexaoBD.con.prepareStatement("delete from enderecos where cod_end2 =" + beansfuncionario.getCodigoend2());
                pst.execute();
                //JOptionPane.showMessageDialog(null, "Sucesso ao excluir dados do paciente, nenhum dado foi inserido.", "Atenção!", 1);
                try {
                    //JOptionPane.showMessageDialog(null, "Tentando setar auto increment da tabela enderecos...", "Atenção!", 1);
                    conexaoBD.rs.first();
                    PreparedStatement pst2 = conexaoBD.con.prepareStatement("ALTER TABLE enderecos AUTO_INCREMENT = ?");
                    //JOptionPane.showMessageDialog(null, "ULTIMO COD END:" + conexaoBD.rs.getInt("cod_end"));
                    //JOptionPane.showMessageDialog(null, "NUMCERTO da tabela ENDERECOS:" + numcerto);
                    pst2.setInt(1, numcerto);
                    pst2.execute();
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, "Erro ao setar auto increment! \n" + err, "Atenção!", 1);
                }
            } catch (SQLException ex2) {
                JOptionPane.showMessageDialog(null, "Nenhum dado foi encontrado para excluir ou ocorreu algum problema! \n" + ex2, "Atenção!", 1);
            }

            // JOptionPane.showMessageDialog(null, "Erro ao inserir dados: \n" + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } /*
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao inserir dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
         */ finally {
            conexaoBD.desconectaBD();
        }
    }

    public BeansFuncionario buscaFuncionario(BeansFuncionario beansfuncionario) {
        //abre a conexão
        conexaoBD.conectaBD();

        //executa a pesquisa no banco
        conexaoBD.executaSql("select * from funcionarios join enderecos on enderecos.cod_end = funcionarios.cod_end where nomefunc like '%" + beansfuncionario.getPesquisar() + "%';");
        try {
            conexaoBD.rs.first();
            beansfuncionario.setCod_func(conexaoBD.rs.getInt("Cod_func"));
            beansfuncionario.setNome(conexaoBD.rs.getString("nomefunc"));
            beansfuncionario.setCPF(conexaoBD.rs.getString("CPF"));
            beansfuncionario.setTelefone(conexaoBD.rs.getString("telefone"));
            beansfuncionario.setCargo(conexaoBD.rs.getString("cargo"));
            beansfuncionario.setRG(conexaoBD.rs.getString("RG"));
            beansfuncionario.setNascimento(conexaoBD.rs.getDate("nascimento"));
            beansfuncionario.setUsuario(conexaoBD.rs.getString("usuario"));
            beansfuncionario.setSenha(conexaoBD.rs.getString("senha"));
            
            beansfuncionario.setCodigoend(conexaoBD.rs.getInt("cod_end"));
            beansfuncionario.setRua(conexaoBD.rs.getString("rua"));
            beansfuncionario.setNumero(conexaoBD.rs.getInt("numero"));
            beansfuncionario.setBairro(conexaoBD.rs.getString("bairro"));
            beansfuncionario.setCidade(conexaoBD.rs.getString("cidade"));
            beansfuncionario.setEstado(conexaoBD.rs.getString("estado"));
            beansfuncionario.setComplemento(conexaoBD.rs.getString("comp"));
            beansfuncionario.setCep(conexaoBD.rs.getInt("cep"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {
            conexaoBD.desconectaBD();
        }

        return beansfuncionario;
    }

    public void editar(BeansFuncionario beansfuncionario) {

        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("update funcionarios set nomefunc=?, CPF=?, Telefone=?, cargo=?, RG=?, nascimento=?, usuario=?, senha=? where Cod_func = ?");
            pst.setString(1, beansfuncionario.getNome());
            pst.setString(2, beansfuncionario.getCPF());
            pst.setString(3, beansfuncionario.getTelefone());
            pst.setString(4, beansfuncionario.getCargo());
            pst.setString(5, beansfuncionario.getRG());
            pst.setDate(6, beansfuncionario.getNascimento());
            pst.setString(7, beansfuncionario.getUsuario());
            pst.setString(8, beansfuncionario.getSenha());
            pst.setInt(9, beansfuncionario.getCod_func());
            pst.execute();
            
            pst = conexaoBD.con.prepareStatement("update enderecos set rua=?, numero=?, bairro=?, cidade=?, estado=?, comp=?, cep=? where cod_end=?");
            pst.setString(1, beansfuncionario.getRua());
            pst.setInt(2, beansfuncionario.getNumero());
            pst.setString(3, beansfuncionario.getBairro());
            pst.setString(4, beansfuncionario.getCidade());
            pst.setString(5, beansfuncionario.getEstado());
            pst.setString(6, beansfuncionario.getComplemento());
            pst.setInt(7, beansfuncionario.getCep());
            pst.setInt(8, beansfuncionario.getCodigoend());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Dados editados com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados: " + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {

            // fecha a conexão
            conexaoBD.desconectaBD();
        }
    }

    public void excluir(BeansFuncionario beansfuncionario) {
        conexaoBD.conectaBD();

        try {
            PreparedStatement pst = conexaoBD.con.prepareStatement("delete from funcionarios where Cod_func=?");
            pst.setInt(1, beansfuncionario.getCod_func());
            pst.execute();
            pst = conexaoBD.con.prepareStatement("delete from enderecos where cod_end=?");
            pst.setInt(1, beansfuncionario.getCodigoend());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados: \n" + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {
            conexaoBD.desconectaBD();
        }
    }
}
