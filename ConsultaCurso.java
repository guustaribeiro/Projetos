/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Curso;
import model.dao.CursoDAO;

/**
 *
 * @author Elton Sola
 */
public class ConsultaCurso extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastroCurso
     */
    public ConsultaCurso() {
        initComponents();
        DefaultTableModel dmtCursos = (DefaultTableModel) jTCursos.getModel(); // usamos o DefaultTableModel para armazenar os objetos em vetores
        jTCursos.setRowSorter(new TableRowSorter(dmtCursos));
        
        
        readAlteraCurso();
    }

    
    public void readAlteraCurso(){
        DefaultTableModel dmtCursos = (DefaultTableModel) jTCursos.getModel();
        dmtCursos.setNumRows(0); // Seta o número de linhas como 0 para não cadastrar na lista duas vezes os mesmos dados
        CursoDAO cdao = new CursoDAO();
        
        for(Curso c: cdao.findAll()){ // Percorremos o objeto setando a lista findAll no Curso 
            
            dmtCursos.addRow(new Object[]{ //Passamos os valores vindo do banco de dados nesse vetor
                c.getCodigo(),
                c.getDescricao(),
                c.getEmenta()
            });
        }
    }
    
    public void readForDesc(String descricao){
        DefaultTableModel dmtAlunos = (DefaultTableModel) jTCursos.getModel();
        dmtAlunos.setNumRows(0); // Seta o número de linhas como 0 para não cadastrar na lista duas vezes os mesmos dados 
        CursoDAO cdao = new CursoDAO();
        
        for(Curso c: cdao.readForDesc(descricao)){ // Percorremos o objeto setando a lista readForDesc no Curso 
            
            dmtAlunos.addRow(new Object[]{ //Passamos os valores vindo do banco de dados nesse vetor
                c.getCodigo(),
                c.getDescricao()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBuscaDesc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCursos = new javax.swing.JTable();

        setClosable(true);
        setTitle("Consultar Curso");

        jLabel1.setText("Consultar Curso");

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTCursos);
        if (jTCursos.getColumnModel().getColumnCount() > 0) {
            jTCursos.getColumnModel().getColumn(0).setMinWidth(70);
            jTCursos.getColumnModel().getColumn(0).setMaxWidth(70);
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
                    .addComponent(txtBuscaDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(302, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTCursosMouseClicked

    }//GEN-LAST:event_jTCursosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        readForDesc(txtBuscaDesc.getText()); //Executamos o metódo que vai procurar o nome do Curso
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTCursos;
    private javax.swing.JTextField txtBuscaDesc;
    // End of variables declaration//GEN-END:variables
}
