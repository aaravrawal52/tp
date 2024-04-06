package command;

public enum CommandType {
    FIGHT("(?i)\\h*(f|fight)\\h*"),
    RUN("(?i)\\h*(r|run)\\h*"),
    MOVE_FORWARD("(?i)\\h*(w)(\\h+\\d+)?\\h*"),
    MOVE_DOWNWARD("(?i)\\h*(s)(\\h+\\d+)?\\h*"),
    MOVE_LEFT("(?i)\\h*(a)(\\h+\\d+)?\\h*"),
    MOVE_RIGHT("(?i)\\h*(d)(\\h+\\d+)?\\h*"),
    INTERACT("(?i)\\h*(e)\\h*"),
    QUIT("(?i)\\h*(q|quit)\\h*"),
    HELP("(?i)\\h*(h|help)\\h*"),
    ERROR(""),
    INVENTORY("(?i)\\h*(i|inventory)\\h*"),
    INV_NEXT("(?i)\\h*(n|next)\\h*"),
    INV_PREV("(?i)\\h*(p|prev|previous)\\h*"),
    USE_ITEM("(?i)\\h*(use)(\\h+\\d+|\\h+\\w+)?\\h*"),
    SELL_ITEM("(?i)\\h*(sell)(\\h+\\d+|\\h+\\w+)?\\h*"),
    CLOSE_INV("(?i)\\h*(c|close)\\h*"); // to delete aft sihan implements
    final String regExpression;

    CommandType(String regExpression) {
        this.regExpression = regExpression;
    }

    public String getRegExpression() {
        return regExpression;
    }
}
