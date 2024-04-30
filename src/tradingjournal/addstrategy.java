/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tradingjournal;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author princy
 */
public class addstrategy extends javax.swing.JFrame {

    /**
     * Creates new form addrecord
     */
    public addstrategy() {
        initComponents();
        checkduplicatestrategy();
        tradedetailstable();
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
            byte[] img4 = rs.getBytes(1);
            format = new ImageIcon(new ImageIcon(img4).getImage().getScaledInstance(jLabel7.getWidth(),jLabel7.getHeight(),Image.SCALE_SMOOTH));
            jLabel7.setIcon(format);
      
            }catch (Exception e){
            System.out.println(e);
            }
    
}   
    
    public void tradedetailstable(){
        try{
            
            Connection con = DBConnection.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select * from add_strategy");
            
            while(rs.next()){
                
                String strategy = rs.getString("strategy");
             //   String wins = rs.getString("wins");
             //   String loss = rs.getString("loss");
                
            
                Object[] obj = {strategy/*,wins,loss*/};
                DefaultTableModel model = (DefaultTableModel) rSTableMetro1.getModel();
                model.addRow(obj);
                
                
            }
        
        }catch (Exception e){
        System.out.println(e);
        }
}
     
public void addtrade(){
        
        String strategy = tstrategy.getText();
        
        
    
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into add_strategy(strategy)values(?)");
           
            
            
            pst.setString(1,strategy);
            
            
       
            int updatedRowCount = pst.executeUpdate();
       
             if(updatedRowCount > 0){
                JOptionPane.showMessageDialog(this,"Strategy added Successfully");
                
                
               
                
            }
            else{
           JOptionPane.showMessageDialog(this,"Strategy Insertion Failed");
            }
       
            
       
        }catch (Exception e){
            System.out.println(e);
        }
    }    

public boolean updatetable(){

    boolean isupdated = false;
    
    String strategy = tstrategy.getText();
  //  String wins = twin.getText();
  //  String loss = tloss.getText();
    
    try{
            
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("update add_strategy set strategy = ?");
        pst.setString(1, strategy);
     //   pst.setString(2, wins);
     //   pst.setString(3, loss);
        int rowCount = pst.executeUpdate();
        if(rowCount > 0){
           isupdated = true;
        }
        else{
           isupdated = false;
        }
        
        }catch (Exception e){
        System.out.println(e);
    }
    return isupdated;

}      
    
public boolean deletetable(){

    boolean isdeleted = false;
    
    String strategy = tstrategy.getText();
    
    try{
            
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("delete from add_strategy where strategy = ?");
        pst.setString(1, strategy);
        
        int rowCount = pst.executeUpdate();
        if(rowCount > 0){
           isdeleted = true;
        }
        else{
           isdeleted = false;
        }
        
            }catch (Exception e){
            System.out.println(e);
    }
    return isdeleted;

}    
public void cleartable(){
     DefaultTableModel model = (DefaultTableModel) rSTableMetro1.getModel();
                model.setRowCount(0);
 }    
public boolean checkduplicatestrategy(){
        String strategy = tstrategy.getText();
        boolean isexist = false;
        
        try{
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("select * from add_strategy where strategy = ?");
        
        pst.setString(1,strategy);
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
    

public void wintrade(){
    
    Statement st = null; 
    ResultSet rs = null;
    
    
   
    String strategy = tstrategy.getText();
    
    try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select count(ProfitLoss) as win from add_trade where ProfitLoss > 0 group by strategy having strategy = ?");
            pst.setString(1,strategy);
            rs = pst.executeQuery();
            
           if(rs.next()){
            twin.setText(rs.getString(1));
            }
            else{
            twin.setText("0");        
            }          
          
            }catch (Exception e){
            System.out.println(e);
    }
}



public void losstrade(){
    
    Statement st = null;  
    ResultSet rs = null;
    String strategy = tstrategy.getText();
    
  //  long l = System.currentTimeMillis();
   // Date todaysDate = new Date(l);
    
    try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select count(ProfitLoss) as win from add_trade where ProfitLoss < 0 group by strategy having strategy = ?");
            pst.setString(1,strategy);
            rs = pst.executeQuery();
         
         //  rs.next();
           if(rs.next()){
            tloss.setText(rs.getString(1));
            }
            else{
            tloss.setText("0");        
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
        rSEstiloTablaHeader1 = new rojeru_san.complementos.RSEstiloTablaHeader();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        jScrollPane1 = new javax.swing.JScrollPane();
        rSTableMetro1 = new rojeru_san.complementos.RSTableMetro();
        rSButtonIconI7 = new rojerusan.RSButtonIconI();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tstrategy = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        twin = new javax.swing.JButton();
        tloss = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        savename = new javax.swing.JLabel();

        jCTextField2.setBackground(new java.awt.Color(0, 0, 102));
        jCTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        jCTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jCTextField2.setPhColor(new java.awt.Color(255, 255, 255));
        jCTextField2.setPlaceholder("Enter Quantity");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 102)), javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255))));
        jPanel1.setPreferredSize(new java.awt.Dimension(810, 430));

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("List of Strategy");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 0, 51));
        rSMaterialButtonCircle3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        rSMaterialButtonCircle3.setText("Delete");
        rSMaterialButtonCircle3.setFont(new java.awt.Font("Roboto Medium", 1, 36)); // NOI18N
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(102, 204, 0));
        rSMaterialButtonCircle2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        rSMaterialButtonCircle2.setText("Add");
        rSMaterialButtonCircle2.setFont(new java.awt.Font("Roboto Medium", 1, 36)); // NOI18N
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });

        rSTableMetro1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Strategy Name"
            }
        ));
        rSTableMetro1.setColorBackgoundHead(new java.awt.Color(51, 0, 204));
        rSTableMetro1.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setColorBordeHead(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setColorFilasBackgound1(new java.awt.Color(102, 153, 255));
        rSTableMetro1.setColorFilasBackgound2(new java.awt.Color(102, 153, 255));
        rSTableMetro1.setColorFilasForeground1(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rSTableMetro1.setGrosorBordeHead(2);
        rSTableMetro1.setRowHeight(30);
        rSTableMetro1.setSelectionBackground(new java.awt.Color(204, 255, 255));
        rSTableMetro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTableMetro1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rSTableMetro1);

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

        jLabel5.setBackground(new java.awt.Color(0, 0, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Strategy Name");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel6.setBackground(new java.awt.Color(0, 0, 102));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Wins");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tstrategy.setBackground(new java.awt.Color(0, 0, 102));
        tstrategy.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tstrategy.setForeground(new java.awt.Color(255, 255, 255));
        tstrategy.setCaretColor(new java.awt.Color(255, 255, 255));
        tstrategy.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tstrategy.setPhColor(new java.awt.Color(255, 255, 255));
        tstrategy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tstrategyActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 0, 102));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Loss");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        twin.setBackground(new java.awt.Color(0, 0, 102));
        twin.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        twin.setForeground(new java.awt.Color(255, 255, 255));
        twin.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        twin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twinMouseClicked(evt);
            }
        });
        twin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twinActionPerformed(evt);
            }
        });

        tloss.setBackground(new java.awt.Color(0, 0, 102));
        tloss.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        tloss.setForeground(new java.awt.Color(255, 255, 255));
        tloss.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        tloss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlossMouseClicked(evt);
            }
        });

        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        savename.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        savename.setForeground(new java.awt.Color(255, 255, 255));
        savename.setText("    Name");
        savename.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savename.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                savenameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(301, 301, 301)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSMaterialButtonCircle3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)
                        .addComponent(rSMaterialButtonCircle2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tstrategy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(twin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tloss, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(212, 212, 212)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tstrategy, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tloss, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(twin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rSMaterialButtonCircle2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonCircle3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
       if(checkduplicatestrategy()==false){
            addtrade();
            cleartable();
            tradedetailstable();
        }
        else{
        JOptionPane.showMessageDialog(this,"Strategy already exist");    
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        int yes_or_no = JOptionPane.showConfirmDialog(this,"Do You Want To Delete","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(yes_or_no == JOptionPane.YES_OPTION){
        if(deletetable() == true){
          JOptionPane.showMessageDialog(this,"strategy deleted successfully");
          cleartable();
          tradedetailstable();
          
       }
       else{
         JOptionPane.showMessageDialog(this,"strategy deletion failed");
       } 
    } 
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

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

    private void tstrategyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tstrategyActionPerformed
        
    }//GEN-LAST:event_tstrategyActionPerformed

    private void rSTableMetro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTableMetro1MouseClicked
        
        int row = rSTableMetro1.getSelectedRow();
        TableModel model = rSTableMetro1.getModel();
        tstrategy.setText(model.getValueAt(row,0).toString());
        cleartable();
        tradedetailstable();
        
        wintrade();
        losstrade();
        
    }//GEN-LAST:event_rSTableMetro1MouseClicked

    private void twinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_twinMouseClicked
        wintrade();
    }//GEN-LAST:event_twinMouseClicked

    private void tlossMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlossMouseClicked
             losstrade();
    }//GEN-LAST:event_tlossMouseClicked

    private void twinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_twinActionPerformed

    private void savenameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_savenameMouseClicked
        editprofile page = new editprofile();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_savenameMouseClicked

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
            java.util.logging.Logger.getLogger(addstrategy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addstrategy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addstrategy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addstrategy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addstrategy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private app.bolivia.swing.JCTextField jCTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSButtonIconI rSButtonIconI7;
    private rojeru_san.complementos.RSEstiloTablaHeader rSEstiloTablaHeader1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro rSTableMetro1;
    private javax.swing.JLabel savename;
    private javax.swing.JButton tloss;
    private app.bolivia.swing.JCTextField tstrategy;
    private javax.swing.JButton twin;
    // End of variables declaration//GEN-END:variables
private ImageIcon format = null;
}
