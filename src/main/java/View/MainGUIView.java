package View;
import Controller.MainGUIController;
import javax.swing.*;
import java.awt.*;

public class MainGUIView extends JFrame {
    // All parameters needed to be used
    private JList<String> salons;
    private JList<String> vehicles;

    private MainGUIController controller;
    JTextField capacityField;

    public MainGUIView() {
        // Set up main frame of the program
        setTitle("Project 6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Add Grid
        JPanel panel = new JPanel(new GridLayout(1, 2));

        // Add salons list
        salons = new JList<>();
        JScrollPane scrollPane1 = new JScrollPane(salons);
        scrollPane1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane1);

        // Add vehicles list
        vehicles = new JList<>();
        JScrollPane scrollPane2 = new JScrollPane(vehicles);
        scrollPane2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane2);

        // Add buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5, 10, 0)); // 5 przyciski z marginesem 10 pikseli
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Adding buttons
        JButton removeSalon = new JButton("Remove Salon");
        JButton removeVehicle = new JButton("Remove Vehicle");
        JButton addSalon = new JButton("Add Salon");
        JButton addVehicle = new JButton("Add Vehicle");
        JButton sortSalons = new JButton("Sort salons");
        capacityField = new JTextField("No showroom selected");
        capacityField.setEditable(false);

        // Add button to the panel
        buttonsPanel.add(capacityField);
        buttonsPanel.add(removeSalon);
        buttonsPanel.add(removeVehicle);
        buttonsPanel.add(addSalon);
        buttonsPanel.add(addVehicle);
        buttonsPanel.add(sortSalons);

        // Add panel to main panel
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        getContentPane().add(panel);

        salons.addListSelectionListener(e -> controller.handleSalonSelection());
        removeSalon.addActionListener(e -> controller.handleRemoveSalon());
        removeVehicle.addActionListener(e -> controller.handleRemoveVehicle());
        addSalon.addActionListener(e -> controller.handleAddSalon());
        addVehicle.addActionListener(e -> controller.handleAddVehicle());
        sortSalons.addActionListener(e -> controller.handleSortSalons());


    }

    public void setController(MainGUIController controller) {
        this.controller = controller;
    }

    public void injectController(MainGUIController controller) {
        setController(controller);
    }

    public void setCapacityLabelText(double v) {
        capacityField.setText("Capacity: " + v);
    }
    public JList<String> getSalons() {
        return salons;
    }

    public JList<String> getVehicles() {
        return vehicles;
    }
}