package Hint;

public class Trigger {
    protected String message;
    protected int xInitial;
    protected int yInitial;
    protected int xEnding;
    protected int yEnding;

    public Trigger(int xStart, int yStart, int xEnd, int yEnd, String newMessage){
        message = newMessage;
        xInitial = xStart;
        yInitial = yStart;
        xEnding = xEnd;
        yEnding = yEnd;
    }

    public int getXInitial() {
        return xInitial;
    }

    public int getYInitial() {
        return yInitial;
    }

    public int getXEnding() {
        return xEnding;
    }

    public int getYEnding() {
        return yEnding;
    }

    public String getMessage() {
        return message;
    }
}
