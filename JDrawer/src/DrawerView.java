import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawerView extends JPanel      //이 판넬은 프레임에서 setWhatToDraw함수로 뭐그릴지 받아와서 해당 객체를 그림
        implements MouseListener, MouseMotionListener{      //JDrawer같은 큰 프로젝트의 경우 함수를 다 사용할 여지가 있음

    /*                                                        //1번방식처럼 JPanel에다가 직접 구현해줌
    int startX;
    int startY;
    int oldX;       //rubber banding에 필요 (마지막 좌표값 기억)
    int oldY;

     */
    public static int DRAW_BOX = 1;
    public static int DRAW_LINE = 2;

    public static int whatToDraw;   //프레임객체에서 setWhatToDraw함수로 뭐그릴지 받아온 값을 저장
    /*private Box pBox;
    private Box[] boxes = new Box[MAX];
    private int nBox = 0; // 배열 대신 ArrayList 사용
    private ArrayList<Box> boxes = new ArrayList<Box>();
    private Line pLine;
    private ArrayList<Line> lines = new ArrayList<Line>();*/    //상속을 이용해 전부 Figure라는 객체로 처리
    private Figure currentFigure;   //현재 작업중인 figure
    private ArrayList<Figure> figures = new ArrayList<Figure>();    //polymorphic container 다양한 걸 담는다
    DrawerView(){
        addMouseListener(this);     //등록
        addMouseMotionListener(this);    //등록
    }
    void setWhatToDraw(int figureType) {
        whatToDraw = figureType;
    }

    //paint event (화면이 나타나거나 갱신될때마다 호출되는 핸들러)
    public void paintComponent(Graphics g) {    //많이쓰이는 이벤트핸들러라서 인터페이스로 존재하지않고 상위클래스에 있음
        super.paintComponent(g);    //paintComponent함수 이용할때 반드시 써줘야함

        /*Box[] arr = (Box[])boxes.toArray(); //ArrayList를 array로 바꾸기
        for (int i = 0; i < arr.length; i++) {  // Collection객체 boxes에 저장된 box만큼 반복해서 draw()실행
            arr[i].draw(g);
        }*/ //아래는 foreach 문법을 이용하는 방법
        for (Figure pFigure : figures) {    // : 오른쪽엔 collection객체, 모든 객체들 travels
            pFigure.draw(g);    //이게 Dynamic Binding : reference의 type에 따라 알아서 draw가 호출됨
        }

    }
    public void mouseClicked(MouseEvent e){    }
    public void mousePressed(MouseEvent e) {
        if (whatToDraw == DRAW_BOX) {   //프레임객체에서 setWhatToDraw함수로 받아와 저장해놓은 whatToDraw에 따라 그릴 그림결정
            currentFigure = new Box();
        } else if (whatToDraw == DRAW_LINE) {
            currentFigure = new Line();
        }
    }
    public void mouseReleased(MouseEvent e){
        Graphics g = getGraphics();

        /* 함수화
        pBox.setX2(e.getX());
        pBox.setY2(e.getY());*/
        currentFigure.setXY2(e.getX(), e.getY());   //figure 객체의 x2, y2 access function(입력)
        currentFigure.draw(g);
         /*
            int minX = Math.min(boxes[i].x1, boxes[i].x2);
            int minY = Math.min(boxes[i].y1, boxes[i].y2);
            int width = Math.abs(boxes[i].x2 - boxes[i].x1);
            int height = Math.abs(boxes[i].y2 - boxes[i].y1);

            g.drawRect(minX, minY, width, height);
            여기까지 Box 그리는 행위를 Box의 Member Function으로 만들어주기
        */
        figures.add(currentFigure); //만든 Figure Collection객체에 넣기,   polymorphic collection객체
        currentFigure = null;   //만든 객체 figures에 넣어줬으니까 현재 작업중인 currentFigure은 비워주기
    }
    public void addFigure(Figure newFigure){    //dialog에서 만든 Figure 추가하는 함수
        figures.add(newFigure);
        repaint();  //paint component함수 다시 실행해줌
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseDragged(MouseEvent e) {
        Graphics g = getGraphics();
        g.setXORMode(getBackground());
        /*
        pBox.draw(g);   // x1,y1과 움직이기 직전의 x2,y2로 Background Color로 그리기(rubber banding)
        pBox.setXY2(e.getX(), e.getY());    //마우스 움직이자마자 x2,y2에 마우스의 새 좌표값 받아오기
        pBox.draw(g);   //저장돼있는 x1,y1과 새로받은 x2,y2로 그리기
        를 함수로 만들어 사용*/
        currentFigure.drawing(g, e.getX(), e.getY()); //rubber banding 해서 그리는 함수

        /*      rubber banding 원시적인 방법
        int minX = Math.min(startX, oldX);
        int minY = Math.min(startY, oldY);
        int width = Math.abs(oldX - startX);
        int height = Math.abs(oldY - startY);
        Graphics g = getGraphics();
        g.setColor(getBackground());
        g.drawRect(minX,minY,width,height);

        int endX = e.getX();
        int endY = e.getY();

        minX = Math.min(startX, endX);
        minY = Math.min(startY, endY);
        width = Math.abs(endX - startX);
        height = Math.abs(endY - startY);
        g.setColor(Color.black);
        g.drawRect(minX , minY, width, height);

        oldX = endX;
        oldY = endY;
*/
    }
    public void mouseMoved(MouseEvent e) {

    }
}
