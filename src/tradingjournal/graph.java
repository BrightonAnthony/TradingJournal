/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tradingjournal;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author princy
 */
public final class graph extends javax.swing.JFrame {

    /**
     * Creates new form addrecord
     */
    public graph() {
        initComponents();
        showPieChart();
        showLineChart();
        updatetrade();
        displayname();
    }
    
    public void displayname(){
    
    Statement st = null; 
    ResultSet rs = null;
    
    long l = System.currentTimeMillis();
    java.util.Date todaysDate = new java.util.Date(1);
    
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
            byte[] img5 = rs.getBytes(1);
            format = new ImageIcon(new ImageIcon(img5).getImage().getScaledInstance(jLabel7.getWidth(),jLabel7.getHeight(),Image.SCALE_SMOOTH));
            jLabel7.setIcon(format);
      
            }catch (Exception e){
            System.out.println(e);
            }
    
}  
    
 
     public void updatetrade(){
    
    Statement st = null; 
    ResultSet rs = null;
    
    long l = System.currentTimeMillis();
    java.util.Date todaysDate = new java.util.Date(l);
    
    try{
            Connection con = DBConnection.getConnection();
            st = (Statement) con.createStatement();
            
            rs = st.executeQuery("select * from add_trade where hide='1'");
            rs.last();
            jTextField2.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select avg((sell_price - buy_price)*quantity) as 'avgP/L' from add_trade where hide='1'");
            rs.next();
            jTextField3.setText(rs.getString(1));
            
            rs = st.executeQuery("select sum((sell_price - buy_price)*quantity) as 'netP/L' from add_trade where hide='1'");
            rs.next();
            jTextField4.setText(rs.getString(1));
            
            rs = st.executeQuery("select(select count(*) from add_trade where sell_price > buy_price and hide='1')/(select count(*) from add_trade where buy_price > sell_price and hide='1')");
            rs.next();
            jTextField8.setText(rs.getString(1));
          
            
            }catch (Exception e){
            System.out.println(e);
    }
}
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      try{
      Connection con = DBConnection.getConnection();
      String sql = "select action , count(*) as types_of_trade from add_trade where hide = '1' group by action";
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      while(rs.next()){
      barDataset.setValue( rs.getString("action") , new Double( rs.getDouble("types_of_trade")));
      
      }
      
      }catch(Exception e){
          System.out.println("e");
      }  
      
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Types of Trade",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Buy", new Color(204,102,0));
        piePlot.setSectionPaint("Sell", new Color(0,204,51));
      
      
       
        piePlot.setBackgroundPaint(new Color(0,0,102));
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        ChartPanel5.removeAll();
        ChartPanel5.add(barChartPanel, BorderLayout.CENTER);
        ChartPanel5.validate();
    }
    
    
    public void showLineChart(){
        updatetrade();
        int ProfitLoss = 0,buy_price =0,sell_price=0,quantity=0;
        double Amount = Double.parseDouble(jTextField3.getText());
        //create dataset for the graph
        
       // DefaultPieDataset barDataset = new DefaultPieDataset( );
        try{
      Connection con = DBConnection.getConnection();
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery("select * from add_trade");
            
            
            while(rs.next()){
                
                
             //  String trade_date = rs.getString("trade_date");
             //  String script = rs.getString("script");
             //  String strategy = rs.getString("strategy");
             //  String action = rs.getString("action");
              //   buy_price = rs.getInt("buy_price");
                 sell_price = rs.getInt("sell_price");
                 quantity = rs.getInt("quantity");
             // String avg = jTextField3.getText();
                 ProfitLoss = rs.getInt("ProfitLoss");
              //  avg = Double.parseDouble(jTextField3.getText());
      }
      
      }catch(Exception e){
          System.out.println("e");
      }
       
       DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
       dataset.setValue(new Double(buy_price), "Values", "Investment");
      //  dataset.setValue(new Double(sell_price), "Values", "sell_price");
       // dataset.setValue(new Double(buy_price), "Values", "buy_price");
        //double avg = 0;
        dataset.setValue(new Double(Amount), "Values", "Rate of Return");
        //dataset.setValue(new Double(ProfitLoss), "Values", "ProfitLoss");
      // dataset.setValue(new Double(quantity), "Values", "quantity");
     //   //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Stock","Amount","Profit/Loss", 
        dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(new Color(0,0,102));
        
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        ChartPanel3.removeAll();
        ChartPanel3.add(lineChartPanel, BorderLayout.CENTER);
        ChartPanel3.validate();
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
        rSButtonIconI7 = new rojerusan.RSButtonIconI();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        ChartPanel3 = new javax.swing.JPanel();
        ChartPanel5 = new javax.swing.JPanel();
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
        jLabel1.setText("Trade Graph");
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

        jTextField2.setBackground(new java.awt.Color(0, 0, 102));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 255)));
        jTextField2.setCaretColor(new java.awt.Color(255, 255, 255));

        jTextField3.setBackground(new java.awt.Color(0, 0, 102));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 255)));
        jTextField3.setCaretColor(new java.awt.Color(255, 255, 255));

        jTextField4.setBackground(new java.awt.Color(0, 0, 102));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 255)));
        jTextField4.setCaretColor(new java.awt.Color(255, 255, 255));

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

        jLabel15.setBackground(new java.awt.Color(0, 0, 102));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Net P & L");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 102, 0));
        jLabel3.setText(" Buy");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 153)));

        jLabel4.setBackground(new java.awt.Color(0, 0, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 51));
        jLabel4.setText("Sell");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 153)));

        jLabel19.setBackground(new java.awt.Color(0, 0, 102));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Win/Loss");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jTextField8.setBackground(new java.awt.Color(0, 0, 102));
        jTextField8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 255)));
        jTextField8.setCaretColor(new java.awt.Color(255, 255, 255));

        ChartPanel3.setBackground(new java.awt.Color(0, 51, 153));
        ChartPanel3.setLayout(new java.awt.BorderLayout());

        ChartPanel5.setBackground(new java.awt.Color(0, 51, 153));
        ChartPanel5.setLayout(new java.awt.BorderLayout());

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(369, 369, 369)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(624, 624, 624)
                                .addComponent(ChartPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(ChartPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 82, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel13)))
                .addGap(197, 197, 197)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(202, 202, 202)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(jTextField4))
                .addGap(131, 131, 131))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(savename, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rSButtonIconI7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChartPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChartPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1278, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void rSButtonIconI7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonIconI7MouseClicked
       homepage home = new homepage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonIconI7MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        int yes_or_no = JOptionPane.showConfirmDialog(this,"Do You Want to Exit","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(yes_or_no == JOptionPane.YES_OPTION){
        System.exit(0); 
        }
        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

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
            java.util.logging.Logger.getLogger(graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new graph().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChartPanel3;
    private javax.swing.JPanel ChartPanel5;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private app.bolivia.swing.JCTextField jCTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private rojerusan.RSButtonIconI rSButtonIconI7;
    private rojeru_san.complementos.RSEstiloTablaHeader rSEstiloTablaHeader1;
    private javax.swing.JLabel savename;
    // End of variables declaration//GEN-END:variables
private ImageIcon format = null;
}
