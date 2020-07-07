/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visão;

import controleX.CriptografaSenha;
import controleX.ValidaCpf;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloConnectionX.ConectaBDX;
import tela_de_sair.Sair;
import java.awt.Color;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import modeloBeansX.BeansCliente;
import modeloBeansX.BeansFuncionario;
import modeloBeansX.ModeloTabelaX;
import modeloDaoX.DaoCliente;
import modeloDaoX.DaoFuncionario;
/*
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
 */
import visão.CadProdutos;

/**
 *
 * @author Filipe
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    String user;
    String permissao;
    String nome;
    ConectaBDX conexaoBD = new ConectaBDX();

    //CADASTRO DE USUARIO
    BeansCliente _beansCliente = new BeansCliente();
    BeansCliente beansCliente = new BeansCliente();
    DaoCliente daoCliente = new DaoCliente();
    ValidaCpf verificaCpf = new ValidaCpf();
    int flag = 0;
    int flagcriando = 0;
    String formaberto = "";
    int flagaberto = 0;
    //CADASTRO DE FUNCIONARIO
    BeansFuncionario _beansfuncionario = new BeansFuncionario();
    BeansFuncionario beansfuncionario = new BeansFuncionario();
    DaoFuncionario daofuncionario = new DaoFuncionario();

    public TelaPrincipal(String user, String permissao, String nome) {

        initComponents();

        jTextFieldCodEndFunc.setVisible(false);

        jInternalFrameConfirmacao.setVisible(false);
        jLabelBemVindo.setText("Bem vindo, " + nome + "!");
        preencherTabelaProdutosEmFalta("select * from estoque where quantidade <= 20 order by quantidade asc");

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        //TELA PRINCIPAL
        jButtonAddFuncionario.setBackground(new Color(0, 0, 0, 0));
        jButtonAddCliente.setBackground(new Color(0, 0, 0, 0));
        jButtonEstoque.setBackground(new Color(0, 0, 0, 0));
        jButtonMinimizar.setBackground(new Color(0, 0, 0, 0));
        jButtonSair.setBackground(new Color(0, 0, 0, 0));
        jButtonSairlogin.setBackground(new Color(0, 0, 0, 0));
        jPanelFunciomario2.setBackground(new Color(0, 0, 0, 0));
        jButtonVendas.setBackground(new Color(0, 0, 0, 0));
        jPanelCadastroCliente.setBackground(new Color(0, 0, 0, 0));
        jButtonRelatorios.setBackground(new Color(0, 0, 0, 0));
        jPanelPrincipal.setBackground(new Color(0, 0, 0, 0));
        jButtonSairCliente.setBackground(new Color(0,0,0,0));
        jButtonSairFunc.setBackground(new Color(0,0,0,0));
        jFormattedTextFieldTelCliente.setBackground(new Color(0,0,0,0));
        jFormattedTextFieldCPFCliente.setBackground(new Color(0,0,0,0));
        jTextFieldNomeCliente.setBackground(new Color(0,0,0,0));
        jTextFieldEmailCliente.setBackground(new Color(0,0,0,0));
        

        //CAD FUNC
        preencherTabelaFunc("select * from funcionarios join enderecos on enderecos.cod_end = funcionarios.cod_end order by nomefunc");
        //jScrollPaneFuncionarios.setBackground(new Color(0, 0, 0, 0));
        //jTableFuncionarios.setBackground(new Color(0, 0, 0, 0));
        jButtonNovoFunc.setBackground(new Color(0, 0, 0, 0));
        jButtonSalvarFunc.setBackground(new Color(0, 0, 0, 0));
        jButtonEditarFunc.setBackground(new Color(0, 0, 0, 0));
        jButtonCancelarFunc.setBackground(new Color(0, 0, 0, 0));
        jButtonDelFunc.setBackground(new Color(0, 0, 0, 0));

        jTextFieldCodFunc.setVisible(false);
        jPanelPraTampar.setVisible(false);

        //CAD CLIENTE
        preencherTabelaUsuario("select * from clientes order by nome_cliente");
        jButtonSalvarCliente.setBackground(new Color(0, 0, 0, 0));
        jButtonCancelarCliente.setBackground(new Color(0, 0, 0, 0));

        this.setResizable(false); // NÃO PERMITIR REDIMENSIONAMENTO

        jLabelUsuario.setText(user);
        this.permissao = permissao; //CONTROLE DE ACESSO
        this.user = user;

        //ICONE DO PROGRAMA
        URL url = this.getClass().getResource("/imagens/logoSafNormal.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(iconeTitulo);

        jPanelCadastroCliente.setVisible(false);
        jPanelFunciomario2.setVisible(false);
        ((BasicInternalFrameUI) jInternalFrameConfirmacao.getUI()).setNorthPane(null); //retirar o painel superior 

    }

    TelaPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    TelaPrincipal(String _tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jInternalFrameConfirmacao = new javax.swing.JInternalFrame();
        jButtonSair2 = new javax.swing.JButton();
        jButtonNao = new javax.swing.JButton();
        jLabelBackground1 = new javax.swing.JLabel();
        jPanelPrincipal = new javax.swing.JPanel();
        jLabelBemVindo = new javax.swing.JLabel();
        jLabelProdutosEmFalta = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProdutosEmFalta = new javax.swing.JTable();
        jLabelUsuario = new javax.swing.JLabel();
        jButtonMinimizar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jButtonAddCliente = new javax.swing.JButton();
        jButtonAddFuncionario = new javax.swing.JButton();
        jButtonEstoque = new javax.swing.JButton();
        jButtonSairlogin = new javax.swing.JButton();
        jButtonRelatorios = new javax.swing.JButton();
        jPanelFunciomario2 = new javax.swing.JPanel();
        jPanelBotoes = new javax.swing.JPanel();
        jButtonNovoFunc = new javax.swing.JButton();
        jButtonEditarFunc = new javax.swing.JButton();
        jButtonDelFunc = new javax.swing.JButton();
        jPanelPraTampar = new javax.swing.JPanel();
        jScrollPaneFuncionarios = new javax.swing.JScrollPane();
        jTableFuncionarios = new javax.swing.JTable();
        jTextFieldCodEndFunc = new javax.swing.JTextField();
        jPasswordFieldConfirmarSenhaFunc = new javax.swing.JPasswordField();
        jPasswordFieldSenhaFunc = new javax.swing.JPasswordField();
        jTextFieldUsuarioFunc = new javax.swing.JTextField();
        jTextFieldCidadeFunc = new javax.swing.JTextField();
        jTextFieldBairroFunc = new javax.swing.JTextField();
        jTextFieldRuaFunc = new javax.swing.JTextField();
        jTextFieldComplementoFunc = new javax.swing.JTextField();
        jTextFieldNumeroFunc = new javax.swing.JTextField();
        jFormattedTextFieldCEPFunc = new javax.swing.JFormattedTextField();
        jTextFieldCodFunc = new javax.swing.JTextField();
        jFormattedTextFieldTelefoneFunc = new javax.swing.JFormattedTextField();
        jButtonSairFunc = new javax.swing.JButton();
        jFormattedTextFieldNascimentoFunc = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCPFFunc = new javax.swing.JFormattedTextField();
        jTextFieldNomeFunc = new javax.swing.JTextField();
        jComboBoxCargoFunc = new javax.swing.JComboBox<>();
        jButtonSalvarFunc = new javax.swing.JButton();
        jButtonCancelarFunc = new javax.swing.JButton();
        jLabelImagemFuncionario = new javax.swing.JLabel();
        jPanelCadastroCliente = new javax.swing.JPanel();
        jPanelBotoesCliente = new javax.swing.JPanel();
        jButtonNovoCliente = new javax.swing.JButton();
        jButtonEditarCliente = new javax.swing.JButton();
        jButtonDel2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jButtonSairCliente = new javax.swing.JButton();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jTextFieldEmailCliente = new javax.swing.JTextField();
        jButtonPesquisarCliente = new javax.swing.JButton();
        jButtonSalvarCliente = new javax.swing.JButton();
        jButtonCancelarCliente = new javax.swing.JButton();
        jFormattedTextFieldCPFCliente = new javax.swing.JFormattedTextField();
        jFormattedTextFieldTelCliente = new javax.swing.JFormattedTextField();
        fundo = new javax.swing.JLabel();
        jTextFieldCod = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonVendas = new javax.swing.JButton();
        jLabelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");
        getContentPane().setLayout(null);

        jInternalFrameConfirmacao.setBorder(null);
        jInternalFrameConfirmacao.setTitle("Confirme");
        jInternalFrameConfirmacao.setVisible(true);

        jButtonSair2.setBorder(null);
        jButtonSair2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonSair2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSair2ActionPerformed(evt);
            }
        });

        jButtonNao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNaoActionPerformed(evt);
            }
        });

        jLabelBackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/SairLogin.png"))); // NOI18N

        javax.swing.GroupLayout jInternalFrameConfirmacaoLayout = new javax.swing.GroupLayout(jInternalFrameConfirmacao.getContentPane());
        jInternalFrameConfirmacao.getContentPane().setLayout(jInternalFrameConfirmacaoLayout);
        jInternalFrameConfirmacaoLayout.setHorizontalGroup(
            jInternalFrameConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(jInternalFrameConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrameConfirmacaoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jInternalFrameConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jInternalFrameConfirmacaoLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(jButtonSair2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabelBackground1)
                        .addGroup(jInternalFrameConfirmacaoLayout.createSequentialGroup()
                            .addGap(260, 260, 260)
                            .addComponent(jButtonNao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jInternalFrameConfirmacaoLayout.setVerticalGroup(
            jInternalFrameConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jInternalFrameConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrameConfirmacaoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jInternalFrameConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelBackground1)
                        .addGroup(jInternalFrameConfirmacaoLayout.createSequentialGroup()
                            .addGap(140, 140, 140)
                            .addGroup(jInternalFrameConfirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonSair2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonNao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jInternalFrameConfirmacao);
        jInternalFrameConfirmacao.setBounds(400, 250, 490, 270);

        jPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jLabelBemVindo.setFont(new java.awt.Font("Tahoma", 0, 42)); // NOI18N
        jLabelBemVindo.setForeground(new java.awt.Color(51, 153, 0));
        jLabelBemVindo.setText("Seja bem vindo(a), !");

        jLabelProdutosEmFalta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelProdutosEmFalta.setForeground(new java.awt.Color(0, 51, 102));
        jLabelProdutosEmFalta.setText(" Produtos em baixa quantidade:");

        jTableProdutosEmFalta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableProdutosEmFalta);

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jLabelBemVindo))
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelProdutosEmFalta))))
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(jLabelBemVindo)
                .addGap(69, 69, 69)
                .addComponent(jLabelProdutosEmFalta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );

        getContentPane().add(jPanelPrincipal);
        jPanelPrincipal.setBounds(270, 80, 1060, 630);
        getContentPane().add(jLabelUsuario);
        jLabelUsuario.setBounds(80, 190, 160, 20);

        jButtonMinimizar.setFont(new java.awt.Font("Gilroy Light", 0, 12)); // NOI18N
        jButtonMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Minimizar.png"))); // NOI18N
        jButtonMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonMinimizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Minimizar Alterado.png"))); // NOI18N
        jButtonMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinimizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMinimizar);
        jButtonMinimizar.setBounds(1270, 10, 30, 30);

        jButtonSair.setFont(new java.awt.Font("Gilroy Light", 0, 12)); // NOI18N
        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Fechar.png"))); // NOI18N
        jButtonSair.setBorder(null);
        jButtonSair.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonSair.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Fechar alterado.png"))); // NOI18N
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSair);
        jButtonSair.setBounds(1300, 10, 30, 30);

        jButtonAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Cliente.png"))); // NOI18N
        jButtonAddCliente.setToolTipText("Adicionar cliente");
        jButtonAddCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonAddCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Cliente Alterado.png"))); // NOI18N
        jButtonAddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAddCliente);
        jButtonAddCliente.setBounds(10, 610, 70, 30);

        jButtonAddFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Funcionario.png"))); // NOI18N
        jButtonAddFuncionario.setToolTipText("Adicionar Funcionário");
        jButtonAddFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonAddFuncionario.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Funcionario Alterado.png"))); // NOI18N
        jButtonAddFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAddFuncionario);
        jButtonAddFuncionario.setBounds(10, 640, 100, 30);

        jButtonEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Acessar estoque .png"))); // NOI18N
        jButtonEstoque.setToolTipText("Estoque");
        jButtonEstoque.setBorder(null);
        jButtonEstoque.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonEstoque.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Acessar estoque alterado.png"))); // NOI18N
        jButtonEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEstoqueActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEstoque);
        jButtonEstoque.setBounds(10, 380, 150, 30);

        jButtonSairlogin.setFont(new java.awt.Font("Gilroy Light", 0, 12)); // NOI18N
        jButtonSairlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/porta fechada.jpg"))); // NOI18N
        jButtonSairlogin.setBorder(null);
        jButtonSairlogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSairlogin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/porta aberta saindo.jpg"))); // NOI18N
        jButtonSairlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairloginActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSairlogin);
        jButtonSairlogin.setBounds(20, 220, 110, 30);

        jButtonRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/relatorio.png"))); // NOI18N
        jButtonRelatorios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/relatorio linha.png"))); // NOI18N
        jButtonRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRelatorios);
        jButtonRelatorios.setBounds(10, 480, 150, 30);

        jPanelFunciomario2.setMinimumSize(new java.awt.Dimension(1050, 610));
        jPanelFunciomario2.setLayout(null);

        jPanelBotoes.setBackground(new java.awt.Color(255, 255, 255));

        jButtonNovoFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao cadastro.png"))); // NOI18N
        jButtonNovoFunc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao cadastro modificado.png"))); // NOI18N
        jButtonNovoFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoFuncActionPerformed(evt);
            }
        });

        jButtonEditarFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao editar.png"))); // NOI18N
        jButtonEditarFunc.setEnabled(false);
        jButtonEditarFunc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao editar modificado.png"))); // NOI18N
        jButtonEditarFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarFuncActionPerformed(evt);
            }
        });

        jButtonDelFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao Excluir.png"))); // NOI18N
        jButtonDelFunc.setEnabled(false);
        jButtonDelFunc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao Excluir modificado.png"))); // NOI18N
        jButtonDelFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelFuncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonNovoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditarFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDelFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNovoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditarFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(386, Short.MAX_VALUE))
        );

        jPanelFunciomario2.add(jPanelBotoes);
        jPanelBotoes.setBounds(10, 90, 570, 520);

        jPanelPraTampar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelPraTamparLayout = new javax.swing.GroupLayout(jPanelPraTampar);
        jPanelPraTampar.setLayout(jPanelPraTamparLayout);
        jPanelPraTamparLayout.setHorizontalGroup(
            jPanelPraTamparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jPanelPraTamparLayout.setVerticalGroup(
            jPanelPraTamparLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jPanelFunciomario2.add(jPanelPraTampar);
        jPanelPraTampar.setBounds(20, 410, 430, 200);

        jTableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Cargo", "CPF", "Telefone", "Endereço", "CEP", "RG", "Nascimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFuncionariosMouseClicked(evt);
            }
        });
        jScrollPaneFuncionarios.setViewportView(jTableFuncionarios);

        jPanelFunciomario2.add(jScrollPaneFuncionarios);
        jScrollPaneFuncionarios.setBounds(620, 90, 400, 440);

        jTextFieldCodEndFunc.setEnabled(false);
        jPanelFunciomario2.add(jTextFieldCodEndFunc);
        jTextFieldCodEndFunc.setBounds(520, 50, 20, 20);

        jPasswordFieldConfirmarSenhaFunc.setEditable(false);
        jPasswordFieldConfirmarSenhaFunc.setBorder(null);
        jPasswordFieldConfirmarSenhaFunc.setEnabled(false);
        jPasswordFieldConfirmarSenhaFunc.setOpaque(false);
        jPanelFunciomario2.add(jPasswordFieldConfirmarSenhaFunc);
        jPasswordFieldConfirmarSenhaFunc.setBounds(190, 558, 230, 20);

        jPasswordFieldSenhaFunc.setEditable(false);
        jPasswordFieldSenhaFunc.setBorder(null);
        jPasswordFieldSenhaFunc.setEnabled(false);
        jPasswordFieldSenhaFunc.setOpaque(false);
        jPanelFunciomario2.add(jPasswordFieldSenhaFunc);
        jPasswordFieldSenhaFunc.setBounds(190, 530, 230, 20);

        jTextFieldUsuarioFunc.setBorder(null);
        jTextFieldUsuarioFunc.setEnabled(false);
        jTextFieldUsuarioFunc.setOpaque(false);
        jTextFieldUsuarioFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jTextFieldUsuarioFunc);
        jTextFieldUsuarioFunc.setBounds(190, 500, 230, 20);

        jTextFieldCidadeFunc.setBorder(null);
        jTextFieldCidadeFunc.setEnabled(false);
        jTextFieldCidadeFunc.setOpaque(false);
        jTextFieldCidadeFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCidadeFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jTextFieldCidadeFunc);
        jTextFieldCidadeFunc.setBounds(190, 460, 230, 20);

        jTextFieldBairroFunc.setBorder(null);
        jTextFieldBairroFunc.setEnabled(false);
        jTextFieldBairroFunc.setOpaque(false);
        jPanelFunciomario2.add(jTextFieldBairroFunc);
        jTextFieldBairroFunc.setBounds(190, 400, 230, 20);

        jTextFieldRuaFunc.setBorder(null);
        jTextFieldRuaFunc.setEnabled(false);
        jTextFieldRuaFunc.setOpaque(false);
        jPanelFunciomario2.add(jTextFieldRuaFunc);
        jTextFieldRuaFunc.setBounds(190, 430, 230, 20);

        jTextFieldComplementoFunc.setBorder(null);
        jTextFieldComplementoFunc.setEnabled(false);
        jTextFieldComplementoFunc.setOpaque(false);
        jTextFieldComplementoFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldComplementoFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jTextFieldComplementoFunc);
        jTextFieldComplementoFunc.setBounds(190, 368, 230, 20);

        jTextFieldNumeroFunc.setBorder(null);
        jTextFieldNumeroFunc.setEnabled(false);
        jTextFieldNumeroFunc.setOpaque(false);
        jPanelFunciomario2.add(jTextFieldNumeroFunc);
        jTextFieldNumeroFunc.setBounds(190, 338, 230, 20);

        jFormattedTextFieldCEPFunc.setBorder(null);
        try {
            jFormattedTextFieldCEPFunc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCEPFunc.setEnabled(false);
        jFormattedTextFieldCEPFunc.setOpaque(false);
        jPanelFunciomario2.add(jFormattedTextFieldCEPFunc);
        jFormattedTextFieldCEPFunc.setBounds(190, 308, 230, 20);

        jTextFieldCodFunc.setEditable(false);
        jTextFieldCodFunc.setBorder(null);
        jTextFieldCodFunc.setEnabled(false);
        jTextFieldCodFunc.setOpaque(false);
        jPanelFunciomario2.add(jTextFieldCodFunc);
        jTextFieldCodFunc.setBounds(192, 129, 40, 20);

        jFormattedTextFieldTelefoneFunc.setBorder(null);
        try {
            jFormattedTextFieldTelefoneFunc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelefoneFunc.setEnabled(false);
        jFormattedTextFieldTelefoneFunc.setOpaque(false);
        jPanelFunciomario2.add(jFormattedTextFieldTelefoneFunc);
        jFormattedTextFieldTelefoneFunc.setBounds(190, 260, 230, 14);

        jButtonSairFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Minimizar.png"))); // NOI18N
        jButtonSairFunc.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonSairFunc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Minimizar Alterado.png"))); // NOI18N
        jButtonSairFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jButtonSairFunc);
        jButtonSairFunc.setBounds(960, 30, 60, 30);

        jFormattedTextFieldNascimentoFunc.setBorder(null);
        try {
            jFormattedTextFieldNascimentoFunc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldNascimentoFunc.setEnabled(false);
        jFormattedTextFieldNascimentoFunc.setOpaque(false);
        jFormattedTextFieldNascimentoFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldNascimentoFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jFormattedTextFieldNascimentoFunc);
        jFormattedTextFieldNascimentoFunc.setBounds(190, 194, 230, 14);

        jFormattedTextFieldCPFFunc.setBorder(null);
        try {
            jFormattedTextFieldCPFFunc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCPFFunc.setEnabled(false);
        jFormattedTextFieldCPFFunc.setOpaque(false);
        jFormattedTextFieldCPFFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldCPFFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jFormattedTextFieldCPFFunc);
        jFormattedTextFieldCPFFunc.setBounds(190, 230, 230, 14);

        jTextFieldNomeFunc.setBorder(null);
        jTextFieldNomeFunc.setOpaque(false);
        jTextFieldNomeFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jTextFieldNomeFunc);
        jTextFieldNomeFunc.setBounds(190, 134, 230, 20);

        jComboBoxCargoFunc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolha um abaixo", "Diretor", "Gerente", "Farmacêutico", "Vendedor", "Perfumista", "Balconista" }));
        jComboBoxCargoFunc.setBorder(null);
        jComboBoxCargoFunc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxCargoFunc.setEnabled(false);
        jComboBoxCargoFunc.setOpaque(false);
        jComboBoxCargoFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCargoFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jComboBoxCargoFunc);
        jComboBoxCargoFunc.setBounds(190, 162, 230, 18);

        jButtonSalvarFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoSalvarNormal.png"))); // NOI18N
        jButtonSalvarFunc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoSalvar.png"))); // NOI18N
        jButtonSalvarFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jButtonSalvarFunc);
        jButtonSalvarFunc.setBounds(450, 380, 100, 30);

        jButtonCancelarFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoCancelarNormal.png"))); // NOI18N
        jButtonCancelarFunc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoCancelar.png"))); // NOI18N
        jButtonCancelarFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarFuncActionPerformed(evt);
            }
        });
        jPanelFunciomario2.add(jButtonCancelarFunc);
        jButtonCancelarFunc.setBounds(450, 420, 100, 30);

        jLabelImagemFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tamanhoMaximoTela_1.png"))); // NOI18N
        jPanelFunciomario2.add(jLabelImagemFuncionario);
        jLabelImagemFuncionario.setBounds(0, 0, 1070, 630);

        getContentPane().add(jPanelFunciomario2);
        jPanelFunciomario2.setBounds(280, 90, 1070, 630);

        jPanelCadastroCliente.setLayout(null);

        jPanelBotoesCliente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBotoesCliente.setForeground(new java.awt.Color(255, 255, 255));

        jButtonNovoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao cadastro.png"))); // NOI18N
        jButtonNovoCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao cadastro modificado.png"))); // NOI18N
        jButtonNovoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoClienteActionPerformed(evt);
            }
        });

        jButtonEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao editar.png"))); // NOI18N
        jButtonEditarCliente.setEnabled(false);
        jButtonEditarCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao editar modificado.png"))); // NOI18N
        jButtonEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarClienteActionPerformed(evt);
            }
        });

        jButtonDel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao Excluir.png"))); // NOI18N
        jButtonDel2.setEnabled(false);
        jButtonDel2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botao Excluir modificado.png"))); // NOI18N
        jButtonDel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesClienteLayout = new javax.swing.GroupLayout(jPanelBotoesCliente);
        jPanelBotoesCliente.setLayout(jPanelBotoesClienteLayout);
        jPanelBotoesClienteLayout.setHorizontalGroup(
            jPanelBotoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNovoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanelBotoesClienteLayout.setVerticalGroup(
            jPanelBotoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBotoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonDel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBotoesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonNovoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(319, Short.MAX_VALUE))
        );

        jPanelCadastroCliente.add(jPanelBotoesCliente);
        jPanelBotoesCliente.setBounds(30, 120, 580, 430);

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Telefone", "Endereço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCliente);

        jPanelCadastroCliente.add(jScrollPane2);
        jScrollPane2.setBounds(630, 90, 370, 460);

        jButtonSairCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Minimizar.png"))); // NOI18N
        jButtonSairCliente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonSairCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/Botao Minimizar Alterado.png"))); // NOI18N
        jButtonSairCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairClienteActionPerformed(evt);
            }
        });
        jPanelCadastroCliente.add(jButtonSairCliente);
        jButtonSairCliente.setBounds(940, 30, 60, 30);

        jTextFieldNomeCliente.setBorder(null);
        jTextFieldNomeCliente.setOpaque(false);
        jTextFieldNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeClienteActionPerformed(evt);
            }
        });
        jPanelCadastroCliente.add(jTextFieldNomeCliente);
        jTextFieldNomeCliente.setBounds(180, 150, 260, 30);

        jTextFieldEmailCliente.setBorder(null);
        jTextFieldEmailCliente.setEnabled(false);
        jTextFieldEmailCliente.setOpaque(false);
        jPanelCadastroCliente.add(jTextFieldEmailCliente);
        jTextFieldEmailCliente.setBounds(180, 350, 260, 20);

        jButtonPesquisarCliente.setText("Pesquisar");
        jButtonPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarClienteActionPerformed(evt);
            }
        });
        jPanelCadastroCliente.add(jButtonPesquisarCliente);
        jButtonPesquisarCliente.setBounds(910, 100, 90, 30);

        jButtonSalvarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoSalvarNormal.png"))); // NOI18N
        jButtonSalvarCliente.setEnabled(false);
        jButtonSalvarCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoSalvar.png"))); // NOI18N
        jButtonSalvarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarClienteActionPerformed(evt);
            }
        });
        jPanelCadastroCliente.add(jButtonSalvarCliente);
        jButtonSalvarCliente.setBounds(480, 450, 120, 40);

        jButtonCancelarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoCancelarNormal.png"))); // NOI18N
        jButtonCancelarCliente.setEnabled(false);
        jButtonCancelarCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/botaoCancelar.png"))); // NOI18N
        jButtonCancelarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarClienteActionPerformed(evt);
            }
        });
        jPanelCadastroCliente.add(jButtonCancelarCliente);
        jButtonCancelarCliente.setBounds(480, 510, 120, 40);

        jFormattedTextFieldCPFCliente.setBorder(null);
        try {
            jFormattedTextFieldCPFCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanelCadastroCliente.add(jFormattedTextFieldCPFCliente);
        jFormattedTextFieldCPFCliente.setBounds(180, 200, 260, 30);

        jFormattedTextFieldTelCliente.setBorder(null);
        try {
            jFormattedTextFieldTelCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) 9 ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelCliente.setText("(  )       -    ");
        jFormattedTextFieldTelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldTelClienteActionPerformed(evt);
            }
        });
        jPanelCadastroCliente.add(jFormattedTextFieldTelCliente);
        jFormattedTextFieldTelCliente.setBounds(180, 240, 260, 40);

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/telaCliente.png"))); // NOI18N
        jPanelCadastroCliente.add(fundo);
        fundo.setBounds(0, 0, 1050, 610);

        jTextFieldCod.setEditable(false);
        jTextFieldCod.setEnabled(false);
        jPanelCadastroCliente.add(jTextFieldCod);
        jTextFieldCod.setBounds(0, 0, 6, 20);

        getContentPane().add(jPanelCadastroCliente);
        jPanelCadastroCliente.setBounds(280, 90, 1050, 610);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Sistema de Administração de Farmácia ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 680, 240, 20);

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Versão 2.0.3 BETA v2019");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 700, 140, 14);

        jButtonVendas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/registrar vendas.png"))); // NOI18N
        jButtonVendas.setBorder(null);
        jButtonVendas.setOpaque(false);
        jButtonVendas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botoes/registrar vendas alterado.png"))); // NOI18N
        jButtonVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVendasActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVendas);
        jButtonVendas.setBounds(0, 350, 160, 30);

        jLabelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/TelaPrincipalSAF_1.png"))); // NOI18N
        jLabelBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelBackgroundMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelBackground);
        jLabelBackground.setBounds(0, 0, 1350, 720);

        setSize(new java.awt.Dimension(1367, 763));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddClienteActionPerformed
        if (flagaberto == 1) {
            int teste = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair da " + formaberto + " e entrar na Administração de Clientes?", "Atenção!", 0, 2);
            if (teste == JOptionPane.YES_OPTION) {
                /*CadFuncionario cadFuncionario = new CadFuncionario(user, permissao);
                    cadFuncionario.setVisible(true);*/
                if (formaberto.equals("Administração de Funcionários")) {
                    jButtonSairFunc.doClick();
                }
                jPanelPrincipal.setVisible(false);
                jPanelCadastroCliente.setVisible(true);
                jButtonSair2.setEnabled(false);
                flagaberto = 1;
                formaberto = "Administração de Clientes";
                jButtonAddCliente.setEnabled(false);
                /*
                    jButtonEstoque.setEnabled(false);
                    jButtonAddFuncionario.setEnabled(false);
                    jButtonVendas.setEnabled(false);
                 */
            }
        } else {
            jPanelPrincipal.setVisible(false);
            jPanelCadastroCliente.setVisible(true);
            jButtonSair2.setEnabled(false);
            flagaberto = 1;
            formaberto = "Administração de Clientes";
            jButtonAddCliente.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonAddClienteActionPerformed

    private void jButtonEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEstoqueActionPerformed
        // TODO add your handling code here:
        CadProdutos cadProdutos = new CadProdutos();
        cadProdutos.setVisible(true);

    }//GEN-LAST:event_jButtonEstoqueActionPerformed

    private void jButtonAddFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFuncionarioActionPerformed

        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "Função indisponível!", "Atenção!", 2);
        if (permissao.equals("Gerente") || permissao.equals("Diretor")) {
            if (flagaberto == 1) {
                int teste = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair da " + formaberto + " e entrar na Administração de Funcionários?", "Atenção!", 0, 2);
                if (teste == JOptionPane.YES_OPTION) {
                    /*CadFuncionario cadFuncionario = new CadFuncionario(user, permissao);
                    cadFuncionario.setVisible(true);*/
                    if (formaberto.equals("Administração de Clientes")) {
                        jButtonSairCliente.doClick();
                    }
                    jPanelPrincipal.setVisible(false);
                    jPanelFunciomario2.setVisible(true);
                    jButtonSair2.setEnabled(false);
                    flagaberto = 1;
                    formaberto = "Administração de Funcionários";
                    jButtonAddFuncionario.setEnabled(false);
                    /*
                    jButtonEstoque.setEnabled(false);
                    jButtonAddCliente.setEnabled(false);
                    jButtonVendas.setEnabled(false);
                     */
                }
            } else {
                jPanelPrincipal.setVisible(false);
                jPanelFunciomario2.setVisible(true);
                jButtonSair2.setEnabled(false);
                flagaberto = 1;
                formaberto = "Administração de Funcionários";
                jButtonAddFuncionario.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Permissão negada para usuários que não sejam: DIRETOR, GERENTE \n Contate seu superior.");
        }

    }//GEN-LAST:event_jButtonAddFuncionarioActionPerformed

    private void jButtonSairloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairloginActionPerformed
        jInternalFrameConfirmacao.setVisible(true);
        jInternalFrameConfirmacao.setBackground(new Color(0, 0, 0, 0));
        jButtonSair2.setBackground(new Color(0, 0, 0, 0));
        jButtonNao.setBackground(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_jButtonSairloginActionPerformed

    private void jLabelBackgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBackgroundMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabelBackgroundMouseClicked

    private void jButtonSairClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairClienteActionPerformed
        // TODO add your handling code here:
        flagaberto = 0;
        formaberto = "";
        /* TelaPrincipal telaPrincipal = new TelaPrincipal(user, permissao);
        telaPrincipal.setVisible(true);
        dispose();*/
        jPanelPrincipal.setVisible(true);
        jPanelCadastroCliente.setVisible(false);
        //jPanelBotoesCliente.setVisible(false);
        jButtonSair2.setEnabled(true);
        jButtonEstoque.setEnabled(true);
        jButtonAddCliente.setEnabled(true);
        jButtonAddFuncionario.setEnabled(true);
        jButtonVendas.setEnabled(true);
    }//GEN-LAST:event_jButtonSairClienteActionPerformed

    private void jButtonPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarClienteActionPerformed

        beansCliente.setPesquisar(jTextFieldNomeCliente.getText());

        _beansCliente.setCpf("");
        _beansCliente.setCodigo(0);
        _beansCliente.setEmail("");
        _beansCliente.setNome("");
        _beansCliente.setTelefone("");

        _beansCliente = daoCliente.buscaCliente(beansCliente);

        //Seteres de texto
        jTextFieldCod.setText(Integer.toString(_beansCliente.getCodigo()));
        jTextFieldNomeCliente.setText(_beansCliente.getNome());
        jFormattedTextFieldCPFCliente.setText(String.valueOf(_beansCliente.getCpf()));
        jFormattedTextFieldTelCliente.setText(String.valueOf(_beansCliente.getTelefone()));
        jTextFieldEmailCliente.setText(_beansCliente.getEmail());

        //Ativadores

        /*jFormattedTextFieldCPFCliente.setEnabled(true);
        jTextFieldEnderecoCliente.setEnabled(true);
        jTextFieldNomeCliente.setEnabled(true);
        jFormattedTextFieldTelCliente.setEnabled(true);
        //jTextFieldCod.setEnabled(true);

        //jLabelCPFCliente.setEnabled(true);
        //jLabelCodCliente.setEnabled(true);
        //jLabelEnderecoCliente.setEnabled(true);
        //jLabelNomeCliente.setEnabled(true);
        //jLabelTelefoneCliente.setEnabled(true);

        jButtonEditarCliente.setEnabled(true);
        jButtonDelCliente.setEnabled(true);
        jButtonCancelarCliente.setEnabled(true);
        //Desativadores
        jButtonNovoCliente.setEnabled(false);
        jButtonPesquisarCliente.setEnabled(false);

        jFormattedTextFieldCPFCliente.setEditable(false);
        jTextFieldEnderecoCliente.setEditable(false);
        jTextFieldNomeCliente.setEditable(false);
        jFormattedTextFieldTelCliente.setEditable(false);*/
    }//GEN-LAST:event_jButtonPesquisarClienteActionPerformed

    private void jTextFieldNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeClienteActionPerformed

    private void jTableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClienteMouseClicked
        // TODO add your handling code here:
        String codigo = "" + jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 0);
        // abre a conexao
        conexaoBD.conectaBD();
        // excuta um string sql
        conexaoBD.executaSql("select * from clientes where cod_cliente='" + codigo + "'");

        try {
            // pega o primeiro do banco
            conexaoBD.rs.first();
            jFormattedTextFieldCPFCliente.setText(conexaoBD.rs.getString("cpf_cliente"));
            jTextFieldCod.setText(Integer.toString(conexaoBD.rs.getInt("cod_cliente")));
            jTextFieldEmailCliente.setText(conexaoBD.rs.getString("email_cliente"));
            jTextFieldNomeCliente.setText(conexaoBD.rs.getString("nome_cliente"));
            jFormattedTextFieldTelCliente.setText(conexaoBD.rs.getString("tel_cliente"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao selecionar dados.\nErro: " + ex);
        }
        // fecha a conexão
        conexaoBD.desconectaBD();

        // ATIVADORES
        jButtonEditarCliente.setEnabled(true);
        jButtonDel2.setEnabled(true);
        jButtonCancelarCliente.setEnabled(true);
        jTextFieldEmailCliente.setEnabled(true);
        jFormattedTextFieldCPFCliente.setEnabled(true);
        jFormattedTextFieldTelCliente.setEnabled(true);
        /*jTextFieldCod.setEnabled(true);
        jLabelCPFCliente.setEnabled(true);
        jLabelCodCliente.setEnabled(true);
        jLabelEnderecoCliente.setEnabled(true);
        jLabelTelefoneCliente.setEnabled(true);*/

        // DESATIVADORES
        jFormattedTextFieldCPFCliente.setEditable(false);
        jTextFieldNomeCliente.setEditable(false);
        jFormattedTextFieldTelCliente.setEditable(false);
        jTextFieldEmailCliente.setEditable(false);
        //jTextFieldCod.setEditable(false);

        jButtonSalvarCliente.setEnabled(false);
        jButtonSairCliente.setEnabled(false);
        jButtonNovoCliente.setEnabled(false);
        jPanelBotoesCliente.setSize(580, 380);
    }//GEN-LAST:event_jTableClienteMouseClicked

    private void jButtonSair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSair2ActionPerformed
        // TODO add your handling code here:[
        jPanelPrincipal.setVisible(true);
        jInternalFrameConfirmacao.dispose();
        dispose();
        TelaLogin tL = new TelaLogin();
        tL.setVisible(true);
    }//GEN-LAST:event_jButtonSair2ActionPerformed

    private void jButtonNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNaoActionPerformed
        // TODO add your handling code here:
        jInternalFrameConfirmacao.dispose();
    }//GEN-LAST:event_jButtonNaoActionPerformed

    private void jButtonMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMinimizarActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jButtonMinimizarActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonNovoFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoFuncActionPerformed
        flag = 1;
        flagcriando = 1;
        //Ativadores

        jButtonSalvarFunc.setEnabled(true);
        jButtonCancelarFunc.setEnabled(true);

        jTextFieldCodFunc.setEnabled(true);
        jComboBoxCargoFunc.setEnabled(true);
        jFormattedTextFieldNascimentoFunc.setEnabled(true);

        //CAMPOS DE ENDEREÇO
        jTextFieldRuaFunc.setEnabled(true);
        jTextFieldBairroFunc.setEnabled(true);
        jTextFieldNumeroFunc.setEnabled(true);
        jFormattedTextFieldCEPFunc.setEnabled(true);
        jTextFieldComplementoFunc.setEnabled(true);
        jTextFieldCidadeFunc.setEnabled(true);

        jTextFieldRuaFunc.setEditable(true);
        jTextFieldBairroFunc.setEditable(true);
        jTextFieldNumeroFunc.setEditable(true);
        jFormattedTextFieldCEPFunc.setEditable(true);
        jTextFieldComplementoFunc.setEditable(true);
        jTextFieldCidadeFunc.setEditable(true);

        //Desativadores
        jButtonNovoFunc.setEnabled(false);
        jButtonEditarFunc.setEnabled(false);
        //jButtonSairFunc2.setEnabled(false);
        jScrollPaneFuncionarios.setEnabled(false);
        jTableFuncionarios.setEnabled(false);
        jPanelBotoes.setVisible(false);
        jPanelPraTampar.setVisible(false);

        //habilita os campos de texto após clicar no botão "novo"
        jFormattedTextFieldCPFFunc.setEnabled(true);
        jTextFieldNomeFunc.setEnabled(true);
        jFormattedTextFieldCEPFunc.setEnabled(true);
        jFormattedTextFieldTelefoneFunc.setEnabled(true);

        jTextFieldUsuarioFunc.setEnabled(true);
        jPasswordFieldSenhaFunc.setEnabled(true);
        jPasswordFieldConfirmarSenhaFunc.setEnabled(true);

        jFormattedTextFieldCPFFunc.setEditable(true);
        jTextFieldNomeFunc.setEditable(true);
        jFormattedTextFieldCEPFunc.setEditable(true);
        jFormattedTextFieldTelefoneFunc.setEditable(true);

        jFormattedTextFieldNascimentoFunc.setEditable(true);
        jTextFieldUsuarioFunc.setEditable(true);
        jPasswordFieldSenhaFunc.setEditable(true);
        jPasswordFieldConfirmarSenhaFunc.setEditable(true);

        //limpar campos de texto
        jTextFieldCodFunc.setText(null);
        jFormattedTextFieldCPFFunc.setText(null);
        jTextFieldNomeFunc.setText(null);

        jFormattedTextFieldCEPFunc.setText(null);
        jFormattedTextFieldTelefoneFunc.setText(null);
        jComboBoxCargoFunc.setSelectedIndex(0);

        jFormattedTextFieldNascimentoFunc.setText(null);

        //coloca o foco no campo "código" após criar um novo cadastro
        jTextFieldNomeFunc.requestFocus();
    }//GEN-LAST:event_jButtonNovoFuncActionPerformed

    private void jTableFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFuncionariosMouseClicked
        // TODO add your handling code here:
        jPanelBotoes.setSize(580, 330);
        if (flagcriando == 0) {
            jPanelPraTampar.setVisible(true);
        }
        jButtonEditarFunc.setEnabled(true);
        jButtonDelFunc.setEnabled(true);

        String codigo = "" + jTableFuncionarios.getValueAt(jTableFuncionarios.getSelectedRow(), 0);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // abre a conexao
        conexaoBD.conectaBD();
        // excuta um string sql
        conexaoBD.executaSql("select * from funcionarios join enderecos on enderecos.cod_end = funcionarios.cod_end where funcionarios.cod_end='" + codigo + "'");

        try {
            // pega o primeiro do banco
            conexaoBD.rs.first();
            jTextFieldCodEndFunc.setText(Integer.toString(conexaoBD.rs.getInt("cod_end")));
            jFormattedTextFieldCPFFunc.setText(conexaoBD.rs.getString("CPF"));
            jTextFieldCodFunc.setText(Integer.toString(conexaoBD.rs.getInt("Cod_func")));
            jTextFieldNomeFunc.setText(conexaoBD.rs.getString("nomefunc"));
            jFormattedTextFieldTelefoneFunc.setText(conexaoBD.rs.getString("telefone"));
            jFormattedTextFieldNascimentoFunc.setText(dateFormat.format(conexaoBD.rs.getDate("nascimento")));
            jComboBoxCargoFunc.setSelectedItem(conexaoBD.rs.getString("cargo"));
            jTextFieldUsuarioFunc.setText(conexaoBD.rs.getString("usuario"));
            jFormattedTextFieldCEPFunc.setText(conexaoBD.rs.getString("cep"));
            jTextFieldRuaFunc.setText(conexaoBD.rs.getString("rua"));
            jTextFieldNumeroFunc.setText(Integer.toString(conexaoBD.rs.getInt("numero")));
            jTextFieldBairroFunc.setText(conexaoBD.rs.getString("bairro"));
            jTextFieldComplementoFunc.setText(conexaoBD.rs.getString("comp"));
            jTextFieldCidadeFunc.setText(conexaoBD.rs.getString("cidade"));
            beansfuncionario.setSenha(conexaoBD.rs.getString("senha"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao selecionar dados.\nErro: " + ex);
        }
        // fecha a conexão
        conexaoBD.desconectaBD();

        // ATIVADORES
        jButtonEditarFunc.setEnabled(true);
        jButtonDelFunc.setEnabled(true);
        jButtonCancelarFunc.setEnabled(true);

        jFormattedTextFieldCPFFunc.setEnabled(true);
        jFormattedTextFieldTelefoneFunc.setEnabled(true);
        jComboBoxCargoFunc.setEnabled(true);
        jFormattedTextFieldNascimentoFunc.setEnabled(true);
        jTextFieldCodFunc.setEnabled(true);
        jTextFieldUsuarioFunc.setEnabled(true);
        jPasswordFieldSenhaFunc.setEnabled(true);
        jTextFieldNomeFunc.setEnabled(true);

        //CAMPOS DE ENDEREÇO
        jTextFieldRuaFunc.setEnabled(true);
        jTextFieldBairroFunc.setEnabled(true);
        jTextFieldNumeroFunc.setEnabled(true);
        jFormattedTextFieldCEPFunc.setEnabled(true);
        jTextFieldComplementoFunc.setEnabled(true);
        jTextFieldCidadeFunc.setEnabled(true);

        // DESATIVADORES
        jFormattedTextFieldCPFFunc.setEditable(false);
        jTextFieldNomeFunc.setEditable(false);
        jFormattedTextFieldTelefoneFunc.setEditable(false);
        jFormattedTextFieldNascimentoFunc.setEditable(false);
        jTextFieldCodFunc.setEditable(false);
        jTextFieldUsuarioFunc.setEditable(false);
        jPasswordFieldSenhaFunc.setEditable(false);

        jFormattedTextFieldCEPFunc.setEditable(false);
        jTextFieldRuaFunc.setEditable(false);
        jTextFieldBairroFunc.setEditable(false);
        jTextFieldNumeroFunc.setEditable(false);
        jFormattedTextFieldCEPFunc.setEditable(false);
        jTextFieldComplementoFunc.setEditable(false);
        jTextFieldCidadeFunc.setEditable(false);

        jButtonSalvarFunc.setEnabled(false);
        jButtonSairCliente.setEnabled(false);
        jButtonNovoFunc.setEnabled(false);
    }//GEN-LAST:event_jTableFuncionariosMouseClicked

    private void jTextFieldUsuarioFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioFuncActionPerformed

    private void jFormattedTextFieldNascimentoFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNascimentoFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldNascimentoFuncActionPerformed

    private void jTextFieldNomeFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeFuncActionPerformed

    private void jComboBoxCargoFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCargoFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCargoFuncActionPerformed

    private void jButtonSalvarFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarFuncActionPerformed
        //jTextField_Pesquisar.requestFocus();
        if (flag == 1) {

            String senha = jPasswordFieldSenhaFunc.getText().trim();
            //quando o campo está em branco
            if ((String) jComboBoxCargoFunc.getSelectedItem() == (String) jComboBoxCargoFunc.getItemAt(0)) {
                JOptionPane.showMessageDialog(null, "Escolha um cargo para continuar", "Atenção!", 2);
            } else if (jTextFieldNomeFunc.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira um nome para prosseguir!", "Atenção!", 2);
                jTextFieldNomeFunc.requestFocus();
            } else if (jTextFieldNomeFunc.getText() != null) {
                try {
                    int num = Integer.parseInt(jTextFieldNomeFunc.getText());
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome válido! (Não pode ser apenas números)");
                } catch (NumberFormatException e) {

                    if (jFormattedTextFieldCPFFunc.getText().equals("   .   .   -  ")) {
                        JOptionPane.showMessageDialog(null, "Insira um CPF para prosseguir!", "Atenção!", 2);
                        jFormattedTextFieldCPFFunc.requestFocus();
                    } else if (jFormattedTextFieldCPFFunc.getText() != null) {
                        //JOptionPane.showMessageDialog(rootPane, jFormattedTextFieldCPF.getText());
                        String parte1 = jFormattedTextFieldCPFFunc.getText().substring(0, 3);
                        String parte2 = jFormattedTextFieldCPFFunc.getText().substring(4, 7);
                        String parte3 = jFormattedTextFieldCPFFunc.getText().substring(8, 11);
                        String parte4 = jFormattedTextFieldCPFFunc.getText().substring(12, 14);
                        String cpf = parte1 + parte2 + parte3 + parte4;
                        //JOptionPane.showMessageDialog(rootPane, jFormattedTextFieldCPF.getText());
                        verificaCpf.validaCpf(cpf);
                        cpf = "";

                        String verifica = String.valueOf(verificaCpf.isValido());

                        if (verifica == "true") {
                            /*JOptionPane.showMessageDialog(rootPane, "CPF Válido");
                            jFormattedTextFieldCPF.setText("");
                            jFormattedTextFieldCPF.requestFocus();*/
                            if (jFormattedTextFieldTelefoneFunc.getText().equals("(  )      -    ")) {
                                JOptionPane.showMessageDialog(null, "Insira um telefone para prosseguir!", "Atenção!", 2);
                                jFormattedTextFieldTelefoneFunc.requestFocus();
                            } else if (jFormattedTextFieldNascimentoFunc.getText().equals("  /  /    ")) {
                                JOptionPane.showMessageDialog(null, "Insira um nascimento para prosseguir!", "Atenção!", 2);
                            } else if (jTextFieldUsuarioFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira um usuário para prosseguir!", "Atenção!", 2);
                            } else if (jPasswordFieldSenhaFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira uma senha para prosseguir!", "Atenção!", 2);
                            } else if (jFormattedTextFieldCEPFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira um CEP para prosseguir!", "Atenção!", 2);
                                jFormattedTextFieldCEPFunc.requestFocus();
                            } else if (jTextFieldRuaFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira uma rua para prosseguir!", "Atenção!", 2);
                            } else if (jTextFieldNumeroFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira um número para prosseguir!", "Atenção!", 2);
                            } else if (jTextFieldBairroFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira um bairro para prosseguir!", "Atenção!", 2);
                            } else if (jTextFieldCidadeFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira uma cidade para prosseguir!", "Atenção!", 2);
                            } else if (!jPasswordFieldSenhaFunc.getText().equals(jPasswordFieldConfirmarSenhaFunc.getText())) {
                                JOptionPane.showMessageDialog(null, "As senhas não coincidem!\nVerifique a ortografia.", "Atenção!", 2);
                            } else {
                                String data = jFormattedTextFieldNascimentoFunc.getText();
                                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = null;
                                try {
                                    date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
                                } catch (ParseException ex) {
                                    JOptionPane.showMessageDialog(null, "Erro na conversão da data \n" + ex);
                                }
                                int cod = 0;
                                conexaoBD.conectaBD();
                                conexaoBD.executaSql("select cod_end from funcionarios order by cod_end desc");
                                CriptografaSenha criptografaSenha = new CriptografaSenha();
                                criptografaSenha.setSenha(senha);
                                String senhaCriptografada = criptografaSenha.getSenha();
                                beansfuncionario.setNome(jTextFieldNomeFunc.getText());
                                beansfuncionario.setCPF(jFormattedTextFieldCPFFunc.getText());
                                beansfuncionario.setTelefone(jFormattedTextFieldTelefoneFunc.getText());
                                beansfuncionario.setCargo((String) jComboBoxCargoFunc.getSelectedItem());
                                beansfuncionario.setNascimento(date);
                                beansfuncionario.setUsuario(jTextFieldUsuarioFunc.getText());
                                beansfuncionario.setSenha(senhaCriptografada);
                                try {
                                    conexaoBD.rs.first();
                                    cod = conexaoBD.rs.getInt("cod_end") + 1;
                                    beansfuncionario.setCod_func(cod);
                                    beansfuncionario.setCodigoend2(cod);
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(null, "Erro ao setar codigo e codigoend2", "Atenção!", 1);
                                }
                                conexaoBD.desconectaBD();

                                beansfuncionario.setRua(jTextFieldRuaFunc.getText());
                                beansfuncionario.setBairro(jTextFieldBairroFunc.getText());
                                beansfuncionario.setNumero(Integer.parseInt(jTextFieldNumeroFunc.getText()));
                                beansfuncionario.setCep(Integer.parseInt(jFormattedTextFieldCEPFunc.getText()));
                                beansfuncionario.setComplemento(jTextFieldComplementoFunc.getText());
                                beansfuncionario.setCidade(jTextFieldCidadeFunc.getText());

                                /*beansUsuario.setUsuario(jTextFieldUsuario.getText());
                                beansUsuario.setSenha(senhaCriptografada);
                                beansUsuario.setPermissao(jComboBoxCargo.getSelectedIndex());*/
                                int teste = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja salvar os dados desse funcionário? \n" + "Cargo: " + beansfuncionario.getCargo() + "\n" + "Nome: " + beansfuncionario.getNome() + "\n" + "CPF: " + beansfuncionario.getCPF() + "\n" + "Telefone: " + beansfuncionario.getTelefone() + "\n" + "Nascimento: " + beansfuncionario.getNascimento(), "Atenção!", 0, 0);
                                if (teste == JOptionPane.YES_OPTION) {
                                    //daoUsuario.salvar(beansUsuario);
                                    daofuncionario.salvar(beansfuncionario);
                                    jButtonCancelarFunc.doClick();
                                    //Ativadores
                                    jPanelBotoesCliente.setVisible(true);
                                    /*jButtonNovoFunc.setEnabled(true);
                                    jButtonSairFunc2.setEnabled(true);
                                    jScrollPaneFuncionarios.setEnabled(true);
                                    jTableFuncionarios.setEnabled(true);

                                    //Desativadores
                                    jButtonCancelarFunc.setEnabled(false);
                                    jButtonEditarFunc.setEnabled(false);
                                    jButtonDelFunc.setEnabled(false);
                                    jButtonSalvarFunc.setEnabled(false);

                                    jFormattedTextFieldCPFFunc.setEnabled(false);
                                    jFormattedTextFieldTelefoneFunc.setEnabled(false);
                                    jComboBoxCargoFunc.setEnabled(false);
                                    jFormattedTextFieldNascimentoFunc.setEnabled(false);
                                    jTextFieldUsuarioFunc.setEnabled(false);
                                    jPasswordFieldSenhaFunc.setEnabled(false);
                                    jPasswordFieldConfirmarSenhaFunc.setEnabled(false);

                                    //CAMPOS DE ENDEREÇO
                                    jTextFieldRuaFunc.setEnabled(false);
                                    jTextFieldBairroFunc.setEnabled(false);
                                    jTextFieldNumeroFunc.setEnabled(false);
                                    jFormattedTextFieldCEPFunc.setEnabled(false);
                                    jTextFieldComplementoFunc.setEnabled(false);
                                    jTextFieldCidadeFunc.setEnabled(false);

                                    //jLabelCPFCliente.setEnabled(false);
                                    //jLabelTelefoneCliente.setEnabled(false);
                                    jLabelUsuario.setEnabled(false);

                                    //Seteres de texto
                                    //jTextFieldCod.setText(null);
                                    jFormattedTextFieldCPFFunc.setText(null);
                                    jTextFieldNomeFunc.setText(null);

                                    jFormattedTextFieldTelefoneFunc.setText(null);
                                    jComboBoxCargoFunc.setSelectedIndex(0);
                                    jFormattedTextFieldNascimentoFunc.setText(null);
                                    jTextFieldUsuarioFunc.setText(null);
                                    jPasswordFieldSenhaFunc.setText(null);
                                    jPasswordFieldConfirmarSenhaFunc.setText(null);

                                    //CAMPOS DE ENDEREÇO
                                    jTextFieldRuaFunc.setText(null);
                                    jTextFieldBairroFunc.setText(null);
                                    jTextFieldNumeroFunc.setText(null);
                                    jFormattedTextFieldCEPFunc.setText(null);
                                    jTextFieldComplementoFunc.setText(null);
                                    jTextFieldCidadeFunc.setText(null);*/

                                    preencherTabelaFunc("select * from funcionarios join enderecos on enderecos.cod_end = funcionarios.cod_end order by nomefunc");
                                }
                            }
                        } else {
                            //JOptionPane.showMessageDialog(rootPane, "CPF Inválido");
                            JOptionPane.showMessageDialog(rootPane, "Insira um CPF válido");
                            jFormattedTextFieldCPFFunc.setText("");
                            cpf = "";
                            jFormattedTextFieldCPFFunc.requestFocus();
                        }
                    }
                }
            }
        } else {
            String senha = jPasswordFieldSenhaFunc.getText().trim();
            //quando o campo está em branco
            if ((String) jComboBoxCargoFunc.getSelectedItem() == (String) jComboBoxCargoFunc.getItemAt(0)) {
                JOptionPane.showMessageDialog(null, "Escolha um cargo para continuar", "Atenção!", 2);
            } else if (jTextFieldNomeFunc.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira um nome para prosseguir!", "Atenção!", 2);
                jTextFieldNomeFunc.requestFocus();
            } else if (jTextFieldNomeFunc.getText() != null) {
                try {
                    int num = Integer.parseInt(jTextFieldNomeFunc.getText());
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome válido! (Não pode ser apenas números)");
                } catch (NumberFormatException e) {

                    if (jFormattedTextFieldCPFFunc.getText().equals("   .   .   -  ")) {
                        JOptionPane.showMessageDialog(null, "Insira um CPF para prosseguir!", "Atenção!", 2);
                        jFormattedTextFieldCPFFunc.requestFocus();
                    } else if (jFormattedTextFieldCPFFunc.getText() != null) {
                        //JOptionPane.showMessageDialog(rootPane, jFormattedTextFieldCPF.getText());
                        String parte1 = jFormattedTextFieldCPFFunc.getText().substring(0, 3);
                        String parte2 = jFormattedTextFieldCPFFunc.getText().substring(4, 7);
                        String parte3 = jFormattedTextFieldCPFFunc.getText().substring(8, 11);
                        String parte4 = jFormattedTextFieldCPFFunc.getText().substring(12, 14);
                        String cpf = parte1 + parte2 + parte3 + parte4;
                        //JOptionPane.showMessageDialog(rootPane, jFormattedTextFieldCPF.getText());
                        verificaCpf.validaCpf(cpf);
                        cpf = "";

                        String verifica = String.valueOf(verificaCpf.isValido());

                        if (verifica == "true") {
                            /*JOptionPane.showMessageDialog(rootPane, "CPF Válido");
                            jFormattedTextFieldCPF.setText("");
                            jFormattedTextFieldCPF.requestFocus();*/
                            if (jFormattedTextFieldTelefoneFunc.getText().equals("(  )      -    ")) {
                                JOptionPane.showMessageDialog(null, "Insira um telefone para prosseguir!", "Atenção!", 2);
                                jFormattedTextFieldTelefoneFunc.requestFocus();
                            } else if (jFormattedTextFieldNascimentoFunc.getText().equals("  /  /    ")) {
                                JOptionPane.showMessageDialog(null, "Insira um nascimento para prosseguir!", "Atenção!", 2);
                            } else if (jFormattedTextFieldCEPFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira um CEP para prosseguir!", "Atenção!", 2);
                                jFormattedTextFieldCEPFunc.requestFocus();
                            } else if (jTextFieldRuaFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira uma rua para prosseguir!", "Atenção!", 2);
                            } else if (jTextFieldNumeroFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira um número para prosseguir!", "Atenção!", 2);
                            } else if (jTextFieldBairroFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira um bairro para prosseguir!", "Atenção!", 2);
                            } else if (jTextFieldCidadeFunc.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Insira uma cidade para prosseguir!", "Atenção!", 2);
                            } else if (!jPasswordFieldSenhaFunc.getText().equals(jPasswordFieldConfirmarSenhaFunc.getText())) {
                                JOptionPane.showMessageDialog(null, "As senhas não coincidem!\nVerifique a ortografia.", "Atenção!", 2);
                            } else {
                                String data = jFormattedTextFieldNascimentoFunc.getText();
                                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = null;
                                try {
                                    date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
                                } catch (ParseException ex) {
                                    JOptionPane.showMessageDialog(null, "Erro na conversão da data \n" + ex);
                                }

                                CriptografaSenha criptografaSenha = new CriptografaSenha();
                                criptografaSenha.setSenha(senha);
                                String senhaCriptografada = criptografaSenha.getSenha();

                                beansfuncionario.setCargo((String) jComboBoxCargoFunc.getSelectedItem());
                                beansfuncionario.setCod_func(Integer.parseInt(jTextFieldCodFunc.getText()));
                                beansfuncionario.setCodigoend(Integer.parseInt(jTextFieldCodEndFunc.getText()));
                                beansfuncionario.setNome(jTextFieldNomeFunc.getText());
                                beansfuncionario.setNascimento(date);
                                beansfuncionario.setCPF(jFormattedTextFieldCPFFunc.getText());
                                beansfuncionario.setTelefone(jFormattedTextFieldTelefoneFunc.getText());
                                beansfuncionario.setUsuario(jTextFieldUsuarioFunc.getText());
                                if (!jPasswordFieldSenhaFunc.getText().isEmpty() && !jPasswordFieldConfirmarSenhaFunc.getText().isEmpty()) {
                                    beansfuncionario.setSenha(senhaCriptografada);
                                }
                                beansfuncionario.setRua(jTextFieldRuaFunc.getText());
                                beansfuncionario.setBairro(jTextFieldBairroFunc.getText());
                                beansfuncionario.setNumero(Integer.parseInt(jTextFieldNumeroFunc.getText()));
                                beansfuncionario.setCep(Integer.parseInt(jFormattedTextFieldCEPFunc.getText()));
                                beansfuncionario.setComplemento(jTextFieldComplementoFunc.getText());
                                beansfuncionario.setCidade(jTextFieldCidadeFunc.getText());

                                int teste = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar os dados desse funcionário? \n" + "Cargo: " + beansfuncionario.getCargo() + "\n" + "Código: " + beansfuncionario.getCod_func() + "\n" + "Nome: " + beansfuncionario.getNome() + "\n" + "CPF: " + beansfuncionario.getCPF() + "\n" + "Telefone: " + beansfuncionario.getTelefone() + "\n" + "RG: " + beansfuncionario.getRG() + "\n" + "Nascimento: " + beansfuncionario.getNascimento(), "Atenção!", 0, 0);
                                if (teste == 0) {
                                    daofuncionario.editar(beansfuncionario);
                                    jButtonCancelarFunc.doClick();
                                    //daoUsuario.editar(beansUsuario);
                                    /*
                                    //Ativadores
                                    jButtonNovoFunc.setEnabled(true);
                                    jButtonSairFunc2.setEnabled(true);

                                    jScrollPaneFuncionarios.setEnabled(true);
                                    jTableFuncionarios.setEnabled(true);

                                    //Desativadores
                                    jButtonCancelarFunc.setEnabled(false);
                                    jButtonEditarFunc.setEnabled(false);
                                    jButtonDelFunc.setEnabled(false);
                                    jButtonSalvarFunc.setEnabled(false);

                                    jFormattedTextFieldCPFFunc.setEnabled(false);
                                    jFormattedTextFieldTelefoneFunc.setEnabled(false);
                                    jComboBoxCargoFunc.setEnabled(false);
                                    jFormattedTextFieldNascimentoFunc.setEnabled(false);
                                    jTextFieldUsuarioFunc.setEnabled(false);
                                    jPasswordFieldSenhaFunc.setEnabled(false);
                                    jPasswordFieldConfirmarSenhaFunc.setEnabled(false);

                                    //CAMPOS DE ENDEREÇO
                                    jTextFieldRuaFunc.setEnabled(false);
                                    jTextFieldBairroFunc.setEnabled(false);
                                    jTextFieldNumeroFunc.setEnabled(false);
                                    jFormattedTextFieldCEPFunc.setEnabled(false);
                                    jTextFieldComplementoFunc.setEnabled(false);
                                    jTextFieldCidadeFunc.setEnabled(false);

                                    //Seteres de texto
                                    jTextFieldCod.setText(null);
                                    jFormattedTextFieldCPFFunc.setText(null);
                                    jTextFieldNomeFunc.setText(null);

                                    jFormattedTextFieldTelefoneFunc.setText(null);
                                    jComboBoxCargoFunc.setSelectedIndex(0);
                                    jFormattedTextFieldNascimentoFunc.setText(null);
                                    jTextFieldUsuarioFunc.setText(null);
                                    jPasswordFieldSenhaFunc.setText(null);
                                    jPasswordFieldConfirmarSenhaFunc.setText(null);

                                    //CAMPOS DE ENDEREÇO
                                    jTextFieldRuaFunc.setText(null);
                                    jTextFieldBairroFunc.setText(null);
                                    jTextFieldNumeroFunc.setText(null);
                                    jFormattedTextFieldCEPFunc.setText(null);
                                    jTextFieldComplementoFunc.setText(null);
                                    jTextFieldCidadeFunc.setText(null);
                                     */
                                    preencherTabelaFunc("select * from funcionarios join enderecos on enderecos.cod_end = funcionarios.cod_end order by nomefunc");
                                }
                            }
                        } else {
                            //JOptionPane.showMessageDialog(rootPane, "CPF Inválido");
                            JOptionPane.showMessageDialog(rootPane, "Insira um CPF válido");
                            jFormattedTextFieldCPFFunc.setText("");
                            cpf = "";
                            jFormattedTextFieldCPFFunc.requestFocus();
                        }
                    }
                }
            }
        }

    }//GEN-LAST:event_jButtonSalvarFuncActionPerformed

    private void jButtonCancelarFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarFuncActionPerformed
        flagcriando = 0;
        jPanelBotoes.setSize(580, 530);
        //Ativadores
        jTextFieldNomeFunc.setEditable(true);
        jTextFieldNomeFunc.setEnabled(true);
        jPanelBotoes.setVisible(true);

        jButtonNovoFunc.setEnabled(true);
        jButtonSairCliente.setEnabled(true);

        jScrollPaneFuncionarios.setEnabled(true);
        jTableFuncionarios.setEnabled(true);

        jFormattedTextFieldCPFFunc.setEnabled(false);
        jTextFieldCodFunc.setEnabled(false);
        jFormattedTextFieldTelefoneFunc.setEnabled(false);
        jComboBoxCargoFunc.setEnabled(false);
        jFormattedTextFieldNascimentoFunc.setEnabled(false);
        jTextFieldUsuarioFunc.setEnabled(false);
        jPasswordFieldSenhaFunc.setEnabled(false);
        jPasswordFieldConfirmarSenhaFunc.setEnabled(false);

        //CAMPOS DE ENDEREÇO
        jTextFieldRuaFunc.setEnabled(false);
        jTextFieldBairroFunc.setEnabled(false);
        jTextFieldNumeroFunc.setEnabled(false);
        jFormattedTextFieldCEPFunc.setEnabled(false);
        jTextFieldComplementoFunc.setEnabled(false);
        jTextFieldCidadeFunc.setEnabled(false);

        jButtonSalvarFunc.setEnabled(false);
        jButtonCancelarFunc.setEnabled(false);
        jButtonEditarFunc.setEnabled(false);
        jButtonDelFunc.setEnabled(false);

        //limpar campos de texto
        jTextFieldCodFunc.setText(null);
        jFormattedTextFieldCPFFunc.setText(null);
        jTextFieldNomeFunc.setText(null);
        jFormattedTextFieldTelefoneFunc.setText(null);
        jComboBoxCargoFunc.setSelectedIndex(0);

        jFormattedTextFieldNascimentoFunc.setText(null);
        jTextFieldUsuarioFunc.setText(null);
        jPasswordFieldSenhaFunc.setText(null);
        jPasswordFieldConfirmarSenhaFunc.setText(null);

        //CAMPOS DE ENDEREÇO
        jTextFieldRuaFunc.setText(null);
        jTextFieldBairroFunc.setText(null);
        jTextFieldNumeroFunc.setText(null);
        jFormattedTextFieldCEPFunc.setText(null);
        jTextFieldComplementoFunc.setText(null);
        jTextFieldCidadeFunc.setText(null);

        //coloca o foco no campo "nome" após cancelar
        jTextFieldNomeFunc.requestFocus();

        preencherTabelaFunc("select * from funcionarios join enderecos on enderecos.cod_end = funcionarios.cod_end order by nomefunc");
    }//GEN-LAST:event_jButtonCancelarFuncActionPerformed

    private void jButtonEditarFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarFuncActionPerformed
        // TODO add your handling code here:
        jPanelBotoes.setVisible(false);
        jPanelPraTampar.setVisible(false);
        jTableFuncionarios.clearSelection();
        flag = 2;

        //Ativadores
        jPasswordFieldConfirmarSenhaFunc.setEditable(true);
        jPasswordFieldConfirmarSenhaFunc.setEnabled(true);

        jFormattedTextFieldCEPFunc.setEditable(true);
        jFormattedTextFieldCPFFunc.setEditable(true);
        jTextFieldNomeFunc.setEditable(true);
        jFormattedTextFieldTelefoneFunc.setEditable(true);
        jFormattedTextFieldNascimentoFunc.setEditable(true);
        jTextFieldUsuarioFunc.setEditable(true);
        jPasswordFieldSenhaFunc.setEditable(true);

        jTextFieldNumeroFunc.setEditable(true);
        jTextFieldComplementoFunc.setEditable(true);
        jTextFieldBairroFunc.setEditable(true);
        jTextFieldRuaFunc.setEditable(true);
        jTextFieldCidadeFunc.setEditable(true);

        jButtonSalvarFunc.setEnabled(true);

        jTextFieldNomeFunc.requestFocus();

        //Desativadores
        jButtonSairCliente.setEnabled(false);
        jButtonEditarFunc.setEnabled(false);
        jButtonDelFunc.setEnabled(false);
        jScrollPaneFuncionarios.setEnabled(false);
        jTableFuncionarios.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarFuncActionPerformed

    private void jButtonDelFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelFuncActionPerformed
        // TODO add your handling code here:
        beansfuncionario.setCod_func(Integer.parseInt(jTextFieldCodFunc.getText()));
        beansfuncionario.setCodigoend(Integer.parseInt(jTextFieldCodEndFunc.getText()));

        int teste = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir os dados desse funcionário? \n" + "Cargo: " + jComboBoxCargoFunc.getSelectedItem() + "\n" + "Nome: " + jTextFieldNomeFunc.getText() + "\n" + "CPF: " + jFormattedTextFieldCPFFunc.getText() + "\n" + "Telefone: " + jFormattedTextFieldTelefoneFunc.getText() + "\n" + "Nascimento: " + jFormattedTextFieldNascimentoFunc.getText(), "Atenção!", 0, 0);
        if (teste == 0) {
            daofuncionario.excluir(beansfuncionario);
            jButtonCancelarFunc.doClick();
            /*
            //Ativadores
            jButtonNovoFunc.setEnabled(true);
            jButtonNovoFunc.setVisible(true);
            jTextFieldNomeFunc.setEditable(true);
            jButtonSairFunc2.setEnabled(true);

            //Desativadores
            jButtonCancelarFunc.setEnabled(false);
            jButtonEditarFunc.setEnabled(false);
            jButtonDelFunc.setEnabled(false);

            jFormattedTextFieldCPFFunc.setEnabled(false);
            jFormattedTextFieldTelefoneFunc.setEnabled(false);
            jFormattedTextFieldCEPFunc.setEnabled(false);
            jComboBoxCargoFunc.setEnabled(false);
            jFormattedTextFieldNascimentoFunc.setEnabled(false);
            jTextFieldUsuarioFunc.setEnabled(false);
            jPasswordFieldSenhaFunc.setEnabled(false);
            jPasswordFieldConfirmarSenhaFunc.setEnabled(false);

            //CAMPOS DE ENDEREÇO
            jTextFieldRuaFunc.setEnabled(false);
            jTextFieldBairroFunc.setEnabled(false);
            jTextFieldNumeroFunc.setEnabled(false);
            jFormattedTextFieldCEPFunc.setEnabled(false);
            jTextFieldComplementoFunc.setEnabled(false);
            jTextFieldCidadeFunc.setEnabled(false);

            //Seteres de texto
            jTextFieldCodFunc.setText(null);
            jFormattedTextFieldCPFFunc.setText(null);
            jTextFieldNomeFunc.setText(null);
            jTextFieldUsuarioFunc.setText(null);
            jPasswordFieldSenhaFunc.setText(null);
            jPasswordFieldConfirmarSenhaFunc.setText(null);

            jFormattedTextFieldTelefoneFunc.setText(null);
            jComboBoxCargoFunc.setSelectedIndex(0);

            jFormattedTextFieldNascimentoFunc.setText(null);

            //CAMPOS DE ENDEREÇO
            jFormattedTextFieldCEPFunc.setText(null);
            jTextFieldRuaFunc.setText(null);
            jTextFieldBairroFunc.setText(null);
            jTextFieldNumeroFunc.setText(null);
            jFormattedTextFieldCEPFunc.setText(null);
            jTextFieldComplementoFunc.setText(null);
            jTextFieldCidadeFunc.setText(null);
             */
            preencherTabelaFunc("select * from funcionarios join enderecos on enderecos.cod_end = funcionarios.cod_end order by nomefunc");
        }
    }//GEN-LAST:event_jButtonDelFuncActionPerformed

    private void jButtonNovoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoClienteActionPerformed
        flag = 1;
        //Ativadores

        jTextFieldEmailCliente.setEnabled((true));
        jButtonSalvarCliente.setEnabled(true);
        jButtonCancelarCliente.setEnabled(true);
        /*
        jLabelCPFCliente.setEnabled(true);
        jLabelCodCliente.setEnabled(true);
        jLabelEnderecoCliente.setEnabled(true);
        jLabelNomeCliente.setEnabled(true);
        jLabelTelefoneCliente.setEnabled(true);
        jTextFieldCod.setEnabled(true);
         */
        //Desativadores
        jButtonNovoCliente.setEnabled(false);
        jButtonPesquisarCliente.setEnabled(false);
        jButtonEditarCliente.setEnabled(false);
        //habilita os campos de texto após clicar no botão "novo"
        jFormattedTextFieldCPFCliente.setEnabled(true);
        jTextFieldNomeCliente.setEnabled(true);

        jTableCliente.setEnabled(false);
        jScrollPane2.setEnabled(false);
        jTableCliente.clearSelection();

        jFormattedTextFieldTelCliente.setEnabled(true);
        jTextFieldEmailCliente.setEnabled(true);

        jFormattedTextFieldCPFCliente.setEditable(true);
        jTextFieldNomeCliente.setEditable(true);

        jFormattedTextFieldTelCliente.setEditable(true);
        jTextFieldEmailCliente.setEditable(true);

        //limpar campos de texto
        jTextFieldCod.setText(null);
        jFormattedTextFieldCPFCliente.setText(null);
        jTextFieldNomeCliente.setText(null);
        jTextFieldEmailCliente.setText(null);

        jFormattedTextFieldTelCliente.setText(null);
        jPanelBotoesCliente.setVisible(false);

        //coloca o foco no campo "código" após criar um novo cadastro
        jTextFieldNomeCliente.requestFocus();
    }//GEN-LAST:event_jButtonNovoClienteActionPerformed

    private void jButtonSalvarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarClienteActionPerformed
        //jTextField_Pesquisar.requestFocus();
        if (flag == 1) {
            //quando o campo está em branco
            if (jTextFieldNomeCliente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira um nome para prosseguir!", "Atenção!", 2);
                jTextFieldNomeCliente.requestFocus();
            } else if (jFormattedTextFieldCPFCliente.getText().equals("   .   .   -  ")) {
                JOptionPane.showMessageDialog(null, "Insira um CPF para prosseguir!", "Atenção!", 2);
                jFormattedTextFieldCPFCliente.requestFocus();
            } else if (jFormattedTextFieldCPFCliente.getText() != null) {
                //JOptionPane.showMessageDialog(rootPane, jFormattedTextFieldCPF.getText());
                String parte1 = jFormattedTextFieldCPFCliente.getText().substring(0, 3);
                String parte2 = jFormattedTextFieldCPFCliente.getText().substring(4, 7);
                String parte3 = jFormattedTextFieldCPFCliente.getText().substring(8, 11);
                String parte4 = jFormattedTextFieldCPFCliente.getText().substring(12, 14);
                String cpf = parte1 + parte2 + parte3 + parte4;
                //JOptionPane.showMessageDialog(rootPane, jFormattedTextFieldCPF.getText());
                verificaCpf.validaCpf(cpf);
                cpf = "";

                String verifica = String.valueOf(verificaCpf.isValido());

                if (verifica == "true") {
                    
                    beansCliente.setNome(jTextFieldNomeCliente.getText());
                    beansCliente.setCpf(jFormattedTextFieldCPFCliente.getText());
                    
                    if (jFormattedTextFieldTelCliente.getText().equals("(  ) 9     -    ")) {
                        beansCliente.setTelefone("");
                    } else {
                        beansCliente.setTelefone(jFormattedTextFieldTelCliente.getText());
                    }
                    beansCliente.setEmail(jTextFieldEmailCliente.getText());
                    daoCliente.salvar(beansCliente);
                    jButtonCancelarCliente.doClick();
                    preencherTabelaUsuario("select * from clientes order by nome_cliente");
                    
                } else {
                    
                    JOptionPane.showMessageDialog(rootPane, "Insira um CPF válido");
                    jFormattedTextFieldCPFCliente.setText("");
                    cpf = "";
                    jFormattedTextFieldCPFCliente.requestFocus();
                    
                }
            }
        } else {
            if (jTextFieldNomeCliente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira um nome para prosseguir!", "Atenção!", 2);
                jTextFieldNomeCliente.requestFocus();
            } else {
                beansCliente.setNome(jTextFieldNomeCliente.getText());
                if (jFormattedTextFieldCPFCliente.getText().equals("   .   .   -  ")) {
                    beansCliente.setCpf("");
                } else {
                    beansCliente.setCpf(jFormattedTextFieldCPFCliente.getText());
                }
                if (jFormattedTextFieldTelCliente.getText().equals("(  ) 9     -    ")) {
                    beansCliente.setTelefone("");
                } else {
                    beansCliente.setTelefone(jFormattedTextFieldTelCliente.getText());
                }
                beansCliente.setEmail(jTextFieldEmailCliente.getText());
                beansCliente.setCodigo(Integer.parseInt(jTextFieldCod.getText()));
                int teste = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar os dados desse funcionário? \n" + "Código: " + beansCliente.getCodigo() + "\n" + "Nome: " + beansCliente.getNome() + "\n" + "CPF: " + beansCliente.getCpf() + "\n" + "Endereço: " + beansCliente.getEmail() + "\n" + "Telefone: " + beansCliente.getTelefone(), "Atenção!", 0, 0);
                if (teste == 0) {
                    daoCliente.editar(beansCliente);
                    jButtonCancelarCliente.doClick();
                   
                    preencherTabelaUsuario("select * from clientes order by nome_cliente");
                    jPanelBotoesCliente.setVisible(true);
                    jButtonSairCliente.setEnabled(true);
                    jPanelBotoesCliente.setSize(580, 430);
                }
            }
        }
    }//GEN-LAST:event_jButtonSalvarClienteActionPerformed

    private void jButtonCancelarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarClienteActionPerformed
        jPanelBotoesCliente.setSize(580, 430);
        //Ativadores
        jPanelBotoesCliente.setVisible(true);
        jTextFieldNomeCliente.setEditable(true);
        jTextFieldNomeCliente.setEnabled(true);

        jButtonNovoCliente.setEnabled(true);
        jButtonPesquisarCliente.setEnabled(true);
        //jLabelNomeCliente.setEnabled(true);
        jScrollPane2.setEnabled(true);
        jTableCliente.setEnabled(true);

        //Desativadores

        /*jLabelCPFCliente.setEnabled(false);
        jLabelCodCliente.setEnabled(false);
        jLabelEnderecoCliente.setEnabled(false);
        jLabelTelefoneCliente.setEnabled(false);*/
        jFormattedTextFieldCPFCliente.setEnabled(false);

        jTextFieldCod.setEnabled(false);
        jFormattedTextFieldTelCliente.setEnabled(false);
        jTextFieldEmailCliente.setEnabled(false);

        jButtonSalvarCliente.setEnabled(false);
        jButtonCancelarCliente.setEnabled(false);
        jButtonEditarCliente.setEnabled(false);
        jButtonDel2.setEnabled(false);

        //limpar campos de texto
        jTextFieldCod.setText(null);
        jFormattedTextFieldCPFCliente.setText(null);
        jTextFieldNomeCliente.setText(null);

        jTextFieldEmailCliente.setText(null);
        jFormattedTextFieldTelCliente.setText(null);

        //coloca o foco no campo "nome" após cancelar
        jTextFieldNomeCliente.requestFocus();
        preencherTabelaUsuario("select * from clientes order by nome_cliente");
        jButtonSairCliente.setEnabled(true);
    }//GEN-LAST:event_jButtonCancelarClienteActionPerformed

    private void jButtonEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarClienteActionPerformed
        // TODO add your handling code here:
        flag = 2;

        //Ativadores
        jButtonEditarCliente.setVisible(true);
        jPanelBotoesCliente.setVisible(false);
        jFormattedTextFieldCPFCliente.setEditable(true);
        jTextFieldEmailCliente.setEditable(true);
        jTextFieldNomeCliente.setEditable(true);
        jFormattedTextFieldTelCliente.setEditable(true);

        jButtonSalvarCliente.setEnabled(true);

        jTextFieldNomeCliente.requestFocus();

        //Desativadores
        jTableCliente.setEnabled(false);
        jScrollPane2.setEnabled(false);
        jTableCliente.clearSelection();
        jButtonPesquisarCliente.setEnabled(false);
    }//GEN-LAST:event_jButtonEditarClienteActionPerformed

    private void jButtonDel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDel2ActionPerformed
        // TODO add your handling code here:
        beansCliente.setCodigo(Integer.parseInt(jTextFieldCod.getText()));

        int teste = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir os dados desse funcionário? \n" + "Código: " + beansCliente.getCodigo() + "\n" + "Nome: " + beansCliente.getNome() + "\n" + "CPF: " + beansCliente.getCpf() + "\n" + "Endereço: " + beansCliente.getEmail() + "Telefone: " + beansCliente.getTelefone(), "Atenção!", 0, 0);
        if (teste == 0) {
            daoCliente.excluir(beansCliente);
            jButtonCancelarCliente.doClick();
            //Ativadores
            /*jButtonNovo.setEnabled(true);
            jButtonPesquisar.setEnabled(true);
            jTextFieldNome.setEditable(true);

            //Desativadores
            jButtonCancelar.setEnabled(false);
            jButtonEditar.setEnabled(false);
            jButtonDel.setEnabled(false);

            jTextFieldEndereco.setEnabled(false);
            jTextFieldCPF.setEnabled(false);
            jTextFieldTelefone.setEnabled(false);

            jLabelEndereco.setEnabled(false);
            jLabelCPF.setEnabled(false);
            jLabelTelefone.setEnabled(false);

            //Seteres de texto
            jTextFieldCod.setText(null);
            jTextFieldCPF.setText(null);
            jTextFieldNome.setText(null);

            jTextFieldEndereco.setText(null);
            jTextFieldTelefone.setText(null);*/
            //preencherTabela("select * from clientes order by nome_cliente");
        }
    }//GEN-LAST:event_jButtonDel2ActionPerformed

    private void jButtonVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVendasActionPerformed
        // TODO add your handling code here:
        TelaVendas telaVendas = new TelaVendas();
        telaVendas.setVisible(true);
    }//GEN-LAST:event_jButtonVendasActionPerformed

    private void jButtonRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRelatoriosActionPerformed
        // TODO add your handling code here:
        Relatorios relatorios = new Relatorios();
        relatorios.setVisible(true);
    }//GEN-LAST:event_jButtonRelatoriosActionPerformed

    private void jButtonSairFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairFuncActionPerformed
        // TODO add your handling code here:
        flagaberto = 0;
        formaberto = "";
        /* TelaPrincipal telaPrincipal = new TelaPrincipal(user, permissao);
        telaPrincipal.setVisible(true);
        dispose();*/
        jPanelPrincipal.setVisible(true);
        jPanelFunciomario2.setVisible(false);
        //jPanelBotoesCliente.setVisible(false);
        jButtonSair2.setEnabled(true);
        jButtonEstoque.setEnabled(true);
        jButtonAddCliente.setEnabled(true);
        jButtonAddFuncionario.setEnabled(true);
        jButtonVendas.setEnabled(true);
    }//GEN-LAST:event_jButtonSairFuncActionPerformed

    private void jFormattedTextFieldTelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTelClienteActionPerformed

    private void jFormattedTextFieldCPFFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCPFFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCPFFuncActionPerformed

    private void jTextFieldComplementoFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldComplementoFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldComplementoFuncActionPerformed

    private void jTextFieldCidadeFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCidadeFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCidadeFuncActionPerformed

    public void preencherTabelaUsuario(String sql) {
        ArrayList dadosCliente = new ArrayList();
        String colunas[] = new String[]{"ID", "Nome", "CPF", "Tel", "Email"};

        // abre a conexao
        conexaoBD.conectaBD();
        conexaoBD.executaSql(sql);

        try {
            // pega o primeiro resultado encontrado na tabela
            conexaoBD.rs.first();

            // mostra todos os dados da tabela
            do {
                dadosCliente.add(new Object[]{
                    conexaoBD.rs.getInt("cod_cliente"),
                    conexaoBD.rs.getString("nome_cliente"),
                    conexaoBD.rs.getString("cpf_cliente"),
                    conexaoBD.rs.getString("tel_cliente"),
                    conexaoBD.rs.getString("email_cliente")

                });
            } while (conexaoBD.rs.next());// enquanto houver dados
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao preencher ArrayList.\nErro: " + ex);
        }

// cria uma intancia do modelo de tabela aqui nos passamos todos os objetos e o título de cada uma das colunas
        ModeloTabelaX modeloTabela = new ModeloTabelaX(dadosCliente, colunas);

        // seta o modelo da tabela 
        jTableCliente.setModel(modeloTabela);

        // ID
        jTableCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableCliente.getColumnModel().getColumn(0).setResizable(false);

        // NOME
        jTableCliente.getColumnModel().getColumn(1).setPreferredWidth(182);
        jTableCliente.getColumnModel().getColumn(1).setResizable(false);

        // CPF
        jTableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableCliente.getColumnModel().getColumn(2).setResizable(false);

        // TELEFONE
        jTableCliente.getColumnModel().getColumn(3).setPreferredWidth(115);
        jTableCliente.getColumnModel().getColumn(3).setResizable(false);

        // EMAIL
        jTableCliente.getColumnModel().getColumn(4).setPreferredWidth(225);
        jTableCliente.getColumnModel().getColumn(4).setResizable(false);

        // não deixa reordenar os dados da tabela
        jTableCliente.getTableHeader().setReorderingAllowed(false);

        // não deixa a tabela ser redimensionada
        jTableCliente.setAutoResizeMode(jTableCliente.AUTO_RESIZE_OFF);

        // permite selecionar apenas um dado na tabela por vez
        jTableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // fecha a conexão
        conexaoBD.desconectaBD();
    }

    public void preencherTabelaFunc(String sql) {
        ArrayList dadosUsuario = new ArrayList();
        String colunas[] = new String[]{"#", "Usuário", "Nome", "Cargo", "CPF", "Tel", "Nascimento", "Rua", "Nº", "Bairro", "Cidade", "Complemento", "CEP"};

        // abre a conexao
        conexaoBD.conectaBD();
        conexaoBD.executaSql(sql);

        try {
            // pega o primeiro resultado encontrado na tabela
            conexaoBD.rs.first();

            // mostra todos os dados da tabela
            do {
                dadosUsuario.add(new Object[]{
                    conexaoBD.rs.getInt("Cod_func"),
                    conexaoBD.rs.getString("usuario"),
                    conexaoBD.rs.getString("nomefunc"),
                    conexaoBD.rs.getString("cargo"),
                    conexaoBD.rs.getString("CPF"),
                    conexaoBD.rs.getString("telefone"),
                    conexaoBD.rs.getDate("Nascimento"),
                    conexaoBD.rs.getString("rua"),
                    conexaoBD.rs.getInt("numero"),
                    conexaoBD.rs.getString("bairro"),
                    conexaoBD.rs.getString("cidade"),
                    conexaoBD.rs.getString("comp"),
                    conexaoBD.rs.getInt("cep")

                });
            } while (conexaoBD.rs.next());// enquanto houver dados
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao preencher ArrayList.\nErro: " + ex);
        }

// cria uma intancia do modelo de tabela aqui nos passamos todos os objetos e o título de cada uma das colunas
        ModeloTabelaX modeloTabela = new ModeloTabelaX(dadosUsuario, colunas);

        // seta o modelo da tabela 
        jTableFuncionarios.setModel(modeloTabela);

        // CODIGO
        jTableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableFuncionarios.getColumnModel().getColumn(0).setResizable(false);

        // USUÁRIO
        jTableFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableFuncionarios.getColumnModel().getColumn(1).setResizable(false);

        // NOME
        jTableFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(182);
        jTableFuncionarios.getColumnModel().getColumn(2).setResizable(false);

        // CARGO
        jTableFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(83);
        jTableFuncionarios.getColumnModel().getColumn(3).setResizable(false);

        // CPF
        jTableFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTableFuncionarios.getColumnModel().getColumn(4).setResizable(false);

        // TELEFONE
        jTableFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(105);
        jTableFuncionarios.getColumnModel().getColumn(5).setResizable(false);

        // NASCIMENTO
        jTableFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(85);
        jTableFuncionarios.getColumnModel().getColumn(6).setResizable(false);

        // RUA
        jTableFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(160);
        jTableFuncionarios.getColumnModel().getColumn(7).setResizable(false);

        // NÚMERO
        jTableFuncionarios.getColumnModel().getColumn(8).setPreferredWidth(45);
        jTableFuncionarios.getColumnModel().getColumn(8).setResizable(false);

        // BAIRRO
        jTableFuncionarios.getColumnModel().getColumn(9).setPreferredWidth(140);
        jTableFuncionarios.getColumnModel().getColumn(9).setResizable(false);

        // CIDADE
        jTableFuncionarios.getColumnModel().getColumn(10).setPreferredWidth(130);
        jTableFuncionarios.getColumnModel().getColumn(10).setResizable(false);

        // COMPLEMENTO
        jTableFuncionarios.getColumnModel().getColumn(11).setPreferredWidth(100);
        jTableFuncionarios.getColumnModel().getColumn(11).setResizable(false);

        // CEP
        jTableFuncionarios.getColumnModel().getColumn(12).setPreferredWidth(80);
        jTableFuncionarios.getColumnModel().getColumn(12).setResizable(false);

        // não deixa reordenar os dados da tabela
        jTableFuncionarios.getTableHeader().setReorderingAllowed(false);

        // não deixa a tabela ser redimensionada
        jTableFuncionarios.setAutoResizeMode(jTableFuncionarios.AUTO_RESIZE_OFF);

        // permite selecionar apenas um dado na tabela por vez
        jTableFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // fecha a conexão
        conexaoBD.desconectaBD();
    }

    public void preencherTabelaProdutosEmFalta(String sql) {
        ArrayList dadosUsuario = new ArrayList();
        String colunas[] = new String[]{"ID", "Nome", "$ Compra", "$ Venda", "QTD", "Fornecedor", "Categoria", "Receita"};

        // abre a conexao
        conexaoBD.conectaBD();
        conexaoBD.executaSql(sql);

        try {
            // pega o primeiro resultado encontrado na tabela
            conexaoBD.rs.first();

            // mostra todos os dados da tabela
            do {
                dadosUsuario.add(new Object[]{
                    conexaoBD.rs.getInt("idproduto"),
                    conexaoBD.rs.getString("nomeproduto"),
                    conexaoBD.rs.getDouble("valorcompra"),
                    conexaoBD.rs.getDouble("valorvenda"),
                    conexaoBD.rs.getString("quantidade"),
                    conexaoBD.rs.getString("fornecedor"),
                    conexaoBD.rs.getString("categoria"),
                    conexaoBD.rs.getString("receita")

                });
            } while (conexaoBD.rs.next());// enquanto houver dados
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao preencher ArrayList.\nErro: " + ex);
        }

// cria uma intancia do modelo de tabela aqui nos passamos todos os objetos e o título de cada uma das colunas
        ModeloTabelaX modeloTabela = new ModeloTabelaX(dadosUsuario, colunas);

        // seta o modelo da tabela 
        jTableProdutosEmFalta.setModel(modeloTabela);

        // ID
        jTableProdutosEmFalta.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableProdutosEmFalta.getColumnModel().getColumn(0).setResizable(false);

        // NOME
        jTableProdutosEmFalta.getColumnModel().getColumn(1).setPreferredWidth(85);
        jTableProdutosEmFalta.getColumnModel().getColumn(1).setResizable(false);

        // VALOR COMPRA
        jTableProdutosEmFalta.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTableProdutosEmFalta.getColumnModel().getColumn(2).setResizable(false);

        // VALOR VENDA
        jTableProdutosEmFalta.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTableProdutosEmFalta.getColumnModel().getColumn(3).setResizable(false);

        // QTD
        jTableProdutosEmFalta.getColumnModel().getColumn(4).setPreferredWidth(35);
        jTableProdutosEmFalta.getColumnModel().getColumn(4).setResizable(false);

        // FORNECEDOR
        jTableProdutosEmFalta.getColumnModel().getColumn(5).setPreferredWidth(120);
        jTableProdutosEmFalta.getColumnModel().getColumn(5).setResizable(false);

        // CATEGORIA
        jTableProdutosEmFalta.getColumnModel().getColumn(6).setPreferredWidth(85);
        jTableProdutosEmFalta.getColumnModel().getColumn(6).setResizable(false);

        // RECEITA
        jTableProdutosEmFalta.getColumnModel().getColumn(7).setPreferredWidth(55);
        jTableProdutosEmFalta.getColumnModel().getColumn(7).setResizable(false);

        // não deixa reordenar os dados da tabela
        jTableProdutosEmFalta.getTableHeader().setReorderingAllowed(false);

        // não deixa a tabela ser redimensionada
        jTableProdutosEmFalta.setAutoResizeMode(jTableProdutosEmFalta.AUTO_RESIZE_OFF);

        // permite selecionar apenas um dado na tabela por vez
        jTableProdutosEmFalta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // fecha a conexão
        conexaoBD.desconectaBD();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fundo;
    private javax.swing.JButton jButtonAddCliente;
    private javax.swing.JButton jButtonAddFuncionario;
    private javax.swing.JButton jButtonCancelarCliente;
    private javax.swing.JButton jButtonCancelarFunc;
    private javax.swing.JButton jButtonDel2;
    private javax.swing.JButton jButtonDelFunc;
    private javax.swing.JButton jButtonEditarCliente;
    private javax.swing.JButton jButtonEditarFunc;
    private javax.swing.JButton jButtonEstoque;
    private javax.swing.JButton jButtonMinimizar;
    private javax.swing.JButton jButtonNao;
    private javax.swing.JButton jButtonNovoCliente;
    private javax.swing.JButton jButtonNovoFunc;
    private javax.swing.JButton jButtonPesquisarCliente;
    private javax.swing.JButton jButtonRelatorios;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSair2;
    private javax.swing.JButton jButtonSairCliente;
    private javax.swing.JButton jButtonSairFunc;
    private javax.swing.JButton jButtonSairlogin;
    private javax.swing.JButton jButtonSalvarCliente;
    private javax.swing.JButton jButtonSalvarFunc;
    private javax.swing.JButton jButtonVendas;
    private javax.swing.JComboBox<String> jComboBoxCargoFunc;
    private javax.swing.JFormattedTextField jFormattedTextFieldCEPFunc;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPFCliente;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPFFunc;
    private javax.swing.JFormattedTextField jFormattedTextFieldNascimentoFunc;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelCliente;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefoneFunc;
    private javax.swing.JInternalFrame jInternalFrameConfirmacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelBackground;
    private javax.swing.JLabel jLabelBackground1;
    private javax.swing.JLabel jLabelBemVindo;
    private javax.swing.JLabel jLabelImagemFuncionario;
    private javax.swing.JLabel jLabelProdutosEmFalta;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelBotoesCliente;
    private javax.swing.JPanel jPanelCadastroCliente;
    private javax.swing.JPanel jPanelFunciomario2;
    private javax.swing.JPanel jPanelPraTampar;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPasswordField jPasswordFieldConfirmarSenhaFunc;
    private javax.swing.JPasswordField jPasswordFieldSenhaFunc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneFuncionarios;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTable jTableFuncionarios;
    private javax.swing.JTable jTableProdutosEmFalta;
    private javax.swing.JTextField jTextFieldBairroFunc;
    private javax.swing.JTextField jTextFieldCidadeFunc;
    private javax.swing.JTextField jTextFieldCod;
    private javax.swing.JTextField jTextFieldCodEndFunc;
    private javax.swing.JTextField jTextFieldCodFunc;
    private javax.swing.JTextField jTextFieldComplementoFunc;
    private javax.swing.JTextField jTextFieldEmailCliente;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeFunc;
    private javax.swing.JTextField jTextFieldNumeroFunc;
    private javax.swing.JTextField jTextFieldRuaFunc;
    private javax.swing.JTextField jTextFieldUsuarioFunc;
    // End of variables declaration//GEN-END:variables
}
/**/
