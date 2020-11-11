/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project;

/**
 *
 * @author riosk
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Button {
    private Frame frame;
    private JButton run, clear;
    public Button(Frame frame){
        this.frame = frame;
        run = new JButton();
		run.setText("run");
		run.setName("run");
		run.setFocusable(false);
		run.addActionListener(frame);
		run.setMargin(new Insets(0,0,0,0));
		run.setVisible(true);
        clear = new JButton();
        clear.setText("clear");
	clear.setName("clear");
	clear.setFocusable(false);
	clear.addActionListener(frame);
	clear.setMargin(new Insets(0,0,0,0));
	clear.setVisible(true);
    }
    
    public void addButton(){
        frame.add(run);
        frame.add(clear);
    }
    public void position(){
        run.setBounds(450, frame.getHeight()-88, 150, 50);
        clear.setBounds(300, frame.getHeight()-88, 100, 50);
    }
    
}
