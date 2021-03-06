/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Aluno;
import model.dao.AlunoDAO;

/**
 *
 * @author Elton Sola
 */
public class AlteraAluno extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastroCurso
     */
    public AlteraAluno() {
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtAluno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNomes = new javax.swing.JTable();

        setClosable(true);
        setTitle("Alterar Aluno");

        jLabel1.setText("Nome");

        txtAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlunoActionPerformed(evt);
            }
        });

        jButton1.setText("Alterar");
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
                    .addComponent(txtAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(jTNomes.getSelectedRow() != -1){ // se for diferente de -1, foi selecionado alguma linha na lista
            Aluno a = new Aluno();
            AlunoDAO dao = new AlunoDAO();
            a.setNome(txtAluno.getText());
            a.setCodigo((int)jTNomes.getValueAt(jTNomes.getSelectedRow(), 0)); 
            dao.update(a);

            txtAluno.setText(""); //limpar campo após alterar
            
            readAlteraAluno();
        }
          
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTNomesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTNomesMouseClicked
        
        if(jTNomes.getSelectedRow() != -1){
                                                  //selecionando linha , (1) referente a coluna
            txtAluno.setText(jTNomes.getValueAt(jTNomes.getSelectedRow(), 1).toString());
            
        }
        
    }//GEN-LAST:event_jTNomesMouseClicked

    private void txtAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlunoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTNomes;
    private javax.swing.JTextField txtAluno;
    // End of variables declaration//GEN-END:variables
}
