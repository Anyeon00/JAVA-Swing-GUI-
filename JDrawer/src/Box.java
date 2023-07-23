import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Box extends TwoPointFigure{

    Box(Color color){
        super(color);
    }
    Box(Color color, int x, int y){
        super(color, x, y);
    }

    Box(Color color, int x1, int y1, int x2, int y2) {
        super(color, x1, y1, x2, y2);
    }
    void draw(Graphics g) {
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        g.setColor(color);
        g.drawRect(minX, minY, width, height);

    }
    Figure copy() {
        //upcasting 하위클래스 -> 상위클래스
        Box newBox = new Box(color, x1, y1, x2, y2);
        newBox.popup = popup;
        newBox.move(MOVE_DX, MOVE_DY);
        return newBox;
    }




}
