package textbox;


import map.PlayerInventory;

public class PlayerStatus {
    private int playerHealth;
    private int playerMoney;
    private int playerExp;
    private int playerDamage;
    private PlayerInventory playerInventory;
    private int playerDamageAmp;

    public PlayerStatus(int startHealth, int startMoney, int startExp, int startDamage,
                        PlayerInventory playerInventory){
        this.playerHealth = startHealth;
        this.playerMoney = startMoney;
        this.playerExp = startExp;
        this.playerDamage = startDamage;
        this.playerInventory = playerInventory;
        this.playerDamageAmp = 0;
    }

    public int getPlayerHealth(){
        return this.playerHealth;
    }

    public int getPlayerMoney(){
        return this.playerMoney;
    }

    public int getPlayerExp(){
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

    public void harmHealth(int dmg){
        playerHealth -= dmg;
    }

    public void addMoney(int money){
        playerMoney += money;
    }

    public void addExp(int exp){
        playerExp += exp;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public int getPlayerDamageAmp(){
        return playerDamageAmp;
    }

    public void setPlayerDamageAmp(int dmg){
        playerDamageAmp = dmg;
    }
}
