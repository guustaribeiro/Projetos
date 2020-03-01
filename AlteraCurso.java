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
public class AlteraCurso extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastroCurso
     */
    public AlteraCurso() {
        initComponents();
        DefaultTableModel dmtCursos = (DefaultTableModel) jTCursos.getModel(); // usamos o DefaultTableModel para armazenar os objetos em vetores
        jTCursos.setRowSorter(new TableRowSorter(dmtCursos));
        
        
        readAlteraCurso();
    }

    
    public void readAlteraCurso(){
        DefaultTableModel dmtCursos = (DefaultTableModel) jTCursos.getModel();
        dmtCursos.setNumRows(0); // Seta o número de linhas como 0 para não cadastrar na lista duas vezes os mesmos dados
        CursoDAO cdao = new CursoDAO();
        
        for(Curso c: cdao.findAll()){ // Percorremos o objeto setando a lista findAll no Aluno 
            
            dmtCursos.addRow(new Object[]{ //Passamos os valores vindo do banco de dados nesse vetor
                c.getCodigo(),
                c.getDescricao(),
                c.getEmenta()
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCurso = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCursos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEmenta = new javax.swing.JTextArea();

        setClosable(true);
        setTitle("Alterar Curso");

        jLabel1.setText("Descrição");

        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Ementa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        jLabel2.setText("Ementa");

        txtEmenta.setColumns(20);
        txtEmenta.setRows(5);
        jScrollPane2.setViewportView(txtEmenta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(txtCurso))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(jTCursos.getSelectedRow() != -1){ // se for diferente de -1, foi selecionado alguma linha na lista
            Curso a = new Curso();
            CursoDAO dao = new CursoDAO();
            a.setDescricao(txtCurso.getText());
            a.setEmenta(txtEmenta.getText());
            a.setCodigo((int)jTCursos.getValueAt(jTCursos.getSelectedRow(), 0)); 
            dao.update(a);

            txtCurso.setText(""); //limpar campo após cadastrar
            txtEmenta.setText("");
            
            readAlteraCurso();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTCursosMouseClicked
        
        if(jTCursos.getSelectedRow() != -1){
                                                       //selecionando linha , (1) referente a coluna
            txtCurso.setText(jTCursos.getValueAt(jTCursos.getSelectedRow(), 1).toString());
            txtEmenta.setText(jTCursos.getValueAt(jTCursos.getSelectedRow(), 2).toString());
            
        }
        
    }//GEN-LAST:event_jTCursosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTCursos;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextArea txtEmenta;
    // End of variables declaration//GEN-END:variables
}
