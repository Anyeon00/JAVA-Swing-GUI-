import javax.swing.*;
import java.awt.*;

public abstract class Figure {
    protected static int MOVE_DX = 20;
    protected static int MOVE_DY = 10;

    protected Polygon region;   //makeRegion()에서 region의 영역을 polygon으로 구현
    protected Popup popup;    //mouseRelease서 도형 우클릭시 figure.popup호출
    protected Color color;  //library에 Color라는 클래스가있음
    Figure(Color color){
        region = null;
        popup = null;
        this.color = color;
    }

    void setFill(){}
    void setColor(Color color) {
        this.color = color;
        region = null;
        popup = null;
    }

    void setPopup(Popup popup) {
        this.popup = popup;
    }
    void popup(JPanel view, int x, int y) {
        popup.popup(view, x, y);
    }
    abstract void draw(Graphics g); //추상클래스로도 만들어줘야됨
    //추상함수, 자식클래스들이 사용하지만 implement내용은 다른 경우, 또는 자식 클래스의 함수를 사용하기위해서,
    //나중에 자식 클래스의 draw가 dynamic binding되어 아래 drawing에서 실행됨
    abstract void setXY2(int x, int y); //5강 23분에는 이 추상함수 안적어줘서 drawing에 에러떠서 직접추가해줌 근데 교수님은 그냥 됨
    abstract void makeRegion();     //figure객체에 커서올렸을때 그 figure를 인식하는 범위를 만드는 함수_move, popup}
    abstract void move(int dx, int dy);
    abstract Figure copy();
    boolean contains(int x, int y){     //DrawerView MouseMove 에서 find()함수에서 사용하기 위해 만듦
        if (region == null) {
            return false;
        } else {
            return region.contains(x, y);
        }
    }

    void move(Graphics g, int dx, int dy) { //FIgure객체 move시 호출 함수
        draw(g);    // 움직이기 전 figure위치에 rubber banding pen으로 그려서 지워지는 효과
        move(dx, dy);   //이 함수는 TwoPointsFigure에서 구현되어야 함
        draw(g);

    }
    void drawing(Graphics g, int newX, int newY){   //마우스로 직접 그릴떄 사용하는, rubber banding하는 함수, newX = x2, newY = y2
        draw(g);
        setXY2(newX, newY);
        draw(g);
    }
    int getX1(){
        return -1;
    }
    int getY1(){
        return -1;
    }
    int getX2(){
        return -1;
    }
    int getY2(){
        return -1;
    }


}
