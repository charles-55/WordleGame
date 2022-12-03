import java.util.ArrayList;

public class WordleModel {

    private final String correctWord;
    private int attemptNumber;
    private Status status;
    private final char[][] grid;
    private final ArrayList<WordleView> views;
    public static final int WORD_LENGTH = 5;
    public enum Status {UNDECIDED, WON, LOST}
    public enum Position {INCORRECT, MISPLACED, CORRECT}

    public WordleModel(String correctWord) throws Exception {
        if(correctWord.length() != WORD_LENGTH)
            throw new Exception("Input length is not equal to " + WORD_LENGTH + "!");
        this.correctWord = correctWord.toUpperCase();
        attemptNumber = 0;
        status = Status.UNDECIDED;

        grid = new char[WORD_LENGTH][WORD_LENGTH];
        for(int i = 0; i < WORD_LENGTH; i++) {
            for(int j = 0; j < WORD_LENGTH; j++) {
                grid[i][j] = ' ';
            }
        }

        views = new ArrayList<>();
    }

    /**
     * Get the number of attempts.
     * @return The number of attempts.
     */
    public int getAttemptNumber() {
        return attemptNumber;
    }

    /**
     * Get the status.
     * @return The current game status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Get the grid.
     * @return The current game grid.
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * Add a view.
     * @param view View to add.
     */
    public void addView(WordleView view) {
        views.add(view);
    }

    /**
     * Play a guess.
     * @param guess The word that was guessed
     * @return An array of the position value of each letter // for test purposes only
     */
    public Position[] play(String guess) {
        /* Check if the guess is the right length */
        if(guess.length() != WORD_LENGTH) {
            for(WordleView view : views)
                view.handleInvalidPlay();
            return null;
        }

        Position[] testPositions = new Position[WORD_LENGTH]; // for test purposes only
        guess = guess.toUpperCase();
        /* Check each letter to see if it is in the word and their position */
        for(int i = 0; i < WORD_LENGTH; i++) {
            grid[attemptNumber][i] = guess.charAt(i);
            Position position = null;
            if(guess.charAt(i) == correctWord.charAt(i))
                position = Position.CORRECT; // if in the right position
            else {
                for(int j = 0; j < WORD_LENGTH; j++) {
                    if(guess.charAt(i) == correctWord.charAt(j)) {
                        position = Position.MISPLACED; // if in the word but wrong position
                        break;
                    }
                }
                if(position == null)
                    position = Position.INCORRECT; // if not in word
            }
            testPositions[i] = position;
            for(WordleView view : views) {
                view.handlePlayUpdate(new WordlePlayEvent(this, attemptNumber, i, guess.charAt(i), position));
            }
        }

        attemptNumber++;
        updateStatus(guess);
        return testPositions;
    }

    /**
     * Update the status based on a guess or number of attempts
     * @param guess The word that was guessed
     */
    private void updateStatus(String guess) {
        if(guess.equals(correctWord))
            status = Status.WON;
        else if(attemptNumber == WORD_LENGTH)
            status = Status.LOST;

        for(WordleView view : views)
            view.handleStatusUpdate(status);
    }
}
