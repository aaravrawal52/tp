package map.BattleInterface;

import interactable.Enemy;
import interactable.InteractableEntity;
import filereader.FileReader;
import map.BaseMap;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;
import math.MathQuestion;
import math.MathPool;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BattleInterface extends BaseMap {
    protected PlayerStatus currentPlayer;
    protected TextBox currentTextBox;
    protected InteractableEntity currentEntity;


    public BattleInterface(PlayerStatus player, TextBox text, InteractableEntity entity) {
        this.currentPlayer = player;
        this.currentTextBox = text;
        this.currentEntity = entity;
    }

    @Override
    public void enableFight() {

    }

    @Override
    public void enableFight(Scanner in) {
        MathPool mathPool = new MathPool();
        mathPool.init();
        Ui ui = new Ui();
        int difficulty = 0;
        while (currentPlayer.getPlayerHealth() > 0 && currentEntity.getHealth() > 0) {
            int answer;
            Pattern pattern = Pattern.compile("^[--]?[0-9]+$"); // Pattern to check if the input is numeric
            ui.printPlayerStatus(currentPlayer);
            ui.printMap(mapData, (Enemy) currentEntity);
            MathQuestion mathQuestion = mathPool.getQuestionByDifficulty(difficulty);
            currentTextBox.setNextNarration(mathQuestion.getQuestion());
            ui.printTextBox(currentTextBox);
            String answerCommand = in.nextLine().trim();
            if (answerCommand.length() > 5) { // Check if input length exceeds 5 characters
                answerCommand = answerCommand.substring(0, 5); // Take only the first 5 characters
            }
            while (!pattern.matcher(answerCommand).matches()) { // Validate the trimmed input
                currentTextBox.setNextError("Answer must be an integer.");
                currentTextBox.setNextInstruction(mathQuestion.getQuestion());
                ui.printTextBox(currentTextBox);
                answerCommand = in.nextLine().trim();
                if (answerCommand.length() > 5) {
                    answerCommand = answerCommand.substring(0, 5); // Again trim input if needed
                }
            }
            answer = Integer.parseInt(answerCommand); // Parse the possibly truncated input
            if (mathQuestion.checkAns(answer)) {
                currentTextBox.setNextDialogue("You got the question CORRECT. You then proceed to swing as " +
                        "hard as you can");
                playerHitEnemy();
                difficulty += 1;
            } else {
                currentTextBox.setNextDialogue("You got the question WRONG. The enemy proceeds to attack you.");
                enemyHitPlayer();
            }
        }
    }


    public void initMap(int givenWidth, int givenHeight) {
        this.width = givenWidth;
        this.height = givenHeight;
        this.mapData = new ArrayList<>(height);

        FileReader fileReader = new FileReader(currentEntity.getFilePath());
        try {
            mapData = fileReader.readDesign();
        } catch (Exception e) {
            currentTextBox.setNextError("Unable to read file from local");
        }
    }


    public void playerHitEnemy() {
        if (currentEntity instanceof Enemy) {
            int dmgDone = currentPlayer.getPlayerDamage() + currentPlayer.getPlayerDamageAmp();
            ((Enemy) currentEntity).harmHealth(dmgDone);
            if (currentPlayer.getPlayerDamageAmp() != 0){
                currentPlayer.setPlayerDamageAmp(0);
            }
        }
    }

    public void enemyHitPlayer() {
        if (currentEntity instanceof Enemy) {
            int dmgDone = ((Enemy) currentEntity).getDamage();
            currentPlayer.harmHealth(dmgDone);
        }
    }


    public InteractableEntity getCurrentEntity() {
        return currentEntity;
    }

    @Override
    public boolean getEntityDeath() {
        return currentEntity.getHealth() <= 0;
    }

    @Override
    public boolean getPlayerDeath() {
        return currentPlayer.getPlayerHealth() <= 0;
    }

    @Override
    public void handleDeath(){
        Ui ui = new Ui();
        ui.printDeathMessage();
    }

    public PlayerStatus getCurrentPlayer() {
        return currentPlayer;
    }

    public void handleLootingByPlayer(){
        int exp = this.currentEntity.getExp_dropped();
        int money = this.currentEntity.getMoney_dropped();
        this.currentPlayer.addExp(exp);
        this.currentPlayer.addMoney(money);
        this.currentTextBox.setNextNarration("The beast was slain. You looted its cold dead corpse and found $" + money
                + " and gained " + exp + " exp.");
    }



}
