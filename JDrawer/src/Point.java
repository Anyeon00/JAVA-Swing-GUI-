import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Point extends OnePointFigure{
    Point(Color color){
        super(color);
    }
    Point(Color color, int x, int y){
        super(color, x, y);
    }
    void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(x1-GAP, y1-GAP,2*GAP, 2*GAP);
        g.fillOval(x1-GAP, y1-GAP,2*GAP, 2*GAP);

    }
    Figure copy() {
        //upcasting 하위클래스 -> 상위클래스
        Point newPoint = new Point(color, x1, y1);
        newPoint.popup = popup;
        newPoint.move(MOVE_DX, MOVE_DY);
        return newPoint;
    }
}
