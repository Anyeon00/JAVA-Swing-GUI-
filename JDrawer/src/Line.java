import java.awt.*;

public class Line extends TwoPointFigure{
    Line(){
        super();
    }
    Line(int x, int y){
        super(x, y);
    }
    Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }
    void draw(Graphics g) {
        g.drawLine(x1,y1,x2,y2);
    }

}
