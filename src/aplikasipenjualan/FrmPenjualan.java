/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasipenjualan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author utama digitall2
 */
public class FrmPenjualan extends javax.swing.JFrame {
    //private JTextField SubTotal, Total, Bayar;
    /**
     * Creates new form FrmPenjualan
     */
    public FrmPenjualan() {
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
        (screenSize.height = screenSize.height) / 10);
        
        table.setModel(tableModel);
        Tabel(table, new int[]{90,300,90,60,60,90});
        
        
        
        setDefaultTable();
        TanggalOtomatis();
        SetEditOff();
        //TampilComboBarang();
        TampilComboPetugas();
        tampilfaktur();
    }
    
    
    public void totalBiaya(){
        int jumlahBaris = table.getRowCount();
        double totalBiaya = 0;
        int jumlahBarang;
        double hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(table.getValueAt(i, 4).toString());
            hargaBarang = Double.parseDouble(table.getValueAt(i, 2).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        Total.setText(String.format("%.2f", totalBiaya));
        txTampil.setText("Rp "+ String.format("%.2f", totalBiaya));
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
        new String [] {"Kode Barang","Nama Barang","Harga Jual","Stok","Jumlah","Sub Total"}
        ){
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    String data[]=new String[6];
    private void setDefaultTable() {
        try{
            
            String sql = "SELECT tblbarang.kodebarang,tblbarang.namabarang,tblbarang.hargajual,tblbarang.stok,tbldetailpenjualan.jumlah,"
                    + "tbldetailpenjualan.subtotal,tblpenjualan.nofaktur "
                    + "FROM tblbarang,tbldetailpenjualan,tblpenjualan WHERE tblbarang.kodebarang=tbldetailpenjualan.kodebarang "
                    + "AND tblpenjualan.nofaktur=tbldetailpenjualan.nofaktur AND tbldetailpenjualan.nofaktur='"+NoFaktur.getText()+"'";

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
                data[5] = res.getString(6);
                tableModel.addRow(data);
            }
            
        }catch(SQLException e){ 
            System.out.println("Terjadi Error");
        }
    }
    
    private void TampilGridDetail(){
        try{
            
            String sql = "SELECT tblbarang.kodebarang,tblbarang.namabarang,tblbarang.hargajual,tblbarang.stok,tbldetailpenjualan.jumlah,"
                    + "tbldetailpenjualan.subtotal,tblpenjualan.nofaktur "
                    + "FROM tblbarang,tbldetailpenjualan,tblpenjualan WHERE tblbarang.kodebarang=tbldetailpenjualan.kodebarang "
                    + "AND tblpenjualan.nofaktur=tbldetailpenjualan.nofaktur AND tbldetailpenjualan.nofaktur='"+NoFaktur.getText()+"'";

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
                data[5] = res.getString(6);
                tableModel.addRow(data);
            }
            
        }catch(SQLException e){ 
           System.err.println(e.getMessage());
        }
    }
    public void tampilfaktur() {
 //  Date tanggal = new Date();
 //  String kode;
 //  NoFaktur.setText(""+ (String.format("%1$tY%1$tm%1$td",tanggal)));

        Date sk = new Date();

        SimpleDateFormat format1=new SimpleDateFormat("yyMMdd");
        String time = format1.format(sk);
        
        
        try{
            String sql = "select right(nofaktur,1) as kd from tblpenjualan order by kd desc";
            Connection c = connection.getKoneksi(); 
            Statement s = c.createStatement();       
            ResultSet rs = s.executeQuery(sql);
                if (rs.next()){

                    int kode = Integer.parseInt(rs.getString("kd"))+1;

                    NoFaktur.setText(time+Integer.toString(kode));

                }else{

                    int kode = 1;

                    NoFaktur.setText(time+Integer.toString(kode));

                }
            

        }catch (SQLException | NumberFormatException e){

            JOptionPane.showMessageDialog(null, e);

        }     
    }
    
    public void BersihData(){
        tableModel.setRowCount(0);
        //NoFaktur.setText("");
        NamaPetugas.setText("");
        IDPetugas.setSelectedIndex(0);
        KDBarang.setText("");
        NamaBarang.setText("");
        HargaJual.setText("");
        Jumlah.setText("");
        Stok.setText("");
        Bayar.setText("0");
        Sisa.setText("0");
        Total.setText("0");
        SubTotal.setText("0");
        txTampil.setText("Rp.0");
        
    }
    
    public void BersihDetail(){
        KDBarang.setText("");
        NamaBarang.setText("");
        HargaJual.setText("");
        Stok.setText("");
        Jumlah.setText("");
        SubTotal.setText("0");
    }
    
    public void SetEditOff(){
        NoFaktur.setEnabled(false); 
        TglPenjualan.setEnabled(false); 
        IDPetugas.setEnabled(false); 
        KDBarang.setEnabled(false); 
        Jumlah.setEnabled(false); 
        Hitung.setEnabled(false); 
        CariData.setEnabled(false); 
        AddItem.setEnabled(false); 
        btncari.setEnabled(false);
//        NoFaktur.setEnabled(false);
//        TglPenjualan.setEnabled(false);
//        IDPetugas.setEnabled(false);
//        KodeBarang.setEnabled(false);
//        NamaBarang.setEnabled(false);
//        HargaJual.setEnabled(false);
//        Stok.setEnabled(false);
//        SubTotal.setEnabled(false);
//        Jumlah.setEnabled(false);
//        Hitung.setEnabled(false);
//        Bayar.setEnabled(false);
//        Sisa.setEnabled(false);
//        Total.setEnabled(false);
//        CariData.setEnabled(false);
//        AddItem.setEnabled(false);
//        NamaPetugas.setEnabled(false);
//        SaveTransaction.setEnabled(false);
    }
    
    public void SetEditOn(){
        NoFaktur.setEnabled(true); 
        TglPenjualan.setEnabled(true); 
        IDPetugas.setEnabled(true); 
        KDBarang.setEnabled(true); 
        Jumlah.setEnabled(true); 
        SaveTransaction.setEnabled(true); 
        CariData.setEnabled(true); 
        Hitung.setEnabled(true); 
        AddItem.setEnabled(true); 
        btncari.setEnabled(true);
//        NoFaktur.setEnabled(true);
//        TglPenjualan.setEnabled(true);
//        IDPetugas.setEnabled(true);
//        KodeBarang.setEnabled(true);
//        NamaBarang.setEnabled(true);
//        HargaJual.setEnabled(true);
//        Stok.setEnabled(true);
//        SubTotal.setEnabled(true);
//        Jumlah.setEnabled(true);
//        CariData.setEnabled(true);
//        Hitung.setEnabled(true);
//        AddItem.setEnabled(true);
//        Bayar.setEnabled(true);
//        Sisa.setEnabled(true);
//        Total.setEnabled(true);
        Bayar.setText("0");
        Sisa.setText("0");
        Total.setText("0");
//        SubTotal.setText("0");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        TglPenjualan = new javax.swing.JTextField();
        CariData = new javax.swing.JButton();
        NoFaktur = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Stok = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        NamaBarang = new javax.swing.JTextField();
        SubTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Jumlah = new javax.swing.JTextField();
        Hitung = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        HargaJual = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        AddItem = new javax.swing.JButton();
        btncari = new javax.swing.JButton();
        KDBarang = new javax.swing.JTextField();
        txTampil = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        IDPetugas = new javax.swing.JComboBox<>();
        NamaPetugas = new javax.swing.JTextField();
        AddNew = new javax.swing.JButton();
        SaveTransaction = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        Bayar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Sisa = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(3, 91, 113));

        jPanel2.setBackground(new java.awt.Color(0, 162, 185));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal Penjualan");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 730, 210));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Petugas");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));
        jPanel2.add(TglPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 170, -1));

        CariData.setText("Cari Data");
        CariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariDataActionPerformed(evt);
            }
        });
        jPanel2.add(CariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));
        jPanel2.add(NoFaktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 100, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Rp");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 620, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Barang", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18), new java.awt.Color(0, 162, 185))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 162, 185));
        jLabel5.setText("Kode Barang");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 162, 185));
        jLabel8.setText("Nama Barang");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 162, 185));
        jLabel9.setText("Harga Jual");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 162, 185));
        jLabel10.setText("Stok");

        SubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        SubTotal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SubTotalCaretUpdate(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 162, 185));
        jLabel11.setText("Jumlah");

        Hitung.setText("Hitung");
        Hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HitungActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 162, 185));
        jLabel12.setText("Sub Total");

        HargaJual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 162, 185));
        jLabel13.setText("Rp");

        AddItem.setText("Add Item");
        AddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemActionPerformed(evt);
            }
        });

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        txTampil.setBackground(new java.awt.Color(255, 153, 153));
        txTampil.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txTampil.setText("Rp. 0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addGap(12, 12, 12)
                .addComponent(KDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncari)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(Hitung)))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13))
                    .addComponent(HargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txTampil, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(KDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel9))
                            .addComponent(btncari))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(Hitung))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel13))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(HargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddItem)
                    .addComponent(txTampil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 730, 240));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("No faktur");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        IDPetugas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silahkan Pilih" }));
        IDPetugas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IDPetugasItemStateChanged(evt);
            }
        });
        jPanel2.add(IDPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 190, -1));
        jPanel2.add(NamaPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 300, -1));

        AddNew.setText("Add New");
        AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewActionPerformed(evt);
            }
        });
        jPanel2.add(AddNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, -1, -1));

        SaveTransaction.setText("Save Transaction");
        SaveTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveTransactionActionPerformed(evt);
            }
        });
        jPanel2.add(SaveTransaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 160, -1));

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        jPanel2.add(Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 590, -1, -1));

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        jPanel2.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 590, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID Petugas");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        Bayar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                BayarCaretUpdate(evt);
            }
        });
        Bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarActionPerformed(evt);
            }
        });
        jPanel2.add(Bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 620, 180, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Sisa");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 650, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Bayar");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 620, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Rp");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 650, -1, -1));

        Sisa.setEnabled(false);
        jPanel2.add(Sisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 650, 180, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 590, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Rp");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 590, -1, -1));

        Total.setEnabled(false);
        jPanel2.add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 590, 180, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Penjualan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tableMouseClicked

    private void IDPetugasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IDPetugasItemStateChanged
        // TODO add your handling code here:
        try {
            Connection kon = connection.getKoneksi();
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM tblpetugas where idpetugas='"+ IDPetugas.getSelectedItem().toString()+"'";
            ResultSet res = stt.executeQuery(SQL);
            res.absolute(1);
            NamaPetugas.setText(res.getString("namapetugas"));
            } catch (SQLException ex) {
        }
    }//GEN-LAST:event_IDPetugasItemStateChanged

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
        // TODO add your handling code here:
        SetEditOn(); 
        BersihData();
        NoFaktur.requestFocus();  
        tampilfaktur();
    }//GEN-LAST:event_AddNewActionPerformed

    private void HitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HitungActionPerformed
        // TODO add your handling code here:
        double a;
        int b;
        double c;   
        a = Double.parseDouble(HargaJual.getText());
        b = Integer.parseInt(Jumlah.getText());
        c = a * b; 
        SubTotal.setText(String.format("%.2f", c));
    }//GEN-LAST:event_HitungActionPerformed

    private void SubTotalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SubTotalCaretUpdate
        // TODO add your handling code here:
//        double d, e,f;   
//        d = Double.parseDouble(SubTotal.getText());  
//        e = Double.parseDouble(Total.getText());  
//        f = e+d;   
//        Total.setText(String.valueOf(f)); 
        
    }//GEN-LAST:event_SubTotalCaretUpdate

    private void BayarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_BayarCaretUpdate
        // TODO add your handling code here:
//        int a; 
//        double b; 
//        double c;
//        
//        a = Integer.parseInt(Bayar.getText());  
//        b = Double.parseDouble(Total.getText()); 
//        c = a - b;   
//        Sisa.setText(String.valueOf(c));
    }//GEN-LAST:event_BayarCaretUpdate

    private void AddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemActionPerformed
        // TODO add your handling code here:
        String NM=NoFaktur.getText();
        String KB=KDBarang.getText();
        String JM=Jumlah.getText();
        
        if ((NM.isEmpty()) | (KB.isEmpty()) |(JM.isEmpty())) {
        JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
        KDBarang.requestFocus();
        }else {
            try{
                Connection kon = connection.getKoneksi();
                Statement stt = kon.createStatement();
                String SQL = "insert into tbldetailpenjualan values('"+NoFaktur.getText()+"',"+
                "'"+KDBarang.getText()+"',"+
                "'"+Jumlah.getText()+"',"+
                "'"+SubTotal.getText()+"')";
                stt.executeUpdate(SQL);
                
                Connection kon1 = connection.getKoneksi();
                Statement stt1 = kon.createStatement();
                String SQL1 = "Update tblbarang Set stok=stok - '"+Jumlah.getText()+"'" +
                "Where kodebarang='"+KDBarang.getText()+"'";
                stt1.executeUpdate(SQL1);
                
                data[0] = KDBarang.getText();
                data[1] = NamaBarang.getText();
                data[2] = HargaJual.getText();
                data[3] = Stok.getText();
                data[4] = Jumlah.getText();
                data[5] = SubTotal.getText();
                tableModel.insertRow(0, data);
                
                totalBiaya();
                
               
                stt.close();
                //kon.close();
                KDBarang.requestFocus();
                //AddItem.setEnabled(false);
                SaveTransaction.setEnabled(true);
                BersihDetail();
                KDBarang.requestFocus();
            } catch(Exception ex){
                 System.out.println("Terjadi Error"+ex.getMessage());
            }
        }
    }//GEN-LAST:event_AddItemActionPerformed

    private void SaveTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveTransactionActionPerformed
        // TODO add your handling code here:
        String NM=NoFaktur.getText();
        
        if ((NM.isEmpty())) {
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            NoFaktur.requestFocus();
        }else {
            try {
           
                Connection kon = connection.getKoneksi();
                Statement stt = kon.createStatement();
                String SQL = "insert into tblpenjualan values('"+NoFaktur.getText()+"',"+
                "'"+TglPenjualan.getText()+"',"+
                "'"+IDPetugas.getSelectedItem()+"',"+
                "'"+Bayar.getText()+"',"+
                "'"+Sisa.getText()+"',"+
                "'"+Total.getText()+"')";
                stt.executeUpdate(SQL);
                
                stt.close();
                
                BersihData();
                SetEditOff();
                 JOptionPane.showMessageDialog(null,"Transaksi Berhasil");
            SaveTransaction.setEnabled(false);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_SaveTransactionActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null,"This application will be close \n if you press button OK","Information", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION)
        this.dispose();

    }//GEN-LAST:event_CloseActionPerformed

    private void CariDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariDataActionPerformed
        // TODO add your handling code here:
        try {
            Connection kon = connection.getKoneksi();
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM tblpenjualan where nofaktur='"+NoFaktur.getText().toString()+"'";
            ResultSet res = stt.executeQuery(SQL);
            res.absolute(1);
            TampilGridDetail();
            TglPenjualan.setText(res.getString("tglpenjualan"));
            IDPetugas.setSelectedItem(res.getString("idpetugas"));
            Bayar.setText(res.getString("bayar"));
            Sisa.setText(res.getString("sisa"));
            Total.setText(res.getString("total"));
            SaveTransaction.setEnabled(false);
            NoFaktur.setEnabled(false);
            CariData.setEnabled(false);
            } catch (SQLException ex) {
                 System.out.println("Terjadi Error"+ex.getMessage());
        }
    }//GEN-LAST:event_CariDataActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        BersihData();
        SetEditOff();
    }//GEN-LAST:event_CancelActionPerformed

    private void BayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarActionPerformed
        // TODO add your handling code here:
        double total, bayar, kembalian;
        
        total = Double.valueOf(Total.getText());
        bayar = Double.valueOf(Bayar.getText());
        
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            Sisa.setText(String.format("%.2f", kembalian));
        }
    }//GEN-LAST:event_BayarActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        ListBarang a = new ListBarang();
        a.setVisible(true);
    }//GEN-LAST:event_btncariActionPerformed
    
    public void TampilComboBarang(){
//        try {
//            Connection kon = connection.getKoneksi();
//            Statement stt = kon.createStatement();
//            String SQL = "SELECT * FROM tblbarang";
//            ResultSet res = stt.executeQuery(SQL);
//            while(res.next()){
//                KodeBarang.addItem(res.getString("kodebarang"));
//            }
//        } catch (SQLException ex) {
//             System.out.println("Terjadi Error"+ex.getMessage());
//        }
    }
    
    public void TampilComboPetugas(){
        try {
            Connection kon = connection.getKoneksi();
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM tblpetugas";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                IDPetugas.addItem(res.getString("idpetugas"));
            }
        } catch (SQLException ex) {
             System.out.println("Terjadi Error"+ex.getMessage());
        }
    }
    
    public void TanggalOtomatis(){
        Date tanggal = new Date();
        TglPenjualan.setText(""+ (String.format("%1$td:%1$tb:%1$tY",tanggal)));
    }
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
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItem;
    private javax.swing.JButton AddNew;
    private javax.swing.JTextField Bayar;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton CariData;
    private javax.swing.JButton Close;
    public static javax.swing.JTextField HargaJual;
    private javax.swing.JButton Hitung;
    private javax.swing.JComboBox<String> IDPetugas;
    private javax.swing.JTextField Jumlah;
    public static javax.swing.JTextField KDBarang;
    public static javax.swing.JTextField NamaBarang;
    private javax.swing.JTextField NamaPetugas;
    private javax.swing.JTextField NoFaktur;
    private javax.swing.JButton SaveTransaction;
    private javax.swing.JTextField Sisa;
    public static javax.swing.JTextField Stok;
    private javax.swing.JTextField SubTotal;
    private javax.swing.JTextField TglPenjualan;
    private javax.swing.JTextField Total;
    private javax.swing.JButton btncari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTable table;
    private javax.swing.JTextField txTampil;
    // End of variables declaration//GEN-END:variables
}
