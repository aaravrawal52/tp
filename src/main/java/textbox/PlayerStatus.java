package textbox;

import map.PlayerInventory;

public class PlayerStatus {
    private static final int MAX_HEALTH = 100000;
    private static final int MIN_VALUE = 0;
    private static final int DEFAULT_HEALTH = 100;

    private int playerHealth;
    private int playerMoney;
    private int playerExp;
    private int playerDamage;
    private PlayerInventory playerInventory;
    private int playerDamageAmp;

    public PlayerStatus(int startHealth, int startMoney, int startExp, int startDamage,
                        PlayerInventory playerInventory) {
        this.playerHealth = setValidHealth(startHealth);
        this.playerMoney = Math.max(startMoney, MIN_VALUE);
        this.playerExp = Math.max(startExp, MIN_VALUE);
        this.playerDamage = Math.max(startDamage, MIN_VALUE);
        this.playerInventory = playerInventory;
        this.playerDamageAmp = 0;
    }

    private int setValidHealth(int health) {
        if (health <= 1 || health > MAX_HEALTH) {
            System.out.println("INVALID HEALTH DETECTED. RESETTING TO DEFAULT");
            return DEFAULT_HEALTH;

        }
        return health;
    }

    public int getPlayerHealth() {
        return this.playerHealth;
    }

    public int getPlayerMoney() {
        return this.playerMoney;
    }

    public int getPlayerExp() {
        return this.playerExp;
    }

    public void setPlayerExp(int playerExp) {
        this.playerExp = playerExp;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public void harmHealth(int dmg) {
        int newHealth = this.playerHealth - dmg;
        setPlayerHealth(newHealth);
    }

    public void addMoney(int money) {
        long newMoney = (long) this.playerMoney + money;
        if (newMoney > Integer.MAX_VALUE) {
            this.playerMoney = Integer.MAX_VALUE;
        } else {
            this.playerMoney = (int) newMoney;
        }
    }

    public void addExp(int exp) {
        long newExp = (long) this.playerExp + exp;
        if (newExp > Integer.MAX_VALUE) {
            this.playerExp = Integer.MAX_VALUE;
        } else {
            this.playerExp = (int) newExp;
        }
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        if (playerInventory == null) throw new NullPointerException("Player inventory cannot be null.");
        this.playerInventory = playerInventory;
    }

    public int getPlayerDamageAmp() {
        return playerDamageAmp;
    }

    public void setPlayerDamageAmp(int dmg) {
        this.playerDamageAmp = Math.max(dmg, MIN_VALUE);
    }
}
