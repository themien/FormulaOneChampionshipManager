// import java.util.ArrayList;

import java.util.ArrayList;

import javax.swing.*;

public class Formula1TableSwing extends JPanel {

    // frame
    JFrame f;
    // Table
    JTable j;
 
    // Constructor
    Formula1TableSwing(ArrayList<Formula1Driver> drivers)
    {
        // Frame initialization
        f = new JFrame();
 
        // Frame Title
        f.setTitle("JTable Example");
 
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
 
        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }

    
//     public Formula1TableSwing(ArrayList<Formula1Driver> drivers) {
//         // String[] columnNames = {"Name",
//         //                         "Location",
//         //                         "Team",
//         //                         // "1st positions",
//         //                         // "2nd positions",
//         //                         // "3rd positions",
//         //                         "Total Points",
//         //                         "Races attended"};
//         // int columnCount = columnNames.length;
//         // int rowCount = drivers.size();
//         // // Object[][] data = getDriversData(rowCount, columnCount, drivers);
//         // Object[][] data = {
//         //     {"John", "Smith", "Manager",
//         //       new Integer(35), new Integer(40000)},
//         //     {"Tom", "Bubble", "Developer",
//         //       new Integer(22), new Integer(22000)},
//         //     {"Helen", "Hitchcock", "Project Leader",
//         //       new Integer(30), new Integer(34000)},
//         //     {"Kate", "Silva", "Receptionist",
//         //       new Integer(20), new Integer(18000)},
//         //     {"Susie", "White", "Developer",
//         //       new Integer(25), new Integer(25000)}
//         // };

//         // final JTable table = new JTable(data, columnNames);
//         // table.setFillsViewportHeight(true);
//         // //Create the scroll pane and add the table to it.
//         // JScrollPane scrollPane = new JScrollPane(table);
//         // //Add the scroll pane to this panel.
//         // add(scrollPane);

//         String[] columnNames = {"First Name",
//                                 "Last Name",
//                                 "Position",
//                                 "Age",
//                                 "Salary"};
//         Object[][] data = {
//                 {"John", "Smith", "Manager",
//                 new Integer(35), new Integer(40000)},
//                 {"Tom", "Bubble", "Developer",
//                 new Integer(22), new Integer(22000)},
//                 {"Helen", "Hitchcock", "Project Leader",
//                 new Integer(30), new Integer(34000)},
//                 {"Kate", "Silva", "Receptionist",
//                 new Integer(20), new Integer(18000)},
//                 {"Susie", "White", "Developer",
//                 new Integer(25), new Integer(25000)}
//         };
//         final JTable table = new JTable(data, columnNames);
//         table.setFillsViewportHeight(true);
//         //Create the scroll pane and add the table to it.
//         JScrollPane scrollPane = new JScrollPane(table);
//         //Add the scroll pane to this panel.
//         add(scrollPane);
//     }


//     public static void createAndShowGUI(ArrayList<Formula1Driver> drivers) {
//         // Create the frame window
//         JFrame frame = new JFrame("Formula 1 Championship Standings"); 
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         // Create and set up the content pane.
//         Formula1TableSwing newContentPane = new Formula1TableSwing(drivers); 
//         newContentPane.setOpaque(true); // content panes must be opaque frame.setContentPane(newContentPane);
//         // Display the window.
//         frame.pack();
//         frame.setVisible(true);
//     }

    
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
