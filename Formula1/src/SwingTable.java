import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

// window event Handler class
class MyWindowListener extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing window!");
        System.exit(0);
    }
}

public class SwingTable extends JPanel{

    public SwingTable(ArrayList<Formula1Driver> drivers) {
        String[] columnNames = {"Name",
                                "Location",
                                "Team",
                                "1st positions",
                                "2nd positions",
                                "3rd positions",
                                "Total Points",
                                "Races attended"};
        int columnCount = columnNames.length;
        int rowCount = drivers.size();
        Object[][] data = getDriversData(rowCount, columnCount, drivers);
        final JTable table = new JTable(data, columnNames);
        // table.setFillsViewportHeight(true);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    public static void displayFrame(ArrayList<Formula1Driver> drivers) {
        // Create the frame window
        JFrame frame = new JFrame("Formula 1 Championship Table"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create and set up the content pane.
        SwingTable newContentPane = new SwingTable(drivers); 
        newContentPane.setOpaque(true); // content panes must be opaque frame.setContentPane(newContentPane);
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

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