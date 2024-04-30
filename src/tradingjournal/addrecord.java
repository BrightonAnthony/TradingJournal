/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tradingjournal;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author princy
 */
public class addrecord extends javax.swing.JFrame {

    /**
     * Creates new form add_record
     */
    public addrecord() {
        initComponents();
        strategylist();
        currentdate();
        displayname();
    }
    
public void displayname(){
    
    Statement st = null; 
    ResultSet rs = null;
    
    long l = System.currentTimeMillis();
    Date todaysDate = new Date(1);
    
  //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   // String dob = sdf.format(tdob.getDate());
    
    try{
            Connection con = DBConnection.getConnection();
            st = (Statement) con.createStatement();
            
            rs = st.executeQuery("select name from profile");
            rs.next();
            savename.setText(rs.getString(1));
            
            rs = st.executeQuery("select photo from profile");
            rs.next();
            byte[] img3 = rs.getBytes(1);
            format = new ImageIcon(new ImageIcon(img3).getImage().getScaledInstance(jLabel7.getWidth(),jLabel7.getHeight(),Image.SCALE_SMOOTH));
            jLabel7.setIcon(format);
      
            }catch (Exception e){
            System.out.println(e);
            }
    
}       
    
    
    public void addtrade(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String trade_date = sdf.format(tdate.getDate());
        String script = tscript.getText();
        String strategy = tstrategy.getSelectedItem().toString();
        String side = tside.getSelectedItem().toString();
        String action = taction.getSelectedItem().toString();
        double buy_price = Double.parseDouble(tbuy.getText());
        double sell_price = Double.parseDouble(tsell.getText());
        double quantity = Double.parseDouble(tquantity.getText());
        double ProfitLoss = Double.parseDouble(tpro.getText());
        
    
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into add_trade(trade_date,script,strategy,side,action,buy_price,sell_price,quantity,ProfitLoss)values(?,?,?,?,?,?,?,?,?)");
           
            
            
            pst.setString(1,trade_date);
            pst.setString(2,script);
            pst.setString(3,strategy);
            pst.setString(4,side);
            pst.setString(5,action);
            pst.setDouble(6,buy_price);
            pst.setDouble(7,sell_price);
            pst.setDouble(8,quantity);
            pst.setDouble(9,ProfitLoss);
            
       
            int updatedRowCount = pst.executeUpdate();
       
             if(updatedRowCount > 0){
                JOptionPane.showMessageDialog(this,"Record Inserted Successfully");
                homepage home = new homepage();
                home.setVisible(true);
                this.dispose();
                
               
                
            }
            else{
           JOptionPane.showMessageDialog(this,"Record Insertion Failed");
            }
       
            
       
        }catch (Exception e){
            System.out.println(e);
        }
    }

 public void currentdate(){
    
    Statement st = null; 
    ResultSet rs = null;
    
    long l = System.currentTimeMillis();
    Date todaysDate = new Date(l);
    
    try{
            Connection con = DBConnection.getConnection();
            st = (Statement) con.createStatement();
          
            rs = st.executeQuery("select dob from profile");
            rs.next();
            tdate.setDate(todaysDate);
            
            }catch (Exception e){
            System.out.println(e);
    }
} 
    

    
public boolean checkduplicatescript(){
        String script = tscript.getText();
        boolean isexist = false;
        
        try{
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from add_trade where script = ?");
        
        pst.setString(1,script);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()){
            isexist = true;
        }
        else{
            isexist = false;
        }
        
        }catch (Exception e){
            System.out.println(e);
        }
    return isexist;
}
    
    
public void strategylist(){
        
        try{
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from add_strategy");
        ResultSet rs = pst.executeQuery();
    
        while(rs.next()){
        
            tstrategy.addItem(rs.getString("strategy"));
        }
    }catch (Exception e){
            System.out.println(e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jCTextField2 = new app.bolivia.swing.JCTextField();
        jSplitPane1 = new javax.swing.JSplitPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tscript = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tbuy = new app.bolivia.swing.JCTextField();
        tside = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        tsell = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tstrategy = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        taction = new javax.swing.JComboBox<>();
        tquantity = new app.bolivia.swing.JCTextField();
        tdate = new com.toedter.calendar.JDateChooser();
        rSButtonIconI7 = new rojerusan.RSButtonIconI();
        jLabel11 = new javax.swing.JLabel();
        tpro = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        savename = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jCTextField2.setBackground(new java.awt.Color(0, 0, 102));
        jCTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        jCTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jCTextField2.setPhColor(new java.awt.Color(255, 255, 255));
        jCTextField2.setPlaceholder("Enter Quantity");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 102)), javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255))));

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Trade");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setBackground(new java.awt.Color(0, 0, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Date :");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setBackground(new java.awt.Color(0, 0, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Script :");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tscript.setBackground(new java.awt.Color(0, 0, 102));
        tscript.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tscript.setForeground(new java.awt.Color(255, 255, 255));
        tscript.setCaretColor(new java.awt.Color(255, 255, 255));
        tscript.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tscript.setPhColor(new java.awt.Color(255, 255, 255));
        tscript.setPlaceholder("Enter Stock Name");

        jLabel4.setBackground(new java.awt.Color(0, 0, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity :");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel5.setBackground(new java.awt.Color(0, 0, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Buy Price :");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tbuy.setBackground(new java.awt.Color(0, 0, 102));
        tbuy.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tbuy.setForeground(new java.awt.Color(255, 255, 255));
        tbuy.setCaretColor(new java.awt.Color(255, 255, 255));
        tbuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tbuy.setPhColor(new java.awt.Color(255, 255, 255));
        tbuy.setPlaceholder("Enter Price");
        tbuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbuyActionPerformed(evt);
            }
        });
        tbuy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbuyKeyPressed(evt);
            }
        });

        tside.setBackground(new java.awt.Color(0, 0, 102));
        tside.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tside.setForeground(new java.awt.Color(255, 255, 255));
        tside.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Long", "Short" }));
        tside.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));

        jLabel8.setBackground(new java.awt.Color(0, 0, 102));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sell Price :");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tsell.setBackground(new java.awt.Color(0, 0, 102));
        tsell.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tsell.setForeground(new java.awt.Color(255, 255, 255));
        tsell.setCaretColor(new java.awt.Color(255, 255, 255));
        tsell.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tsell.setPhColor(new java.awt.Color(255, 255, 255));
        tsell.setPlaceholder("Enter Price");
        tsell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsellActionPerformed(evt);
            }
        });
        tsell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tsellKeyPressed(evt);
            }
        });

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(102, 204, 0));
        rSMaterialButtonCircle1.setBorder(null);
        rSMaterialButtonCircle1.setText("Add");
        rSMaterialButtonCircle1.setFont(new java.awt.Font("Roboto Medium", 1, 36)); // NOI18N
        rSMaterialButtonCircle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonCircle1MouseClicked(evt);
            }
        });
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 102));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("X");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 0, 102));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Strategy :");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tstrategy.setBackground(new java.awt.Color(0, 0, 102));
        tstrategy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tstrategy.setForeground(new java.awt.Color(255, 255, 255));
        tstrategy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "        -----" }));
        tstrategy.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));

        jLabel12.setBackground(new java.awt.Color(0, 0, 102));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Side :");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        taction.setBackground(new java.awt.Color(0, 0, 102));
        taction.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        taction.setForeground(new java.awt.Color(255, 255, 255));
        taction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buy", "Sell" }));
        taction.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));

        tquantity.setBackground(new java.awt.Color(0, 0, 102));
        tquantity.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tquantity.setForeground(new java.awt.Color(255, 255, 255));
        tquantity.setCaretColor(new java.awt.Color(255, 255, 255));
        tquantity.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tquantity.setPhColor(new java.awt.Color(255, 255, 255));
        tquantity.setPlaceholder("Enter Quantity");
        tquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tquantityKeyPressed(evt);
            }
        });

        tdate.setBackground(new java.awt.Color(0, 0, 102));
        tdate.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tdate.setDateFormatString("dd-MM-yyyy");
        tdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        rSButtonIconI7.setBackground(new java.awt.Color(204, 0, 51));
        rSButtonIconI7.setBorder(null);
        rSButtonIconI7.setIcon(new javax.swing.ImageIcon("C:\\Users\\princy\\Desktop\\Brighton\\pic\\next-button.png")); // NOI18N
        rSButtonIconI7.setText(" Back ");
        rSButtonIconI7.setColorHover(new java.awt.Color(204, 0, 51));
        rSButtonIconI7.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        rSButtonIconI7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonIconI7MouseClicked(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 0, 102));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Profit/Loss :");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tpro.setBackground(new java.awt.Color(0, 0, 102));
        tpro.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tpro.setForeground(new java.awt.Color(255, 255, 255));
        tpro.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tpro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tproMouseClicked(evt);
            }
        });

        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        savename.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        savename.setForeground(new java.awt.Color(255, 255, 255));
        savename.setText("     Name");
        savename.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savename.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savenameMouseClicked(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 0, 102));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Action :");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(335, 335, 335)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 183, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(taction, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tstrategy, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tside, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tdate, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                    .addComponent(tscript, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addGap(260, 260, 260)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tpro, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tquantity, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(tsell, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tbuy, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(178, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(507, 507, 507)
                .addComponent(rSMaterialButtonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(tdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tstrategy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(tscript, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tside, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(taction, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbuy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tsell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tpro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(rSMaterialButtonCircle1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbuyActionPerformed

    private void tsellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tsellActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
     
    if(checkduplicatescript()==false){    
        
        if(tscript.getText().length()==0){
        JOptionPane.showMessageDialog(this,"Please enter script");
     }
     else if(tbuy.getText().length()==0){
        JOptionPane.showMessageDialog(this,"Please enter buy price");
     }
     else if(tsell.getText().length()==0){
        JOptionPane.showMessageDialog(this,"Please enter sell price");
     }
     else if(tquantity.getText().length()==0){
        JOptionPane.showMessageDialog(this,"Please enter quantity");
     }
    
     else{
         double buy_price = Double.parseDouble(tbuy.getText());
        double sell_price = Double.parseDouble(tsell.getText());
        double quantity = Double.parseDouble(tquantity.getText());
        double ProfitLoss = (sell_price - buy_price)*quantity;
        tpro.setText(String.valueOf(ProfitLoss));
        addtrade();     
    }
    }
    else{
        JOptionPane.showMessageDialog(this,"Please enter valid script name");    
        }
        
        
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
    int yes_or_no = JOptionPane.showConfirmDialog(this,"Do You Want to Exit","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(yes_or_no == JOptionPane.YES_OPTION){
        System.exit(0); 
        }
        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void rSButtonIconI7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonIconI7MouseClicked
        homepage home = new homepage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIconI7MouseClicked

    private void rSMaterialButtonCircle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle1MouseClicked

    private void tproMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tproMouseClicked
        double buy_price = Double.parseDouble(tbuy.getText());
        double sell_price = Double.parseDouble(tsell.getText());
        double quantity = Double.parseDouble(tquantity.getText());
        double ProfitLoss = (sell_price - buy_price)*quantity;
        tpro.setText(String.valueOf(ProfitLoss));
    }//GEN-LAST:event_tproMouseClicked

    private void savenameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savenameMouseClicked
        editprofile page = new editprofile();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_savenameMouseClicked

    private void tbuyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbuyKeyPressed
       String mobiles = tbuy.getText(); 
    int length = mobiles.length();
    
    char c = evt.getKeyChar();
    
    if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
    if(length < 20){
    tbuy.setEditable(true);
    }
    else{
      tbuy.setEditable(false);
    }
    }
    else{
     if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE  ||evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
     tbuy.setEditable(true);
     }
     else{
      tbuy.setEditable(false);
    }
    }// TODO add your handling code here:
    }//GEN-LAST:event_tbuyKeyPressed

    private void tsellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tsellKeyPressed
    String mobiles = tsell.getText(); 
    int length = mobiles.length();
    
    char c = evt.getKeyChar();
    
    if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
    if(length < 20){
    tsell.setEditable(true);
    }
    else{
      tsell.setEditable(false);
    }
    }
    else{
     if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE  ||evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
     tsell.setEditable(true);
     }
     else{
      tsell.setEditable(false);
    }
    }
    }//GEN-LAST:event_tsellKeyPressed

    private void tquantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tquantityKeyPressed
        String mobiles = tquantity.getText(); 
    int length = mobiles.length();
    
    char c = evt.getKeyChar();
    
    if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
    if(length < 20){
    tquantity.setEditable(true);
    }
    else{
      tquantity.setEditable(false);
    }
    }
    else{
     if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE  ||evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
     tquantity.setEditable(true);
     }
     else{
      tquantity.setEditable(false);
    }
    }
    }//GEN-LAST:event_tquantityKeyPressed

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
            java.util.logging.Logger.getLogger(addrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addrecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private app.bolivia.swing.JCTextField jCTextField2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private rojerusan.RSButtonIconI rSButtonIconI7;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private javax.swing.JLabel savename;
    private javax.swing.JComboBox<String> taction;
    private app.bolivia.swing.JCTextField tbuy;
    private com.toedter.calendar.JDateChooser tdate;
    private javax.swing.JButton tpro;
    private app.bolivia.swing.JCTextField tquantity;
    private app.bolivia.swing.JCTextField tscript;
    private app.bolivia.swing.JCTextField tsell;
    private javax.swing.JComboBox<String> tside;
    private javax.swing.JComboBox<String> tstrategy;
    // End of variables declaration//GEN-END:variables
private ImageIcon format = null;
}
