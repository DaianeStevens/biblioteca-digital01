/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.unijui.form;

import br.edu.unijui.model.Livro;
import br.edu.unijui.model.Locacao;
import br.edu.unijui.model.Usuario;
import br.edu.unijui.model.dao.LivroImpl;
import br.edu.unijui.model.dao.LocacaoImpl;
import br.edu.unijui.model.dao.UsuarioImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author chayk
 */
public class LocacaoForm extends javax.swing.JFrame {

    Locacao locacao = new Locacao();
    DefaultTableModel model;
    DefaultTableModel modelLivros;
    DefaultTableModel modelLivrosAdicionados;
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    /**
     * Creates new form LocacaoForm
     */
    public LocacaoForm() {
        initComponents();
        this.model = (DefaultTableModel) locacoesTable.getModel();
        this.modelLivrosAdicionados = (DefaultTableModel) livrosAdicionadosTable.getModel();

        disabledInputs();
        btnDevolver.setEnabled(false);

        locacoesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = locacoesTable.getSelectedRow();
                    if (selectedRow >= 0) {

                        btnDevolver.setEnabled(true);
                        // Aqui você pode acessar os dados da linha selecionada
                        Object codigo = locacoesTable.getValueAt(selectedRow, 0);
                        Object codigoUsuario = locacoesTable.getValueAt(selectedRow, 1);
                        Object dataLocacao = locacoesTable.getValueAt(selectedRow, 2);
                        Object dataPrazoDevolucao = locacoesTable.getValueAt(selectedRow, 3);
                        Object dataDevolucao = locacoesTable.getValueAt(selectedRow, 4);

                        // Faça o que desejar com os dados da linha selecionada
                        System.out.println("Linha selecionada: " + codigo + ", " + codigoUsuario + ", " + dataLocacao + ", " + dataPrazoDevolucao + ", " + dataDevolucao);
                    } else {
                        btnDevolver.setEnabled(false);
                        btnSalvar.setEnabled(false);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        locacoesTable = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnDevolver = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        selectUsuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAdicionaLivro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        livrosAdicionadosTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        livrosTable = new javax.swing.JTable();
        inputFiltroLivro = new javax.swing.JTextField();
        btnFiltroLivro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gerenciar Locações");

        locacoesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Código Usuário", "Data Locação", "Data Prazo Devolução", "Data Devolução"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(locacoesTable);
        if (locacoesTable.getColumnModel().getColumnCount() > 0) {
            locacoesTable.getColumnModel().getColumn(0).setMinWidth(40);
            locacoesTable.getColumnModel().getColumn(0).setMaxWidth(100);
            locacoesTable.getColumnModel().getColumn(1).setMinWidth(100);
            locacoesTable.getColumnModel().getColumn(1).setMaxWidth(150);
        }

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarMouseClicked(evt);
            }
        });

        btnDevolver.setText("Devolver");
        btnDevolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDevolverMouseClicked(evt);
            }
        });
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        btnSalvar.setBackground(new java.awt.Color(0, 204, 51));
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalvarMouseClicked(evt);
            }
        });

        selectUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuário:");

        jLabel3.setText("Livros:");

        btnAdicionaLivro.setBackground(new java.awt.Color(51, 153, 255));
        btnAdicionaLivro.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionaLivro.setText("Adicionar");
        btnAdicionaLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdicionaLivroMouseClicked(evt);
            }
        });
        btnAdicionaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionaLivroActionPerformed(evt);
            }
        });

        livrosAdicionadosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Título"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(livrosAdicionadosTable);
        if (livrosAdicionadosTable.getColumnModel().getColumnCount() > 0) {
            livrosAdicionadosTable.getColumnModel().getColumn(0).setMinWidth(50);
            livrosAdicionadosTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jLabel4.setText("Livros adicionados:");

        livrosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Título", "Autor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(livrosTable);
        if (livrosTable.getColumnModel().getColumnCount() > 0) {
            livrosTable.getColumnModel().getColumn(0).setMinWidth(50);
            livrosTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        inputFiltroLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFiltroLivroActionPerformed(evt);
            }
        });

        btnFiltroLivro.setText("Buscar");
        btnFiltroLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFiltroLivroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnCadastrar)
                            .addGap(18, 18, 18)
                            .addComponent(btnDevolver))
                        .addComponent(jScrollPane1)
                        .addComponent(jSeparator1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(inputFiltroLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnFiltroLivro)))
                                    .addGap(0, 1, Short.MAX_VALUE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(btnAdicionaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputFiltroLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFiltroLivro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnAdicionaLivro))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnDevolver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            buscaLocacoes();

            //Popula Table Livros
            this.modelLivros = (DefaultTableModel) livrosTable.getModel();
            LivroImpl livroImpl = new LivroImpl();
            PopulateTableLivros(livroImpl.getLivrosFiltro(null));

            //Popula select de usuários
            UsuarioImpl usuarioImpl = new UsuarioImpl();
            usuarios = usuarioImpl.getUsuariosFiltro(null);
            selectUsuario.removeAllItems();
            for (Usuario item : usuarios) {
                selectUsuario.addItem(item.getNome());
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LivroForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LivroForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMouseClicked
        enabledInputs();
        btnDevolver.setEnabled(false);
//        btnSalvar.setEnabled(true);
        locacoesTable.clearSelection();


    }//GEN-LAST:event_btnCadastrarMouseClicked

    private void selectUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectUsuarioActionPerformed

    private void btnAdicionaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionaLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionaLivroActionPerformed

    private void inputFiltroLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFiltroLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputFiltroLivroActionPerformed

    private void btnFiltroLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFiltroLivroMouseClicked
        try {
            String filtro = inputFiltroLivro.getText();
            LivroImpl livroImpl = new LivroImpl();
            PopulateTableLivros(livroImpl.getLivrosFiltro(filtro));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LivroForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LivroForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnFiltroLivroMouseClicked

    private void btnAdicionaLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdicionaLivroMouseClicked

        int selectedRow = livrosTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Aqui você pode acessar os dados da linha selecionada
            Livro livroAdicionado = new Livro();
            livroAdicionado.setId((Integer) livrosTable.getValueAt(selectedRow, 0));
            livroAdicionado.setTitulo((String) livrosTable.getValueAt(selectedRow, 1));
            PopulateTableLivrosAdicionados(livroAdicionado);
            // Faça o que desejar com os dados da linha selecionada
            //System.out.println("Linha selecionada: " + codigo + ", " + titulo);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um livro na tabela acima!");
        }

    }//GEN-LAST:event_btnAdicionaLivroMouseClicked

    private void btnSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseClicked
        try {
            //id do usuario
            int indexUsuario = selectUsuario.getSelectedIndex();
            Usuario objUusario = usuarios.get(indexUsuario);

            LocacaoImpl locacaoImpl;

            locacaoImpl = new LocacaoImpl();

            Locacao objLocacao = new Locacao();
            objLocacao.setIdUsuario(objUusario.getId());
            objLocacao.setDtLocacao(new java.sql.Date(new Date().getTime()));
            objLocacao.setDtPrazoDevolucao(new java.sql.Date(new Date().getTime()));
            // Obtenha a data atual
            Date dataAtual = new Date();
            // Crie um objeto Calendar e configure-o com a data atual
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataAtual);
            // Adicione 15 dias à data
            calendar.add(Calendar.DAY_OF_MONTH, 15);
            // Obtenha a nova data após adicionar os 15 dias
            Date dataPrazo = calendar.getTime();
            //objLocacao.setDtPrazoDevolucao(new java.sql.Date(dataPrazo.getTime()));

            ArrayList<Livro> livrosAdicionados = buscaLivrosSelecionados();

            locacaoImpl.insereLocacao(objLocacao, livrosAdicionados);
            buscaLocacoes();
            disabledInputs();

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar locação!", "Erro!", ERROR);
            Logger.getLogger(LocacaoForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSalvarMouseClicked

    private void btnDevolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDevolverMouseClicked
        LocacaoImpl locacaoImpl;
        try {
            locacaoImpl = new LocacaoImpl();

            int selectedRow = locacoesTable.getSelectedRow();
            if (selectedRow >= 0) {

                Integer codigo = (int) locacoesTable.getValueAt(selectedRow, 0);

                locacaoImpl.devolveLocacao(codigo);
                buscaLocacoes();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir locação!", "Erro!", ERROR);
            Logger.getLogger(LocacaoForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDevolverMouseClicked

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDevolverActionPerformed

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
            java.util.logging.Logger.getLogger(LocacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocacaoForm().setVisible(true);
            }
        });
    }

    private void disabledInputs() {
        selectUsuario.setEnabled(false);
        inputFiltroLivro.setEnabled(false);
        btnFiltroLivro.setEnabled(false);
        livrosTable.setEnabled(false);
        livrosAdicionadosTable.setEnabled(false);
        btnAdicionaLivro.setEnabled(false);
        btnSalvar.setEnabled(false);

        inputFiltroLivro.setText("");
        modelLivrosAdicionados.setRowCount(0);

    }

    private void enabledInputs() {

        selectUsuario.setEnabled(true);
        inputFiltroLivro.setEnabled(true);
        btnFiltroLivro.setEnabled(true);
        livrosTable.setEnabled(true);
        livrosAdicionadosTable.setEnabled(true);
        btnAdicionaLivro.setEnabled(true);
        btnSalvar.setEnabled(true);
    }

    private void PopulateTableLocacoes(ArrayList<Locacao> locacoes) {
        model.setRowCount(0);
        for (Locacao obj : locacoes) {
            Object rowData[] = new Object[5];
            rowData[0] = obj.getId();
            rowData[1] = obj.getIdUsuario();
            rowData[2] = obj.getDtPrazoDevolucao();
            rowData[3] = obj.getDtLocacao();
            rowData[4] = obj.getDtDevolucao();
            model.addRow(rowData);
        }
    }

    private void PopulateTableLivros(ArrayList<Livro> livros) {
        modelLivros.setRowCount(0);
        for (Livro obj : livros) {
            Object rowData[] = new Object[3];
            rowData[0] = obj.getId();
            rowData[1] = obj.getTitulo();
            rowData[2] = obj.getAutor();
            modelLivros.addRow(rowData);
        }
    }

    private void PopulateTableLivrosAdicionados(Livro livro) {
        Object rowData[] = new Object[2];
        rowData[0] = livro.getId();
        rowData[1] = livro.getTitulo();
        modelLivrosAdicionados.addRow(rowData);
    }

    private ArrayList<Livro> buscaLivrosSelecionados() {
        ArrayList<Livro> livrosAdicionados = new ArrayList<Livro>();

        int numRows = modelLivrosAdicionados.getRowCount();

        for (int row = 0; row < numRows; row++) {
            int codigo = (int) modelLivrosAdicionados.getValueAt(row, 0);

            Livro livro = new Livro();
            livro.setId(codigo);

            livrosAdicionados.add(livro);
        }

        return livrosAdicionados;
    }

    private void buscaLocacoes() {
        //Popula Table Locacoes
        LocacaoImpl locacaoImpl;
        try {
            locacaoImpl = new LocacaoImpl();

            PopulateTableLocacoes(locacaoImpl.getLocacaoes());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LocacaoForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionaLivro;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnFiltroLivro;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField inputFiltroLivro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable livrosAdicionadosTable;
    private javax.swing.JTable livrosTable;
    private javax.swing.JTable locacoesTable;
    private javax.swing.JComboBox<String> selectUsuario;
    // End of variables declaration//GEN-END:variables
}
