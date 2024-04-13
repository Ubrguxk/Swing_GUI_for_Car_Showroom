package Model.Codes;

public class Vehicle implements Comparable<Vehicle> {
    String label;
    String model;
    ItemCondition state;
    Double price;
    Integer prodYear;
    double mileage;
    double engineCapacity;

    public Vehicle(String userLabel, String userModel, ItemCondition userState, Double userPrice, Integer userProdYear, double userMileage, double userEngineCapacity){
        this.label = userLabel;
        this.model = userModel;
        this.state = userState;
        this.price = userPrice;
        this.prodYear = userProdYear;
        this.mileage = userMileage;
        this.engineCapacity = userEngineCapacity;
    }

    public void print(){
        System.out.println("Label " + this.label);
        System.out.println("Controller " + this.model);
        System.out.println("State " + this.state);
        System.out.println("Price " + this.price);
        System.out.println("Production Year " + this.prodYear);
        System.out.println("Mileage " + this.mileage);
        System.out.println("Engine Capacity " + this.engineCapacity);
    }
    @Override
    public int compareTo(Vehicle other) {
        return this.model.compareTo(other.model);
    }

    @Override
    public boolean equals(Object object) {
        Vehicle anotherPerson= (Vehicle) object; //downcasting from object to Person
        if (this.model != anotherPerson.model) {
            return false;
        }
        return true;
    }

    public String writeAllParameters() {
        String returned = "<html><body>Label: " + this.label + "<br>Model: " + this.model + "<br>State: " + this.state + "<br>Price: " + this.price + "<br>Production Year: " + this.prodYear + "<br>Mileage: " + this.mileage + "<br>Engine Capacity: " + this.engineCapacity;
        return returned;
    }
}
