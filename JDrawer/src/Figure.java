import java.awt.*;

public abstract class Figure {
    Figure(){}
    abstract void draw(Graphics g); //추상클래스로도 만들어줘야됨
    //추상함수, 자식클래스들이 사용하지만 implement내용은 다른 경우, 또는 자식 클래스의 함수를 사용하기위해서,
    //나중에 자식 클래스의 draw가 dynamic binding되어 아래 drawing에서 실행됨
    abstract void setXY2(int x, int y); //5강 23분에는 이 추상함수 안적어줘서 drawing에 에러떠서 직접추가해줌 근데 교수님은 그냥 됨
    void drawing(Graphics g, int newX, int newY){   //마우스로 직접 그릴떄 사용하는, rubber banding하는 함수, newX = x2, newY = y2
        draw(g);
        setXY2(newX, newY);
        draw(g);
    }
}
