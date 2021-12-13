
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class RaceTableModel extends AbstractTableModel {

    private Race race;

    public RaceTableModel(Race race) {
        this.race = race;
    }


    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 2    ;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return int.class;
            case 1: return String.class;
        }
        return Object.class;
    }


    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Position";
            case 1: return "Driver";
        }
        return null;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Formula1Driver driver = race.getDriverByStanding(rowIndex);
        switch (columnIndex) {
            case 0: return rowIndex + 1;
            case 1: return driver.getName();
        }
        return null;
    }
}


