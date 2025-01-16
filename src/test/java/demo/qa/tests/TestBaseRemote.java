package demo.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demo.qa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBaseRemote {

	@BeforeAll
	static void beforeAll() {

		Configuration.baseUrl = System.getProperty("base_url", "https://demoqa.com");
		Configuration.browser = System.getProperty("browser", "chrome");
		Configuration.browserVersion = System.getProperty("browser_version", "126.0");
		Configuration.browserSize = System.getProperty("browser_size", "1920x1080");
		Configuration.remote = System.getProperty("remote_driver_url", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("selenoid:options", Map.of(
				"enableVNC", true,
				"enableVideo", true
		));

		Configuration.browserCapabilities = capabilities;
	}

	@BeforeEach
	void setUp() {
		SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
	}

	@AfterEach
	void addAttachments() {
		Attach.screenshotAs("Last screenshot");
		Attach.pageSource();
		Attach.browserConsoleLogs();
		Attach.addVideo();
	}
}
