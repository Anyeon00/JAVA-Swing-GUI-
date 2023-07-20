import javax.swing.*;

public class Popup {
    JPopupMenu popupPtr;    //여기서 사용할 popupMenu
    Popup(String title) {    //popup창 띄울 판넬
        JPopupMenu popupPtr;    //팝업메뉴창 생성
        popupPtr = new JPopupMenu();
        popupPtr.add(title);     //팝엄메뉴창 타이틀
        popupPtr.addSeparator();


    }
    public void popup(JPanel view, int x, int y){
        popupPtr.show(view, x, y);  //popup메뉴창 실행, argument : 실행할 판넬, 마우스 x y 좌표
    }
}
