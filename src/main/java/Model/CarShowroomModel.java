package Model;

import Model.Codes.CarShowroom;
import Model.Codes.CarShowroomContainer;
import Model.Codes.ItemCondition;
import Model.Codes.Vehicle;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

public class CarShowroomModel {
    private CarShowroomContainer container;
    private DataGenerator dataGenerator;
    public CarShowroomModel(){
        dataGenerator = new DataGenerator();
        container = dataGenerator.PreMadeSalons();
    }
    public CarShowroomContainer getContainer() {
        return container;
    }
    public CarShowroom findShowroom(String x){
        for (Map.Entry<String, CarShowroom> entry : getContainer().salons.entrySet()) {
            if (entry.getValue().salonName == x){
                return entry.getValue();
            }
        }
        return null;
    }
    public String [] salonsList(){
        Map<String, CarShowroom> showroom = container.salons;
        Set<String> keys = showroom.keySet();
        return keys.toArray(new String[keys.size()]);
    }
    public double returnCapacity(CarShowroom x){
        return x.getCapacity();
    }
    public String [] vehiclesList(CarShowroom x){
        if(x == null){
            System.out.println("No such showroom found");
            return null;
        }
        else {
            String [] vehList = x.allVehiclesList();
            return vehList;
        }
    }
    public boolean removeCenter(String x){
        CarShowroom selected = findShowroom(x);
        if(selected == null){
            JOptionPane.showMessageDialog(null, "Showroom not picked");
            return false;
        }
        else {
            container.removeCenter(x);
            return true;
        }
    }
    public String extractLabel(String allParametersString) {
        int startIndex = allParametersString.indexOf("Model: ") + "Model: ".length();
        int endIndex = allParametersString.indexOf("<", startIndex);

        String label = allParametersString.substring(startIndex, endIndex);
        return label;
    }
    public boolean removeVehicle(String x, String y){
        CarShowroom selected = findShowroom(x);
        if (selected == null){
            JOptionPane.showMessageDialog(null, "No showroom picked.");
            return false;
        }
        if (y == null){
            JOptionPane.showMessageDialog(null, "No vehicle picked.");
            return false;
        }
        String carName = extractLabel(y);
        Vehicle selectedCar = selected.search(carName);
        selected.removeProduct(selectedCar);
        return true;
    }
    public void addSalon(String enteredName, int enteredCapacity){
        container.addCenter(enteredName,enteredCapacity);
    }
    public void addVehicle(String selectedCentre, String labelName, String modelName, ItemCondition cond, Double priceName,int prodYearName,Double  mileageName, Double engineName){
        Vehicle car = new Vehicle(labelName,modelName,cond,priceName,prodYearName,mileageName, engineName);
        findShowroom(selectedCentre).addProducts(car);
    }

    public void sort(){
        container.Sort();
    }
}
