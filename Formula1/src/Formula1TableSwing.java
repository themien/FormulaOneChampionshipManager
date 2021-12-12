import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

class MyActionListener implements ActionListener {
    private int i = 1;
    JFrame frame;
    Formula1ChampionshipManager formula1CM;

    public MyActionListener(JFrame f, Formula1ChampionshipManager formula1CM) {
        this.frame = f; 
        this.formula1CM = formula1CM;
    }

    public void actionPerformed(ActionEvent e) {
        if ("simulateRace".equals(e.getActionCommand())) {
            this.formula1CM.addRace();
            new Formula1TableSwing(formula1CM);
        }
        // System.out.println("Pressed Button " + i++ + "th time!");
        // if (i % 2 == 0)
        //     frame.getContentPane().setBackground(Color.red);
        // else
        //     frame.getContentPane().setBackground(Color.white);
        // } 
    }

}

public class Formula1TableSwing extends JPanel {

    public JFrame frame;
    public JTable table;
    private Formula1ChampionshipManager formula1CM;


    // Constructor
    public Formula1TableSwing(Formula1ChampionshipManager formula1CM) {
        this.formula1CM = formula1CM;
        frame = new JFrame();
        frame.setTitle("Formula 1 Championship Table");
        String[] columnNames = {"Name",
                                "Location",
                                "Team",
                                "1st positions",
                                "2nd positions",
                                "3rd positions",
                                "Total Points",
                                "Races attended"};
        int columnCount = columnNames.length;
        int rowCount = formula1CM.drivers.size();
        Object[][] data = getDriversData(rowCount, columnCount, formula1CM.drivers); 
        // Initializing the JTable
        table = new JTable(data, columnNames);

        // Create a table sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        // Sort by points
        sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
        // Sort by number of first places
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        table.setBounds(30, 40, 600, 300);

        simulateRaceButton();

         // adding it to JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    private void simulateRaceButton() {
        JButton simulateRaceButton = new JButton("Simulate race");
        simulateRaceButton.setActionCommand("simulateRace");
        JPanel jp = new JPanel();
        jp.setBackground(Color.white);
        // set the content pane to be the newly created JPanel
        frame.setContentPane(jp);
        frame.getContentPane().add(simulateRaceButton);
        // register an event handler for frame events
        simulateRaceButton.addActionListener(new MyActionListener(frame, formula1CM));
        frame.setSize(400, 400);
        frame.setVisible(true);
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
