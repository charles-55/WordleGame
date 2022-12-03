import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordleModelTest {

    private WordleModel model;

    @Before
    public void setUp() throws Exception {
         model = new WordleModel("HELLO");
    }

    @After
    public void tearDown() {
        assertNull(model = null);
    }

    @Test
    public void testWonGame() {
        WordleModel.Position[] expected, actual;

        expected = new WordleModel.Position[] {WordleModel.Position.INCORRECT, WordleModel.Position.CORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.MISPLACED};
        actual = model.play("MEDAL");
        assertArrayEquals(expected, actual);
        assertEquals(1, model.getAttemptNumber());
        assertEquals(WordleModel.Status.UNDECIDED, model.getStatus());

        expected = new WordleModel.Position[] {WordleModel.Position.CORRECT, WordleModel.Position.CORRECT, WordleModel.Position.CORRECT, WordleModel.Position.CORRECT, WordleModel.Position.CORRECT};
        actual = model.play("HELLO");
        assertArrayEquals(expected, actual);
        assertEquals(2, model.getAttemptNumber());
        assertEquals(WordleModel.Status.WON, model.getStatus());
    }

    @Test
    public void testLostGame() {
        WordleModel.Position[] expected, actual;

        expected = new WordleModel.Position[] {WordleModel.Position.INCORRECT, WordleModel.Position.CORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.MISPLACED};
        actual = model.play("MEDAL");
        assertArrayEquals(expected, actual);
        assertEquals(1, model.getAttemptNumber());
        assertEquals(WordleModel.Status.UNDECIDED, model.getStatus());

        expected = new WordleModel.Position[] {WordleModel.Position.INCORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.MISPLACED, WordleModel.Position.INCORRECT};
        actual = model.play("FRIES");
        assertArrayEquals(expected, actual);
        assertEquals(2, model.getAttemptNumber());
        assertEquals(WordleModel.Status.UNDECIDED, model.getStatus());

        expected = new WordleModel.Position[] {WordleModel.Position.INCORRECT, WordleModel.Position.MISPLACED, WordleModel.Position.INCORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.MISPLACED};
        actual = model.play("PLACE");
        assertArrayEquals(expected, actual);
        assertEquals(3, model.getAttemptNumber());
        assertEquals(WordleModel.Status.UNDECIDED, model.getStatus());

        expected = new WordleModel.Position[] {WordleModel.Position.CORRECT, WordleModel.Position.CORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.INCORRECT, WordleModel.Position.INCORRECT};
        actual = model.play("HEART");
        assertArrayEquals(expected, actual);
        assertEquals(4, model.getAttemptNumber());
        assertEquals(WordleModel.Status.UNDECIDED, model.getStatus());

        expected = new WordleModel.Position[] {WordleModel.Position.INCORRECT, WordleModel.Position.CORRECT, WordleModel.Position.CORRECT, WordleModel.Position.CORRECT, WordleModel.Position.CORRECT};
        actual = model.play("JELLO");
        assertArrayEquals(expected, actual);
        assertEquals(5, model.getAttemptNumber());
        assertEquals(WordleModel.Status.LOST, model.getStatus());
    }

    @Test
    public void testGrid() {
        String guess;

        guess = "MEDAL";
        model.play(guess);
        for(int i = 0; i < WordleModel.WORD_LENGTH; i++) {
            assertEquals(guess.charAt(i), model.getGrid()[0][i]);
        }

        guess = "FRIES";
        model.play(guess);
        for(int i = 0; i < WordleModel.WORD_LENGTH; i++) {
            assertEquals(guess.charAt(i), model.getGrid()[1][i]);
        }

        guess = "PLACE";
        model.play(guess);
        for(int i = 0; i < WordleModel.WORD_LENGTH; i++) {
            assertEquals(guess.charAt(i), model.getGrid()[2][i]);
        }
    }
}