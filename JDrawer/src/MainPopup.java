import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPopup extends Popup{    //Figure객체간 공유하는 우클릭시 Popup클래스
    /*
    DrawerView view;   //Popup창 띄울 판넬 _DrawerView
    JPopupMenu popupPtr;    //여기서 사용할 popupMenu_우클릭시 젤 첨 생성되는 메뉴창
    */        //얘네는 한번더 상위 클래스에 올려줌

    MainPopup(DrawerView view){
        /*
        JPopupMenu popupPtr;    //팝업메뉴창 생성
        popupPtr = new JPopupMenu("그림");

        popupPtr.add("그림");     //글자Item
        popupPtr.addSeparator();
        */      //이것도 위 클래스로 올리기
        super("그림");

        JMenuItem pointItem = new JMenuItem("Point (P)");
        popupPtr.add(pointItem);
        pointItem.addActionListener((evt) ->{
            view.setWhatToDraw(DrawerView.DRAW_POINT);
        });

        JMenuItem boxItem = new JMenuItem("Box (B)");   //기능Item
        popupPtr.add(boxItem);
        boxItem.addActionListener((evt) ->{
            view.setWhatToDraw(DrawerView.DRAW_BOX);
        });

        JMenuItem lineItem = new JMenuItem("Line (L)");
        popupPtr.add(lineItem);
        lineItem.addActionListener((evt) ->{
            view.setWhatToDraw(DrawerView.DRAW_LINE);
        });

        JMenuItem circleItem = new JMenuItem("Circle (C)");
        popupPtr.add(circleItem);
        circleItem.addActionListener((evt) ->{
            view.setWhatToDraw(DrawerView.DRAW_CIRCLE);
        });
    }
}
