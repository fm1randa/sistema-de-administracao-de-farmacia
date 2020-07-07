/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDaoX;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeansX.BeansEstoque;
import modeloConnectionX.ConectaBDX;

/**
 *
 * @author Filipe
 */
public class DaoEstoque {

    ConectaBDX conexaoBD = new ConectaBDX();
    BeansEstoque beansEstoque = new BeansEstoque();

    public void salvar(BeansEstoque beansEstoque) {
        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("insert into estoque(nomeproduto, valorcompra, valorvenda, quantidade, fornecedor, categoria, receita)values(?,?,?,?,?,?,?)");
            pst.setString(1, beansEstoque.getNomeproduto());
            pst.setDouble(2, beansEstoque.getValorcompra());
            pst.setDouble(3, beansEstoque.getValorvenda());
            pst.setInt(4, beansEstoque.getQuantidade());
            pst.setString(5, beansEstoque.getFornecedor());
            pst.setString(6, beansEstoque.getCategoria());
            pst.setString(7, beansEstoque.getReceita());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: " + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {
            conexaoBD.desconectaBD();
        }
    }
    public void editar(BeansEstoque beansEstoque){
        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("update estoque set nomeproduto=?, valorcompra=?, valorvenda=?, quantidade=?, fornecedor=?, categoria=?, receita=? where idproduto=?");
            pst.setString(1, beansEstoque.getNomeproduto());
            pst.setDouble(2, beansEstoque.getValorcompra());
            pst.setDouble(3, beansEstoque.getValorvenda());
            pst.setInt(4, beansEstoque.getQuantidade());
            pst.setString(5, beansEstoque.getFornecedor());
            pst.setString(6, beansEstoque.getCategoria());
            pst.setString(7, beansEstoque.getReceita());
            pst.setInt(8, beansEstoque.getIdproduto());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados: " + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {

            // fecha a conexão
            conexaoBD.desconectaBD();
        }
    }
    public void darBaixa(BeansEstoque beansEstoque) {
        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("update estoque set quantidade=? where idproduto=?");
            pst.setInt(1, beansEstoque.getQuantidade());
            pst.setInt(2, beansEstoque.getIdproduto());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar venda: " + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {

            // fecha a conexão
            conexaoBD.desconectaBD();
        }
    }

    public void excluir(BeansEstoque beansEstoque) {
        conexaoBD.conectaBD();

        try {
            PreparedStatement pst = conexaoBD.con.prepareStatement("delete from estoque where idproduto=?");
            pst.setInt(1, beansEstoque.getIdproduto());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados: \n" + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {
            conexaoBD.desconectaBD();
        }
    }
    public BeansEstoque buscaEstoque(BeansEstoque beansEstoque){
        conexaoBD.conectaBD();
        
        
        //executa a pesquisa no banco
        conexaoBD.executaSql("select * from estoque where nomeproduto like '%" + beansEstoque.getPesquisa()+"%'");
        try {
            conexaoBD.rs.first();
            beansEstoque.setIdproduto(conexaoBD.rs.getInt("idproduto"));
            beansEstoque.setNomeproduto(conexaoBD.rs.getString("nomeproduto"));
            beansEstoque.setValorcompra(conexaoBD.rs.getDouble("valorcompra"));
            beansEstoque.setValorvenda(conexaoBD.rs.getDouble("valorvenda"));
            beansEstoque.setQuantidade(conexaoBD.rs.getInt("quantidade"));
            beansEstoque.setFornecedor(conexaoBD.rs.getString("fornecedor"));
            beansEstoque.setCategoria(conexaoBD.rs.getString("categoria"));
            beansEstoque.setReceita(conexaoBD.rs.getString("receita"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
            conexaoBD.desconectaBD();
        }
         
        return beansEstoque;
    }
}
