package view;


import Model.PlayerColor;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class ElephantChessComponent extends JComponent {
    private PlayerColor owner;

    private boolean selected;
    int rank;

    public ElephantChessComponent(int rank,PlayerColor owner, int size) {
        this.rank=rank;
        this.owner = owner;
        this.selected = false;
        setSize(size/2, size/2);
        setLocation(0,0);
        setVisible(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("楷体", Font.PLAIN, getWidth() / 2);
        g2.setFont(font);
        g2.setColor(owner.getColor());
        if (rank==8){
        g2.drawString("象", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.
        if (rank==7){
            g2.drawString("狮", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.
        if (rank==6){
            g2.drawString("虎", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.
        if (rank==5){
            g2.drawString("豹", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.
        if (rank==4){
            g2.drawString("狼", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.
        if (rank==3){
            g2.drawString("狗", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.
        if (rank==2){
            g2.drawString("猫", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.
        if (rank==1){
            g2.drawString("鼠", getWidth() / 4, getHeight() * 5 / 8); }// FIXME: Use library to find the correct offset.

        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
