import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DrawerFrame extends JFrame {   //DRawerFrame 컴포넌트
    static class ZoomBox extends JComboBox implements ActionListener{   //툴바에서 사용하는 콤보박스에서 사용하려고 만든 이너클래스
        DrawerView canvas;
        static String[] size = {"100", "80", "50"};
        ZoomBox(DrawerView canvas){
            super();
            this.canvas = canvas;
            setMaximumSize(new Dimension(1000,200));
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            String ratio = (String) box.getSelectedItem();
            canvas.zoom(Integer.parseInt(ratio));
        }

    }
    DrawerView canvas;    //JPanel 상속받아 만든 Panel객체, main panel
    StatusBar statusBar;    //statusBar panel
    FigureDialog dialog;
    TableDialog tableDialog;
    TreeDialog treeDialog;

    public void writePosition(String s) {   //statusBar에서 마우스포지션움직임나타내는데 사용
        statusBar.writePosition(s); //view에서 구현할때 statusbar의 함수필요한데 frame객체를 거쳐서 사용
    }

    public void writeFigureType(String s) {
        statusBar.writeFigureType(s);
    }
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

        statusBar = new StatusBar();        //StatusBar(panel)생성
        container.add(statusBar, "South");  //Content Pane에 StatusBar(Panel) 붙이기

        canvas = new DrawerView(this);    //Panel 생성, argument: writePosition함수 사용하기위해
        JScrollPane sp = new JScrollPane(canvas);   //panel을 스크롤pane에 붙임(메인판넬을 붙인 스크롤바 판넬을 프레임에 다시 붙임)
        /*container.add(canvas,"Center");           //Content Pane에 Panel 붙이기*/
        container.add(sp, "Center");    //Content Pane에 메인panel을 붙인 스크롤바pane을 붙이기

        //page down/page up키로 스크롤바 조종하는 기능만들기
        sp.registerKeyboardAction(new ActionListener() {    //함수설명 10-2JscrollPane //lamdaExpression으로 써도됨
                                      public void actionPerformed(ActionEvent evt) {
                                          JScrollBar scrollBar = sp.getVerticalScrollBar();   //스크롤바 가지고 오기
                                          scrollBar.setValue(scrollBar.getValue() + scrollBar.getBlockIncrement()); //스크롤바 움직이기
                                      }
                                  }
	        , KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0)//pageDown키 눌럿을때
            , JComponent.WHEN_IN_FOCUSED_WINDOW);//현재 작업중인창
        sp.registerKeyboardAction(new ActionListener() {    //내리는 기능, 위에는 올리는 기능
                                      public void actionPerformed(ActionEvent evt) {
                                          JScrollBar scrollBar = sp.getVerticalScrollBar();
                                          scrollBar.setValue(scrollBar.getValue() - scrollBar.getBlockIncrement()); //스크롤바 움직이기
                                      }
                                  }
                , KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0)
                , JComponent.WHEN_IN_FOCUSED_WINDOW);

        JToolBar selectToolBar = new JToolBar();    //툴바_AbstractAction인 SelectAction을 이용해 툴바에도 그림그리는 버튼만들기
        selectToolBar.add(canvas.getPointAction());
        selectToolBar.add(canvas.getBoxAction());
        selectToolBar.add(canvas.getLineAction());
        selectToolBar.add(canvas.getCircleAction());
        selectToolBar.add(canvas.getTVAction());
        selectToolBar.add(canvas.getKiteAction());
        selectToolBar.add(new ZoomBox(canvas));
        selectToolBar.add(javax.swing.Box.createGlue());
        container.add(selectToolBar, BorderLayout.NORTH);

        addComponentListener(new ComponentAdapter() {    //Component의 변화가 있을때 사용되는 listner
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension sz = canvas.getSize();    //component의 사이즈 알아오기
                String s = "" + sz.width + " X " + sz.height + "px";
                statusBar.writeSize(s);

            }
        });

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

        JMenuItem figurePoint = new JMenuItem(canvas.getPointAction());
        figureMenu.add(figurePoint);
        /*JMenuItem figureBox = new JMenuItem("Box (B)");
        figureMenu.add(figureBox);
        figureBox.addActionListener((e) ->
        {
            canvas.setWhatToDraw(DrawerView.ID_BOX);  //canvas는 Panel객체 레퍼런스(를상속받은class, DrawerView는 해당 판넬객체 클래스명(static member이므로)
        });*/   // Abstract Action 사용전
        JMenuItem figureBox = new JMenuItem(canvas.getBoxAction());
        figureMenu.add(figureBox);      //Abstract Action 사용후

        JMenuItem figureLine = new JMenuItem(canvas.getLineAction());
        figureMenu.add(figureLine);

        JMenuItem figureCircle = new JMenuItem(canvas.getCircleAction());
        figureMenu.add(figureCircle);

        JMenuItem figureTV= new JMenuItem(canvas.getTVAction());
        figureMenu.add(figureTV);

        JMenuItem figureKite= new JMenuItem(canvas.getKiteAction());
        figureMenu.add(figureKite);
        //figureMenu 작성완료
        //ToolMenu 작성시작     (dialog에서 좌표 입력해서 그리기)
        JMenu toolMenu = new JMenu("도구 (T)");
        menus.add(toolMenu);

        JMenuItem modalTool = new JMenuItem("Modal (M)");
        toolMenu.add(modalTool);
        modalTool.addActionListener((e) ->
        {
            if (dialog == null) {   //instanciation 중복으로 인한 메모리낭비를 막기 위해
                dialog = new FigureDialog("Figure Dialog", canvas);//agument설명은 FigureDialog클래스에
                dialog.setModal(true);  //Modal Dialog를 사용하려면 flag변수 사용해서 true면 modal, false면 modaless
            }
            dialog.setVisible(true);
        });

        JMenuItem modalessTool = new JMenuItem("Modaless (S)");
        toolMenu.add(modalessTool);
        modalessTool.addActionListener((e) ->
        {
            if (dialog == null) {
                FigureDialog dialog = new FigureDialog("Figure Dialog", canvas);
                dialog.setModal(false);     //여기 setModal만 false로 만들면 modaless dialog
            }
            dialog.setVisible(true);

        });
        JMenuItem tableTool = new JMenuItem("Table (T)");
        toolMenu.add(tableTool);
        tableTool.addActionListener((e) ->
        {
            if (tableDialog == null) {
                tableDialog = new TableDialog("Table Dialog", canvas);
                tableDialog.setModal(false);     //여기 setModal만 false로 만들면 modaless dialog
            }
            tableDialog.setVisible(true);
        });

        JMenuItem treeTool = new JMenuItem("Tree (R)");
        toolMenu.add(treeTool);
        treeTool.addActionListener((e) ->
        {
            if (treeDialog == null) {
                treeDialog = new TreeDialog("Tree Dialog", canvas);
                treeDialog.setModal(false);     //여기 setModal만 false로 만들면 modaless dialog
            }
            treeDialog.setVisible(true);
        });

        JMenu zoomMenu = new JMenu("Zoom");
        toolMenu.add(zoomMenu);

        JMenuItem zoom100 = new JMenuItem("100%");
        zoomMenu.add(zoom100);
        zoom100.addActionListener((e) -> {
            canvas.zoom(100);
        });

        JMenuItem zoom80 = new JMenuItem("80%");
        zoomMenu.add(zoom80);
        zoom100.addActionListener((e) -> {
            canvas.zoom(80);
        });


        JMenuItem zoom50 = new JMenuItem("50%");
        zoomMenu.add(zoom50);
        zoom100.addActionListener((e) -> {
            canvas.zoom(50);
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
