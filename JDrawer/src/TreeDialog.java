import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class TreeDialog extends JDialog {
    static class MyTreeNode {
        Object userObject;
        ArrayList<MyTreeNode> children;
        MyTreeNode(Object data) {
            userObject = data;
            children = new ArrayList<MyTreeNode>();
        }
        public String toString() {
            return userObject.toString();
        }
        public Object getUserObject() {
            return userObject;
        }
        public void add(MyTreeNode child) {
            children.add(child);
        }
        public MyTreeNode getChildAt(int index) {
            return children.get(index);
        }
        public int getChildCount() {    //child가 몇개니
            return children.size();
        }
        public boolean isLeaf() {
            if (children.size() == 0) return true;
            else return false;
        }
        public int getIndex(MyTreeNode child) { //child 가 몇번째자식이냐
            return children.indexOf(child);
        }
    }
    static class FigureTreeModel implements TreeModel
    {
        DrawerView view;
        ArrayList<Figure> figures;
        MyTreeNode root = null;

        FigureTreeModel(DrawerView view) {
            this.view = view;
            figures = view.getFigures();
            constructTree();
        }
        public void constructTree() {
            root = new MyTreeNode("Figure");
            ArrayList<String> names = new ArrayList<String>();
            int length = DrawerView.figureType.length;
            MyTreeNode nodes[] = new MyTreeNode[length];
            for(int i = 0; i < length; i++) {
                String name = DrawerView.figureType[i];
                names.add(name);
                MyTreeNode node = new MyTreeNode(name);
                nodes[i] = node;
                root.add(node);
            }
            for(Figure ptr : figures) {
                String figureTypeName = ptr.getClass().getName();
                int index = names.indexOf(figureTypeName);
                nodes[index].add(new MyTreeNode(ptr));
            }
        }
        public Object getRoot() {   //너 루트가 누구니
            return root;
        }
        public Object getChild(Object parent,int index) {   //parent의 index번째 자식객체는 누구니
            MyTreeNode pNode = (MyTreeNode)parent;
            return pNode.getChildAt(index);
        }
        public int getChildCount(Object parent) {   //루트야 너 차일드가 몇개니
            MyTreeNode pNode = (MyTreeNode)parent;
            return pNode.getChildCount();
        }
        public boolean isLeaf(Object node) {
            MyTreeNode pNode = (MyTreeNode)node;
            return pNode.isLeaf();
        }
        public int getIndexOfChild(Object parent,Object child) {
            MyTreeNode pNode = (MyTreeNode)parent;
            MyTreeNode cNode = (MyTreeNode)child;
            return pNode.getIndex(cNode);
        }
        public void valueForPathChanged(TreePath path,Object newValue) {
        }
        public void addTreeModelListener(TreeModelListener l) {
        }
        public void removeTreeModelListener(TreeModelListener l) {
        }
    }
    static class FigureTree extends JTree
    {
        FigureTreeModel model;
        FigureTree(DrawerView view) {
            super();
            model = new FigureTreeModel(view);
            setModel(model);
        }
        public void updateUI() {
            if (model != null) model.constructTree();
            super.updateUI();
            setExpandedState(getPathForRow(0),true);
            int length = DrawerView.figureType.length;
            for(int i = length; i > 0; i--) {
                setExpandedState(getPathForRow(i),true);
            }
        }
    }
    static class DialogPanel extends JPanel implements ActionListener
    {
        JDialog dialog;
        DrawerView view;
        JButton done;
        JButton remove;
        FigureTree tree;

        DialogPanel(JDialog dialog,DrawerView view) {
            this.view = view;
            this.dialog = dialog;
            setLayout(new BorderLayout());

            tree = new FigureTree(view);
            JScrollPane sp = new JScrollPane(tree);
            add(sp,BorderLayout.CENTER);

            JPanel bottom = new JPanel();
            bottom.add(remove = new JButton("Remove"));
            bottom.add(done = new JButton("Done"));
            add(bottom,BorderLayout.SOUTH);

            remove.addActionListener(this);
            done.addActionListener(this);
        }
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == done)
            {
                dialog.setVisible(false);
            } else if (event.getSource() == remove)
            {
                TreePath selectedPath = tree.getSelectionPath();
                if (selectedPath == null) return;

                DefaultMutableTreeNode selectedNode
                        = (DefaultMutableTreeNode)selectedPath.getLastPathComponent();
                Object selectedObject = selectedNode.getUserObject();
                if (selectedObject instanceof Figure)
                {
                    view.removeFromFigures((Figure)selectedObject);
                    updateUI();
                }
            }
        }
        public void updateUI() {
            if (tree != null) tree.updateUI();
        }
    }

    TreeDialog(String title,DrawerView view) {
        super((JFrame)null,title);
        setLocation(200,300);
        setSize(400,300);

        Container container = getContentPane();
        JPanel panel = new DialogPanel(this,view);
        container.add(panel);

        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                panel.updateUI();
            }
        });
    }
}
