import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DrawerFrame extends JFrame {   //DRawerFrame 컴포넌트
    DrawerView canvas;    //JPanel 상속받아 만든 Panel객체
    StatusBar statusBar;
    DrawerFrame(){
        setTitle("Drawer");
        Toolkit tk = Toolkit.getDefaultToolkit();   //컴포넌트사이즈 설정(고급자바내용)
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setSize(screenWidth * 2 / 3, screenHeight * 2 / 3);
        setLocation(screenWidth/6, screenHeight/6);
        Image img = tk.getImage("ball.gif");    //컴포넌트 상단 아이콘, toolkit에서 제공함수
        setIconImage(img);

        //Panel생성
        Container container = new Container();  //Content Pane(Frame의 부속객체인 작업공간) 생성
        canvas = new DrawerView();    //Panel 생성
        container.add(canvas,"Center");           //Content Pane에 Panel 붙이기
        statusBar = new StatusBar();
        container.add(statusBar, "South");

        //MenuBar - Menu - MenuItem
        JMenuBar menus = new JMenuBar();    //MenuBar 생성
        this.setJMenuBar(menus);    //이 프레임객체에 menu(MenuBar)붙이기

        //FileMenu 작성시작
        JMenu fileMenu = new JMenu("파일(F)"); //Menu생성
        menus.add(fileMenu);    //menus에(MenuBar)에 fileMenu(Menu) 붙이기

        JMenuItem newFile = new JMenuItem("새 파일(N)");   //MenuItem생성
        fileMenu.add(newFile);  //fileMenu(Menu)에 newFile(MenuItem) 붙이기
        newFile.setMnemonic('N');   //단축키 설정
        newFile.setIcon(new ImageIcon("new.gif"));  //newFile(MenuItem) 이미지아이콘생성
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));   //Accelerator 설정
        newFile.addActionListener(new ActionListener() {    //람다 익스프레션, 새파일 버튼 클릭시 실행
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("새파일(N)");
            }
        });

        JMenuItem openFile = new JMenuItem("열기 (O)");
        fileMenu.add(openFile);
        openFile.addActionListener( (e) ->     //새파일 버튼 클릭시, 람다 익스프레션 2_위 newFile(MenuItem)에서
                        // 이미 ActionListener인터페이스와 actionPerformed함수를 사용했기 때문에 해당 문법으로 재사용
                {
                    System.out.println("열기 (O)");
                    System.out.println("Hello");
                }
        );

        JMenuItem saveFile = new JMenuItem("저장 (S)");
        fileMenu.add(saveFile);

        JMenuItem anotherFile = new JMenuItem("다른 이름으로 저장(A)");
        fileMenu.add(anotherFile);

        fileMenu.addSeparator();    //구분선 추가

        JMenuItem exit = new JMenuItem("종료 (X)");
        fileMenu.add(exit);
        exit.addActionListener(new ActionListener() {    //람다 익스프레션, 새파일 버튼 클릭시 실행
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //FileMenu 작성완료
        //figure선택 메뉴 작성시작  (Figure선택해서 그리기)
        JMenu figureMenu = new JMenu("그림 (F)");
        menus.add(figureMenu);

        JMenuItem figurePoint = new JMenuItem("Point (P)");
        figureMenu.add(figurePoint);
        figurePoint.addActionListener((e) ->
        {
            canvas.setWhatToDraw(DrawerView.DRAW_POINT);
        });
        JMenuItem figureBox = new JMenuItem("Box (B)");
        figureMenu.add(figureBox);
        figureBox.addActionListener((e) ->
        {
            canvas.setWhatToDraw(DrawerView.DRAW_BOX);  //canvas는 Panel객체 레퍼런스(를상속받은class, DrawerView는 해당 판넬객체 클래스명(static member이므로)
        });
        JMenuItem figureLine = new JMenuItem("Line (L)");
        figureMenu.add(figureLine);
        figureBox.addActionListener((e) ->
        {
            canvas.setWhatToDraw(DrawerView.DRAW_LINE);
        });
        JMenuItem figureCircle = new JMenuItem("Circle (C)");
        figureMenu.add(figureCircle);
        figureCircle.addActionListener((e) ->
        {
            canvas.setWhatToDraw(DrawerView.DRAW_CIRCLE);
        });
        //figureMenu 작성완료
        //ToolMenu 작성시작     (dialog에서 좌표 입력해서 그리기)
        JMenu toolMenu = new JMenu("도구 (T)");
        menus.add(toolMenu);

        JMenuItem modalTool = new JMenuItem("Modal (M)");
        toolMenu.add(modalTool);
        modalTool.addActionListener((e) ->
        {
            FigureDialog dialog = new FigureDialog("Figure Dialog", canvas);//agument설명은 FigureDialog클래스에
            dialog.setModal(true);  //Modal Dialog를 사용하려면 flag변수 사용해서 true면 modal, false면 modaless
            dialog.setVisible(true);
        });

        JMenuItem modalessTool = new JMenuItem("Modaless (S)");
        toolMenu.add(modalessTool);
        modalessTool.addActionListener((e) ->
        {
            FigureDialog dialog = new FigureDialog("Figure Dialog", canvas);
            dialog.setModal(false);     //여기 setModal만 false로 만들면 modaless dialog
            dialog.setVisible(true);

        });

        //HelpMenu 작성시작
        JMenu helpMenu = new JMenu("도움말 (H)");
        menus.add(helpMenu);

        JMenuItem infoHelp = new JMenuItem("Drawer 정보 (I)");
        helpMenu.add(infoHelp);
        infoHelp.addActionListener( (e) ->
                { JOptionPane.showMessageDialog(null,"Hello\r\nWorld", "Drawer 정보", JOptionPane.INFORMATION_MESSAGE);}  //Dialog 사용, JOptionPane클래스의 static function 이용
        );
        //HelpMenu 작성종료

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
