package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Color getBackgroundNonSelectionColor() {
        return (null);
    }

    @Override
    public Color getBackgroundSelectionColor() {
        return Color.GRAY;
    }

    @Override
    public Color getBackground() {
        return (null);
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        this.setLeafIcon(new Image("entity.png").getIcon());
        this.setClosedIcon(new Image("close.png").getIcon());
        this.setOpenIcon(new Image("open.png").getIcon());
        final Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        this.setTextNonSelectionColor(Color.LIGHT_GRAY);
        this.setFont(new Font("Arial", Font.PLAIN, 14));

        return component;
    }
}