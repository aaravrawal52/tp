package interactable;

public abstract class InteractableEntity {
    protected int x;
    protected int y;
    protected int expDropped;
    protected int moneyDropped;
    protected String name;

    public abstract int getHealth();

    public abstract int getDefence();

    public abstract int getDamage();

    public abstract String getName();

    public abstract String getFilePath();

    public int getExp_dropped(){
        return expDropped;
    }

    public int getMoney_dropped(){
        return moneyDropped;
    }
    public int getHeight() {
        return 0;
    }

    public void setHeight() {}
}
