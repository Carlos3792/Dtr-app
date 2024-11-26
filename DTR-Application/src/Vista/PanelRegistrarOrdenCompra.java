/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Modelo.Producto;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlos
 */
public class PanelRegistrarOrdenCompra extends javax.swing.JPanel implements ActionListener{
    //Creacion del modelo.
    DefaultTableModel modelo1 = new DefaultTableModel();
    //Object []filas = new Object[3];
    ArrayList<Producto> productosagregados = new ArrayList<Producto>();
    //
    ArrayList<PanelProductoAgregar> paneles = new ArrayList<PanelProductoAgregar>(); 
    /**
     * Creates new form PanelRegistrarOrdenCompra
     */
    public PanelRegistrarOrdenCompra() {
        initComponents();
        initComponents();
        //Libro:
        //modelo1.addColumn("Id");
        modelo1.addColumn("Nombre del producto");
        modelo1.addColumn("Precio");
        modelo1.addColumn("Cantidad");    
        jTable1.setModel(modelo1);
        
        jRadioButton1.addActionListener(this);
        jRadioButton2.addActionListener(this);
        jButton3.addActionListener(this);
        
        addMouseListenerToButton(jButton2);
        addMouseListenerToButton(jButton3);
        addMouseListenerToButton(jButton4);
        
        Controlador controlador = new Controlador(this);
        controlador.cargarProductoOrdenCompra();
        controlador.filtrarProductosOrdenCompra();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(21, 65, 83));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        jLabel1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de productos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(581, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel2.setText("Tipo de orden de compra:");

        jTextField1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 19, 46));

        jLabel3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel3.setText("Descripción del producto:");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel4.setText("Precio del producto:");

        jTextField3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(0, 19, 46));

        jLabel7.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel7.setText("Cantidad del producto:");

        jTextField6.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(0, 19, 46));

        jLabel5.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel5.setText("Categoría del producto:");

        jTextField4.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(0, 19, 46));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionBackground(new java.awt.Color(21, 65, 83));
        jScrollPane2.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel6.setText("Productos:");

        jRadioButton1.setText("Registro común");

        jRadioButton2.setText("Pedido a domicilio");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jLabel8.setText("Nombre del producto:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(10, 0, 1, 10));
        jScrollPane3.setViewportView(jPanel2);

        jTextField2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 19, 46));

        jButton4.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(21, 65, 83));
        jButton4.setText("🔍");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(21, 65, 83), 3));
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(21, 65, 83));
        jButton2.setText("Registrar");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(21, 65, 83), 3));
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(21, 65, 83));
        jButton3.setText("Quitar el ultimo");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(21, 65, 83), 3));
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Quitar el ultimo")){
            Controlador controlador = new Controlador(this);
            controlador.eliminarProductoOrdenCompra();
        }
        if(e.getActionCommand().equals("Paciente")){
            //Activar los botones de paciiente:
            /*jTextField6.setEnabled(true);
            jTextField7.setEnabled(true);
            //Desactivar los botones de empleado.
            jTextField8.setEnabled(false);
            jTextField9.setEnabled(false);
            jTextField10.setEnabled(false);
            jTextField11.setEnabled(false);
            jTextField12.setEnabled(false);
            jTextField13.setEnabled(false);
            jTextField14.setEnabled(false);
            jComboBox3.setEnabled(false);
            jComboBox1.setEnabled(false);
            jComboBox2.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton3.setSelected(true);
            jRadioButton4.setEnabled(false);*/
        }
        
        if(e.getActionCommand().equals("Empleado")){
            /*//Activar los botones de paciiente:
            jTextField6.setEnabled(false);
            jTextField7.setEnabled(false);
            //Desactivar los botones de empleado.
            jTextField8.setEnabled(true);
            jTextField9.setEnabled(true);
            jTextField10.setEnabled(true);
            jTextField11.setEnabled(true);
            jTextField12.setEnabled(true);
            jTextField13.setEnabled(true);
            //jTextField13.setEnabled(true);
            //jTextField14.setEnabled(true);
            jComboBox1.setEnabled(true);
            jComboBox2.setEnabled(true);
            jRadioButton3.setEnabled(true);
            jRadioButton4.setEnabled(true);*/
            
        }
    }
    
    // Método para agregar un MouseListener a los botones
    private void addMouseListenerToButton(JButton button) {
        // Crear un borde original para el botón
        Border originalBorder = button.getBorder();

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar el borde cuando el mouse entra
                button.setBorder(BorderFactory.createLineBorder(new Color(236, 74, 28), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaurar el borde original cuando el mouse sale
                button.setBorder(originalBorder);
            }
        });
    }
    
    public JPanel getjPanel2() {
        return jPanel2;
    }

    public void setjPanel2(JPanel jPanel2) {
        this.jPanel2 = jPanel2;
    }

    public JTextField getjTextField2() {
        return jTextField2;
    }

    public void setjTextField2(JTextField jTextField2) {
        this.jTextField2 = jTextField2;
    }

    public DefaultTableModel getModelo1() {
        return modelo1;
    }

    public void setModelo1(DefaultTableModel modelo1) {
        this.modelo1 = modelo1;
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public void setjTable1(JTable jTable1) {
        this.jTable1 = jTable1;
    }

    public ArrayList<Producto> getProductosagregados() {
        return productosagregados;
    }

    public void setProductosagregados(ArrayList<Producto> productosagregados) {
        this.productosagregados = productosagregados;
    }


    public ArrayList<PanelProductoAgregar> getPaneles() {
        return paneles;
    }

    public void setPaneles(ArrayList<PanelProductoAgregar> paneles) {
        this.paneles = paneles;
    }
    
    
    
}
