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

import static java.lang.System.getProperty;

public class TestBaseRemote {

	@BeforeAll
	static void beforeAll() {

		Configuration.baseUrl = getProperty("base_url", "https://demoqa.com");
		Configuration.browser = getProperty("browser", "chrome");
		Configuration.browserVersion = getProperty("browser_version", "126.0");
		Configuration.browserSize = getProperty("browser_size", "1920x1080");
		Configuration.remote = getProperty("remote_driver_url", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

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
