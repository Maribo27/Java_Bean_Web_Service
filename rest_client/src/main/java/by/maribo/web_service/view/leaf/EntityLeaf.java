package by.maribo.web_service.view.leaf;

import by.maribo.web_service.entity.Entity;

import javax.swing.tree.DefaultMutableTreeNode;

public class EntityLeaf {
    private DefaultMutableTreeNode node;
    private Entity entity;

    public EntityLeaf(Entity entity, boolean allowsChildren) {
        this.entity = entity;
        node = new DefaultMutableTreeNode(entity.getName(), allowsChildren);
    }

    public DefaultMutableTreeNode getNode() {
        return node;
    }

    public Entity getEntity() {
        return entity;
    }

    public String getName(){
        return entity.getName();
    }

    public String getDescription(){
        return entity.getDescription();
    }

    public String getTable() {
        return entity.getType();
    }
}
