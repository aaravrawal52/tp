package inventoryitems;

public abstract class Item {
    protected String name;
    protected String description;
    protected int quantity;
    protected int sellPrice;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int additional) {
        this.quantity += additional;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getDamageAmpAmt() {
        return -1;
    }

    public int getHealAmt() {
        return -1;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
}
