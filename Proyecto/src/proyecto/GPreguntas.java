/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author AitorBM
 */
public class GPreguntas extends javax.swing.JFrame {

    /**
     * Creates new form GPreguntas
     */
    private Connection conn;
    private int opcion;
    private int n = 0;
    private List<Pregunta> pre = new ArrayList<>();
    private List<Categoria> cat = new ArrayList<>();
    private List<Respuesta> res = new ArrayList<>();

    private void ActualizarPreguntas() throws SQLException {
        int i;
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select * from preguntas");
        while (rset.next()) {
            Pregunta p = new Pregunta();
            p.setId(Integer.parseInt(rset.getString(1)));
            p.setTexto(rset.getString(2));
            i = Integer.parseInt(rset.getString(3));
            p.setCategoria(cat.get(i - 1));
            pre.add(p);
        }
        stmt.close();
    }

    private void addRes(JRadioButton rb, JTextField tf) throws SQLException {
        int correcta = 0;
        if (rb.isSelected()) {
            correcta = 1;
        }
        int respuesta = pre.get(pre.size() - 1).getId();
        String sql = "{ CALL GEST_PROYECTO_GIFT.INSERT_RESPUESTA(?,?,?) }";
        CallableStatement cs;
        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, respuesta);
            cs.setString(2, tf.getText());
            cs.setInt(3, correcta);
            cs.execute();
            System.out.println("INFO: Procedimiento ejecutado");
        } catch (SQLException ex) {
            System.out.println("ERROR: No se ha podido ejecutar la consulta");
            Logger.getLogger(GCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateRes(JRadioButton rb, JTextField tf, int i) throws SQLException {
        int correcta = 0;
        if (rb.isSelected()) {
            correcta = 1;
        }
        int respuesta = pre.get(n).getRespuestas().get(i).getId();
        String sql = "{ CALL GEST_PROYECTO_GIFT.UPDATE_RESPUESTA(?,?,?) }";
        CallableStatement cs;
        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, respuesta);
            cs.setString(2, tf.getText());
            cs.setInt(3, correcta);
            cs.execute();
            System.out.println("INFO: Procedimiento ejecutado");
        } catch (SQLException ex) {
            System.out.println("ERROR: No se ha podido ejecutar la consulta");
            Logger.getLogger(GCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GPreguntas(int opcion) throws SQLException, ClassNotFoundException {
        this.opcion = opcion;

        initComponents();

        /*
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        //Conexion maquina vagrant
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "system", "oracle");
        Conexion server egibide -> conn = DriverManager.getConnection("jdbc:oracle:thin:@SrvOracle:1521:orcl", "noc08", "noc08");
        Conexion en mi casa -> conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.2:1521:orcl", "SYSTEM", "root");
        */
        
        Class.forName("com.mysql.jdbc.Driver");
            
            // Cadena de conexión
            String bd = "scotchbox";
            String login = "root";
            String password = "root";
            String url = "jdbc:mysql://192.168.33.10:3306/" + bd;

            // Establecimiento de conexión
            conn = DriverManager.getConnection(url, login, password);

            // [2] Consulta
        System.out.println("INFO: Conexión abierta");

        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select * from categorias");
        while (rset.next()) {
            Categoria c = new Categoria();
            c.setId(rset.getInt(1));
            c.setNombre(rset.getString(2));
            cat.add(c);
        }
        stmt.close();

        ActualizarPreguntas();

        stmt = conn.createStatement();
        rset = stmt.executeQuery("select * from respuestas");
        while (rset.next()) {
            Respuesta r = new Respuesta();
            r.setId(Integer.parseInt(rset.getString(1)));
            r.setTexto(rset.getString(2));
            r.setCorrecta(rset.getInt(3));
            int np = rset.getInt(4);
            r.setPregunta(pre.get(np - 1));
            pre.get(np - 1).addRespuesta(r);
            res.add(r);
        }
        stmt.close();

        jcbCategoriaCB.removeAllItems();
        for (Categoria cate : cat) {
            jcbCategoriaCB.addItem(cate.getNombre());
        }

        switch (opcion) {
            case 0:
                jtfID.setText(String.valueOf(pre.get(n).getId()));
                jtfTexto.setText(pre.get(n).getTexto());
                jtfCategoria.setText(pre.get(n).getCategoria().getNombre());
                jtfRes1.setText(pre.get(n).getRespuestas().get(0).getTexto());
                if (pre.get(n).getRespuestas().get(0).getCorrecta() == 1) {
                    jrbCorrecta1.setSelected(true);
                }
                jtfRes2.setText(pre.get(n).getRespuestas().get(1).getTexto());
                if (pre.get(n).getRespuestas().get(1).getCorrecta() == 1) {
                    jrbCorrecta2.setSelected(true);
                }
                jtfRes3.setText(pre.get(n).getRespuestas().get(2).getTexto());
                if (pre.get(n).getRespuestas().get(2).getCorrecta() == 1) {
                    jrbCorrecta3.setSelected(true);
                }
                jtfRes4.setText(pre.get(n).getRespuestas().get(3).getTexto());
                if (pre.get(n).getRespuestas().get(3).getCorrecta() == 1) {
                    jrbCorrecta4.setSelected(true);
                }
                jtfID.setEditable(false);
                jtfTexto.setEditable(false);
                jtfCategoria.setEditable(false);
                jtfRes1.setEditable(false);
                jtfRes2.setEditable(false);
                jtfRes3.setEditable(false);
                jtfRes4.setEditable(false);
                jrbCorrecta1.setEnabled(false);
                jrbCorrecta2.setEnabled(false);
                jrbCorrecta3.setEnabled(false);
                jrbCorrecta4.setEnabled(false);
                jbLimpiar.setVisible(false);
                jlCategoriaCB.setVisible(false);
                jcbCategoriaCB.setVisible(false);
                break;
            case 1:
                jbAtras.setVisible(false);
                jbAlante.setVisible(false);
                jtfID.setEnabled(false);
                jlCategoria.setVisible(false);
                jtfCategoria.setVisible(false);
                break;
            case 2:
                jtfID.setText(String.valueOf(pre.get(n).getId()));
                jtfTexto.setText(pre.get(n).getTexto());
                jtfCategoria.setText(pre.get(n).getCategoria().getNombre());
                jtfRes1.setText(pre.get(n).getRespuestas().get(0).getTexto());
                if (pre.get(n).getRespuestas().get(0).getCorrecta() == 1) {
                    jrbCorrecta1.setSelected(true);
                }
                jtfRes2.setText(pre.get(n).getRespuestas().get(1).getTexto());
                if (pre.get(n).getRespuestas().get(1).getCorrecta() == 1) {
                    jrbCorrecta2.setSelected(true);
                }
                jtfRes3.setText(pre.get(n).getRespuestas().get(2).getTexto());
                if (pre.get(n).getRespuestas().get(2).getCorrecta() == 1) {
                    jrbCorrecta3.setSelected(true);
                }
                jtfRes4.setText(pre.get(n).getRespuestas().get(3).getTexto());
                if (pre.get(n).getRespuestas().get(3).getCorrecta() == 1) {
                    jrbCorrecta4.setSelected(true);
                }
                jtfID.setEditable(false);
                jtfCategoria.setEditable(false);
                jlCategoriaCB.setVisible(false);
                jcbCategoriaCB.setVisible(false);
                break;
            case 3:
                jtfID.setText(String.valueOf(pre.get(n).getId()));
                jtfTexto.setText(pre.get(n).getTexto());
                jtfCategoria.setText(pre.get(n).getCategoria().getNombre());
                jtfID.setEditable(false);
                jtfTexto.setEditable(false);
                jtfCategoria.setEditable(false);
                jbLimpiar.setVisible(false);
                jlCategoriaCB.setVisible(false);
                jcbCategoriaCB.setVisible(false);
                jpRespuestas.setVisible(false);
                JOptionPane.showMessageDialog(this, "CUIDADO. Al borrar la pregunta también borraras las respuestas.");
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgCorrecta = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfTexto = new javax.swing.JTextField();
        jlCategoria = new javax.swing.JLabel();
        jtfCategoria = new javax.swing.JTextField();
        jbAlante = new javax.swing.JButton();
        jbAtras = new javax.swing.JButton();
        jlCategoriaCB = new javax.swing.JLabel();
        jcbCategoriaCB = new javax.swing.JComboBox();
        jbAceptar = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jpRespuestas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jrbCorrecta1 = new javax.swing.JRadioButton();
        jrbCorrecta2 = new javax.swing.JRadioButton();
        jrbCorrecta3 = new javax.swing.JRadioButton();
        jrbCorrecta4 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jtfRes1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfRes2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfRes3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfRes4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID");

        jLabel2.setText("Texto");

        jlCategoria.setText("Categoría");

        jbAlante.setText(">");
        jbAlante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAlanteActionPerformed(evt);
            }
        });

        jbAtras.setText("<");
        jbAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtrasActionPerformed(evt);
            }
        });

        jlCategoriaCB.setText("Categoría");

        jcbCategoriaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlCategoria)
                            .addComponent(jlCategoriaCB, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbCategoriaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(252, 252, 252))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtfTexto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbAlante))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAlante)
                    .addComponent(jbAtras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCategoria)
                    .addComponent(jtfCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCategoriaCB)
                    .addComponent(jcbCategoriaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jbAceptar.setText("Aceptar");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });

        jbLimpiar.setText("Limpiar");
        jbLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimpiarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Correcta");

        bgCorrecta.add(jrbCorrecta1);
        jrbCorrecta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        bgCorrecta.add(jrbCorrecta2);
        jrbCorrecta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        bgCorrecta.add(jrbCorrecta3);
        jrbCorrecta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        bgCorrecta.add(jrbCorrecta4);
        jrbCorrecta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setText("Res.1");

        jLabel5.setText("Res.2");

        jLabel6.setText("Res.3");

        jLabel7.setText("Res.4");

        jLabel8.setText("Respuestas");

        javax.swing.GroupLayout jpRespuestasLayout = new javax.swing.GroupLayout(jpRespuestas);
        jpRespuestas.setLayout(jpRespuestasLayout);
        jpRespuestasLayout.setHorizontalGroup(
            jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRespuestasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRespuestasLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfRes1))
                    .addGroup(jpRespuestasLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfRes2))
                    .addGroup(jpRespuestasLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfRes3))
                    .addGroup(jpRespuestasLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfRes4))
                    .addGroup(jpRespuestasLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbCorrecta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbCorrecta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbCorrecta3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbCorrecta4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpRespuestasLayout.setVerticalGroup(
            jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRespuestasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jtfRes4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRespuestasLayout.createSequentialGroup()
                        .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpRespuestasLayout.createSequentialGroup()
                                .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpRespuestasLayout.createSequentialGroup()
                                        .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jrbCorrecta1)
                                            .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(jtfRes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jrbCorrecta2))
                                    .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jtfRes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbCorrecta3))
                            .addGroup(jpRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jtfRes3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrbCorrecta4)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpRespuestas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 249, Short.MAX_VALUE)
                        .addComponent(jbCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbAceptar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpRespuestas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAceptar)
                    .addComponent(jbLimpiar)
                    .addComponent(jbCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtrasActionPerformed
        // TODO add your handling code here:
        if (n > 0) {
            n -= 1;
            jtfID.setText(String.valueOf(pre.get(n).getId()));
            jtfTexto.setText(pre.get(n).getTexto());
            jtfCategoria.setText(pre.get(n).getCategoria().getNombre());
            if (jpRespuestas.isVisible() == true) {
                jtfRes1.setText(pre.get(n).getRespuestas().get(0).getTexto());
                if (pre.get(n).getRespuestas().get(0).getCorrecta() == 1) {
                    jrbCorrecta1.setSelected(true);
                }
                jtfRes2.setText(pre.get(n).getRespuestas().get(1).getTexto());
                if (pre.get(n).getRespuestas().get(1).getCorrecta() == 1) {
                    jrbCorrecta2.setSelected(true);
                }
                jtfRes3.setText(pre.get(n).getRespuestas().get(2).getTexto());
                if (pre.get(n).getRespuestas().get(2).getCorrecta() == 1) {
                    jrbCorrecta3.setSelected(true);
                }
                jtfRes4.setText(pre.get(n).getRespuestas().get(3).getTexto());
                if (pre.get(n).getRespuestas().get(3).getCorrecta() == 1) {
                    jrbCorrecta4.setSelected(true);
                }
            }
        }
    }//GEN-LAST:event_jbAtrasActionPerformed

    private void jbAlanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAlanteActionPerformed
        // TODO add your handling code here:
        if (n < pre.size() - 1) {
            n += 1;
            jtfID.setText(String.valueOf(pre.get(n).getId()));
            jtfTexto.setText(pre.get(n).getTexto());
            jtfCategoria.setText(pre.get(n).getCategoria().getNombre());
            if (jpRespuestas.isVisible() == true) {
                jtfRes1.setText(pre.get(n).getRespuestas().get(0).getTexto());
                if (pre.get(n).getRespuestas().get(0).getCorrecta() == 1) {
                    jrbCorrecta1.setSelected(true);
                }
                jtfRes2.setText(pre.get(n).getRespuestas().get(1).getTexto());
                if (pre.get(n).getRespuestas().get(1).getCorrecta() == 1) {
                    jrbCorrecta2.setSelected(true);
                }
                jtfRes3.setText(pre.get(n).getRespuestas().get(2).getTexto());
                if (pre.get(n).getRespuestas().get(2).getCorrecta() == 1) {
                    jrbCorrecta3.setSelected(true);
                }
                jtfRes4.setText(pre.get(n).getRespuestas().get(3).getTexto());
                if (pre.get(n).getRespuestas().get(3).getCorrecta() == 1) {
                    jrbCorrecta4.setSelected(true);
                }
            }
        }
    }//GEN-LAST:event_jbAlanteActionPerformed

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        // TODO add your handling code here:
        switch (opcion) {
            case 0:
                this.dispose();
                break;
            case 1:
                String categoria = String.valueOf(jcbCategoriaCB.getSelectedItem());
                String sql = "{ CALL GEST_PROYECTO_GIFT.INSERT_PREGUNTA(?,?) }";
                CallableStatement cs;
                try {
                    cs = conn.prepareCall(sql);
                    cs.setString(1, categoria);
                    cs.setString(2, jtfTexto.getText());
                    cs.execute();
                    ActualizarPreguntas();
                    System.out.println("INFO: Procedimiento ejecutado");
                    addRes(jrbCorrecta1, jtfRes1);
                    addRes(jrbCorrecta2, jtfRes2);
                    addRes(jrbCorrecta3, jtfRes3);
                    addRes(jrbCorrecta4, jtfRes4);
                    conn.close();
                    JOptionPane.showMessageDialog(this, "La pregunta y sus respuestas han sido añadidas correctamente.");
                } catch (SQLException ex) {
                    System.out.println("ERROR: No se ha podido ejecutar la consulta");
                    JOptionPane.showMessageDialog(this, "No se a podido añadir correctamente la pregunta o respuesta/s.");
                    Logger.getLogger(GCategorias.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
                break;
            case 2:
                sql = "{ CALL GEST_PROYECTO_GIFT.UPDATE_PREGUNTA(?,?) }";
                try {
                    cs = conn.prepareCall(sql);
                    cs.setInt(1, pre.get(n).getId());
                    cs.setString(2, jtfTexto.getText());
                    cs.execute();
                    updateRes(jrbCorrecta1, jtfRes1, 0);
                    updateRes(jrbCorrecta2, jtfRes2, 1);
                    updateRes(jrbCorrecta3, jtfRes3, 2);
                    updateRes(jrbCorrecta4, jtfRes4, 3);
                    conn.close();
                    System.out.println("INFO: Procedimiento ejecutado");
                    JOptionPane.showMessageDialog(this, "La pregunta y sus respuestas han sido modificadas correctamente.");
                } catch (SQLException ex) {
                    System.out.println("ERROR: No se ha podido ejecutar la consulta");
                    JOptionPane.showMessageDialog(this, "No se a podido modificar correcatmente la pregunta o respuesta/s.");
                    Logger.getLogger(GCategorias.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
                break;
            case 3:
                sql = "{ CALL GEST_PROYECTO_GIFT.DELETE_PREGUNTA(?) }";
                try {
                    cs = conn.prepareCall(sql);
                    cs.setInt(1, pre.get(n).getId());
                    cs.execute();
                    conn.close();
                    System.out.println("INFO: Procedimiento ejecutado");
                    JOptionPane.showMessageDialog(this, "La pregunta y sus respuestas han sido eliminadas correctamente.");
                } catch (SQLException ex) {
                    System.out.println("ERROR: No se ha podido ejecutar la consulta");
                    JOptionPane.showMessageDialog(this, "No se a podido eliminar correctamente la pregunta o las respuestas.");
                    Logger.getLogger(GCategorias.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.dispose();
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jbAceptarActionPerformed

    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        // TODO add your handling code here:
        jtfID.setText("");
        jtfTexto.setText("");
        jtfCategoria.setText("");
        jtfRes1.setText("");
        jtfRes2.setText("");
        jtfRes3.setText("");
        jtfRes4.setText("");
    }//GEN-LAST:event_jbLimpiarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jbCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgCorrecta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbAlante;
    private javax.swing.JButton jbAtras;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JComboBox jcbCategoriaCB;
    private javax.swing.JLabel jlCategoria;
    private javax.swing.JLabel jlCategoriaCB;
    private javax.swing.JPanel jpRespuestas;
    private javax.swing.JRadioButton jrbCorrecta1;
    private javax.swing.JRadioButton jrbCorrecta2;
    private javax.swing.JRadioButton jrbCorrecta3;
    private javax.swing.JRadioButton jrbCorrecta4;
    private javax.swing.JTextField jtfCategoria;
    private javax.swing.JTextField jtfID;
    private javax.swing.JTextField jtfRes1;
    private javax.swing.JTextField jtfRes2;
    private javax.swing.JTextField jtfRes3;
    private javax.swing.JTextField jtfRes4;
    private javax.swing.JTextField jtfTexto;
    // End of variables declaration//GEN-END:variables
}
