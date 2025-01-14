package demo.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestBase {

	@BeforeAll
	static void beforeAll() {
		SelenideLogger.addListener("allure", new AllureSelenide());
		Configuration.holdBrowserOpen = true;
		Configuration.browserSize = "1920x1080";
		Configuration.baseUrl = "https://demoqa.com";
		String nameFile = "Img.jpg";
		Path file = Path.of("src/test/resources/" + nameFile);
		try {
			if (Files.notExists(file)) {
				Files.createFile(file);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
