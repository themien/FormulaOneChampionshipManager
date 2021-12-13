import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

class MyActionListener implements ActionListener {
    private int i = 1;
    private JFrame frame;
    public JTable table;
    Formula1ChampionshipManager formula1CM;

    public MyActionListener(JFrame f, JTable t, Formula1ChampionshipManager formula1CM) {
        this.frame = f;
        this.formula1CM = formula1CM;
        this.table = t;
    }

    public void actionPerformed(ActionEvent e) {
        if ("simulateRace".equals(e.getActionCommand())) {
            this.formula1CM.addRace();
            this.table.setModel(new Formula1ChampionshipTableModel(this.formula1CM.drivers));
        }
        // System.out.println("Pressed Button " + i++ + "th time!");
        // if (i % 2 == 0)
        // frame.getContentPane().setBackground(Color.red);
        // else
        // frame.getContentPane().setBackground(Color.white);
        // }
    }

}

public class Formula1ChampionshipGUI {
    public JFrame frame;
    public JTable table;
    public JPanel panel;
    private Formula1ChampionshipManager formula1CM;

    public Formula1ChampionshipGUI(Formula1ChampionshipManager formula1CM) {
        this.formula1CM = formula1CM;
        // creating a frame
        this.frame = new JFrame();
        this.frame.setTitle("Formula 1 Championship GUI");
        // creating a panel to hold the Buttons
        JPanel jp = new JPanel();
        jp.setBackground(Color.white);
        this.panel = jp;
        this.frame.setContentPane(jp);

        // TODO: this.table should be populated before simulateraceButton()
        // this.table = new Formula1ChamipionshipStandingsTable(formula1CM).getFormula1DriversTable();
        Formula1ChampionshipTableModel model = new Formula1ChampionshipTableModel(this.formula1CM.drivers);
        this.table = new JTable();
        this.table.setModel(model);
        // Create a table sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.table.getModel());
        this.table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(15);
        // Sort by points
        sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
        // Sort by number of first places
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);

        simulateRaceButton();
        // TODO: this.table should be populated before simulateraceButton()
        // this.table = new Formula1ChamipionshipStandingsTable(formula1CM).getFormula1DriversTable();
        // // adding it to JScrollPane
        
        // System.out.println(this.table); //////////////////////
        JScrollPane tablePane = new JScrollPane(table);
        this.frame.add(tablePane);
        this.frame.setSize(800, 400);
        this.frame.setVisible(true);

    }

    private void simulateRaceButton() {
        JButton simulateRaceButton = new JButton("Simulate race");
        simulateRaceButton.setActionCommand("simulateRace");
        this.frame.getContentPane().add(simulateRaceButton);
        // register an event handler for frame events
        simulateRaceButton.addActionListener(new MyActionListener(frame, this.table, formula1CM));
        // this.frame.setSize(400, 400);
        // this.frame.setVisible(true);
    }

}
