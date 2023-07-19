import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Box extends TwoPointFigure{

    Box(){
        super();
    }
    Box(int x, int y){
        super(x, y);
    }

    Box(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }
    void draw(Graphics g) {
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        g.drawRect(minX, minY, width, height);

    }




}
