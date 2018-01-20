/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Stack;

/**
 *
 * @author WINDOWS10
 */
public class Homework1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TOD.O code application logic here
        
        String in ="251-*32*+";
        //System.out.println(in.length());
        node tree = (node) infix(in);
        inorder(tree);  
        int ans = calculate(tree);
        System.out.print("=" + ans);    
    }
    
    public static Object infix(String in) {
        Stack e = new Stack();
        for(int i=0;i<in.length();i++)
        {
            node x = new node(in.charAt(i));
            if(e.empty()||(x.data!='+'&&x.data!='-'&&x.data!='*'&&x.data!='/'))
            {
                e.push(x);
            }
            else
            {
                node n1 =  (node) e.pop();
                node n2 =  (node) e.pop();
                x.left = n2;
                x.right = n1;
                n1.parent = x;
                n2.parent = x;
                e.push(x);
            }
        }
        
        return e.pop();
        
    }
    
    public static void inorder(node x){
        if(x==null){              
        return;
        }
        else{
                inorder(x.left); 
                if(x.parent == null||x.parent.parent==null)
                {  
                }
                else if(x==x.parent.left)
                {
                    System.out.print("(");
                }
                System.out.print(x.data);
                inorder(x.right);
                if(x.parent == null||x.parent.parent==null)
                {  
                }
                else if(x==x.parent.right)
                {
                    System.out.print(")");
                }
            }
        }
        public static int calculate(node x){
            int sum = 0;
            if((x.right.data=='+'||x.right.data=='-'||x.right.data=='*'||x.right.data=='/')&&(x.left.data=='+'||x.left.data=='-'||x.left.data=='*'||x.left.data=='/'))
            {
                sum+=calculate(x.right); 
                sum+=calculate(x.left); 
            }
            else if(x.right.data=='+'||x.right.data=='-'||x.right.data=='*'||x.right.data=='/')
            {
                sum+=calculate(x.right); 
                switch(x.data){
                    case '+':
                        sum = Character.getNumericValue(x.left.data)+sum;   
                        break;
                    case '-':
                        sum = Character.getNumericValue(x.left.data)-sum;   
                        break;
                    case '*':
                        sum = Character.getNumericValue(x.left.data)*sum;
                        break;
                    case '/':
                        sum = Character.getNumericValue(x.left.data)/sum;
                        break;
                }
            }
            else if(x.left.data=='+'||x.left.data=='-'||x.left.data=='*'||x.left.data=='/')
            {
                sum+=calculate(x.left); 
                switch(x.data){
                    case '+':
                        sum = sum+Character.getNumericValue(x.right.data);   
                        break;
                    case '-':
                        sum = sum-Character.getNumericValue(x.right.data);   
                        break;
                    case '*':
                        sum = sum*Character.getNumericValue(x.right.data);
                        break;
                    case '/':
                        sum = sum/Character.getNumericValue(x.right.data);
                        break;
                }
            }
            else
            {
                switch(x.data){
                    case '+':
                        sum = Character.getNumericValue(x.left.data)+Character.getNumericValue(x.right.data);   
                        break;
                    case '-':
                        sum = Character.getNumericValue(x.left.data)-Character.getNumericValue(x.right.data);   
                        break;
                    case '*':
                        sum = Character.getNumericValue(x.left.data)*Character.getNumericValue(x.right.data);
                        break;
                    case '/':
                        sum = Character.getNumericValue(x.left.data)/Character.getNumericValue(x.right.data);
                        break;
                }
            }
            return sum;   
        }

}
