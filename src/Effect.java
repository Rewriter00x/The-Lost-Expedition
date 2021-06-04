public class Effect {

    public Effect(int effect) {
        this.effect=effect;
    } // Card actions move or die

    public Effect(int effect, boolean mode) { // effect 1 2 3
        this.effect=effect;
        this.mode=mode;
    }

    public Effect(Token token, boolean mode) { // for tokens
        this.token=token;
        this.mode=mode;
    }

    public void doEffect() {
        if (token==null) {
            if (effect >= 1 && effect <= 3) {
                if (mode) {
                    switch (effect) {
                        case 1:
                            frame.healPanel();
                            break;
                        case 2:
                            frame.addFood();
                            frame.textPanel("Food added");
                            break;
                        case 3:
                            frame.addBullet();
                            frame.textPanel("Bullet added");
                            break;
                    }
                } else {
                    switch (effect) {
                        case 1:
                            frame.damegePanel();
                            break;
                        case 2:
                            frame.foodRemovePanel();
                            break;
                        case 3:
                            frame.removeBullet();
                            frame.textPanel("Bullet removed");
                            break;
                    }
                }
            } else {
                switch (effect) {
                    case -1:
                        frame.killPanel();
                        break;
                    case 0:
                        frame.moveMan();
                        frame.incPathOn();
                        frame.textPanel("You moved one step");
                        break;
                    case 4:
                        frame.removeNextCard();
                        break;
                    case 5:
                        frame.textPanel("Swap add here");
                        break;
                    case 6:
                        frame.removeLastRoadCard();
                        break;
                    case 7:
                        frame.putCardLast();
                        frame.textPanel("Card added");
                        break;
                }
            }
        }
        else {
            if (mode) {
                frame.addToken(token);
                frame.addExpCard("Cards/card" + frame.getCurrentCard().getNumber() + ".png");
                frame.textPanel("Token added");
            }
            else {
                frame.tokenRemovePanel(token.getTokens().get(0));
            }
        }
    }

    public static void setFrame(GameFrame frame) {Effect.frame=frame;}

    public int getEffect() {return effect;}

    public boolean getMode() {return mode;}

    private int effect=-2;
    private boolean mode;
    private Token token=null;

    private static GameFrame frame;

    public static final int KILL = -1;

    public static final int MOVE = 0;

    public static final int HEALTH = 1;

    public static final int FOOD = 2;

    public static final int BULLET = 3;

    public static final int SKIP_CARD = 4;

    public static final int SWAP_CARD = 5;

    public static final int REMOVE_CARD = 6;

    public static final int ADD_CARD = 7;

    public static final boolean ADD = true;

    public static final boolean REMOVE = false;
}
