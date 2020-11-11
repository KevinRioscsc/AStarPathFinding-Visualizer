/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* The main graphics class for APathfinding. Controls the window,
 * and all path finding node graphics. Need to work on zoom function,
 * currently only zooms to top left corner rather than towards mouse
 * by Devon Crawford
 */
public class Frame extends JPanel implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
        int j = 0;
        int delay = 100;
        int size = 25;
        int spacing = 1;
        Node start, end;
        JFrame screen;
        APath something;
        char key = (char) 0;
        //Timer timer = new Timer(100, this);
        BufferedImage image;
        Timer timer = new Timer(20, this);;
        boolean show;
        Button butt;
       
        
         public static void main(String[] args) {
            new Frame(); 
            
            
    }    
	public Frame(){
            butt = new Button(this);
            show = true;
            setLayout(null);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            something = new APath();
            screen = new JFrame();
           
            
            
            screen.setContentPane(this);
            
            screen.setTitle("APath");
            screen.getContentPane().setPreferredSize(new Dimension(1000, 1000));
            
            this.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e){
                     MapCalculations(e);
                    //System.out.print("position of Mouse X: %d ");
                    //System.out.print("Mouse was clicked\n");
                  
                }
               
                
                  public void mousePressed(MouseEvent e) {
        
                         }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }

            });
            this.addMouseMotionListener(new MouseMotionListener(){
               @Override
                public void mouseDragged(MouseEvent e) {
                    MapCalculations(e);


                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    
                  
                }

                
                public void mouseWheelMoved(MouseWheelEvent e) {

                }

                
            });
          
            
            screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            screen.pack();
            screen.setLocationRelativeTo(null);
            
            
            
            
            screen.setVisible(true);
            timer.start();
            butt.addButton();
            this.revalidate();
            this.repaint(); 
       }
        
              
              
              
     
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                
                
                
              
               
                g.setColor(Color.red);
                for(int i = 0; i < this.getHeight(); i+=size){
                    for(int j = 0; j < this.getWidth(); j+=size){
                        g.drawRect(j,i,size,size);
                       // System.out.printf("New Node X: %d and Y: %d\n", j, i);
                        
                        
                    }
                
                }
                    g.setColor(Color.black);
                    
                    for(int i = 0; i < something.walls.size(); i++){
                        g.fillRect(something.walls.get(i).getX()+1, something.walls.get(i).getY()+1, size -1, size-1);
                        
                    }
                
                
                    System.out.printf("The size of open: %d\n", something.getOpenList().size());
                
                   for(int i = 0; i < something.getOpenList().size(); i ++){  
                       System.out.printf("We Printing Open\n");
                         g.setColor(Color.BLUE);
                      g.fillRect(something.getOpenList().get(i).getX()+1, something.getOpenList().get(i).getY()+1, size-1, size-1);
                      
                   }
                
                
                    
                     System.out.printf("The size of open: %d\n", something.getClosedList().size());
                    for(int i = 0; i < something.getClosedList().size(); i++){
                            System.out.printf("We Printing Closed\n");
                         g.setColor(Color.red);
                        g.fillRect(something.getClosedList().get(i).getX()+1, something.getClosedList().get(i).getY()+1, size-1, size-1);
                         //System.out.printf("The Closed Set is X: %d and Y: %d\n",something.closed.get(i).getX(), something.closed.get(i).getY() );
                    }
                
                
               
                
                     System.out.printf("The size of open: %d\n", something.getPathList().size());
                    for(int i = 0; i < something.getPathList().size(); i++){
                        System.out.printf("We Printing Pathn\n");
                         g.setColor(Color.PINK);
                        g.fillRect(something.getPathList().get(i).getX()+1, something.getPathList().get(i).getY()+1, size-1, size-1);
                        }
             
      
                        

                    
           

                    if(start != null){
                   g.setColor(Color.ORANGE);
                   g.fillRect(start.getX()+1, start.getY()+1, size-1, size-1);
                    System.out.printf("The position of mouse X: %d and Y: %d", start.getX(), start.getY());
               }
               if(end != null){
                   g.setColor(Color.red);
                   g.fillRect(end.getX()+1, end.getY()+1,size-1,size-1);
               }
               butt.position();
              
                
            }
            
       
    public void MapCalculations(MouseEvent e){
        
        if(SwingUtilities.isLeftMouseButton(e)){
            switch (key) {
                case 's':
                    {
                        int x_placement = e.getX() % size;
                        int y_placement = e.getY() % size;
                        if(start == null){
                            start = new Node(e.getX() - x_placement, e.getY() - y_placement);
                        }
                        repaint();
                        break;
                    }
                case 'e':
                    {
                        int x_placement = e.getX() % size;
                        int y_placement = e.getY() % size;
                        if(end == null){
                            end = new Node(e.getX() - x_placement, e.getY() - y_placement);
                        }
                        repaint();
                        break;
                    }
                default:
                    int WallX = e.getX() - (e.getX() % size);
                    int WallY = e.getY() - (e.getY() % size);
                    Node wall = new Node(WallX, WallY);
                    something.AddWalls(wall);
                    repaint();
                    break;
            }
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
			int mouseBoxX = e.getX() - (e.getX() % size);
			int mouseBoxY = e.getY() - (e.getY() % size);

            // If 's' is pressed remove start node
            switch (key) {
                case 's':
                    if (start != null && mouseBoxX == start.getX() && start.getY() == mouseBoxY) {
                        start = null;
                        repaint();
                    }
                    break;
                case 'e':
                    if (end != null && mouseBoxX == end.getX() && end.getY() == mouseBoxY) {
                        end = null;
                        repaint();
                    }
                    break;
                default:
                    
                    repaint();
                    break;
            }
		}
       
            
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(something.isRunning() && show){
           something.A_path(something.getPar(), this);
       }
       if(e.getActionCommand() != null) {
                if(e.getActionCommand().equals("run") && !something.isRunning()) {
				
				start();
			}
                else if(e.getActionCommand().equals("clear")){
                    something.reset();
                }
       }
       repaint();
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MapCalculations(e);
        System.out.print("position of Mouse X: %d ");
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MapCalculations(e);
         
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
         timer.start();
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char newkey = e.getKeyChar();
        key = newkey;
     
        if(e.getKeyCode() == KeyEvent.VK_2){
            if(start != null && end != null){
                System.out.print("key was pressed");
                start();
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key = (char) 0;
       
    }
    
     void start(){       
                something.setup(start, end,this);
               
                timer.start();
                
        }
       
    
        
}