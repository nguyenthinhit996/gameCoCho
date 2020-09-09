/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demogame;

import com.sun.javafx.font.FontConstants;
import com.sun.org.apache.xpath.internal.operations.Gt;
import java.awt.BorderLayout;
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
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import scences.ketnoi;

/**
 *
 * @author ThinhIt
 */
class manHinhGame extends JPanel{
    private Image nen;
    private BufferedImage cho;
    private TexturePaint scho;
    private JButton end;
    private JPanel checkjPanel;
    private JPanel chienthangp;
    private JPanel hienthidanh;
    private JPanel thuap;
    //---------
    // di chuyen 
    private ZRectangle zrect1;
    private ZRectangle zrect2;
    private ZRectangle zrect3;
    private ZRectangle zrect4;
   
    
    private Area a1;
    private Area a2;
    private Area a3;
    private Area a4;
    private Area a5;
    
    // kiem tra luot di 0 mau vang di truoc 
    int luot;
    int luotgoc;


    
    // truyen tai du lieu 
    ketnoi connet;
    
    public manHinhGame(ketnoi con,int luot){
        this.connet=con;
        this.luot=luot;
        this.luotgoc=luot;
       
        loadImage();
        setSurfaceSize();
        chienthang();
        chaykiemxoat();
        //di chuyen
        MovingAdapter ma = new MovingAdapter();
        addMouseMotionListener(ma);
        addMouseListener(ma);
        zrect1 = new ZRectangle(70, 50, 70, 70);
        zrect2 = new ZRectangle(370, 50, 70, 70);
        zrect3 = new ZRectangle(70, 370, 70, 70);
        zrect4 = new ZRectangle(370, 370, 70, 70);
    }
    void loadImage(){
        nen=new ImageIcon("./src/demogame/v.jpg").getImage();
        try {
            cho = ImageIO.read(new File("./src/demogame/f.png"));
        } catch (IOException ex) {
            Logger.getLogger(manHinhGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setSurfaceSize() {
         
        Dimension d = new Dimension();
        d.width = nen.getWidth(null);
        d.height = nen.getHeight(null);
        setPreferredSize(d);        
    }
 
    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(nen, 0, 0, null);
        scho=new TexturePaint(cho, new Rectangle(70,43,90,80));
        g2d.setPaint(scho);
        g2d.fillRoundRect(340, 200, 90,83, 25, 25);
        // di chuyen 
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);        
        g2d.setPaint(new Color(0,0,0));
        a1 = new Area(new Rectangle2D.Double(80,60,30, 30)); 
        a2 = new Area(new Rectangle2D.Double(380,60,30,30)); 
        a3 = new Area(new Rectangle2D.Double(230,230,30, 30)); 
        a4 = new Area(new Rectangle2D.Double(80,390,30, 30)); 
        a5 = new Area(new Rectangle2D.Double(380,390,30, 30)); 
        g2d.fill(a1);
        g2d.fill(a2);
        g2d.fill(a3);
        g2d.fill(a4);
        g2d.fill(a5);
        
        g2d.setPaint(Color.YELLOW);
        g2d.fill(zrect1);
        g2d.fill(zrect2);
       
        g2d.setPaint(Color.RED);        
        g2d.fill(zrect3);
        g2d.fill(zrect4);
        
    }
 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    class ZRectangle extends Rectangle2D.Float {
 
        public ZRectangle(float x, float y, float width, float height) {
             
            setRect(x, y, width, height);
        }
 
        public boolean isHit(float x, float y) {
             
            return getBounds2D().contains(x, y);
        }
 
        public void addX(float x) {
            this.x += x;
        }
 
        public void addY(float y) {
             
            this.y += y;
        }     
    }
    
    class MovingAdapter extends MouseAdapter {
        private int x_t;
        private int y_t;
        private int x=0;
        private int y=0;
 
        @Override
        public void mousePressed(MouseEvent e) {   
            x_t = e.getX();
            y_t = e.getY();
            if(zrect1.isHit(x_t,y_t)||zrect2.isHit(x_t,y_t)||zrect3.isHit(x_t,y_t)||zrect4.isHit(x_t,y_t)){
                x=x_t;
                y=y_t;
            }
            else{
                if(zrect1.isHit(x,y)||zrect2.isHit(x, y)||zrect3.isHit(x,y)||zrect4.isHit(x,y)){
                    if(a1.contains(x_t, y_t) || a2.contains(x_t, y_t)||a3.contains(x_t, y_t)||a4.contains(x_t, y_t)||a5.contains(x_t, y_t)){                 
                        System.out.println(luot);
                        System.out.println("-----");
                        doMove(e);
                    }               
                }
            }                      
        }      
        private void doMove(MouseEvent e) { 
            if(luotgoc==0){
                if(luot==0){
                    if(zrect1.isHit(x, y))
                    {
                            Area vungden=getArea(x_t,y_t);
                            int vd=getAreaI(zrect1.getCenterX(), zrect1.getCenterY());
                            int vden=getAreaI(x_t, y_t);
                            if(kiemtradi(vd, vden)){
                            double tx=vungden.getBounds().getBounds2D().getX()-10;
                            double ty=vungden.getBounds().getBounds2D().getY()-10;
                            zrect1.setFrame(tx,ty,70,70);
                            if(kiemtrathang(zrect3, zrect4, zrect2, zrect1)){
                                chienthangp.setVisible(true);
                                String di=1+":"+vden+":0";
                                connet.ghi(di);
                            }
                            else{
                                luot=1;
                                String di=1+":"+vden;
                                connet.ghi(di);
                                chaykiemxoat();
                            }
                            
                        }
                    }
                    if(zrect2.isHit(x, y))
                    {
                        Area vungden=getArea(x_t,y_t);
                        int vd=getAreaI(zrect2.getCenterX(), zrect2.getCenterY());
                        int vden=getAreaI(x_t, y_t);
                        if(kiemtradi(vd, vden)){
                            double tx=vungden.getBounds().getBounds2D().getX()-10;
                            double ty=vungden.getBounds().getBounds2D().getY()-10;
                            zrect2.setFrame(tx,ty,70,70);
                            if(kiemtrathang(zrect3, zrect4, zrect2, zrect1)){
                               chienthangp.setVisible(true);
                                String di=2+":"+vden+":0";
                                connet.ghi(di);
                            }else{
                            luot=1;
                            String di=2+":"+vden;
                            connet.ghi(di);
                            chaykiemxoat();
                            }
                        }
                      }
                    
                }
            }           
            else{
                if(luot==0){
                    if(zrect3.isHit(x, y))
                    { 
                            Area vungden=getArea(x_t,y_t);
                            int vd=getAreaI(zrect3.getCenterX(), zrect3.getCenterY());
                             int vden=getAreaI(x_t, y_t);
                             if(kiemtradi(vd, vden)){
                            double tx=vungden.getBounds().getBounds2D().getX()-10;
                            double ty=vungden.getBounds().getBounds2D().getY()-10;
                            zrect3.setFrame(tx,ty,70,70);
                            if(kiemtrathang(zrect1, zrect2, zrect3, zrect4)){
                              chienthangp.setVisible(true);
                              String di=2+":"+vden+":0";
                              connet.ghi(di);
                            }
                            else{
                                
                            luot=1;
                            String di=3+":"+vden;
                            connet.ghi(di);
                            chaykiemxoat();
                            }
                        
                      }
                    }
                    if(zrect4.isHit(x, y))
                    { 
                        
                        Area vungden=getArea(x_t,y_t);
                        int vd=getAreaI(zrect4.getCenterX(), zrect4.getCenterY());
                        int vden=getAreaI(x_t, y_t);
                        if(kiemtradi(vd, vden)){
                            double tx=vungden.getBounds().getBounds2D().getX()-10;
                            double ty=vungden.getBounds().getBounds2D().getY()-10;
                            zrect4.setFrame(tx,ty,70,70);
                            if(kiemtrathang(zrect1, zrect2, zrect3, zrect4)){
                               chienthangp.setVisible(true);
                                String di=4+":"+vden+":0";
                                connet.ghi(di);
                            }
                            else{
                                 luot=1;
                                String di=4+":"+vden;
                                connet.ghi(di);
                                chaykiemxoat();
                            }
                           
                        }
                    
                    }  
                }
            }          
        repaint();        
       }
        Area getArea(double x,double y){
            if(a1.contains(x, y)){
                return a1;
            }
            if(a2.contains(x, y)){
                return a2;
            }
            if(a3.contains(x, y)){
                return a3;
            }
            if(a4.contains(x, y)){
                return a4;
            }
            if(a5.contains(x, y)){
                return a5;
            }
            return null;
        }
        int getAreaI(double x,double y){
            if(a1.contains(x, y)){
                return 1;
            }
            if(a2.contains(x, y)){
                return 2;
            }
            if(a3.contains(x, y)){
                return 3;
            }
            if(a4.contains(x, y)){
                return 4;
            }
            if(a5.contains(x, y)){
                return 5;
            }
            return 0;
        }
        boolean kiemtradi(int d, int den){
            if(d==1&&den==5||d==5&&den==1){
                return false;
            }
            if( d==2&&den==4 || d==4&&den==2){
                return false;
            }
            if(d==5&&den==2 || d==2&& den==5){
                return false;
            }
            return true;           
        }
        boolean kiemtrathang(ZRectangle a,ZRectangle b,ZRectangle c,ZRectangle d){
             int sa=getAreaI(a.getCenterX(),a.getCenterY());
             int sb=getAreaI(b.getCenterX(), b.getCenterY()); 
             int sc=getAreaI(c.getCenterX(),c.getCenterY());
             int sd=getAreaI(d.getCenterX(), d.getCenterY()); 
             if(sa==1&&sb==2||sa==2&&sb==1){
                if(sc==3&&sd==4 ||sc==4&&sd==3){
                        return true;
                    }
                }
            if(sa==4&&sb==5||sa==5&&sb==4){
                if(sc==1&&sd==3 ||sc==3&&sd==1){
                        return true;
                    }
                }
            return false;
        }
    }
        public void chienthang(){
            GridBagConstraints gbc = new GridBagConstraints();  
            GridBagLayout layout = new GridBagLayout();  
            this.setLayout(layout); 
            gbc.weighty=0.5;
            gbc.gridx=0;
            gbc.gridy=0;
            chienthangp= new chienthangpanel();
            chienthangp.setVisible(false);
            this.add(chienthangp,gbc);
            
            gbc.gridx=0;
            gbc.gridy=0;
            thuap= new thua();
            thuap.setVisible(false);
            this.add(thuap,gbc);
            
             
            if(this.luotgoc==0){
               gbc.gridx=0;
                gbc.gridy=1;
                hienthidanh= new hienthidanh("Bạn đánh trước ( Màu vàng ) ");
                hienthidanh.setVisible(true);
                this.add(hienthidanh,gbc); 
            }
            else{
                gbc.gridx=0;
                gbc.gridy=1;
                hienthidanh= new hienthidanh("Bạn đánh sau ( Màu đỏ ) ");
                hienthidanh.setVisible(true);
                this.add(hienthidanh,gbc);
            }
        
            gbc.gridx=0;
            gbc.gridy=2;
            gbc.ipadx=100;
            gbc.ipady=100;
            gbc.insets = new Insets(0,0,20,0);
            checkjPanel= new checkFinally("Ban muon thoat game");
            checkjPanel.setVisible(false);
            this.add(checkjPanel,gbc);
       
            gbc.ipadx=0;
            gbc.ipady=0;
            gbc.insets = new Insets(0,0,30,0);
            end= new JButton(" KẾT THÚC ");
            gbc.anchor=GridBagConstraints.PAGE_END;       
            this.add(end,gbc);
            end.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   checkjPanel.setVisible(true);
                }
            });
    }
     class kiemsoat implements Runnable{
        private ketnoi kn;
        public kiemsoat(ketnoi g){
            kn=g;
        }
        void kiemtra(){
         System.out.println("demogame.manHinhGame.kiemsoat()");
         if(luotgoc==0){
            if(luot==1){
                String nhanString=kn.nhan();
                String [] n=nhanString.split(":");
                if(n.length==3){
                    chienthangp.setVisible(true);
                }
                int khoi =Integer.parseInt(n[0]);
                int vu=Integer.parseInt(n[1]);
                Area vungg=null;
                if(vu==1){
                    vungg=a1;
                }
                if(vu==2){
                    vungg=a2;
                }
                if(vu==3){
                    vungg=a3;
                }
                if(vu==4){
                    vungg=a4;
                }
                if(vu==5){
                    vungg=a5;
                }
                if(khoi==3){
                    double tx=vungg.getBounds().getBounds2D().getX()-10;
                    double ty=vungg.getBounds().getBounds2D().getY()-10;
                    zrect3.setFrame(tx,ty,70,70);
                    luot=0;
                }
                if(khoi==4){
                    double tx=vungg.getBounds().getBounds2D().getX()-10;
                    double ty=vungg.getBounds().getBounds2D().getY()-10;
                    zrect4.setFrame(tx,ty,70,70);
                    luot=0;
                }
                
                }         
            }
            else{ // 0 la doi nhan
             if(luot==1){
                String nhanString=kn.nhan();
                String [] n=nhanString.split(":");
                int khoi =Integer.parseInt(n[0]);
                int vu=Integer.parseInt(n[1]);
                if(vu==1){
                    if(khoi==1){
                    double tx=a1.getBounds().getBounds2D().getX()-10;
                    double ty=a1.getBounds().getBounds2D().getY()-10;
                    zrect1.setFrame(tx,ty,70,70);
                    luot=0;
                    }
                    if(khoi==2){
                        double tx=a1.getBounds().getBounds2D().getX()-10;
                        double ty=a1.getBounds().getBounds2D().getY()-10;
                        zrect2.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                }
                if(vu==2){
                    if(khoi==1){
                        double tx=a2.getBounds().getBounds2D().getX()-10;
                        double ty=a2.getBounds().getBounds2D().getY()-10;
                        zrect1.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                    if(khoi==2){
                        double tx=a2.getBounds().getBounds2D().getX()-10;
                        double ty=a2.getBounds().getBounds2D().getY()-10;
                        zrect2.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                }
                if(vu==3){
                    if(khoi==1){
                        double tx=a3.getBounds().getBounds2D().getX()-10;
                        double ty=a3.getBounds().getBounds2D().getY()-10;
                        zrect1.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                    if(khoi==2){
                        double tx=a3.getBounds().getBounds2D().getX()-10;
                        double ty=a3.getBounds().getBounds2D().getY()-10;
                        zrect2.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                }
                if(vu==4){
                    if(khoi==1){
                        double tx=a4.getBounds().getBounds2D().getX()-10;
                        double ty=a4.getBounds().getBounds2D().getY()-10;
                        zrect1.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                    if(khoi==2){
                        double tx=a4.getBounds().getBounds2D().getX()-10;
                        double ty=a4.getBounds().getBounds2D().getY()-10;
                        zrect2.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                }
                if(vu==5){
                    if(khoi==1){
                        double tx=a5.getBounds().getBounds2D().getX()-10;
                        double ty=a5.getBounds().getBounds2D().getY()-10;
                        zrect1.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                    if(khoi==2){
                        double tx=a5.getBounds().getBounds2D().getX()-10;
                        double ty=a5.getBounds().getBounds2D().getY()-10;
                        zrect2.setFrame(tx,ty,70,70);
                        luot=0;
                    }
                }
             }
            }
         repaint();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(manHinhGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            kiemtra();
        }
    }
    void chaykiemxoat(){
        Runnable t= new kiemsoat(connet);
        Thread tt= new Thread(t);
        tt.start();
    }
}
  
class thua extends JPanel{
    private JButton yes;
    private JLabel l;
    public thua(){
        init();
    }
    void init(){
       this.setLayout(new GridBagLayout());
       GridBagConstraints gbc= new GridBagConstraints();
       gbc.insets= new Insets(0, 20, 0, 0);
       gbc.gridx=1;
       gbc.gridy=0;
       gbc.ipadx=50;
       gbc.ipady=90;
       gbc.anchor=GridBagConstraints.NORTH;
       l= new JLabel("Bạn Thua roi bạn");
       this.add(l,gbc);
       gbc.ipadx=0;
       gbc.ipady=0;
       
       gbc.insets= new Insets(0, 0, 0, 0);
       gbc.anchor=GridBagConstraints.PAGE_END;
       yes= new JButton("Đồng ý");
       this.add(yes,gbc);
       yes.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { 
               System.exit(0);
           }
       });  
    }
      
}
class chienthangpanel extends JPanel{
    private JButton yes;
    private JLabel l;
    public chienthangpanel(){
        init();
    }
    void init(){
       this.setLayout(new GridBagLayout());
       GridBagConstraints gbc= new GridBagConstraints();
       gbc.insets= new Insets(0, 20, 0, 0);
       gbc.gridx=1;
       gbc.gridy=0;
       gbc.ipadx=50;
       gbc.ipady=90;
       gbc.anchor=GridBagConstraints.NORTH;
       l= new JLabel("Bạn chiến thắng chúc mừng bạn");
       this.add(l,gbc);
       gbc.ipadx=0;
       gbc.ipady=0;
       
       gbc.insets= new Insets(0, 0, 0, 0);
       gbc.anchor=GridBagConstraints.PAGE_END;
       yes= new JButton("Đồng ý");
       this.add(yes,gbc);
       yes.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { 
               System.exit(0);
           }
       });  
    }
       
}


class checkFinally extends JPanel{
    private JButton yes;
    private JButton no;
    private JLabel l;
    public checkFinally(String s){
        init(s);
    }
    void init(String s){
       this.setLayout(new GridBagLayout());
       GridBagConstraints gbc= new GridBagConstraints();
       gbc.insets= new Insets(20,0,0,0);
       gbc.weighty=0.5;
       gbc.gridx=0;
       gbc.gridy=0;
       gbc.anchor=GridBagConstraints.NORTH;
       l= new JLabel(s);
       this.add(l,gbc);
       
       gbc.weighty=0.1;
       
       gbc.anchor=GridBagConstraints.CENTER;
       yes= new JButton("Đông ý");
       gbc.insets= new Insets(30,-100,0,0);
       this.add(yes,gbc);
       no=new JButton("Hủy");
       gbc.insets= new Insets(30,100,0,0);
       this.add(no,gbc);
       yes.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { 
               choiceyes(); 
           }
       });
       no.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { 
             choiceno();
           }
       });    
    }
    void choiceyes(){
       this.setVisible(false);
       System.exit(0);
    }
    void choiceno(){
        this.setVisible(false);
    }
}

class hienthidanh extends JPanel{
    private JButton yes;
    private JLabel l;
    public hienthidanh(String f){
        init(f);
    }
    void init(String f){
       GridBagConstraints gbc= new GridBagConstraints();
       gbc.weighty=0.5;
       gbc.gridx=0;
       gbc.gridy=0;
       gbc.anchor=GridBagConstraints.NORTH;
       l= new JLabel(f);
       this.add(l,gbc);
       gbc.gridx=0;
       gbc.gridy=1;
       yes= new JButton("Đồng ý");
       this.add(yes,gbc);
       yes.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { 
               setVisible(false);
           }
       });  
    }
}

 
public class Demogame extends JFrame{
    public Demogame(ketnoi knoi,int check) {
        initUI(knoi,check);       
    }
    public void huy(){
       this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    private void initUI(ketnoi knoi,int c) { 
                manHinhGame s=  new manHinhGame(knoi,c);
                add(s,BorderLayout.CENTER);
                pack();        
                setTitle("Game");
                setLocationRelativeTo(null);
                setResizable(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
}
