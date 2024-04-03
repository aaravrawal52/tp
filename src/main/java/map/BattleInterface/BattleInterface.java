package map.battleinterface;

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

        while (currentPlayer.getPlayerHealth() > 0 && currentEntity.getHealth() > 0) {
            int answer;
            Pattern pattern = Pattern.compile("^[--]?[0-9]+$");
            ui.printPlayerStatus(currentPlayer);
            ui.printMap(mapData, (Enemy) currentEntity);
            MathQuestion mathQuestion = mathPool.getQuestionByDifficulty(0);
            ui.printQuestion(mathQuestion);
            String answerCommand = in.nextLine().trim();
            while (!pattern.matcher(answerCommand).matches()) {
                currentTextBox.setNextError("Answer must be an integer.");
                currentTextBox.setNextInstruction(mathQuestion.getQuestion());
                ui.printTextBox(currentTextBox);
                answerCommand = in.nextLine().trim();
            }
            answer = Integer.parseInt(answerCommand);
            if (mathQuestion.checkAns(answer)) {
                System.out.println("CORRECT!!!");

                playerHitEnemy();
            } else {
                System.out.println("WRONG ANSWER!!!");
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
            mapData = fileReader.readEnemyDesign();
        } catch (Exception e) {
            // display exception, see how sihan wants to do.
        }
    }


    public void playerHitEnemy() {
        if (currentEntity instanceof Enemy) {
            int dmgDone = 10;
            ((Enemy) currentEntity).harmHealth(dmgDone);
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
        System.exit(0);
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
