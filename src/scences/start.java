/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scences;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ThinhIt
 */
class ss extends JPanel{
    private Image nen;
    private JButton starts;
    private JButton guide;
    private JLabel textField;
    private loadgame load;
    //private JTextField load;
    private ketnoi cnt;

    
    
    public ss(){
        loadImage();
        setSurfaceSize();
        thembutton();
    }
    void loadImage(){
        nen=new ImageIcon("./src/scences/start.png").getImage();
    }
    private void setSurfaceSize() {
         
        Dimension d = new Dimension();
        d.width = nen.getWidth(null);
        d.height = nen.getHeight(null);
        setPreferredSize(d);        
    }
 
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(nen, 0, 0, null);
    }
 
    @Override
    public void paintComponent(Graphics g) {
 
        super.paintComponent(g);
        doDrawing(g);
    }
    void thembutton(){
       
        this.setLayout(new GridBagLayout());
        GridBagConstraints gb= new GridBagConstraints();
        gb.anchor=GridBagConstraints.NORTH;
        gb.gridx = 0;
        gb.gridy = 0;
        textField= new JLabel(" CỜ CHÓ");
        textField.setForeground(Color.GREEN);
        textField.setFont(new Font("Serif", Font.BOLD,40));
        textField.setVisible(true);
        this.add(textField,gb);
       
        gb.anchor=GridBagConstraints.CENTER;
        gb.gridx = 0;
        gb.gridy = 1;
        gb.ipadx=100;
        gb.ipady=100;
        gb.weighty = 0.5; 
        load= new loadgame();
        load.setVisible(false);
        //load= new JTextField();
        this.add(load,gb);
       
       
        
        starts= new JButton(" BẮT ĐẦU ");
        gb.ipady = 0; 
        gb.ipadx=0;
        
        gb.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gb.insets = new Insets(0,-250,50,0);  //top paddin
        this.add(starts,gb);
       
        guide = new JButton("HƯỚNG DẪN");
         gb.insets = new Insets(0,250,50,0);
        this.add(guide,gb);
        
        this.starts.addMouseListener(new MouseAdapter() {
             @Override
                public void mousePressed(MouseEvent e) {
                 load.setVisible(true);
                }
        });
        this.starts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 loadgamenow();
            }
        });
        this.guide.addMouseListener(new MouseAdapter() {
             @Override
                public void mousePressed(MouseEvent e) {
                   System.out.println(".mousePressed()");
                }
        });
    }
      void loadgamenow() {
       this.load.setVisible(true);
       ketnoi ket= new ketnoi();
       this.cnt=ket;
       cnt.connect();
       this.setVisible(false);
       String g=cnt.nhan();
       System.out.println("xuat:"+g);
       if(g.equals("danh sau")){
            java.awt.EventQueue.invokeLater(new Runnable() 
            {
                @Override
                public void run() 
                {
                   demogame.Demogame t= new demogame.Demogame(cnt,1);
                    t.setVisible(true);
                    System.out.println("xuat da vo:"+g);
                }
            });
           
       }
        else
       {
           java.awt.EventQueue.invokeLater(new Runnable() 
            {
                @Override
                public void run() 
                {
                    demogame.Demogame r= new demogame.Demogame(cnt,0);
                    r.setVisible(true);
                }
            });
       }
    }
}
public class start extends JFrame{
    public start() {
 
        initUI();
    }
    private void initUI() {
        add(new ss());
        pack();        
        setTitle("Game");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    public static void main(String[] args) {
 
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                start ex = new start();
                ex.setVisible(true);
            }
        });
    }
}
