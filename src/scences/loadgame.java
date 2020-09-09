/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scences;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ThinhIt
 */
public class loadgame extends JPanel
        implements ActionListener {
 
    private Timer timer;
    private int count;
    private JLabel l;
    private final int INITIAL_DELAY = 200;
    private final int DELAY = 80;
    private final int NUMBER_OF_LINES = 8;
    private final int STROKE_WIDTH = 3;
     
    private final double[][] trs = {
        {0.0, 0.15, 0.30, 0.5, 0.65, 0.80, 0.9, 1.0},
        {1.0, 0.0, 0.15, 0.30, 0.5, 0.65, 0.8, 0.9},
        {0.9, 1.0, 0.0, 0.15, 0.3, 0.5, 0.65, 0.8},
        {0.8, 0.9, 1.0, 0.0, 0.15, 0.3, 0.5, 0.65},
        {0.65, 0.8, 0.9, 1.0, 0.0, 0.15, 0.3, 0.5},
        {0.5, 0.65, 0.8, 0.9, 1.0, 0.0, 0.15, 0.3},
        {0.3, 0.5, 0.65, 0.8, 0.9, 1.0, 0.0, 0.15},
        {0.15, 0.3, 0.5, 0.65, 0.8, 0.9, 1.0, 0.0}
    };
 
    public loadgame() {
         
        initTimer();
    }
     
    private void initTimer() {
         
        l= new JLabel("Đang kết nối ");
        l.setForeground(Color.BLACK);
        l.setFont(new Font("Serif", Font.BOLD,15));
        this.add(l,BorderLayout.NORTH);
        timer = new Timer(DELAY, this);
        timer.setInitialDelay(INITIAL_DELAY);
        timer.start();        
    }
 
    private void doDrawing(Graphics g) {
 
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
         
        int width = getWidth();
        int height = getHeight();
 
        g2d.setStroke(new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g2d.translate(width / 2, height / 2);
 
        for (int i = 0; i < NUMBER_OF_LINES; i++) {
             
            float alpha = (float) trs[count % NUMBER_OF_LINES][i];
            AlphaComposite acomp = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(acomp);
 
            g2d.rotate(Math.PI / 4f);
            g2d.drawLine(0, -10, 0, -40);
        }
         
        g2d.dispose();
    }
 
    @Override
    public void paintComponent(Graphics g) {
 
        super.paintComponent(g);
        doDrawing(g);
    }
   
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
          repaint();
        count++;
    }
    
}
 
