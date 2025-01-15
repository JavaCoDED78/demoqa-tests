package simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("simple")
public class SkippedTest {

	@Test
	@Disabled("some reason1")
	void test1() {
		assertTrue(true);
	}

	@Test
	@Disabled("some reason2")
	void test2() {
		assertTrue(true);
	}
}
