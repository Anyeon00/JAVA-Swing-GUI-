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
        JPopupMenu popupPtr = new JPopupMenu();     //이유모름 오류때문에 여기서도 popupMenu 추가해봄

        JMenuItem pointItem = new JMenuItem(view.getPointAction());
        popupPtr.add(pointItem);

/*
        JMenuItem boxItem = new JMenuItem("Box (B)");   //기능Item
        popupPtr.add(boxItem);
        boxItem.addActionListener((evt) ->{
            view.setWhatToDraw(DrawerView.ID_BOX);
        });
*/  //ActionAbstract사용전
/*
        JMenuItem boxItem = new JMenuItem(
                new SelectAction("Box (B)", new ImageIcon("box.gif"), view, DrawerView.ID_BOX)
        );
*/  //ActionAbastract사용후 1차버전 아래는 완성

        JMenuItem boxItem = new JMenuItem(view.getBoxAction());
        popupPtr.add(boxItem);

        JMenuItem lineItem = new JMenuItem(view.getLineAction());
        popupPtr.add(lineItem);

        JMenuItem circleItem = new JMenuItem(view.getCircleAction());
        popupPtr.add(circleItem);

        JMenuItem tvItem = new JMenuItem(view.getTVAction());
        popupPtr.add(tvItem);

        JMenuItem kiteItem = new JMenuItem(view.getKiteAction());
        popupPtr.add(kiteItem);
    }
}
