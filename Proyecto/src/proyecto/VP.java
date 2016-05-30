/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AitorBM
 */
public class VP extends javax.swing.JFrame {

    private int exportadosCat = 0;
    private int exportadosLib = 0;

    /**
     * Creates new form VP
     *
     * @throws java.sql.SQLException
     */
    public VP() throws SQLException {
        initComponents();

        //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "system", "oracle");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbCategorias = new javax.swing.JButton();
        jbPreguntas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jbExportarCategoria = new javax.swing.JButton();
        jbExportarLibre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gestión:");

        jbCategorias.setText("Categorias");
        jbCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCategoriasActionPerformed(evt);
            }
        });

        jbPreguntas.setText("Preguntas");
        jbPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPreguntasActionPerformed(evt);
            }
        });

        jLabel2.setText("Exportado:");

        jbExportarCategoria.setText("Por categoría");
        jbExportarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExportarCategoriaActionPerformed(evt);
            }
        });

        jbExportarLibre.setText("Libre");
        jbExportarLibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExportarLibreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbExportarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbExportarLibre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbPreguntas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCategorias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbPreguntas)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbExportarCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbExportarLibre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCategoriasActionPerformed
        // TODO add your handling code here:
        VCategorias vCat = new VCategorias();
        vCat.setVisible(true);
    }//GEN-LAST:event_jbCategoriasActionPerformed

    private void jbPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPreguntasActionPerformed
        // TODO add your handling code here:
        VPreguntas vPre = new VPreguntas();
        vPre.setVisible(true);
    }//GEN-LAST:event_jbPreguntasActionPerformed

    private void jbExportarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExportarCategoriaActionPerformed
        try {
            VExportarCat vECat = new VExportarCat(this);
            vECat.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(VP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbExportarCategoriaActionPerformed

    private void jbExportarLibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExportarLibreActionPerformed
        // TODO add your handling code here:
        try {
            VExportarLibre vELib = new VExportarLibre(this);
            vELib.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(VP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbExportarLibreActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbCategorias;
    private javax.swing.JButton jbExportarCategoria;
    private javax.swing.JButton jbExportarLibre;
    private javax.swing.JButton jbPreguntas;
    private javax.swing.JPanel jpPrincipal;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the exportadosCat
     */
    public int getExportadosCat() {
        return exportadosCat;
    }

    /**
     * @param exportadosCat the exportadosCat to set
     */
    public void setExportadosCat(int exportadosCat) {
        this.exportadosCat = exportadosCat;
    }

    /**
     * @return the exportadosLib
     */
    public int getExportadosLib() {
        return exportadosLib;
    }

    /**
     * @param exportadosLib the exportadosLib to set
     */
    public void setExportadosLib(int exportadosLib) {
        this.exportadosLib = exportadosLib;
    }
}
