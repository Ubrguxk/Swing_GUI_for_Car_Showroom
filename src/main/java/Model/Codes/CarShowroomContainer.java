package Model.Codes;

import java.util.*;

public class CarShowroomContainer {
    public Map<String, CarShowroom> salons;

    public CarShowroomContainer(){
        salons = new HashMap<String,CarShowroom>();
    }
    public void addCenter(String x, double y){
        for(String i: salons.keySet()){
            if(x == i){
                System.out.println("Jest juz salon o takiej nazwie");
                return;
            }
        }
        CarShowroom newCarSh = new CarShowroom(x,y);
        this.salons.put(x,newCarSh);
    }
    public void removeCenter(String x){
        if (salons.containsKey(x)){
            salons.remove(x);
        }
        else {
            System.out.println("Nie ma takiego salonu");
        }
    }
    public void findEmpty(){
        // Map.Entry Ser ma liste
        // Map.Entry to jakas mapa z danymi wartosicami
        for (Map.Entry<String, CarShowroom> entry : salons.entrySet()) {
             if (entry.getValue().carNum() == 0) {
                System.out.println("Pusty salon: " + entry.getKey());
            }
        }
    }
    public void summanary(){
        for (Map.Entry<String, CarShowroom> entry : salons.entrySet()) {
            System.out.println("Showroom name: " + entry.getKey());
            System.out.println("How much is the showroom filled (0 - none, 1 - full): " + entry.getValue().calculateProc());
        }
    }

    public void Sort() {
        List<Map.Entry<String, CarShowroom>> sortedCenters = new ArrayList<>(salons.entrySet());
        Collections.sort(sortedCenters, new Comparator<Map.Entry<String, CarShowroom>>() {
            @Override
            public int compare(Map.Entry<String, CarShowroom> entry1, Map.Entry<String, CarShowroom> entry2) {
                return Double.compare(entry1.getValue().getCapacity(), entry2.getValue().getCapacity());
            }
        });

        // Creating new linkedHashMap to keep the sorted order
        LinkedHashMap<String, CarShowroom> sortedCarList = new LinkedHashMap<>();
        for (Map.Entry<String, CarShowroom> entry : sortedCenters) {
            sortedCarList.put(entry.getKey(), entry.getValue());
        }

        salons = sortedCarList;

    }
}
