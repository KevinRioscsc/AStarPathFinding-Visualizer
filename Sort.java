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
import java.io.*; 
import java.util.List; 
import java.util.ArrayList; 

import java.lang.Math; 
import java.util.Collections;


public class Sort {
    
    public void quickSort(ArrayList<Node> list, int low, int high){
        if(low < high){
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);  // Before pi
            quickSort(list, pi + 1, high); // After pi
        }
        
    }
    public int partition(ArrayList<Node> list, int low, int high){
        int pivot;
        int index;
        pivot = list.get(high).getFcost();
        index = (low - 1);
        
        for(int i = low; i <= high - 1; i++){
            if(list.get(i).getFcost() < pivot){
                index++;
                Collections.swap(list, index, i);
               
                
            }
            
            
        }
        Collections.swap(list, index + 1, high);
        
        
        return (index + 1);
        
        
        
    }
    
     public static void main(String[] args) {
        
          ArrayList<Node> nodes = new ArrayList<>();
          
          
            for(int i = 0; i < 25; i++){
                int rand = (int)(Math.random() * 50 ); 
                Node n = new Node(0,1);
                n.setFcost(rand);
                nodes.add(n);
                System.out.printf("The array is: %d\n", rand);
                
            }
            
            Sort som = new Sort();
            
            som.quickSort(nodes, 0, nodes.size()-1);
            System.out.printf("---------------------------------------");
             System.out.printf("size of nodes: %d", nodes.size());
            
            for(int i = 0; i < nodes.size(); i++){
                System.out.printf("\nSorted: %d\n", nodes.get(i).getFcost());
            }
            
            
            
            
            
             
        
    }
}
