import java.awt.*;

public class Line extends TwoPointFigure{
    Line(Color color){
        super(color);
    }
    Line(Color color, int x, int y){
        super(color, x, y);
    }
    Line(Color color, int x1, int y1, int x2, int y2) {
        super(color, x1, y1, x2, y2);
    }
    void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x1,y1,x2,y2);
    }
    Figure copy() {
        Line newLine = new Line(color, x1, y1, x2, y2);
        newLine.popup = popup;
        newLine.move(MOVE_DX, MOVE_DY);
        return newLine;
    }

}
