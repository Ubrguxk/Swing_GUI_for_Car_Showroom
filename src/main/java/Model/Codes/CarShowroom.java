package Model.Codes;

import java.util.*;

public class CarShowroom{
    public String salonName;
    public Map<Vehicle, Integer> carList;
    double maxCarCapacity;

    public CarShowroom(String userSalonName, double capacity){
        salonName = userSalonName;
        carList = new HashMap<Vehicle, Integer>();
        maxCarCapacity = capacity;
    }

    public double getCapacity() {
        return maxCarCapacity;
    }
    public class CompareVehiclesModel implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return o1.compareTo(o2);
        }
    }
    public class CompareQuantity implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return Integer.compare(carList.get(o1), carList.get(o2));
        }
    }
    public Vehicle search(String x) {
        for (Map.Entry<Vehicle, Integer> entry : carList.entrySet()) {
            if (entry.getKey().model.equals(x)) {
                //System.out.println("Vehicle Founded: " + entry.getKey().model);
                return entry.getKey();
            }
        }
        //System.out.println("Vehicle not founded");
        return null;
    }
    public void searchPartial(String x){
        for (Map.Entry<Vehicle, Integer> entry : carList.entrySet()) {
            if(entry.getKey().model.contains(x)){
                System.out.println("Vehicle founded: " + entry.getKey().model);
            }
        }
    }
    public void addProducts(Vehicle car){
        if (this.carNum() == this.maxCarCapacity){
            System.out.println("Vehicle limits. Please remove vehicles to add more");
            return;
        }
        for(Vehicle i: carList.keySet()){
            if(i.equals(car)){
                carList.put(i, carList.get(i) + 1);
                return;
            }
        }
        this.carList.put(car,1);

    }
    public void getProduct(Vehicle car){
        Iterator<Vehicle> iterator = carList.keySet().iterator();
        while(iterator.hasNext()){
            Vehicle i = iterator.next();
            if(i.equals(car)){
                if (carList.get(i) == 1){
                    iterator.remove();
                    return;
                }
                else {
                    this.carList.put(i, carList.get(i) - 1);
                    return;
                }
            }
        }
        System.out.println("Vehicle not founded");
    }
    public void sortByName() {
        List<Vehicle> sortedCars = new ArrayList<>(carList.keySet());
        Collections.sort(sortedCars, new CompareQuantity());

        // Creating new map to keep the Vehicles number
        Map<Vehicle, Integer> sortedCarList = new LinkedHashMap<>();
        for (Vehicle car : sortedCars) {
            sortedCarList.put(car, carList.get(car));
        }

        carList = sortedCarList;
    }
    public void sortByAmount() {
        List<Vehicle> sortedCars = new ArrayList<>(carList.keySet());
        Collections.sort(sortedCars, new CompareVehiclesModel());

        // Creating new map to keep the Vehicles number
        Map<Vehicle, Integer> sortedCarList = new LinkedHashMap<>();
        for (Vehicle car : sortedCars) {
            sortedCarList.put(car, carList.get(car));
        }

        carList = sortedCarList;
    }
    public void removeProduct(Vehicle car){
        Iterator<Vehicle> iterator = carList.keySet().iterator();
        while(iterator.hasNext()){
            Vehicle i = iterator.next();
            if(i.equals(car)){
                iterator.remove();
                return;
            }
        }
        System.out.println("Car not founded");
    }
    public Integer carNum(){
        int sum = 0;
        for (Integer key: carList.values()){
            sum += key;
        }
        return sum;
    }
    public void summanary(){
        for (Vehicle i: carList.keySet()){
            i.print();
            System.out.println("Vehicle amount: " + carList.get(i));
        }
    }
    public double calculateProc(){
        int allCars = carNum();
        if(allCars == 0){
            return 0;
        }
        return (allCars / maxCarCapacity);
    }
    public Vehicle max(){
        Integer max = 0;
        Vehicle returned = null;
        for (Map.Entry<Vehicle, Integer> entry : carList.entrySet()) {
            if( entry.getValue() > max){
                max = entry.getValue();
                returned = entry.getKey();
            }
        }
        System.out.println("The most: " + returned.model);
        System.out.println("The least: " + carList.get(returned));
        return returned;
    }
    public void countByCondition(){
        Integer newCars = 0;
        Integer usedCars = 0;
        Integer damagedCars = 0;
        for (Map.Entry<Vehicle, Integer> entry : carList.entrySet()) {
            if(entry.getKey().state == ItemCondition.NEW){
                newCars += entry.getValue();
            }
            if(entry.getKey().state == ItemCondition.USED){
                usedCars += entry.getValue();
            }
            if(entry.getKey().state == ItemCondition.DAMAGED){
                damagedCars += entry.getValue();
            }
        }
        System.out.println("New vehicles: " + newCars);
        System.out.println("Used vehicles: " + usedCars);
        System.out.println("Damaged vehicles: " + damagedCars);
    }
    public String[] allVehiclesList(){
        String [] list = new String[carList.size()];
        Integer j = 0;

        for (Vehicle i: carList.keySet()){
            list[j] = i.writeAllParameters() + "<br>Vehicle amount: " + carList.get(i) + "<br></body></html>";
            j++;
        }
        return list;
    }
}
