import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

class MyActionListener implements ActionListener {
    private int i = 1;
    private JFrame frame;
    public JTable formula1Table;
    public JTable allRacesTable;
    public JTable raceTable;
    Formula1ChampionshipManager formula1CM;

    public MyActionListener(JFrame f, JTable formula1Table, JTable allRacesTable, JTable raceTable, Formula1ChampionshipManager formula1CM) {
        this.frame = f;
        this.formula1CM = formula1CM;
        this.formula1Table = formula1Table;
        this.allRacesTable = allRacesTable;
        this.raceTable = raceTable;
    }

    public void actionPerformed(ActionEvent e) {
        if ("simulateRace".equals(e.getActionCommand())) {

            Race race = new Race(this.formula1CM.drivers);
            this.formula1CM.addRace(race);

            this.formula1Table.setModel(new Formula1ChampionshipTableModel(this.formula1CM.drivers));
            this.allRacesTable.setModel(new RacesTableModel(this.formula1CM.races));
            this.raceTable.setModel(new RaceTableModel(race));
            // TODO: sort the updated datamodel

        } else if ("simulateRaceProb".equals(e.getActionCommand())) {
            Race race = new Race(this.formula1CM.drivers);//TODO:.simulateWithWeights();
            this.formula1CM.addRace(race);
            this.formula1Table.setModel(new Formula1ChampionshipTableModel(this.formula1CM.drivers));

        } else if ("searchDriverRaces".equals(e.getActionCommand())) {
            
        }
        // System.out.println("Pressed Button " + i++ + "th time!");
        // if (i % 2 == 0)
        // frame.getContentPane().setBackground(Color.red);
        // else
        // frame.getContentPane().setBackground(Color.white);
        // }
    }

}

class TextSearchActionListener implements ActionListener {
    private JFrame frame;
    private JTextField textBox;
    private Formula1ChampionshipManager formula1CM;  

    public TextSearchActionListener(JFrame f, JTextField textBox, Formula1ChampionshipManager formula1CM) {
        this.frame = f;
        this.textBox = textBox;
        this.formula1CM = formula1CM;
    }

    public void actionPerformed(ActionEvent e) {
        if ("searchDriverRaces".equals(e.getActionCommand())) {
            String searchString = this.textBox.getText();
            try {
                Formula1Driver driver = this.formula1CM.driverFindByName(searchString);
                // create a DriverRaces model from driver and formula1CM
                DriverRaces driverRaces = new DriverRaces(driver, formula1CM);
                // display the model on a table
                JTable testTable = new JTable();
                testTable.setModel(new DriverRacesTableModel(driverRaces.driverRaces));
                JScrollPane testSC = new JScrollPane(testTable);
                // TODO: make this frame nicer
                JFrame testFrame = new JFrame();
                testFrame.add(testSC);
                testFrame.setSize(1200, 600);
                testFrame.setVisible(true);
            } catch (Exception err) {
                //TODO: handle exception
            }
            
            }
    }

}

public class Formula1ChampionshipGUI {
    public JFrame frame;
    public JTable formula1Table;
    public JTable allRacesTable;
    public JTable raceTable;
    public JPanel panel;
    private Formula1ChampionshipManager formula1CM;

    public Formula1ChampionshipGUI(Formula1ChampionshipManager formula1CM) {
        this.formula1CM = formula1CM;
        // creating a frame
        this.frame = new JFrame();
        this.frame.setLayout(new BorderLayout());
        this.frame.setTitle("Formula 1 Championship GUI");

        // creating a panel to hold the Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.white);
        this.panel = buttonsPanel;
        // this.frame.setContentPane(buttonsPanel);
        this.frame.add(buttonsPanel, BorderLayout.NORTH);

        // Jtable with formula 1 Championship standings
        this.formula1Table = new JTable();
        this.addFormula1Table();
        JScrollPane formula1TablePane = new JScrollPane(formula1Table);
        formula1TablePane.setBorder(BorderFactory.createTitledBorder("Formula 1 Championship Table"));
        this.frame.add(formula1TablePane, BorderLayout.WEST);

        // JTable with last race details
        this.raceTable = new JTable();
        this.addRaceTable();
        JScrollPane raceTablePane = new JScrollPane(raceTable);
        raceTablePane.setBorder(BorderFactory.createTitledBorder("Last race standings"));
        this.frame.add(raceTablePane, BorderLayout.CENTER);

        // JTable with all races
        this.allRacesTable = new JTable();
        this.addAllRacesTable();
        JScrollPane allRacesTablePane = new JScrollPane(allRacesTable);
        allRacesTablePane.setBorder(BorderFactory.createTitledBorder("All races"));
        this.frame.add(allRacesTablePane, BorderLayout.EAST);

        this.simulateRaceButton(buttonsPanel);
        this.simulateRaceButtonWithProbabilities(buttonsPanel);
        this.searchForDriverRacesButton();

        this.frame.setSize(1200, 600);
        this.frame.setVisible(true);

    }

    private void addFormula1Table() {
        Formula1ChampionshipTableModel model = new Formula1ChampionshipTableModel(this.formula1CM.drivers);
        this.formula1Table.setModel(model);
        // Create a table sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.formula1Table.getModel());
        this.formula1Table.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(15);
        // Sort by points
        sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
        // Sort by number of first places
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }


    private void addRaceTable() {
        // TODO:check if there is a race yet first
        // TODO: change to get selected race
        Race race = this.formula1CM.races.get(0);
        // System.out.println(race.getStandings());//////////////////////////////////
        RaceTableModel model = new RaceTableModel(race);
        this.raceTable.setModel(model);
    }


    private void addAllRacesTable() {
        RacesTableModel model = new RacesTableModel(this.formula1CM.races);
        this.allRacesTable.setModel(model);
        // Create a table sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.allRacesTable.getModel());
        this.allRacesTable.setRowSorter(sorter);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(15);
        // Sort by date
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
    }


    private void simulateRaceButton(JPanel p) {
        JButton simulateRaceButton = new JButton("Simulate race");
        simulateRaceButton.setActionCommand("simulateRace");
        // this.frame.getContentPane().add(simulateRaceButton);
        p.add(simulateRaceButton);
        simulateRaceButton.addActionListener(new MyActionListener(this.frame, this.formula1Table, this.allRacesTable, this.raceTable, formula1CM));
    }


    private void simulateRaceButtonWithProbabilities(JPanel p) {
        JButton simulateRaceButtonProb = new JButton("Simulate race biased by starting position");
        simulateRaceButtonProb.setActionCommand("simulateRaceProb");
        // this.frame.getContentPane().add(simulateRaceButtonProb);
        simulateRaceButtonProb.addActionListener(new MyActionListener(this.frame, this.formula1Table, this.allRacesTable, this.raceTable, formula1CM));
        p.add(simulateRaceButtonProb);
    }


    private void searchForDriverRacesButton() {
        JButton searchDriverRacesButton = new JButton("Search for driver races");
        searchDriverRacesButton.setActionCommand("searchDriverRaces");
        JTextField searchBox = new JTextField();
        searchDriverRacesButton.addActionListener(new TextSearchActionListener(this.frame, searchBox, formula1CM));
        JPanel searchPanel = new JPanel(new GridLayout(1,1));
        searchPanel.add(searchBox);
        searchPanel.add(searchDriverRacesButton);
        this.frame.add(searchPanel, BorderLayout.SOUTH);
    }

}
