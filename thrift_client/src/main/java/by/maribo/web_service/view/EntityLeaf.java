package by.maribo.web_service.view;

import by.maribo.web_service.entity.Entity;

import javax.swing.tree.DefaultMutableTreeNode;

public class EntityLeaf {
    private DefaultMutableTreeNode node;
    private Entity entity;

    EntityLeaf(Entity entity, boolean allowsChildren) {
        this.entity = entity;
        node = new DefaultMutableTreeNode(entity.getName(), allowsChildren);
    }

    DefaultMutableTreeNode getNode() {
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
}
