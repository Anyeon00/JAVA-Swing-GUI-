import java.awt.*;

public class TV extends TwoPointFigure {
    private boolean fillFlag;
    TV(Color color, boolean antennaFlag){
        super(color);
        fillFlag = false;
    }
    TV(Color color, int x, int y, boolean antennaFlag){
        super(color, x, y);
    }

    TV(Color color, int x1, int y1, int x2, int y2, boolean antennaFlag) {
        super(color, x1, y1, x2, y2);
    }
    void setFill(){
        fillFlag = !fillFlag;
    }
    void pressPowerButton(){
/*
        screen.setFill();
        powerButton.setFill();
*/
    }
    void draw(Graphics g) {
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        g.setColor(color);
        g.drawRect(minX, minY, width, height);

        if (fillFlag == true) {
            g.fillRect(minX, minY, width, height);
        }
    }
    Figure copy() {
        //upcasting 하위클래스 -> 상위클래스
        TV newBox = new TV(color, x1, y1, x2, y2, true);
        newBox.popup = popup;
        newBox.fillFlag = fillFlag;
        newBox.move(MOVE_DX, MOVE_DY);
        return newBox;
    }





}
