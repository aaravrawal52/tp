package inventoryitems.wearableitems;

import inventoryitems.Gear;

public class Helmet extends Gear {
    protected int additionalHealth;
    public Helmet(String name, String description, boolean isEquipped, int additionalHealth) {
        super(isEquipped);
        this.name = name;
        this.description = description;
        this.additionalHealth = additionalHealth;
    }

    public int getAdditionalHealth() {
        return additionalHealth;
    }

    public void setAdditionalHealth(int additionalHealth) {
        this.additionalHealth = additionalHealth;
    }
}
