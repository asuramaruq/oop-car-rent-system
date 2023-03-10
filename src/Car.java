public class Car {
    private int id;
    private int year;
    private String brand;
    private String model;
    private String type;
    private String color;
    private String status;//status can be bool 1/0
    //private bool status

    //price for rent for each specific car

    //private int pricePerHour
    //private int pricePerDay
    //private int pricePerWeek

    public Car(){

    }
    public Car(int id, int year, String brand, String model, String color, String type, String status){
        this.id =id;
        this.year = year;
        this.brand =brand;
        this.model = model;
        this.color = color;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
