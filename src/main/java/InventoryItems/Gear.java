package inventoryitems;

public abstract class Gear extends Item{
    protected boolean isEquipped;

    public Gear(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }
}
