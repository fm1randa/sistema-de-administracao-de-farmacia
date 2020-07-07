/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDaoX;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeansX.BeansVendas;
import modeloConnectionX.ConectaBDX;

/**
 *
 * @author Filipe
 */
public class DaoVendas {

    ConectaBDX conexaoBD = new ConectaBDX();
    BeansVendas beansVendas = new BeansVendas();
    
    public void salvar(BeansVendas beansVendas) {
        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("insert into vendas(nomeproduto, valortotal, quantidade, nomecliente, nomemedico, datavenda, categoria, idproduto, receita)values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, beansVendas.getNomeProduto());
            pst.setDouble(2, beansVendas.getValorTotal());
            pst.setInt(3, beansVendas.getQuantidade());
            pst.setString(4, beansVendas.getNomeCliente());
            pst.setString(5, beansVendas.getNomeMedico());
            pst.setDate(6, (Date) beansVendas.getDataVenda());
            pst.setString(7, beansVendas.getCategoria());
            pst.setInt(8, beansVendas.getIdProduto());
            pst.setString(9, beansVendas.getReceita());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Venda registrada!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar venda: " + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {
            conexaoBD.desconectaBD();
        }
    }

    public void editar(BeansVendas beansVendas) {
        conexaoBD.conectaBD();
        try {
            PreparedStatement pst = (PreparedStatement) conexaoBD.con.prepareStatement("update vendas set nomeproduto=?, valortotal=?, quantidade=?, nomecliente=?, nomemedico=?, datavenda=?, categoria=?, idproduto=?, receita=? where idvenda=?");
            pst.setString(1, beansVendas.getNomeProduto());
            pst.setDouble(2, beansVendas.getValorTotal());
            pst.setInt(3, beansVendas.getQuantidade());
            pst.setString(4, beansVendas.getNomeCliente());
            pst.setString(5, beansVendas.getNomeMedico());
            pst.setDate(6, beansVendas.getDataVenda());
            pst.setString(7, beansVendas.getCategoria());
            pst.setInt(8, beansVendas.getIdProduto());
            pst.setString(9, beansVendas.getReceita());
            pst.setInt(10, beansVendas.getIdVenda());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Venda editada com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados: " + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {

            // fecha a conexão
            conexaoBD.desconectaBD();
        }
    }

    public void excluir(BeansVendas beansVendas) {
        conexaoBD.conectaBD();

        try {
            PreparedStatement pst = conexaoBD.con.prepareStatement("delete from vendas where idvenda=?");
            pst.setInt(1, beansVendas.getIdVenda());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!", "Atenção!", 1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados: \n" + ex + "\n \n Contate a equipe de T.I.", "Atenção!", 0);
        } finally {
            conexaoBD.desconectaBD();
        }
    }
    public BeansVendas buscaVendas(BeansVendas beansVendas){
        conexaoBD.conectaBD();
        
        
        //executa a pesquisa no banco
        conexaoBD.executaSql("select * from vendas where nomeproduto like '%" + beansVendas.getPesquisa()+"%'");
        try {
            conexaoBD.rs.first();
            beansVendas.setIdProduto(conexaoBD.rs.getInt("idproduto"));
            beansVendas.setNomeProduto(conexaoBD.rs.getString("nomeproduto"));
            beansVendas.setValorTotal(conexaoBD.rs.getDouble("valortotal"));
            beansVendas.setQuantidade(conexaoBD.rs.getInt("quantidade"));
            beansVendas.setNomeCliente(conexaoBD.rs.getString("nomecliente"));
            beansVendas.setNomeMedico(conexaoBD.rs.getString("nomemedico"));
            beansVendas.setDataVenda(conexaoBD.rs.getDate("datavenda"));
            beansVendas.setCategoria(conexaoBD.rs.getString("categoria"));
            beansVendas.setReceita(conexaoBD.rs.getString("receita"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar dados: "+ ex +"\n \n Contate a equipe de T.I.", "Atenção!", 0);
        }
        finally{
            conexaoBD.desconectaBD();
        }
         
        return beansVendas;
    }
}
