package command;

public enum CommandType {
    FIGHT("(?i)\\h*(f|fight)\\h*"),
    RUN("(?i)\\h*(r|run)\\h*"),
    MOVE_FORWARD("(?i)\\h*(w)(\\h+\\d{1,5})?\\h*"),
    MOVE_DOWNWARD("(?i)\\h*(s)(\\h+\\d{1,5})?\\h*"),
    MOVE_LEFT("(?i)\\h*(a)(\\h+\\d{1,5})?\\h*"),
    MOVE_RIGHT("(?i)\\h*(d)(\\h+\\d{1,5})?\\h*"),
    INTERACT("(?i)\\h*(e)\\h*"),
    QUIT("(?i)\\h*(q|quit)\\h*"),
    HELP("(?i)\\h*(h|help)\\h*"),
    EXIT("(?i)\\h*(exit)\\h*"),
    ERROR(""),
    INVENTORY("(?i)\\h*(i|inventory)\\h*"),
    USE_ITEM("(?i)\\h*(use)(\\h+\\d+|\\h+\\w+)?\\h*"),
    CLOSE_INV("(?i)\\h*(close)\\h*"),
    RESET("(?i)\\h*(reset)\\h*");
    final String regExpression;

    CommandType(String regExpression) {
        this.regExpression = regExpression;
    }

    public String getRegExpression() {
        return regExpression;
    }
}
