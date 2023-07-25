import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FigurePopup extends Popup {  //도형우클릭시 생기는 popup
    FigurePopup(DrawerView view, String title, boolean fillFlag){
        super(title);

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener((evt) -> {
            view.deleteFigure();
        });
        popupPtr.add(deleteItem);
        JMenuItem copyItem = new JMenuItem("Copy");
        deleteItem.addActionListener((evt) -> {
            view.copyFigure();
        });
        popupPtr.add(copyItem);

        //PopupMenu속 Menu : JMenu
        JMenu colorMenu = new JMenu("Colors");
        popupPtr.add(colorMenu);
        //PopupMenu속 Menu인 JMenu(colorMenu)에 MenuItem추가
        JMenuItem blackItem = new JMenuItem("Black");
        blackItem.addActionListener((evt)-> {
            view.setColorForSelectedFigure(Color.black);
        });
        colorMenu.add(blackItem);

        JMenuItem redItem = new JMenuItem("Red");
        redItem.addActionListener((evt)-> {
            view.setColorForSelectedFigure(Color.red);
        });
        colorMenu.add(redItem);

        JMenuItem greenItem = new JMenuItem("Green");
        greenItem.addActionListener((evt)-> {
            view.setColorForSelectedFigure(Color.green);
        });
        colorMenu.add(greenItem);

        JMenuItem chooserItem = new JMenuItem("Chooser");
        chooserItem.addActionListener((evt)-> {
            view.showColorChooser();
        });
        colorMenu.add(chooserItem);

        if (fillFlag == true) {
            JMenuItem fillItem = new JMenuItem("Fill");
            fillItem.addActionListener((evt)-> {
                view.fillFigure();
            });
            popupPtr.add(fillItem);

        }




    }
}
