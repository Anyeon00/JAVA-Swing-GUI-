import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class TwoPointFigure extends Figure{
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;

    TwoPointFigure(){
        super();
    }
    TwoPointFigure(int x, int y) {
        super();
        x1 = x2 = x;
        y1 = y2 = y;
    }
    TwoPointFigure(int x1, int y1, int x2, int y2) {
        super();
        this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
    }
    void setXY2(int x, int y) {
        setX2(x);
        setY2(y);
    }
    int getX1(){
        return x1;
    }
    int getY1(){
        return y1;
    }
    int getX2(){
        return x2;
    }
    int getY2(){
        return y2;
    }

    void setX1(int x) {
        x1 = x;
    }

    void setY1(int y) {
        y1 = y;
    }

    void setX2(int x) {
        x2 = x;
    }

    void setY2(int y) {
        y2 = y;
    }
}
