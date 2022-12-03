import java.util.EventObject;

public class WordlePlayEvent extends EventObject {

    private final int x;
    private final int y;
    private final char letter;
    private final WordleModel.Position position;

    public WordlePlayEvent(WordleModel model, int x, int y, char letter, WordleModel.Position position) {
        super(model);
        this.x = x;
        this.y = y;
        this.letter = letter;
        this.position = position;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getLetter() {
        return letter;
    }

    public WordleModel.Position getPosition() {
        return position;
    }
}
