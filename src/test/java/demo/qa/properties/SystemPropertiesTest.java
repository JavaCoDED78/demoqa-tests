package demo.qa.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTest {

	@Test
	@Tag("one_property")
	void simplePropertiesTest() {
		String browser = System.getProperty("browser");
		System.out.println(browser);
	}
}
