/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Aluno;
import model.dao.AlunoDAO;

/**
 *
 * @author Elton Sola
 */
public class ConsultaAluno extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastroCurso
     */
    public ConsultaAluno() {
        initComponents();
        DefaultTableModel dmtAlunos = (DefaultTableModel) jTNomes.getModel(); // usamos o DefaultTableModel para armazenar os objetos em vetores
        jTNomes.setRowSorter(new TableRowSorter(dmtAlunos));
        
        readAlteraAluno();
    }
    
    public void readAlteraAluno(){
        DefaultTableModel dmtAlunos = (DefaultTableModel) jTNomes.getModel();
        dmtAlunos.setNumRows(0); // Seta o número de linhas como 0 para não cadastrar na lista duas vezes os mesmos dados 
        AlunoDAO adao = new AlunoDAO();
        
        for(Aluno a: adao.findAll()){ // Percorremos o objeto setando a lista findAll no Aluno 
            
            dmtAlunos.addRow(new Object[]{ //Passamos os valores vindo do banco de dados nesse vetor
                a.getCodigo(),
                a.getNome()
            });
        }
    }
    
    public void readForNome(String nome){
        DefaultTableModel dmtAlunos = (DefaultTableModel) jTNomes.getModel();
        dmtAlunos.setNumRows(0); // Seta o número de linhas como 0 para não cadastrar na lista duas vezes os mesmos dados 
        AlunoDAO adao = new AlunoDAO();
        
        for(Aluno a: adao.readForNome(nome)){ // Percorremos o objeto setando a lista readForNome no Aluno 
            
            dmtAlunos.addRow(new Object[]{ //Passamos os valores vindo do banco de dados nesse vetor
                a.getCodigo(),
                a.getNome()
            });
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBucaNome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNomes = new javax.swing.JTable();

        setClosable(true);
        setTitle("Consultar Aluno");

        jLabel1.setText("Consultar Aluno");

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTNomes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTNomes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTNomesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTNomes);
        if (jTNomes.getColumnModel().getColumnCount() > 0) {
            jTNomes.getColumnModel().getColumn(0).setMinWidth(70);
            jTNomes.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(txtBucaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(293, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBucaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTNomesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTNomesMouseClicked
        
    }//GEN-LAST:event_jTNomesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

            readForNome(txtBucaNome.getText()); //Executamos o metódo que vai procurar o nome do Aluno
            
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTNomes;
    private javax.swing.JTextField txtBucaNome;
    // End of variables declaration//GEN-END:variables
}
