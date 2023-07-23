import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FigureDialog extends JDialog{  //DrawerFrame에서 MenuBar의 ToolBar의 modal선택시 띄워지는 dialog 작성 클래스
    static class DialogPanel extends JPanel implements ActionListener{   //이 Dialog에서 사용할 JPanel 생성 (아래 Dialog객체 생성하는게 먼저, 이 Panel만들기는 그다음)
        //이 Dialog에서 이뤄지는 작업은 이 panel안에서
        static int TOP_GAP = 30;
        static int LEFT_GAP = 40;
        static int LABEL_WIDTH = 40;
        static int HEIGHT = 30;
        static int RIGHT_GAP = LEFT_GAP + 20;
        static int FIRST_ROW = TOP_GAP;

        JTextField x1Field;
        JTextField y1Field;
        JTextField x2Field;
        JTextField y2Field;
        JComboBox<String> box;
        String[] figures = {"Box", "Line"};
        JDialog dialog;     //이판넬을 생성시킨 dialog를 cancel버튼으로 종료시키기위해 parameter로 dialog를 받기 위한 멤버
        DrawerView view;    //그리고 이 판넬에서 만든 정보대로 DrawerView판넬에 그려야하므로 DrawerView를 받아옴
        DialogPanel(JDialog dialog, DrawerView view){   //둘다 아래의 dialog클래스로부터 넘겨받음, dialog클래스는 dialog를 호출한 곳에서 넘겨받음
            this.view = view;
            this.dialog = dialog;
            //Jpanel은 내부component배치를 위한 layout객체를 가지고 있는데 layout객체 종류에 따라 component배치 스타일이 다름
            //아래처럼 setBounds()로 지정해주려면 null로 지정_Layout manager안쓰겠다(직접 좌표값 설정)
            //this.setLayout(null); label들 크기 지정을 안해줘서 일단 디폴트 layout객체 사용해서 보이도록 주석처리해놓은 것, 원래대로 하려면 크기 static매크로들 다 써주고 크기지정다 해주고 주석 풀기, 캡스톤프린트나 캡스톤5-2영상 참고


            JLabel x1Label = new JLabel("x1 : ");   //x1 Label생성    //label관련 캡스톤자바 5-2 Dialog메모 참고
            x1Label.setFont(new Font("Courier New", Font.BOLD, 16));    //x1Label 폰트설정
            x1Label.setBounds(40, 30, 40, 30);  //Label 좌표설정(x좌표,y좌표,width,height)
            add(x1Label);   //이 label을 Panel에 추가

            x1Field = new JTextField("0");  //x1 label의 입력칸 생성
            x1Field.setBounds(80, 30, 80, 30);
            x1Field.setHorizontalAlignment(JTextField.RIGHT);   //text alighn right설정
            add(x1Field);

            y1Field = new JTextField("0");  //x1 label의 입력칸 생성
            y1Field.setBounds(80, 30, 80, 30);
            y1Field.setHorizontalAlignment(JTextField.RIGHT);   //text alighn right설정
            add(y1Field);

            x2Field = new JTextField("0");  //x1 label의 입력칸 생성
            x2Field.setBounds(80, 30, 80, 30);
            x2Field.setHorizontalAlignment(JTextField.RIGHT);   //text alighn right설정
            add(x2Field);

            y2Field = new JTextField("0");  //x1 label의 입력칸 생성
            y2Field.setBounds(80, 30, 80, 30);
            y2Field.setHorizontalAlignment(JTextField.RIGHT);   //text alighn right설정
            add(y2Field);


            JComboBox<String> box = new JComboBox<String>(figures);
            add(box);

            JButton ok = new JButton("OK");
            add(ok);
            ok.addActionListener((ActionListener) this);    //이벤트핸들러를 생성자가 아닌 버튼에 추가하는 경우
            // 사용할 이벤트핸들러의 인터페이스의 argument로 그 이벤트 핸들러가 구현된 객체를 넘겨줌
            //근데 this는 ActionListner구현 객체기도 하지만 panel객체로도 쓰이고 있으므로
            // panel객체가 아닌 구현객체로서 사용하겠다는 의미로 typecasting

            JButton cancel = new JButton("Cancel");
            add(cancel);
            cancel.addActionListener((ActionListener) this);
            }
        private void onOK(){
            int x1,y1,x2,y2;
            //ComboBox.getSelectedItem() 쓰면 ComboBox선택내용 가져오는데 object type이므로 typecasting
            String selection = (String)(box.getSelectedItem());
            try{      //textField에 정수가 아닌 값 들어 올때 exception handling
                //TextField.getText()함수 쓰면 TextField에 쓰인 String을 읽어옴, 정수로 typecasting
                x1 = Integer.parseInt(x1Field.getText());
                y1 = Integer.parseInt(y1Field.getText());
                x2 = Integer.parseInt(x2Field.getText());
                y2 = Integer.parseInt(y2Field.getText());
            }catch(Exception ex){
                System.out.println("Invalid text field!! Try again!");
                return;     //그냥 ok처리안하고 리턴시키기
            }
            Figure newFigure = null;
            if (selection.equals("Box")) {
                newFigure = new Box(Color.BLACK, x1,y1,x2,y2);
                newFigure.setPopup(view.boxPopup());
            } else if (selection.equals("Line")) {
                newFigure = new Line(Color.BLACK, x1,y1,x2,y2);
                newFigure.setPopup(view.linePopup());
            }else {}
            view.addFigure(newFigure);  //view : DrawerView 판넬 (생성자에서 argument로 받아옴), 받아온 판넬에 여기서 만든 그림 추가하기위해
            x1Field.setText("0");
            y1Field.setText("0");
            x2Field.setText("0");
            y2Field.setText("0");
        }
        private void onCancel() {
            dialog.setVisible(false);
        }
        public void actionPerformed(ActionEvent event) {
            String name = event.getActionCommand();     //버튼선택시 버튼생성argument string에 따라 처리
            if (name.equals("OK")) {        //"OK"가 argument로 넘어가면서 만들어진 버튼 클릭시
                onOK();
            } else if (name.equals("Cancel")) {
                onCancel();
            }
        }
    }
    FigureDialog(String title, DrawerView view){     //argument : 해당 dialog의 title, view에대한 설명은 아래에
        super((JFrame)null, title);  //contructer에 해당 frame포인터와 title을 argument에 넣어야함
        setLocation(200,300);
        setSize(400, 500);

        //Dialog의 작업공간 생성 (JFrame에 JPanel생성해서 붙일때랑 마찬가지_DrawerFrame클래스 생성자의 Panel생성부분 참고
        Container container = getContentPane();
        //이 Dialog에서 쓸 Panel 생성, 해당 panel에서 cancel시 이 dialog객체 종료시키기 위해 parameter로 이 dialog 넘겨줌
        JPanel panel = new DialogPanel(this,view);  //그리고 넘겨받은 메인 판넬을 다시 이 dialog판넬에 넘겨줌(dilog판넬이 그려서 메인판넬에 넘겨주기위해)
        container.add(panel);               //이 Dialog의 작업공간Container에 사용할 panel만든거 붙이기
    }

}
