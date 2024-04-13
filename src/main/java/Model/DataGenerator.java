package Model;

import Model.Codes.CarShowroom;
import Model.Codes.CarShowroomContainer;
import Model.Codes.ItemCondition;
import Model.Codes.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// CLASS JUST TO MAKE PROP ELEMENTS
public class DataGenerator {
    public CarShowroomContainer PreMadeSalons(){
        CarShowroomContainer tmp = new CarShowroomContainer();
        tmp.addCenter("Luxe Auto Gallery", 500);
        tmp.addCenter("Velocity Motors", 300);
        tmp.addCenter("Elite Wheels Emporium", 400);
        tmp.addCenter("Precision Auto Salon", 250);
        tmp.addCenter("Supreme Motors Showcase", 350);
        tmp.addCenter("Platinum Drive Dealership", 450);
        tmp.addCenter("Apex Auto Palace", 550);
        tmp.addCenter("Summit Automotive Lounge", 600);
        tmp.addCenter("Royal Ride Pavilion", 700);
        tmp.addCenter("Grandeur Garage", 800);

        Map<Vehicle, Integer> vehicles = addVehiclesList();
        for (Map.Entry<String, CarShowroom> entry : tmp.salons.entrySet()) {
            addVehicles(entry.getValue(), vehicles);
        }
        return tmp;
    }
    public void addVehicles(CarShowroom x, Map<Vehicle, Integer> vehicles){
        Random random = new Random();
        int numberOfVehiclesToAdd = random.nextInt(10) + 1; // Losowanie liczby od 1 do 10
        for(int i = 0; i < numberOfVehiclesToAdd; i++){
            int randomIndex = random.nextInt(vehicles.size());
            Vehicle[] vehicleArray = vehicles.keySet().toArray(new Vehicle[0]);
            Vehicle randomVehicle = vehicleArray[randomIndex];
            x.addProducts(randomVehicle);
        }
    }
    public Map<Vehicle, Integer> addVehiclesList(){
        Map<Vehicle,Integer> vehicles = new HashMap<Vehicle, Integer>();
        vehicles.put(new Vehicle("Toyota", "Camry", ItemCondition.USED, 15000.0, 2018, 30000, 2.5),14);
        vehicles.put(new Vehicle("Honda", "Civic", ItemCondition.NEW, 20000.0, 2022, 0, 1.5),2);
        vehicles.put(new Vehicle("Ford", "F-150", ItemCondition.USED, 25000.0, 2015, 80000, 5.0),3);
        vehicles.put(new Vehicle("Chevrolet", "Silverado", ItemCondition.USED, 28000.0, 2019, 40000, 6.2),4);
        vehicles.put(new Vehicle("Tesla", "Model 3", ItemCondition.NEW, 50000.0, 2023, 0, 0.0),5);
        vehicles.put(new Vehicle("Nissan", "Leaf", ItemCondition.USED, 18000.0, 2017, 35000, 0.0),6);
        vehicles.put(new Vehicle("Harley-Davidson", "Sportster", ItemCondition.USED, 9000.0, 2016, 20000, 1.2),7);
        vehicles.put(new Vehicle("Honda", "CBR600RR", ItemCondition.USED, 7000.0, 2015, 15000, 0.6),8);
        vehicles.put(new Vehicle("Toyota", "RAV4", ItemCondition.NEW, 35000.0, 2024, 0, 2.5),9);
        vehicles.put(new Vehicle("Ford", "Escape", ItemCondition.USED, 22000.0, 2019, 45000, 2.0),10);
        vehicles.put(new Vehicle("BMW", "3 Series", ItemCondition.USED, 18000.0, 2016, 55000, 2.0), 11);
        vehicles.put(new Vehicle("Audi", "A4", ItemCondition.USED, 21000.0, 2017, 40000, 2.0), 12);
        vehicles.put(new Vehicle("Lexus", "RX", ItemCondition.USED, 32000.0, 2019, 35000, 3.5), 13);
        vehicles.put(new Vehicle("Jeep", "Wrangler", ItemCondition.USED, 28000.0, 2018, 30000, 3.6), 14);
        vehicles.put(new Vehicle("GMC", "Sierra", ItemCondition.USED, 31000.0, 2020, 25000, 5.3), 15);
        vehicles.put(new Vehicle("RAM", "1500", ItemCondition.USED, 29000.0, 2017, 40000, 5.7), 16);
        vehicles.put(new Vehicle("Dodge", "Charger", ItemCondition.USED, 25000.0, 2019, 35000, 3.6), 17);
        vehicles.put(new Vehicle("Volkswagen", "Jetta", ItemCondition.USED, 17000.0, 2016, 50000, 1.4), 18);
        vehicles.put(new Vehicle("Subaru", "Outback", ItemCondition.USED, 19000.0, 2018, 40000, 2.5), 19);
        vehicles.put(new Vehicle("Hyundai", "Sonata", ItemCondition.USED, 15000.0, 2017, 45000, 2.4), 20);
        return vehicles;
    }
}
