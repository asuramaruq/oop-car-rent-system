public class Car {
    private int id;
    private String manufacturer;
    private String carModel;
    private int productionYear;
    private String color;
    public Car(){
    }
    public Car(String manufacturer, String carModel, int productionYear, String color){
        this();
        setManufacturer(manufacturer);
        setCarModel(carModel);
        setProductionYear(productionYear);
        setColor(color);
    }
    public int getId(){
        return id;
    }
    public void setManufacturer(String manufacturer){
        this.manufacturer=manufacturer;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public void setCarModel(String carModel){
        this.carModel=carModel;
    }
    public String getCarModel(){
        return carModel;
    }
    public void setProductionYear(int productionYear){
        this.productionYear=productionYear;
    }
    public int getProductionYear(){
        return productionYear;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return color;
    }
}
