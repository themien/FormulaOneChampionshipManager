import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Formula1ChamipionshipStandingsTable extends JTable {

    private Formula1ChampionshipManager formula1CM;
    public JTable table;
    public TableModel model;

    public Formula1ChamipionshipStandingsTable(Formula1ChampionshipManager formula1ChampionshipM) {
        this.formula1CM = formula1ChampionshipM;
        String[] columnNames = {"Name",
                                "Location",
                                "Team",
                                "1st positions",
                                "2nd positions",
                                "3rd positions",
                                "Total Points",
                                "Races attended"};
        int columnCount = columnNames.length;
        int rowCount = this.formula1CM.drivers.size();
        Object[][] data = getDriversData(rowCount, columnCount, this.formula1CM.drivers); 
        this.model = new TableModel(data);
        // Initializing the JTable
        this.table = new JTable(data, columnNames);

        // Create a table sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.table.getModel());
        this.table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(15);
        // Sort by points
        sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
        // Sort by number of first places
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        this.table.setBounds(30, 40, 600, 300);
    }


    public JTable getFormula1DriversTable() {
        JTable formula1DriversTable = this.table;
        return formula1DriversTable;
    }
    

    /*
        Gets the drivers information from the Formula 1 Chamionship Manger
    */
    private Object getValueAt(int row, int col, ArrayList<Formula1Driver> drivers) {
        Object temp = null;
        if (col == 0) {
           temp = drivers.get(row).getName();
        }
        else if (col == 1) {
           temp = drivers.get(row).getLocation();
        }
        else if (col == 2) {
           temp = drivers.get(row).getTeam();
        }
        else if (col == 3) {
            temp = drivers.get(row).getTimesFirst();
         }
         else if (col == 4) {
            temp = drivers.get(row).getTimesSecond();
         }
         else if (col == 5) {
            temp = drivers.get(row).getTimesThird();
         }
         else if (col == 6) {
            temp = drivers.get(row).getTotalPoints();
        }
        else if (col == 7) {
            temp = drivers.get(row).getRacesAttended();
        }
        return temp;
    }


    /*
        Obtains the data to provide to the JTable
    */
    private Object[][] getDriversData(int nRows, int nCols, ArrayList<Formula1Driver> drivers) {
        Object[][] data = new Object[nRows][nCols];
        for (int i = 0; i<nRows; i++) {
            for (int j=0; j<nCols; j++) {
                data[i][j] = getValueAt(i, j, drivers); 
            }
        }
        return data;
    }



}  
    
    

