/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasipenjualan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author utama digitall2
 */
public class FrmJenis extends javax.swing.JFrame {

    
    /**
     * Creates new form FrmJenis
     */
    public FrmJenis() {
        initComponents();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=this.getSize();
        if(frameSize.height > screenSize.height){
            frameSize.height=screenSize.height;
        }
        
        if(frameSize.width > screenSize.width){
            frameSize.width=screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2,
        (screenSize.height = screenSize.height) / 4);
        
       
        loadData();
       
        SetEnabledFalse();
    }
    
    public void SetEnabledFalse(){
        txtkode.setEnabled(false);
        txtjenis.setEnabled(false);
        Save.setEnabled(false);
        Update.setEnabled(false);
        Delete.setEnabled(false);
    }
    
    public void SetEnabledTrue(){
        txtkode.setEnabled(true);
        txtjenis.setEnabled(true);
        Save.setEnabled(true);
        Update.setEnabled(true);
        Delete.setEnabled(true);
    }
    
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
    
    private void loadData(){
        //membuat model
        tableModel = new DefaultTableModel();
        
        // menghapus seluruh data 
        tableModel.getDataVector().removeAllElements();
        // memberi tahu bahwa data telah kosong 
        tableModel.fireTableDataChanged();
        
        Table.setModel(tableModel);
        tableModel.addColumn("Kode Jenis");
        tableModel.addColumn("Jenis Barang");
        
        try{
            
            String sql = "SELECT * FROM tbljenis";

            Connection c = connection.getKoneksi(); 
            Statement s = c.createStatement();       
            ResultSet r = s.executeQuery(sql);  

            while(r.next()){
            // lakukan penelusuran baris 
                tableModel.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2)
                });
            }
            Table.setModel(tableModel);
        }catch(SQLException e){ 
            System.out.println("Terjadi Error");
        }
    }
    
    private void Tabel(javax.swing.JTable tb, int lebar[] ) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int kolom=tb.getColumnCount();
        for(int i=0;i < kolom;i++) {
            javax.swing.table.TableColumn tbc=tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(17);
        }
    }
    
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        return new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String [] {"Kode Jenis","Jenis Barang"}
        ){
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    String data[]=new String[2];
    private void setDefaultTable() {
        String stat ="";
        
         try{
            
            String sql = "SELECT * FROM tbljenis";

            Connection c = connection.getKoneksi(); 
            Statement s = c.createStatement();       
            ResultSet r = s.executeQuery(sql); 

            while(r.next()){
            // lakukan penelusuran baris 
                tableModel.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2)
                });
            }
            Table.setModel(tableModel);
        }catch(SQLException e){ 
            System.out.println("Terjadi Error"+e.getMessage());
        }
    }
    
    int row = 0;
    public void Tampil(){
        row = Table.getSelectedRow();
        txtkode.setText(tableModel.getValueAt(row, 0).toString());
        txtjenis.setText(tableModel.getValueAt(row, 1).toString());
        Save.setEnabled(false);
        Update.setEnabled(true);
        Delete.setEnabled(true);
        SetEditOn();
    }
    
    public void BersihData(){
        txtkode.setText("");
        txtjenis.setText("");
    }
    public void SetEditOff(){
        txtkode.setEnabled(false);
        txtjenis.setEnabled(false);
    }
    public void SetEditOn(){
        txtkode.setEnabled(true);
        txtjenis.setEnabled(true);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        AddNew = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        txtjenis = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(3, 91, 113));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 162, 185));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));

        AddNew.setText("Add New");
        AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewActionPerformed(evt);
            }
        });

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Close)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNew)
                    .addComponent(Save)
                    .addComponent(Update)
                    .addComponent(Delete)
                    .addComponent(Cancel)
                    .addComponent(Close))
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 730, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Jenis");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jenis Barang");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel2.add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 210, -1));
        jPanel2.add(txtjenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 210, -1));

        Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 530, 210));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 720, 410));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Jenis Barang");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        // TODO add your handling code here:
       if(JOptionPane.showConfirmDialog(null,"This application will be close \n if you press button OK",
        "Information",JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
        this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
        // TODO add your handling code here:
        BersihData();
        txtkode.requestFocus();
        Save.setEnabled(true);
        Update.setEnabled(false);
        Delete.setEnabled(false);
        SetEditOn();
        //SetEnabledTrue();
        //AddNew.setEnabled(false);
    }//GEN-LAST:event_AddNewActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        BersihData();
        SetEnabledFalse();
        //SetEditOff();
    }//GEN-LAST:event_CancelActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
        String KJ=txtkode.getText();
        String J=txtjenis.getText();
        
        if ((KJ.isEmpty()) | (J.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            txtkode.requestFocus();
        } else {
            try {
                Connection c = connection.getKoneksi();
                String sql = "INSERT INTO tbljenis VALUES (?, ?)";
                PreparedStatement p = c.prepareStatement(sql); 
                
                p.setString(1, KJ);
                p.setString(2, J);

                p.executeUpdate(); 
                p.close();
                
                
                
                JOptionPane.showMessageDialog(null,"Penyimpanan Data Berhasil");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage());
            } finally{
                loadData();
                BersihData();
                Save.setEnabled(false);
                SetEditOff();
            }
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            Tampil();
        }
    }//GEN-LAST:event_TableMouseClicked

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        String KJ=txtkode.getText();
        String J=txtjenis.getText();
        
        if ((KJ.isEmpty()) | (J.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            txtkode.requestFocus();
        } else {
            try {
                Connection c = connection.getKoneksi();
                String sql = "UPDATE tbljenis SET jenis = ? WHERE kodejenis = ?";
                PreparedStatement p = c.prepareStatement(sql); 
                
                p.setString(1, J);
                p.setString(2, KJ);

                p.executeUpdate(); 
                p.close();
                
                
                JOptionPane.showMessageDialog(null,"Perubahan Data Berhasil");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }finally{
                loadData();
                BersihData();
                Save.setEnabled(false);
                SetEditOff();
            }
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        String KJ=txtkode.getText();
        String J=txtjenis.getText();
        
        if ((KJ.isEmpty()) | (J.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            txtkode.requestFocus();
        } else{
            try {
                Connection c = connection.getKoneksi();
                String sql = "DELETE FROM tbljenis WHERE kodejenis = ?";
                PreparedStatement p = c.prepareStatement(sql); 
                
                p.setString(1, KJ);

                p.executeUpdate(); 
                p.close();
                
                
                JOptionPane.showMessageDialog(null,"Hapus Data Berhasil");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }finally{
                loadData();
                BersihData();
                Save.setEnabled(false);
                SetEditOff();
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

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
            java.util.logging.Logger.getLogger(FrmJenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJenis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJenis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNew;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Close;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Save;
    private javax.swing.JTable Table;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtjenis;
    private javax.swing.JTextField txtkode;
    // End of variables declaration//GEN-END:variables
}
