/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloBeansX;

/**
 *
 * @author Filipe
 */
public class BeansEstoque {

    /**
     * @return the receita
     */
    public String getReceita() {
        return receita;
    }

    /**
     * @param receita the receita to set
     */
    public void setReceita(String receita) {
        this.receita = receita;
    }

    /**
     * @return the pesquisa
     */
    public String getPesquisa() {
        return pesquisa;
    }

    /**
     * @param pesquisa the pesquisa to set
     */
    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    /**
     * @return the idproduto
     */
    public int getIdproduto() {
        return idproduto;
    }

    /**
     * @param idproduto the idproduto to set
     */
    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    /**
     * @return the nomeproduto
     */
    public String getNomeproduto() {
        return nomeproduto;
    }

    /**
     * @param nomeproduto the nomeproduto to set
     */
    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    /**
     * @return the valorcompra
     */
    public double getValorcompra() {
        return valorcompra;
    }

    /**
     * @param valorcompra the valorcompra to set
     */
    public void setValorcompra(double valorcompra) {
        this.valorcompra = valorcompra;
    }

    /**
     * @return the valorvenda
     */
    public double getValorvenda() {
        return valorvenda;
    }

    /**
     * @param valorvenda the valorvenda to set
     */
    public void setValorvenda(double valorvenda) {
        this.valorvenda = valorvenda;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    private int idproduto;
    private String nomeproduto;
    private double valorcompra;
    private double valorvenda;
    private int quantidade;
    private String fornecedor;
    private String categoria;
    private String pesquisa;
    private String receita;
    
}
