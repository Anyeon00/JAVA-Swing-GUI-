import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Circle extends TwoPointFigure{
    private boolean fillFlag;
    Circle(Color color){
        super(color);
        fillFlag = false;
    }
    Circle(Color color, int x, int y){
        super(color, x, y);
    }

    Circle(Color color, int x1, int y1, int x2, int y2) {
        super(color, x1, y1, x2, y2);
    }
    boolean getFillFlag(){
        return fillFlag;
    }
    void setFillFlag(boolean flag){
        fillFlag = flag;
    }
    void setFill(){
        fillFlag = !fillFlag;
    }
    void draw(Graphics g) {
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        g.setColor(color);
        g.drawOval(minX, minY, width, height);

        if (fillFlag == true) {
            g.fillOval(minX, minY, width, height);
        }
    }
    Figure copy() {
        //upcasting 하위클래스 -> 상위클래스
        Circle newCirlce = new Circle(color, x1, y1, x2, y2);
        newCirlce.popup = popup;
        newCirlce.fillFlag = fillFlag;
        newCirlce.move(MOVE_DX, MOVE_DY);
        return newCirlce;
    }





}
