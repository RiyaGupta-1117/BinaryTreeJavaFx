/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import java.awt.Color;
import java.awt.Graphics;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Khushi
 */

class Node                          // Class For a single node string Value, color of the node, x-y coordinates of each Node in the binary tree
{
    int data;
    int x, y;
    Node left;
    Node right;
    Color c;
    public Node()
    {
        x = 960;
        y = 100;
        left = null;
        right = null;
        c = Color.WHITE;
    }
}

class BST {
     Node root;
    int hsep = 50, vsep = 80;
    
    BST()
    {
        root = null;
    }
    
    private void makeLeftAdjustments(Node r, int parentx, int parenty) // Magic function for left tree
    {
        if (r == null)
        {
            return;
        }
        
        makeLeftAdjustments(r.right, parentx, r.y);
        
        r.x = parentx - (Vertices(r.right) + 1) * hsep;
        r.y = parenty + vsep;
        
        makeLeftAdjustments(r.left, r.x, r.y);
        
    }
    
    
    private void makeRightAdjustments(Node r, int parentx, int parenty) // Magic function for right tree
    {
        if(r == null)
        {
            return;
        }
        
        makeRightAdjustments(r.left, parentx, r.y);
        
        r.x = parentx + (Vertices(r.left) + 1) * hsep;
        r.y = parenty + vsep;
        
        makeRightAdjustments(r.right, r.x, r.y);
        
    }
    
    private Node InsertWithR(Node r, int data) //Insert the node in the respective position according to the data specified
    {
        if (r == null)
        {
            r = new Node();
            r.data = data;
        }
        else if(data < r.data)
        {
            r.left = InsertWithR(r.left, data);
        }
        else
        {
            r.right = InsertWithR(r.right, data);
        }
        return r;
    }
    
    private int HeightWithR(Node r)  // Function for calculating the Height of the tree With the r as the root node
    { 
        if (r == null) 
            return 0; 
        else 
        { 
            int left = HeightWithR(r.left); 
            int right = HeightWithR(r.right); 
   
            if (left > right) 
                return (left + 1); 
             else 
                return (right + 1); 
        } 
    } 
    
    private int Vertices(Node r) // Function for calculating the no. of vertices with a Node as a parameter
    {
        if (r == null) 
            return 0; 
        else 
        { 
            return HeightWithR(r.left) + HeightWithR(r.right) + 1; 
        } 
    }
    
    private Node MaxValue(Node r) // Function for finding the max value of the tree under the Node passed
    {
        Node cur = r;
        while(cur != null && cur.right != null)
        {
            cur = cur.right;
        }
        return cur;
    }
    
    private Node MinValue(Node r) // Function for finding the min value of the tree under the Node passed
    {
        Node cur = r;
        while(cur != null && cur.left != null)
        {
            cur = cur.left;
        }
        return cur;
    }

    private int SearchWithR(Node r, int data) // Function for finding the Node which has the value equal to data
    {
        int l = 0;
        if (r == null)
        {
            return 0;
        }
        if (r.data == data)
        {
            r.c = Color.GREEN;
            return 1;
        }
        else if(data < r.data)
        {
            return SearchWithR(r.left, data);
        }
        else
        {
            return SearchWithR(r.right, data);
        }
    }
    
    private String InorderWithR(Node r) // This functions returns a string which is the inorder traversal of the tree
    {
        String s = "";
        if (r == null)
        {
            return ""; 
        }
        
        s = s.concat(InorderWithR(r.left));
        s = s.concat(Integer.toString(r.data) + " ");
        s = s.concat(InorderWithR(r.right));
        
        return s;
    }
    
    
    
    private Node DeleteWithR(Node r, int data) // This function deletes the Node which has the value as data
    {  
        
        if(r == null)
        {
            return null;
        }
        
        if(data < r.data)
        {
            r.left = DeleteWithR(r.left, data);
        }
        
        else if(data > r.data)
        {
            r.right = DeleteWithR(r.right, data);
        } 
        
        else
        {
            if (r.left == null && r.right == null)
            {
                return null;
            }
            
            else if (r.right == null)
            {
                Node temp = MaxValue(r.left);
                r.data = temp.data;
                r.left = DeleteWithR(r.left, temp.data);
            }
            
            else
            {
                Node temp = MinValue(r.right);
                r.data = temp.data;
                r.right = DeleteWithR(r.right, temp.data);    
            }
        }
        return r;
    }

    private Node refreshColorWithR(Node r) // This function refreshes the color for each vertex
    {
        if (r == null)
        {
            return null;
        }
        
        refreshColorWithR(r.left);
        r.c = Color.CYAN;
        refreshColorWithR(r.right);
       
        return r;
    }
    
    private void makeAdjustments(Node r) // This is where the magic happens
    {
        if (r == null) return;
        makeLeftAdjustments(r.left, r.x, r.y);
        makeRightAdjustments(r.right, r.x, r.y);
    }
    
    private void DisplayWithR(Node r, Graphics g) // This functions is used to display the Nodes with respect to their x, y positions
        {
            String s = "";
            
            if (r == null)
            {
                return;
            }
        
            if (r.left != null)
            {
                g.setColor(Color.BLACK);
                g.drawLine(r.x + 30, r.y + 30, r.left.x + 30, r.left.y + 30);
            }
            
            DisplayWithR(r.left, g);
            
            
            if (r.right != null)
            {
                g.setColor(Color.BLACK);
                g.drawLine(r.x + 30, r.y + 30, r.right.x + 30, r.right.y + 30);
            }
            
            g.setColor(Color.BLACK);
            g.fillOval(r.x,r.y,60,60);
            g.setColor(r.c);
            g.fillOval(r.x+5,r.y+5,50,50);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(r.data), r.x + 25, r.y + 35);
            
            
            DisplayWithR(r.right, g);       
    }
    
    // The functions defined under are defined so that they can be called from the GUI class
    
    public void Display(Graphics g)
    {
        DisplayWithR(root, g);
    }
    
    public void refreshColor()
    {
        refreshColorWithR(root);
    }
    
    public void refresh() 
    {
        makeAdjustments(root);
    }
    
    public void Insert(int data)
    {
        if (SearchWithR(root, data) == 0)
        root = InsertWithR(root, data);
    }
    
    public int Height()
    {
        return HeightWithR(root);
    }
    
    public int Novertices()
    {
        return Vertices(root);
    }
    
    public int Search(int data)
    {
        return SearchWithR(root, data);
    }

    public String Inorder()
    {
        String s = InorderWithR(root);
        return s;
    }
    
    public void Delete(int data)
    {
        if (SearchWithR(root, data) == 1)
        root = DeleteWithR(root, data);
    }


}
public class JavaFXApplication5 extends Application {
    
    BST bst = new BST();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Binary Search Tree");
    }
    


    public static void main(String[] args) {
        Application.launch(JavaFXApplication5.class ,args);
    }
    
}
