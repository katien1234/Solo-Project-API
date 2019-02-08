import static org.junit.Assert.*;
import org.junit.Test;
import com.qa.business.service.QuizServiceImpl;
import com.qa.persistence.domain.Quiz;

public class APITests {

	QuizServiceImpl rules = new QuizServiceImpl();

	Quiz swearTest = new Quiz("Tom cruise has never fucking won an oscar", "True", "Fame");
	Quiz swearTest2 = new Quiz("Tom cruise has never won an oscar", "True", "Fame");

	Quiz answerTest = new Quiz("Tom cruise has never won an oscar", "True", "Fame");
	Quiz answerTest2 = new Quiz("Tom cruise has never won an oscar", "katien1234", "Fame");

	@Test
	public void testSwearWords() {
		assertEquals("Swear Words test failed", false, rules.checkSwearWords(swearTest.getQuestion()));
	}

	@Test
	public void testSwearWords2() {
		assertEquals("Swear Words test passed", true, rules.checkSwearWords(swearTest2.getQuestion()));
	}

	@Test
	public void testTrueOrFalse() {
		assertEquals("True or false test passed", true, rules.checkTrueOrFalse(answerTest.getAnswer()));
	}

	@Test
	public void testTrueOrFalse2() {
		assertEquals("True or false test failed", false, rules.checkTrueOrFalse(answerTest2.getAnswer()));
	}

}
