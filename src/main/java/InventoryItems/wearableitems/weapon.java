package inventoryitems.wearableitems;

import inventoryitems.Gear;

public class Weapon extends Gear {
    protected int additionalDamage;

    public Weapon(String name, String description, boolean isEquipped, int additionalDamage) {
        super(isEquipped);
        this.name = name;
        this.description = description;
        this.additionalDamage = additionalDamage;
    }

    public int getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(int additionalDamage) {
        this.additionalDamage = additionalDamage;
    }
}
