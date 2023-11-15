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
public class FrmBarang extends javax.swing.JFrame {

    /**
     * Creates new form FrmBarang
     */
    public FrmBarang() {
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
        (screenSize.height = screenSize.height) / 6);
        
        Tabel(Table, new int[]{90,300,340,260,100});
        loadData();
       
        SetEnabledFalse();
        TampilComboJenis();
    }
    
    
    
    public  void TampilComboJenis(){
        try {
            
            String sql = "SELECT * FROM tbljenis";

            Connection c = connection.getKoneksi(); 
            Statement s = c.createStatement();       
            ResultSet r = s.executeQuery(sql); 
            
            while(r.next()){
                KodeJenis.addItem(r.getString("kodejenis"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void SetEnabledFalse(){
        KodeBarang.setEnabled(false);
        NamaBarang.setEnabled(false);
        KodeJenis.setEnabled(false);
        HargaNet.setEnabled(false);
        HargaJual.setEnabled(false);
        Stok.setEnabled(false);
        Save.setEnabled(false);
        Update.setEnabled(false);
        Delete.setEnabled(false);
    }
    
    public void SetEnabledTrue(){
        KodeBarang.setEnabled(true);
        NamaBarang.setEnabled(true);
        KodeJenis.setEnabled(true);
        HargaNet.setEnabled(true);
        HargaJual.setEnabled(true);
        Stok.setEnabled(true);
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
        
        tableModel.addColumn("Kode Barang");
        tableModel.addColumn("Nama Barang");
        tableModel.addColumn("Kode Jenis");
        tableModel.addColumn("Harga NET");
        tableModel.addColumn("Harga Jual");
        tableModel.addColumn("Stok");
        
        try{
            
            String sql = "SELECT * FROM tblbarang";

            Connection c = connection.getKoneksi(); 
            Statement s = c.createStatement();       
            ResultSet r = s.executeQuery(sql);  

            while(r.next()){
            // lakukan penelusuran baris 
                tableModel.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getString(6)
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
        new String [] {"Kode Barang","Nama Barang","Kode Jenis","Harga NET","Harga Jual","Stok"}
        ){
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    int row = 0;
    public void Tampil(){
        row = Table.getSelectedRow();
        KodeBarang.setText(tableModel.getValueAt(row, 0).toString());
        NamaBarang.setText(tableModel.getValueAt(row, 1).toString());
        KodeJenis.setSelectedItem(tableModel.getValueAt(row, 2).toString());
        HargaNet.setText(tableModel.getValueAt(row, 3).toString());
        HargaJual.setText(tableModel.getValueAt(row, 4).toString());
        Stok.setText(tableModel.getValueAt(row, 5).toString());
        Save.setEnabled(false);
        Update.setEnabled(true);
        Delete.setEnabled(true);
        SetEditOn();
    }
    
    public void BersihData(){
        KodeBarang.setText("");
        NamaBarang.setText("");
        KodeJenis.setSelectedIndex(0);
        Jenis.setText("");
        HargaNet.setText("");
        HargaJual.setText("");
        Stok.setText("0");
    }
    public void SetEditOff(){
        KodeBarang.setEnabled(false);
        NamaBarang.setEnabled(false);
        KodeJenis.setEnabled(false);
        HargaNet.setEnabled(false);
        HargaJual.setEnabled(false);
        Stok.setEnabled(false);
    }
    public void SetEditOn(){
        KodeBarang.setEnabled(true);
        NamaBarang.setEnabled(true);
        KodeJenis.setEnabled(true);
        HargaNet.setEnabled(true);
        HargaJual.setEnabled(true);
        Stok.setEnabled(true);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        KodeBarang = new javax.swing.JTextField();
        NamaBarang = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        KodeJenis = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        Jenis = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        HargaNet = new javax.swing.JTextField();
        HargaJual = new javax.swing.JTextField();
        Stok = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        AddNew = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(3, 91, 113));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 162, 185));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Barang");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel2.add(KodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 90, -1));
        jPanel2.add(NamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 410, -1));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 570, 210));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Jenis Barang");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        KodeJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih kode jenis barang" }));
        KodeJenis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KodeJenisItemStateChanged(evt);
            }
        });
        jPanel2.add(KodeJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 410, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jenis Barang");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        Jenis.setEnabled(false);
        jPanel2.add(Jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 410, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Harga Net");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Harga Jual");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Rp");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));
        jPanel2.add(HargaNet, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 120, -1));
        jPanel2.add(HargaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 120, -1));
        jPanel2.add(Stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 80, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Stok");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Rp");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddNew.setText("Add New");
        AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewActionPerformed(evt);
            }
        });
        jPanel3.add(AddNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        jPanel3.add(Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        jPanel3.add(Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        jPanel3.add(Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        jPanel3.add(Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        jPanel3.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 670, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 670, 650));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Data Barang");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
        // TODO add your handling code here:
        BersihData();
        KodeBarang.requestFocus();
        Save.setEnabled(true);
        Update.setEnabled(false);
        Delete.setEnabled(false);
        SetEditOn();
    }//GEN-LAST:event_AddNewActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
        String KB=KodeBarang.getText();
        String NB=NamaBarang.getText();
        String KJ=KodeJenis.getSelectedItem().toString();
        String HN=HargaNet.getText();
        String HJ=HargaJual.getText();
        String ST=Stok.getText();
        
       if ((KB.isEmpty()) | (NB.isEmpty()) |(KJ.isEmpty()) |(HN.isEmpty())|(HJ.isEmpty())|(ST.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            KodeBarang.requestFocus();
        } else {
            try {
                Connection c = connection.getKoneksi();
                String sql = "INSERT INTO tblbarang VALUES (?, ?, ?, ?, ?,?)";
                PreparedStatement p = c.prepareStatement(sql);

                p.setString(1, KB);
                p.setString(2, NB);
                p.setString(3, KJ);
                p.setString(4, HN);
                p.setString(5, HJ);
                 p.setString(6, ST);
                 
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

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        String KJ=KodeBarang.getText();
        String J=NamaBarang.getText();

        if ((KJ.isEmpty()) | (J.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            KodeBarang.requestFocus();
        } else {
            try {
                Connection c = connection.getKoneksi();
                String sql = "UPDATE tbljenis SET jenis = ? WHERE kodejenis = ?";
                PreparedStatement p = c.prepareStatement(sql);

                p.setString(1, J);
                p.setString(2, KJ);

                p.executeUpdate();
                p.close();

                JOptionPane.showMessageDialog(null,"Penyimpanan Data Berhasil");
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
        String KJ=KodeBarang.getText();
        String J=NamaBarang.getText();

        if ((KJ.isEmpty()) | (J.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            KodeBarang.requestFocus();
        } else{
            try {
                Connection c = connection.getKoneksi();
                String sql = "DELETE FROM tblbarang WHERE kodebarang = ?";
                PreparedStatement p = c.prepareStatement(sql);

                p.setString(1, KJ);

                p.executeUpdate();
                p.close();

                JOptionPane.showMessageDialog(null,"Penyimpanan Data Berhasil");
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

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        BersihData();
        SetEnabledFalse();
        //SetEditOff();
    }//GEN-LAST:event_CancelActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null,"This application will be close \n if you press button OK",
            "Information",JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
    this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            Tampil();
        }
    }//GEN-LAST:event_TableMouseClicked

    private void KodeJenisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KodeJenisItemStateChanged
        // TODO add your handling code here:
         try {
            
            String sql = "SELECT * FROM tbljenis WHERE kodejenis='"+ KodeJenis.getSelectedItem().toString()+"'";

            Connection c = connection.getKoneksi(); 
            Statement s = c.createStatement();       
            ResultSet r = s.executeQuery(sql); 

            r.absolute(1);
            Jenis.setText(r.getString("jenis"));
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_KodeJenisItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNew;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Close;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField HargaJual;
    private javax.swing.JTextField HargaNet;
    private javax.swing.JTextField Jenis;
    private javax.swing.JTextField KodeBarang;
    private javax.swing.JComboBox<String> KodeJenis;
    private javax.swing.JTextField NamaBarang;
    private javax.swing.JButton Save;
    private javax.swing.JTextField Stok;
    private javax.swing.JTable Table;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
