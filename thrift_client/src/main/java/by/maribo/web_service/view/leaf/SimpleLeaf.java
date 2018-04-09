package by.maribo.web_service.view.leaf;

import javax.swing.tree.DefaultMutableTreeNode;

public class SimpleLeaf {
    private DefaultMutableTreeNode node;
    private String table;
    private String name;

    public SimpleLeaf(String name, String table) {
        this.table = table;
        this.name = name;
        node = new DefaultMutableTreeNode(name, true);
    }

    public DefaultMutableTreeNode getNode() {
        return node;
    }

    public String getName(){
        return name;
    }

    public String getTable() {
        return table;
    }
}