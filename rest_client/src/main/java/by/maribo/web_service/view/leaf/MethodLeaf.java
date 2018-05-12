package by.maribo.web_service.view.leaf;

import by.maribo.web_service.entity.Method;

import javax.swing.tree.DefaultMutableTreeNode;

public class MethodLeaf {
    private DefaultMutableTreeNode node;
    private Method method;

    public MethodLeaf(Method method, boolean allowsChildren) {
        this.method = method;
        node = new DefaultMutableTreeNode(method.getName(), allowsChildren);
    }

    public DefaultMutableTreeNode getNode() {
        return node;
    }

    public Method getMethod() {
        return method;
    }

    public String getName(){
        return method.getName();
    }

    public String getDescription(){
        return method.getDescription() + "\n Наличие: " + method.getNecessity();
    }
}
