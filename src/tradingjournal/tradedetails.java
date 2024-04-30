/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tradingjournal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author princy
 */
public class tradedetails extends javax.swing.JFrame {
    
    String trade_date,script,strategy,action;
    int buy_price,sell_price,quantity,ProfitLoss;
    DefaultTableModel model;
          

    /**
     * Creates new form addrecord
     */
    public tradedetails() {
        initComponents();
     //   showPieChart();
        cleartable();
        tradedetailstable();
        updatetrade();
        currentdate();
        displayname();
      //  view("","");
      //  DefaultTableModel model = (DefaultTableModel)rSTableMetro1.getModel();
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
            byte[] img6 = rs.getBytes(1);
            format = new ImageIcon(new ImageIcon(img6).getImage().getScaledInstance(jLabel7.getWidth(),jLabel7.getHeight(),Image.SCALE_SMOOTH));
            jLabel7.setIcon(format);
      
            }catch (Exception e){
            System.out.println(e);
            }
    
}  
    
    
    public void tradedetailstable(){
        try{
            
            Connection con = DBConnection.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select * from add_trade where hide='1'");
            
            
            while(rs.next()){
                
                
                trade_date = rs.getString("trade_date");
                script = rs.getString("script");
                strategy = rs.getString("strategy");
                action = rs.getString("action");
                buy_price = rs.getInt("buy_price");
                sell_price = rs.getInt("sell_price");
                quantity = rs.getInt("quantity");
                ProfitLoss = rs.getInt("ProfitLoss");
                
            
                Object[] obj = {trade_date,script,strategy,action,buy_price,sell_price,quantity,ProfitLoss};
                model = (DefaultTableModel) rSTableMetro1.getModel();
                model.addRow(obj);
                
              
            }
        
        }catch (Exception e){
        System.out.println(e);
        }
}

public void view(String d1,String d2){
    cleartable();
    Connection con = DBConnection.getConnection();
    PreparedStatement pst ;    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 //   String trade_date1 = sdf.format(date1.getDate());
   //  String trade_date2 = sdf.format(date2.getDate()); 

    try{
    
    if(d1.equals("")  || d2.equals("")){
       pst = con.prepareStatement("select * from add_trade where hide='1'");
      
    }
    else{
        
      pst = con.prepareStatement("select * from add_trade where trade_date between ? and ? and hide='1'");
      pst.setString(1, d1);
      pst.setString(2, d2);
    }
    
    ResultSet rs = pst.executeQuery();  
        while(rs.next()){
                
                
                trade_date = rs.getString("trade_date");
                script = rs.getString("script");
                strategy = rs.getString("strategy");
                action = rs.getString("action");
                buy_price = rs.getInt("buy_price");
                sell_price = rs.getInt("sell_price");
                quantity = rs.getInt("quantity");
                ProfitLoss = rs.getInt("ProfitLoss");
                
            
                Object[] obj = {trade_date,script,strategy,action,buy_price,sell_price,quantity,ProfitLoss};
                model = (DefaultTableModel) rSTableMetro1.getModel();
                model.addRow(obj);
                
              
            }
      
    }catch(Exception e){
    System.out.println(e);
    }
 
 
 
 
 }       
    
    
public boolean deletetable(){

    boolean isdeleted = false;
    
    String dscript = jscript.getText();
    
    try{
            
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement("update add_trade set hide='0' where script = ?");
        pst.setString(1, dscript);
        
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
    
    
    
    
 public void updatetrade(){
    
    Statement st = null; 
    ResultSet rs = null;
    
    long l = System.currentTimeMillis();
    Date todaysDate = new Date(l);
    
    try{
            Connection con = DBConnection.getConnection();
            st = (Statement) con.createStatement();
            
            rs = st.executeQuery("select * from add_trade where hide='1'");
            rs.last();
            jButton6.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select avg((sell_price - buy_price)*quantity) as 'avgP/L' from add_trade where hide='1'");
            rs.next();
            jButton7.setText(rs.getString(1));
            
            rs = st.executeQuery("select sum((sell_price - buy_price)*quantity) as 'netP/L' from add_trade where hide='1'");
            rs.next();
            jButton8.setText(rs.getString(1));
            
            rs = st.executeQuery("select(select count(*) from add_trade where sell_price > buy_price and hide='1')/(select count(*) from add_trade where buy_price > sell_price and hide='1')");
            rs.next();
            jButton9.setText(rs.getString(1));
          
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
            date2.setDate(todaysDate);
            
            }catch (Exception e){
            System.out.println(e);
    }
} 
 
 
 public void cleartable(){
    DefaultTableModel model = (DefaultTableModel) rSTableMetro1.getModel();
    model.setRowCount(0);
 }
/*    
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      try{
      Connection con = DBConnection.getConnection();
      String sql = "select ProfitLoss , count(*) as types_of_trade from add_trade group by ProfitLoss";
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      while(rs.next()){
      barDataset.setValue( rs.getString("ProfitLoss") , new Double( rs.getDouble("types_of_trade")));
      
      }
      
      }catch(Exception e){
          System.out.println("e");
      }    
      
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Trade Result",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Win", new Color(37,218,255));
        piePlot.setSectionPaint("Loss", new Color(217,68,68));
      
      
       
        piePlot.setBackgroundPaint(new Color(0,0,102));
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        ChartPanel1.removeAll();
        ChartPanel1.add(barChartPanel, BorderLayout.CENTER);
        ChartPanel1.validate();
    }
*/
    
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
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jScrollPane1 = new javax.swing.JScrollPane();
        rSTableMetro1 = new rojeru_san.complementos.RSTableMetro();
        rSButtonIconI7 = new rojerusan.RSButtonIconI();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jscript = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        date1 = new com.toedter.calendar.JDateChooser();
        date2 = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        rSMaterialButtonCircle5 = new rojerusan.RSMaterialButtonCircle();
        jLabel7 = new javax.swing.JLabel();
        savename = new javax.swing.JLabel();

        jCTextField2.setBackground(new java.awt.Color(0, 0, 102));
        jCTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 255, 255)));
        jCTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jCTextField2.setPhColor(new java.awt.Color(255, 255, 255));
        jCTextField2.setPlaceholder("Enter Quantity");

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 102)), javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255))));
        jPanel1.setPreferredSize(new java.awt.Dimension(810, 430));

        jLabel1.setBackground(new java.awt.Color(0, 0, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Trade Details");
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
        rSMaterialButtonCircle3.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        rSMaterialButtonCircle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonCircle3MouseClicked(evt);
            }
        });
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });

        rSTableMetro1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Script", "Strategy", "Action", "Buy Price", "Sell Price", "Quantity", "Profit/Loss"
            }
        ));
        rSTableMetro1.setColorBackgoundHead(new java.awt.Color(51, 0, 204));
        rSTableMetro1.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setColorBordeHead(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setColorFilasBackgound1(new java.awt.Color(102, 153, 255));
        rSTableMetro1.setColorFilasBackgound2(new java.awt.Color(102, 153, 255));
        rSTableMetro1.setColorFilasForeground1(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        rSTableMetro1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        jLabel13.setBackground(new java.awt.Color(0, 0, 102));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("No. of Trade");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(0, 0, 102));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Avg Profit/Loss");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(0, 0, 102));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Net P & L");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(0, 0, 102));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Win/Loss Ratio");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 0, 102));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("     Script");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jscript.setBackground(new java.awt.Color(0, 0, 102));
        jscript.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jscript.setForeground(new java.awt.Color(255, 255, 255));
        jscript.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));
        jscript.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton6.setBackground(new java.awt.Color(51, 0, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 0, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton8.setBackground(new java.awt.Color(51, 0, 153));
        jButton8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(51, 0, 153));
        jButton9.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 0, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Date Range");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 204, 255)));

        date1.setBackground(new java.awt.Color(0, 0, 102));
        date1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 255)));
        date1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        date2.setBackground(new java.awt.Color(0, 0, 102));
        date2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 255)));
        date2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel16.setBackground(new java.awt.Color(0, 0, 102));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Choose Specific Period");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        rSMaterialButtonCircle5.setBackground(new java.awt.Color(102, 204, 0));
        rSMaterialButtonCircle5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        rSMaterialButtonCircle5.setText("SEARCH");
        rSMaterialButtonCircle5.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        rSMaterialButtonCircle5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonCircle5MouseClicked(evt);
            }
        });
        rSMaterialButtonCircle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle5ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(rSMaterialButtonCircle5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jscript, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rSMaterialButtonCircle3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jscript, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                                    .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rSMaterialButtonCircle3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rSMaterialButtonCircle5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(528, 528, 528))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1278, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        int yes_or_no = JOptionPane.showConfirmDialog(this,"Do You Want to Exit","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(yes_or_no == JOptionPane.YES_OPTION){
        System.exit(0); 
        }
        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void rSButtonIconI7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonIconI7MouseClicked
            homepage page = new homepage();
            page.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_rSButtonIconI7MouseClicked

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void rSTableMetro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTableMetro1MouseClicked
        int row = rSTableMetro1.getSelectedRow();
        TableModel model = rSTableMetro1.getModel();
        jscript.setText(model.getValueAt(row,1).toString());
        
        
        
        
    }//GEN-LAST:event_rSTableMetro1MouseClicked

    private void rSMaterialButtonCircle3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3MouseClicked
        int yes_or_no = JOptionPane.showConfirmDialog(this,"Do You Want To Delete","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(yes_or_no == JOptionPane.YES_OPTION){
        if(deletetable() == true){
          JOptionPane.showMessageDialog(this,"trade Moved To Trash");
          cleartable();
          tradedetailstable();
          updatetrade();
       }
       else{
         JOptionPane.showMessageDialog(this,"trade deletion failed");
       }
    }
        
        
       
    }//GEN-LAST:event_rSMaterialButtonCircle3MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void rSMaterialButtonCircle5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle5MouseClicked

    private void rSMaterialButtonCircle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle5ActionPerformed
        
        try{
        Object[] obj = {trade_date,script,strategy,action,buy_price,sell_price,quantity,ProfitLoss};
        model = (DefaultTableModel) rSTableMetro1.getModel();
        model.addRow(obj);  
      //  rSTableMetro1.setModel(new DefaultTableModel(null,new Object[] {trade_date,script,strategy,action,buy_price,sell_price,quantity,ProfitLoss}));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String trade_date1 = sdf.format(date1.getDate());
        String trade_date2 = sdf.format(date2.getDate());
        view(trade_date1,trade_date2);
        }catch(Exception e){
            System.out.println("e");
        }
        
    }//GEN-LAST:event_rSMaterialButtonCircle5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(tradedetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tradedetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tradedetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tradedetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tradedetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private app.bolivia.swing.JCTextField jCTextField2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jscript;
    private rojerusan.RSButtonIconI rSButtonIconI7;
    private rojeru_san.complementos.RSEstiloTablaHeader rSEstiloTablaHeader1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle5;
    private rojeru_san.complementos.RSTableMetro rSTableMetro1;
    private javax.swing.JLabel savename;
    // End of variables declaration//GEN-END:variables
private ImageIcon format = null;
}
