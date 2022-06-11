import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCases
{
    QuizViewController qvc;

    @Before
    public void setUp()
    {
        qvc = new QuizViewController();
    }

    @Test
    public void testGetPercentageText()
    {
        assertEquals(qvc.getPercentageText(8, 10), "You got 80%");
        assertEquals(qvc.getPercentageText(5, 10), "You got 50%");
        assertEquals(qvc.getPercentageText(1, 10), "You got 10%");
    }

    @Test
    public void testIsAnswerCorrect()
    {
        assertFalse(qvc.isAnswerCorrect(4, 1));
        assertTrue(qvc.isAnswerCorrect(4, 4));
    }

    @Test
    public void testGetScreenMessage()
    {
        assertEquals(qvc.getScreenMessage(10), "Nice Try!");
        assertEquals(qvc.getScreenMessage(35), "You're Getting There!");
        assertEquals(qvc.getScreenMessage(60), "Great Job!");
        assertEquals(qvc.getScreenMessage(80), "Awesome Work!");
        assertEquals(qvc.getScreenMessage(100), "Bravo! Amazing! Spectacular!");
    }
}
