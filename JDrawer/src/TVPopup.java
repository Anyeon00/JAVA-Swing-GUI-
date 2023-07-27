import javax.swing.*;
import java.awt.*;

public class TVPopup extends FigurePopup{
    //도형우클릭시 생기는 popup
    TVPopup(DrawerView view){
        super(view, "TV", false);
        JPopupMenu popupPtr = new JPopupMenu(); //이유모름 오류때문에 추가해봄

        JMenuItem powerItem = new JMenuItem("ON/OFF");
        powerItem.addActionListener((evt) -> {
            view.onOffTV();
        });
        popupPtr.add(powerItem);

        JMenuItem antennaItem = new JMenuItem("Antenna");
        antennaItem.addActionListener((evt) -> {
            view.setAntenna();
        });
        popupPtr.add(antennaItem);



    }

}
