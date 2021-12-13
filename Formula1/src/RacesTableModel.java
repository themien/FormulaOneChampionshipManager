import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class RacesTableModel extends AbstractTableModel {
    
    private ArrayList<Race> races;

    public RacesTableModel(ArrayList<Race> races) {
        this.races = races;
    }

    @Override
    public int getRowCount() {
        return races.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return LocalDate.class;
            case 1: return Object.class;
        }
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Race date";
            case 1: return "First position";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Race race = races.get(rowIndex);
        switch (columnIndex) {
            case 0: return race.getDate();
            case 1: return race.getStandings().get(0).getName();
        }
        return null;
    }
}
