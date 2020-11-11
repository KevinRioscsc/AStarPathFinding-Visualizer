/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riosk
 */
public class APath {
    private Node start,end,parent,current;
    Node par;
    boolean complete;
    long delay = (long)3000;
    boolean running = false;
    boolean pathFound = false;
    
    
    ArrayList<Node> open,closed,mainPath,neighbors,walls;
  
    Sort sort;
    
    public APath(){
        open = new ArrayList<>();
        closed = new ArrayList<>();
        walls = new ArrayList<>();
        mainPath = new ArrayList<>();
       
        
    }
    public void setup(Node s, Node e, Frame frame){
        running = true;
        s.setGcost(0);
        s.setFcost(0);
        s.setHcost(0);
        par = start;
        start = s;
        end = e;
        addClosed(start);
        addOpen(start);
        
       
        
        complete=true;
    }
    public void A_path(Node start,Frame frame2) {
       
        sort = new Sort();
       
        
        
        //while(getOpenList().size() > 0){
         
              
                current = getOpen(0); //current = node in Open witht the lowest f cost
                
                
                for(int i = 0; i < getOpenList().size(); i++){
                    if(getOpen(i).getFcost() < current.getFcost() || getOpen(i).getFcost() == current.getFcost() && getOpen(i).getHcost() < current.getHcost() || getOpen(i).getHcost() == current.getHcost() && getOpen(i).getGcost() < current.getGcost()){
                        current =getOpen(i);
                    }
                }
                if(current == null){
                    System.out.print("We Found NO PATH");
                    pathFound = true;
                    frame2.repaint();
                    return;
                }
                if(current.getX() == end.getX() && current.getY() == end.getY()){
                    pathFound = true;
                    track_path(current);
                    running = false;
                    System.out.printf("WE DONE\n");
                    frame2.repaint();
                    
                    return;
                }
                removeOpen(current);
                addClosed(current);
               // System.out.printf("------------------------------------------------------------\n");
               // System.out.printf("Cuurent is X; %d and Y: %d", current.getX(), current.getY());
                getNeighbors(current,frame2);
                
               // System.out.printf("\nThe size of open is: %d\n", open.size());
                
                
                
               // System.out.printf("size of neighbor %d\n", neighbors.size());
                
                
                //System.out.printf("Open set\n");
                
                // for(int i = 0; i < open.size(); i ++){
                // System.out.printf("Current Open sets X: %d, and Y: %d\n", open.get(i).getX(),open.get(i).getY());
                
                // }
               // System.out.printf("The size of closed: %d", closed.size());
                
               // System.out.printf("------------------------------------------------------------\n");
                
                
                
                
                for(int i = 0; i < getNeighborList().size(); i ++){
                    
                    
                    if(!isNeighborClosed(getNeigh(i)) && !neighborNotWall(getNeigh(i))){
                        int tentG;
                        tentG = current.getGcost() + 25;
                        boolean newPath = false;
                        
                        if(isNeighborOpen(getNeigh(i))){
                            
                            
                            if(tentG < getNeigh(i).getGcost()){
                                getNeigh(i).setGcost(tentG);
                                newPath =true;
                            }
                            continue;
                        }
                        else if(!isNeighborOpen(getNeigh(i))){
                            getNeigh(i).setGcost(tentG);
                            newPath = true;
                            addOpen(getNeigh(i));
                            
                        }
                        if(newPath){
                            getNeigh(i).setHcost(heuristics(getNeigh(i),end));                              //set H cost
                            getNeigh(i).setFcost(getNeigh(i).getGcost() + getNeigh(i).getHcost());//set F cost
                            getNeigh(i).setParent(current);
                        }
                    }
                    
                    //System.out.printf("Current Neighbor X: %d, and Y: %d\n", neighbors.get(i).getX(),neighbors.get(i).getY());
                   // System.out.printf("Its FCost: %d and GCost: %d and HCost: %d\n", neighbors.get(i).getFcost(),neighbors.get(i).getGcost(),neighbors.get(i).getHcost());
                    
                }
                //System.out.printf("\nThe Current F cost : %d and H cost: %d and Gcost:%d\n", current.getFcost(),current.getHcost(),current.getGcost());
                // System.out.printf("\nThe lowest F cost: %d and H cost: %d and Gcost:%d\n", open.get(0).getFcost(),open.get(0).getHcost(), open.get(0).getGcost());
                sort.quickSort(open, 0, open.size() - 1);
                par = current;
                
            
            
       // }
    
    }
    public void AddWalls(Node wall){
        walls.add(wall);
        
    }
    public int getCols(Frame frame){
        
        int cols2;
        return cols2= frame.getHeight()/25;
        
    }
    public int getrows(Frame frame){
        
        int row;
        return row= frame.getWidth()/25;
        
    }
    public boolean isNeighborOpen(Node neighbor){
         for(int i = 0; i < open.size();i++){
            if(neighbor.getX() == open.get(i).getX() && neighbor.getY() == open.get(i).getY())
                return true;
        }
        return false;
        
    }
    public boolean isNeighborClosed(Node neighbor){
        for(int i = 0; i < closed.size();i++){
            if(neighbor.getX() == closed.get(i).getX() && neighbor.getY() == closed.get(i).getY())
                return true;
        }
        return false;
    }
    public boolean neighborNotWall(Node neighbor){
       
            
            for(int i = 0; i < walls.size();i++){
                if(neighbor.getX() == walls.get(i).getX() && neighbor.getY() == walls.get(i).getY())
                    return true;
            }
        
        return false;
    }
     public boolean IsWall(Node neighbor){
       
            int som = neighbor.getX() + 25;
          
            for(int i = 0; i < walls.size();i++){
                if(neighbor.getX()+25 == walls.get(i).getX() && neighbor.getY()+25== walls.get(i).getY())
                    return true;
            }
        
        return false;
    }
    public void getNeighbors(Node current, Frame frame3){
                int cols;
                int rows;
               
               neighbors = new ArrayList<>();
                cols = getCols(frame3);
                rows = getrows(frame3);
               
                int x = current.getX();
                int y = current.getY();

                if(x < frame3.getWidth() - 25){
                addNeigbors(new Node(x + 25, y));
                }
                if(x > 0){
                addNeigbors(new Node(x - 25, y));
                }
                if(y < frame3.getHeight() - 25){
                addNeigbors(new Node(x, y + 25));
                }
                if(y > 0){
                addNeigbors(new Node(x, y - 25));
                }
                if(x >0 && y >0){
                addNeigbors(new Node(x - 25, y - 25));
                }
                if((x < frame3.getWidth() - 25 && y >0)){
                 addNeigbors(new Node(x + 25, y - 25));   
                }
                if(x>0 && y < frame3.getHeight() - 25){
                 addNeigbors(new Node(x - 25, y + 25));   
                }
                if(x < frame3.getWidth()-25 && y < frame3.getHeight() - 25){
                    if(!IsWall(current)){
                      addNeigbors(new Node(x + 25, y + 25));      
                    }
                  
                }
            }
    
    public ArrayList<Node> track_path(Node current){
          Node temp = current;
         
       addPath(current);
      
        while(current.hasParent(temp)){
                addPath(temp.getParent());
               
                temp = temp.getParent();
            
        }
        return mainPath;
        
    }  
    public int heuristics(Node current, Node end){
        int x1 = current.getX();
        int x2 = end.getX();
        int y1 = current.getY();
        int y2 = end.getY();
        int distance;
        return distance =  Math.abs(x2 - x1) + Math.abs(y2 - y1);
        /*int HxDiff = Math.abs(endNode.getX() - openNode.getX());
		int HyDiff = Math.abs(endNode.getY() - openNode.getY());
		int hCost = HxDiff + HyDiff;
		openNode.setH(hCost);
        Math.abs(x2 - x1) + Math.abs(y2 - y1);*/
        
    }   
    public boolean isRunning(){
        return running;
    }
    public Node getPar(){
        return par;
    }
    public boolean thereBorder(){
        if(walls.isEmpty()){
               return false;
        }
        return true;
        
    }
    public boolean isDone(){
        return complete;
    }
    public void addOpen(Node node) {
		if (open.isEmpty()) {
			open.add(node);
		} else{
			open.add(node);
		}
	}
    public void addClosed(Node node) {
		if (closed.isEmpty()) {
			closed.add(node);
		} else{
			closed.add(node);
		}
	}
    public void addPath(Node node) {
		if (mainPath.isEmpty()) {
			mainPath.add(node);
		} else {
			mainPath.add(node);
		}
	}
    public void removeOpen(Node node) {
		for (int i = 0; i < open.size(); i++) {
			if (node.getX() == open.get(i).getX() && node.getY() == open.get(i).getY()) {
				open.remove(i);
			}
		}
	}
    public ArrayList<Node> getOpenList() {
		return open;
	}
     public ArrayList<Node> getNeighborList() {
		return neighbors;
	}
    public void addNeigbors(Node neighbor){
        if(neighbors.isEmpty()){
            neighbors.add(neighbor);
            
        }
        else{
            neighbors.add(neighbor);
        }
    }

	public Node getOpen(int location) {
            if(open.isEmpty()){
                return null;
            }
		return open.get(location);
	}
        public Node getNeigh(int index){
            return neighbors.get(index);
        }
	public ArrayList<Node> getClosedList() {
		return closed;
	}

	public ArrayList<Node> getPathList() {
		return mainPath;
	}
        public Node getOpenNode(int x, int y) {
		for (int i = 0; i < open.size(); i++) {
			if (open.get(i).getX() == x && open.get(i).getY() == y) {
				return open.get(i);
			}
		}
		return null;
	}
        public void reset() {
		while(open.size() > 0) {
			open.remove(0);
		}
		
		while(closed.size() > 0) {
			closed.remove(0);
		}
		
		while(mainPath.size() > 0) {
			mainPath.remove(0);
		}
		while(walls.size()>0){
                    walls.remove(0);
                }
		running = false;
		complete = false;
	}

        
}