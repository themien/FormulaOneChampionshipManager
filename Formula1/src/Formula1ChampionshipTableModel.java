import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Holds the GUI driver's table model
 */
public class Formula1ChampionshipTableModel extends AbstractTableModel {

    private ArrayList<Formula1Driver> drivers;

    public Formula1ChampionshipTableModel(ArrayList<Formula1Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public int getRowCount() {
        return drivers.size();
    }

    @Override
    public int getColumnCount() {
        return 8    ;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return Integer.class;
            case 4: return Integer.class;
            case 5: return Integer.class;
            case 6: return Integer.class;
            case 7: return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Name";
            case 1: return "Location";
            case 2: return "Team";
            case 3: return "1st Places";
            case 4: return "2nd Places";
            case 5: return "3rd Places";
            case 6: return "Total Points";
            case 7: return "Races Attended";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Formula1Driver driver = drivers.get(rowIndex);
        switch (columnIndex) {
            case 0: return driver.getName();
            case 1: return driver.getLocation();
            case 2: return driver.getTeam();
            case 3: return driver.getTimesFirst();
            case 4: return driver.getTimesSecond();
            case 5: return driver.getTimesThird();
            case 6: return driver.getTotalPoints();
            case 7: return driver.getRacesAttended();
        }
        return null;
    }
    
    
    
}
