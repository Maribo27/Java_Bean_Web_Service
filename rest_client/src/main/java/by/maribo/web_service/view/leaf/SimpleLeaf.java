package by.maribo.web_service.view.leaf;

import javax.swing.tree.DefaultMutableTreeNode;

public class SimpleLeaf {
    private DefaultMutableTreeNode node;
    private String name;
    private String table;

    public SimpleLeaf(String name) {
        this.name = name;
        node = new DefaultMutableTreeNode(name, true);
    }

    public DefaultMutableTreeNode getNode() {
        return node;
    }

    public String getName(){
        return name;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}