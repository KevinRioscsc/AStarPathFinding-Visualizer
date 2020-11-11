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
public class Node {
    private int x;
    private int y;
    private int g;
    private int h;
    private int f;
    private Node parent;
    private Node currentNode;
    
    public Node(int x, int y){
        this.x =x;
        this.y = y;
    }
    public void setGcost(int g){
        this.g = g;
        
    }
    public void setHcost(int h){
        this.h = h;
        
    }
    public void setFcost(int f){
        this.f = f;
        
    }
    public void setParent(Node parent){
        this.parent = parent;
    }
    public void setCurrent(Node currentNode){
        this.currentNode = currentNode;
    }
    public boolean hasParent(Node current){
        if(current.getParent() != null){
            return true;
    }
        return false;
        
    }
    public Node getParent(){
        return parent;
    }
    public Node getNode(){
        return currentNode;
    }
    public int getGcost(){
        return g;
    }
    public int getHcost(){
        return h;
    }
    public int getFcost(){
        return f;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
