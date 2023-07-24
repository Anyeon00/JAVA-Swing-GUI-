import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class OnePointFigure extends Figure{
    protected int GAP = 3;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;

    OnePointFigure(Color color){
        super(color);
    }
    OnePointFigure(Color color, int x, int y) {
        super(color);
        x1 = x;
        y1 = y;
    }

    void move(int dx, int dy) {
        x1 = x1 + dx; y1 = y1 + dy;
    }
    void makeRegion(){      //figure객체의 region을 정해주는 함수(figure에 커서올렸을때 figure인식범위)_ move, popup 실행시 사용
        if (x1 > x2) {
            int tmp = x1;
            x1= x2;
            x2 =  tmp;
        }
        if (y1 > y2) {
            int tmp = y1;
            y1= y2;
            y2 =  tmp;
        }

        int xpoints[] = new int[4];
        int ypoints[] = new int[4];

        xpoints[0] = x1 - GAP; ypoints[0] = y1 - GAP;
        xpoints[1] = x2 + GAP; ypoints[1] = y1 - GAP;
        xpoints[2] = x2 + GAP; ypoints[2] = y2 + GAP;
        xpoints[3] = x1 - GAP; ypoints[3] = y2 + GAP;

        region = new Polygon(xpoints, ypoints, 4);
    }
    void setXY2(int x, int y) {}
    int getX1(){
        return x1;
    }
    int getY1(){
        return y1;
    }
    void setX1(int x) {
        x1 = x;
    }
    void setY1(int y) {
        y1 = y;
    }
}
