package modeloBeansX;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danie
 */
public class ModeloTabelaX extends AbstractTableModel{
    
    private ArrayList linhas = null;
    private String colunas[] = null;
    
    
    // método construtor para inicializar a tabela com dados
    public ModeloTabelaX (ArrayList linha, String colunas[]){
        setLinhas(linha);
        setColunas(colunas);
    }

    /**
     * @return the linhas
     */
    public ArrayList getLinhas() {
        return linhas;
    }

    /**
     * @param linhas the linhas to set
     */
    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    /**
     * @return the colunas
     */
    public String[] getColunas() {
        return colunas;
    }

    /**
     * @param colunas the colunas to set
     */
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
    // conta o número de colunas da tabela
    public int getColumnCount(){
        return colunas.length;
    }
    
    // metodo de conta quantas linhas terão no array
    public int getRowCount(){
        return linhas.size();   
    }
    
    // retorna a qtde de colunas, ou seja, depois de pegar o nome ele retorna a quantidade de colunas
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    
    // metodo que monta a tabela. Ele q adiciona as linhas da tabela.
    public Object getValueAt(int numLin, int numCol){
        Object linha[] = (Object[]) getLinhas().get(numLin); 
        return linha[numCol];
    }
    
}
