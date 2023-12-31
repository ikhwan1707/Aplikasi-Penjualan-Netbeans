/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasipenjualan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author utama digitall2
 */
public class FrmPetugas extends javax.swing.JFrame {
    
    private Object tabel;
    
    /**
     * Creates new form FrmPetugas
     */
    public FrmPetugas() {
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
        
        table.setModel(tableModel);
        Tabel(table, new int[]{90,300,340,260,100});
        setDefaultTable();
        SetEnabledFalse();
    }
    
     public void SetEnabledFalse(){
       IDPetugas.setEnabled(false);
        NamaPetugas.setEnabled(false);
        Alamat.setEnabled(false);
        Email.setEnabled(false);
        Telpon.setEnabled(false);
        Save.setEnabled(false);
        Update.setEnabled(false);
        Delete.setEnabled(false);
    }
    
    public void SetEnabledTrue(){
       IDPetugas.setEnabled(true);
        NamaPetugas.setEnabled(true);
        Alamat.setEnabled(true);
        Email.setEnabled(true);
        Telpon.setEnabled(true);
        Save.setEnabled(true);
        Update.setEnabled(true);
        Delete.setEnabled(true);
    }
    
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
    
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
        new String [] {"ID Petugas","Nama Petugas","Alamat Petugas","Alamat E-Mail","Telpon"}
        ){
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    String data[]=new String[5];
    private void setDefaultTable() {
        try{
            
            String sql = "SELECT * FROM tblpetugas";

            Connection c = connection.getKoneksi(); 
            Statement s = c.createStatement();       
            ResultSet res = s.executeQuery(sql);  

            while(res.next()){
            // lakukan penelusuran baris 
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                tableModel.addRow(data);
            }
           
        }catch(SQLException e){ 
            System.out.println("Terjadi Error");
        }
    }
    
    int row = 0;
    public void Tampil(){
        row = table.getSelectedRow();
        IDPetugas.setText(tableModel.getValueAt(row, 0).toString());
        NamaPetugas.setText(tableModel.getValueAt(row, 1).toString());
        Alamat.setText(tableModel.getValueAt(row, 2).toString());
        Email.setText(tableModel.getValueAt(row, 3).toString());
        Telpon.setText(tableModel.getValueAt(row, 4).toString());
        Save.setEnabled(false);
        Update.setEnabled(true);
        Delete.setEnabled(true);
        SetEditOn();
    }
    
    public void BersihData(){
        IDPetugas.setText("");
        NamaPetugas.setText("");
        Alamat.setText("");
        Email.setText("");
        Telpon.setText("");
    }
    
    public void SetEditOff(){
        IDPetugas.setEnabled(false);
        NamaPetugas.setEnabled(false);
        Alamat.setEnabled(false);
        Email.setEnabled(false);
        Telpon.setEnabled(false);
    }
    public void SetEditOn(){
        IDPetugas.setEnabled(true);
        NamaPetugas.setEnabled(true);
        Alamat.setEnabled(true);
        Email.setEnabled(true);
        Telpon.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
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
        IDPetugas = new javax.swing.JTextField();
        NamaPetugas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Alamat = new javax.swing.JTextArea();
        Email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Telpon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(3, 91, 113));

        jPanel2.setBackground(new java.awt.Color(0, 162, 185));

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
                .addGap(41, 41, 41)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNew)
                    .addComponent(Save)
                    .addComponent(Update)
                    .addComponent(Delete)
                    .addComponent(Cancel)
                    .addComponent(Close))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Petugas ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("E-mail");

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Petugas");

        Alamat.setColumns(20);
        Alamat.setRows(5);
        jScrollPane2.setViewportView(Alamat);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Alamat Petugas");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Maksimal 15 digit");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Telepon");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(IDPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(21, 21, 21)
                        .addComponent(NamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(72, 72, 72)
                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(62, 62, 62)
                        .addComponent(Telpon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(IDPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(NamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(Telpon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Petugas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel1))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
        // TODO add your handling code here:
        BersihData();
        IDPetugas.requestFocus();
        Save.setEnabled(true);
        Update.setEnabled(false);
        Delete.setEnabled(false);
        SetEditOn();
    }//GEN-LAST:event_AddNewActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
       String ID=IDPetugas.getText();
        String NM=NamaPetugas.getText();
        String AM=Alamat.getText();
        String EM=Email.getText();
        String TP=Telpon.getText();
        
        if ((ID.isEmpty()) | (NM.isEmpty()) |(AM.isEmpty()) |(EM.isEmpty())|(TP.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            IDPetugas.requestFocus();
        }else {
            try {
            Connection c = connection.getKoneksi();
            Statement stt = c.createStatement();
            String SQL = "insert into tblpetugas values('"+IDPetugas.getText()+"',"+
            "'"+NamaPetugas.getText()+"',"+
            "'"+Alamat.getText()+"',"+
            "'"+Email.getText()+"',"+
            "'"+Telpon.getText()+"')";
            stt.executeUpdate(SQL);
            data[0] = IDPetugas.getText();
            data[1] = NamaPetugas.getText();
            data[2] = Alamat.getText();
            data[3] = Email.getText();
            data[4] = Telpon.getText();
            tableModel.insertRow(0, data);
            stt.close();
            c.close();
            
            JOptionPane.showMessageDialog(null,"Penyimpanan Data Berhasil");
            
            BersihData();
            Save.setEnabled(false);
            SetEditOff();
            
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
        } 
    }//GEN-LAST:event_SaveActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        String ID=IDPetugas.getText();
        String NM=NamaPetugas.getText();
        String AM=Alamat.getText();
        String EM=Email.getText();
        String TP=Telpon.getText();
        
        if ((ID.isEmpty()) | (NM.isEmpty()) |(AM.isEmpty()) |(EM.isEmpty())|(TP.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            IDPetugas.requestFocus();
        }else {
            try {
                
                Connection kon = connection.getKoneksi();
                Statement stt = kon.createStatement();
                String SQL = "Update tblpetugas set namapetugas='"+NamaPetugas.getText()+"',"+
                "alamat='"+Alamat.getText()+"',"+
                "email='"+Email.getText()+"',"+
                "telpon='"+Telpon.getText()+"'"+
                "Where idpetugas='"+IDPetugas.getText()+"'";
                stt.executeUpdate(SQL);
                
                data[0] = IDPetugas.getText();
                data[1] = NamaPetugas.getText();
                data[2] = Alamat.getText();
                data[3] = Email.getText();
                data[4] = Telpon.getText();
                tableModel.removeRow(row);
                tableModel.insertRow(row,data);
                
                stt.close();
                kon.close();
                
                JOptionPane.showMessageDialog(null,"Perubahan Data Berhasil");
                
                BersihData();
                Save.setEnabled(false);
                SetEditOff();
                
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(this,ex.getMessage());
            }
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        String ID=IDPetugas.getText();
        String NM=NamaPetugas.getText();
        String AM=Alamat.getText();
        String EM=Email.getText();
        String TP=Telpon.getText();
        
        if ((ID.isEmpty()) | (NM.isEmpty()) |(AM.isEmpty()) |(EM.isEmpty())|(TP.isEmpty())) {
        JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
        IDPetugas.requestFocus();
        }else {
            try {
                
                Connection kon = connection.getKoneksi();
                Statement stt = kon.createStatement();
                String SQL = "Delete From tblpetugas Where idpetugas='"+IDPetugas.getText().toString()+"'";
                stt.executeUpdate(SQL);
                
                data[0] = IDPetugas.getText();
                data[1] = NamaPetugas.getText();
                data[2] = Alamat.getText();
                data[3] = Email.getText();
                data[4] = Telpon.getText();
                tableModel.removeRow(row);
                
                stt.close();
                kon.close();
                
                JOptionPane.showMessageDialog(null,"Hapus Data Berhasil");
                
                BersihData();
                Save.setEnabled(false);
                SetEditOff();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
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
       if(JOptionPane.showConfirmDialog(null,"This application will be close \n if you press button OK","Information", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
        this.dispose();
        
    }//GEN-LAST:event_CloseActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            Tampil();
        }
    }//GEN-LAST:event_tableMouseClicked

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
            java.util.logging.Logger.getLogger(FrmPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddNew;
    private javax.swing.JTextArea Alamat;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Close;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField IDPetugas;
    private javax.swing.JTextField NamaPetugas;
    private javax.swing.JButton Save;
    private javax.swing.JTextField Telpon;
    private javax.swing.JButton Update;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
