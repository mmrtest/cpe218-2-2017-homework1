

import java.util.Stack;

public class Homework1 {

    
    public static String input;
    public static node tree = new node();
    public static void main(String[] args) {
        // TOD.O code application logic here
        //if (args.length > 0) {
	    //String in = args[0];
        String in ="251-*32*+";
        input = in;


        //System.out.println(in.length());

        tree = infix(tree);
        inorder(tree);  
        int ans = calculate(tree);
        System.out.print("=" + ans);
        GUI.main(args);
        //}
    }
    
    public static node infix(node tree) {
        Stack e = new Stack();
        for(int i=0;i<input.length();i++)
        {
            node x = new node(input.charAt(i));
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
        tree = (node) e.pop();
        return tree;
        
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
                switch(x.data){
                    case '+':
                        sum = calculate(x.left)+calculate(x.right);   
                        break;
                    case '-':
                        sum = calculate(x.left)-calculate(x.right);   
                        break;
                    case '*':
                        sum = calculate(x.left)*calculate(x.right); 
                        break;
                    case '/':
                        sum = calculate(x.left)/calculate(x.right); 
                        break;
                }
                
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
