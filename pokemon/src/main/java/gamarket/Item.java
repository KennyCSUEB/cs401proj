package gamarket;

public abstract class Item {
    String description = "Unknown Item";
    double cost =  0;
    String type;

    public String getDescription () {
        return this.description;
    }

    public double getCost () {
        return this.cost;
    }
    
    public void use () {}

	public String getType() {
        return this.type;
    }
}