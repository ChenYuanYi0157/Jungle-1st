package view;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the Cell class,
 * but this class only cares how to draw Cells on ChessboardComponent
 * 这相当于 Cell 类，
 * *但这个类只关心如何在棋盘上绘制单元格组件
 */

public class CellComponent extends JPanel {
    private Color background;
//    颜色
    public CellComponent(Color background, Point location, int size) {
        setLayout(new GridLayout(1,1));
        setLocation(location);
        setSize(size, size);
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics g) {//Graphics是图形
        super.paintComponents(g);
        g.setColor(background);
        g.fillRect(1, 1, this.getWidth()-1, this.getHeight()-1);
    }
}
