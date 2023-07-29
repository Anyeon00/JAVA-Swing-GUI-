import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class TableDialog extends JDialog	//상단메뉴바에서 dialog 툴에서 현재 도형 테이블로 보여주는 Dialog
        //Dialog판넬 -하단버튼판넬, 스크롤 판넬  // 스크롤판넬 - table
{
    static class FigureTableModel implements TableModel
    {
        DrawerView view;
        ArrayList<Figure> figures;
        static final String[] columnNames = new String[] {
                "Figure Type", "x1", "y1", "x2", "y2"
        };
        static final Class[] columnTypes = new Class[] {
                String.class, Integer.class, Integer.class, Integer.class, Integer.class
        };
        FigureTableModel(DrawerView view) {
            this.view = view;
            figures = view.getFigures();
        }
        public int getRowCount() {
            return figures.size();
        } //행 개수
        public int getColumnCount() {
            return columnNames.length;
        }// 컬럼 개수
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        } //컬럼 이름
        public Class getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }  //컬럼 데이터의 data type 명시, ex) 문자열이면 return String.class;
        public boolean isCellEditable(int rowIndex,int columnIndex) {
            return false;
        }//특정행이 편집가능한지
        public Object getValueAt(int rowIndex,int columnIndex) {  //i, j번째 값이 뭐냐
            Figure ptr = figures.get(rowIndex);
            switch(columnIndex) {
                case 0: return ptr.getClass().getName();
                case 1: return ptr.getX1();
                case 2: return ptr.getY1();
                case 3: return (ptr.getX2() > 0) ? ptr.getX2() : null;
                case 4: return (ptr.getY2() > 0) ? ptr.getY2() : null;
                default: return null;
            }
        }
        public void setValueAt(Object aValue,int rowIndex,int columnIndex) { //i, j번째에 값을 어떻게 저장할거냐, 굳이 구현안해도 무방
        }
        public void addTableModelListener(TableModelListener l) {
        }
        public void removeTableModelListener(TableModelListener l) {
        }
    }
    static class FigureTable extends JTable    //FigureTable의 Table 모델을 만들어줘야함
    {
        FigureTable(DrawerView view) {
            super(new FigureTableModel(view));
            DefaultListSelectionModel selectionModel
                    = new DefaultListSelectionModel(); //dialog에서 figure를 selec해 제거하기 위해만듦
            setSelectionModel(selectionModel);

            TableColumnModel colModel = getColumnModel();   //컬럼정보관리하는 객체(text align하려고 만든거)
            TableColumn nameColumn = colModel.getColumn(0); //column중 0번째 index column 갖고오기
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();//어떤 list에서 한 행을 의미(rende   r)
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            nameColumn.setCellRenderer(renderer);   //dialog의 figure 이름 column의 text align을 center로 만듦
        }
        public int getSelectedIndex() {
            return selectionModel.getMinSelectionIndex();
        }
    }
    static class DialogPanel extends JPanel implements ActionListener
    {
        JDialog dialog;
        DrawerView view;
        JButton done;
        JButton remove;
        FigureTable table;

        DialogPanel(JDialog dialog,DrawerView view) {
            this.view = view;
            this.dialog = dialog;
            setLayout(new BorderLayout());

            table = new FigureTable(view);
            JScrollPane sp = new JScrollPane(table);    //화면상에서 JScrollPane에다 table을 집어넣기
            add(sp,BorderLayout.CENTER);

            JPanel bottom = new JPanel();
            bottom.add(remove = new JButton("Remove"));
            bottom.add(done = new JButton("Done"));
            add(bottom,BorderLayout.SOUTH);

            remove.addActionListener(this);
            done.addActionListener(this);
        }
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == done)    //done 버튼이 클릭되면
            {
                dialog.setVisible(false);
            } else if (event.getSource() == remove)
            {
                view.remove(table.getSelectedIndex());
                updateUI();
            }
        }
        public void updateUI() {
            if (table != null) table.updateUI();
        }
    }

    TableDialog(String title, DrawerView view){     //argument : 해당 dialog의 title, view에대한 설명은 아래에
        super((JFrame)null, title);  //contructer에 해당 frame포인터와 title을 argument에 넣어야함
        setLocation(200,300);
        setSize(400, 300);

        //Dialog의 작업공간 생성 (JFrame에 JPanel생성해서 붙일때랑 마찬가지_DrawerFrame클래스 생성자의 Panel생성부분 참고
        Container container = getContentPane();
        //이 Dialog에서 쓸 Panel 생성, 해당 panel에서 cancel시 이 dialog객체 종료시키기 위해 parameter로 이 dialog 넘겨줌
        JPanel panel = new DialogPanel(this,view);  //그리고 넘겨받은 메인 판넬을 다시 이 dialog판넬에 넘겨줌(dilog판넬이 그려서 메인판넬에 넘겨주기위해)
        container.add(panel);               //이 Dialog의 작업공간Container에 사용할 panel만든거 붙이기

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                panel.updateUI();
            }
        });
    }
}
