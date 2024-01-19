/**
 * 
 */
package lan.parkinson.bfcu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.Map;
import java.util.logging.LogManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author dean
 *
 */
class TestGorilla {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		InputStream logcfg = TestGorilla.class.getResourceAsStream("/logging.properties");
		LogManager.getLogManager().readConfiguration(logcfg);
	}

//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterEach
//	void tearDown() throws Exception {
//	}

	@Test
	void testOneStudent() {
		String input = "[Joe Cool 9,10,11]";
		Gorilla it = new Gorilla();
		Map<String,Integer> results = it.parseStudents(input);
		assertNotNull(results);
		assertEquals(1, results.size());
		assertTrue(results.containsKey("Joe Cool"));
		assertEquals(10, results.get("Joe Cool"));
	}

	@Test
	void testMultStudents() {
		String input = "[Joe Cool 9,10,11] [Fred Flinstone 12;14-13]";
		Gorilla it = new Gorilla();
		Map<String,Integer> results = it.parseStudents(input);
		assertNotNull(results);
		assertEquals(2, results.size());
		assertTrue(results.containsKey("Joe Cool"));
		assertEquals(10, results.get("Joe Cool"));
		assertTrue(results.containsKey("Fred Flinstone"));
		assertEquals(13, results.get("Fred Flinstone"));
	}
}
