import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 * Table model for GUI table
 */
public class DriverRacesTableModel extends AbstractTableModel {

    private ArrayList<DriverRace> driverRaces;

    public DriverRacesTableModel(ArrayList<DriverRace> driverRaces) {
        this.driverRaces = driverRaces;
    }


    @Override
    public int getRowCount() {
        return driverRaces.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return LocalDate.class;
            case 1: return Integer.class;
        }
        return Object.class;
    }


    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Race date";
            case 1: return "Final position";
        }
        return null;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DriverRace race = driverRaces.get(rowIndex);
        switch (columnIndex) {
            case 0: return race.raceDate;
            case 1: return race.position    ;
        }
        return null;
    }
}

