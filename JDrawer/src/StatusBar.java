import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StatusBar extends JPanel {

    JTextField position;    //마우스포지션
    JTextField figureType;  //그리고있는 도형
    JTextField viewSize;    //viewSize
    StatusBar(){
        setLayout(new BorderLayout());    //panel의 layout 바꾸기 _defalut값은 flow layout
        position = new JTextField("Position", 8);   //상태를 TextField를 이용해 출력
        position.setEditable(false);    //보여주는 용도의 TextField이므로 입력불가능하게
        position.setHorizontalAlignment(JTextField.CENTER); //text alighn
        figureType = new JTextField("Type", 8);
        figureType.setEditable(false);
        figureType.setHorizontalAlignment(JTextField.CENTER);
        viewSize = new JTextField("Size", 8);
        viewSize.setEditable(false);
        viewSize.setHorizontalAlignment(JTextField.CENTER);

        //내가만든 클래스아 라이브러리클래스 중복되는경우 라이브러리클래스 fullname사용
        javax.swing.Box box1 = javax.swing.Box.createHorizontalBox();    //Box layout manager
        box1.add(javax.swing.Box.createHorizontalStrut(20));    //Box와 Box사이의 간격
        box1.add(position);
        box1.add(javax.swing.Box.createHorizontalStrut(5));

        box1.add(figureType);
        setBorder(BorderFactory.createEtchedBorder());//panel의 경계선그리기(statusBar의 경계선그리기)

        javax.swing.Box box2 = javax.swing.Box.createHorizontalBox();
        box2.add(javax.swing.Box.createHorizontalStrut(20));
        box2.add(viewSize);

        add(box1,"West");   //box를 statusBar에 붙이기
        add(box2, "East");
    }
}
