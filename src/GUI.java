

import javax.swing.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;

public class GUI extends JPanel
        implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;


    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
    public static node root = Homework1.tree;

    public GUI() {
        super(new GridLayout(1,0));
        //node root = Homework1.tree;
        //Create the nodes.
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode(root.data);
        createNodes(top,root);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        JScrollPane htmlView = new JScrollPane(htmlPane);

        ImageIcon leafIcon = createImageIcon("middle.gif");
        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer =
                    new DefaultTreeCellRenderer();
            renderer.setOpenIcon(leafIcon);
            renderer.setClosedIcon(leafIcon);
            tree.setCellRenderer(renderer);
        }

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(300, 200);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(200);
        splitPane.setPreferredSize(new Dimension(800, 500));

        //Add the split pane to this panel.
        add(splitPane);
    }


    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


    /** Required by TreeSelectionListener interface. */
    public static DefaultMutableTreeNode checkRoot;
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();
        checkRoot = node;
        String ans = GetString(node);
        //System.out.println(""+Getans(node));
        if(!node.isLeaf())
        {
            ans += "=" +Getans(node);
        }

        Getans(node);


        htmlPane.setText(ans);


    }

    public String GetString(DefaultMutableTreeNode node)
    {
        String show = "";
        if (node == null) return null;
        else
        {
            if(node.isLeaf())
            {
                show += node.toString();
            }
            else
            {
                DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) node.getChildAt(0);
                //System.out.println(node1.toString());
                if(node1.getParent()== null||node1.getParent().getParent()==null||node1.getParent()==checkRoot)
                {
                }
                else if(node1==node1.getParent().getChildAt(0))
                {
                    show +="(";
                }
                show += GetString(node1);

                show += node.toString();
                node1 = (DefaultMutableTreeNode) node.getChildAt(1);
                //System.out.println(node1.toString());

                show += GetString(node1);
                if(node1.getParent()== null||node1.getParent().getParent()==null||node1.getParent()==checkRoot)
                {
                }
                else if(node1==node1.getParent().getChildAt(1))
                {
                    show +=")";
                }
            }

        }
        return show;
    }

    public int Getans(DefaultMutableTreeNode node)
    {
        char checkO = node.toString().charAt(0);
        if(checkO=='+'||checkO=='-'||checkO=='*'||checkO=='/')
        {
            switch (checkO){
                case '+': return Getans((DefaultMutableTreeNode) node.getChildAt(0))+Getans((DefaultMutableTreeNode) node.getChildAt(1));
                case '-': return Getans((DefaultMutableTreeNode) node.getChildAt(0))-Getans((DefaultMutableTreeNode) node.getChildAt(1));
                case '*': return Getans((DefaultMutableTreeNode) node.getChildAt(0))*Getans((DefaultMutableTreeNode) node.getChildAt(1));
                case '/': return Getans((DefaultMutableTreeNode) node.getChildAt(0))/Getans((DefaultMutableTreeNode) node.getChildAt(1));
            }
        }
        return Integer.parseInt(node.toString());
    }




    private void createNodes(DefaultMutableTreeNode top,node root) {
        DefaultMutableTreeNode left = new DefaultMutableTreeNode(root.left.data);
        DefaultMutableTreeNode right = new DefaultMutableTreeNode(root.right.data);
        top.add(left);
        top.add(right);
        if(root.left.left!=null)
        {
            createNodes(left,root.left);
        }
        if(root.right.right!=null)
        {
            createNodes(right,root.right);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }






    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}