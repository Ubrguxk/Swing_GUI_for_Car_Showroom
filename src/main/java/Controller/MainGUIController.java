package Controller;

import Model.CarShowroomModel;
import Model.Codes.CarShowroom;
import View.MainGUIView;
import javax.swing.*;
import Model.Codes.ItemCondition;


public class MainGUIController {
    private CarShowroomModel model;
    private MainGUIView view;

    public MainGUIController(CarShowroomModel model, MainGUIView view) {
        this.model = model;
        this.view = view;
        updateSalonsList();
    }
    public void handleSalonSelection() {
        String selectedSalonStr = view.getSalons().getSelectedValue();
        CarShowroom selectedSalon= model.findShowroom(selectedSalonStr);

        if (selectedSalon != null) {
            view.setCapacityLabelText(model.returnCapacity(selectedSalon));
            showVehicles();
        } else {
            clearVehicles();
        }
    }
    public void clearVehicles(){
        view.getVehicles().setListData(new String[0]);
    }
    public void showVehicles(){
        CarShowroom selected = model.findShowroom(view.getSalons().getSelectedValue());
        String [] vehList = model.vehiclesList(selected);
        if (vehList != null) {
            view.getVehicles().setListData(vehList);
        }
    }
    public void updateSalonsList() {
       view.getSalons().setListData(model.salonsList());
    }
    public void handleRemoveSalon() {
        boolean ifExist = model.removeCenter(view.getSalons().getSelectedValue());
        if (ifExist == true){
            clearVehicles();
            model.getContainer().salons.remove(view.getSalons().getSelectedValue());
            updateSalonsList();
        }
    }
    public void handleRemoveVehicle() {
        boolean ifExists = model.removeVehicle(view.getSalons().getSelectedValue(), view.getVehicles().getSelectedValue());
        if(ifExists == true){
            showVehicles();
        }
    }
    public void handleAddSalon() {

        JTextField salonNameField = new JTextField();
        JTextField capacityField = new JTextField();
        Object[] message = {
                "Salon name: ", salonNameField,
                "Max capacity: ", capacityField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Register", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String enteredName = salonNameField.getText();
            String enteredCapacityStr = capacityField.getText();
            int enteredCapacity = 0;

            if (enteredName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Salon name is empty.");

            } else {
                try {
                    enteredCapacity = Integer.parseInt(enteredCapacityStr);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid capacity: " + enteredCapacityStr, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                model.addSalon(enteredName, enteredCapacity);
                updateSalonsList();
            }

        } else {
            System.out.println("Canceled");
        }
    }
    public void handleAddVehicle() {
        JTextField labelField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField prodYearField = new JTextField();
        JTextField mileageField = new JTextField();
        JTextField engineCapacityField = new JTextField();
        JComboBox<ItemCondition> stateField = new JComboBox(new ItemCondition[]{ItemCondition.valueOf("NEW"), ItemCondition.valueOf("USED"), ItemCondition.valueOf("DAMAGED")});

        Object[] message = {
                "Label: ", labelField,
                "Model: ", modelField,
                "Price: ", priceField,
                "Production Year: ", prodYearField,
                "Engine Capacity: ", engineCapacityField,
                "Mileage: ", mileageField,
                "State: ", stateField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Register", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String labelName = labelField.getText();
            String modelName = modelField.getText();
            String priceNameStr = priceField.getText();
            String prodYearStr = prodYearField.getText();
            String engStr = engineCapacityField.getText();
            String mileageStr = mileageField.getText();
            double priceName = 0;
            int prodYearName = 0;
            double engineName = 0;
            double mileageName = 0.0;
            ItemCondition cond = (ItemCondition) stateField.getSelectedItem();

            if (labelName.isEmpty() || modelName.isEmpty() || prodYearStr.isEmpty() || engStr.isEmpty() || mileageStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Not all fields are filled");
                return;
            }
            try {
                priceName = Double.parseDouble(priceNameStr);
                prodYearName = Integer.parseInt(prodYearStr);
                engineName = Double.parseDouble(engStr);
                mileageName = Double.parseDouble(mileageStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid capacities");
                return;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if ("NEW".equals(cond.toString()) && mileageName != 0.0) {
                JOptionPane.showMessageDialog(null, "If the car is new, the mileage must be 0");
                return;
            }

            if(view.getSalons().getSelectedValue() == null){
                JOptionPane.showMessageDialog(null, "No salon picked, pick salon and then add vehicle");
                return;
            }
            model.addVehicle(view.getSalons().getSelectedValue(), labelName,modelName,cond,priceName,prodYearName,mileageName, engineName);
            showVehicles();

        } else {
            System.out.println("Canceled");
        }

    }
    public void handleSortSalons() {
        model.sort();
        updateSalonsList();
    }
}