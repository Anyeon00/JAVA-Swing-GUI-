import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class SelectAction extends AbstractAction {
    private DrawerView view;
    public SelectAction(String name, Icon icon, DrawerView view, int id){
        putValue(Action.NAME, name);    //상위클래스에 가지고 있는 NAME에 arguement name넣어주면 자동으로 붙여짐
        putValue(Action.SMALL_ICON, icon);
        this.view = view;   //상위클래스에 없는 멤버이므로 직접 이 클래스에 멤버만들어서 저장
        putValue("id", id);
    }
    public void actionPerformed(ActionEvent e) {    //사용할 이벤트핸들러도 여기서 구현
        int id = (int) getValue("id");  //return typel object라서 type casting
        view.setWhatToDraw(id);
    }

}
