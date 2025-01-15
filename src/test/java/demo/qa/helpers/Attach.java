package demo.qa.helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {

	private static final Logger logger = LoggerFactory.getLogger(Attach.class);


	@Attachment(value = "{attachName}", type = "image/png")
	public static byte[] screenshotAs(String attachName) {
		return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "Page source", type = "text/plain")
	public static byte[] pageSource() {
		return Objects.requireNonNull(getWebDriver().getPageSource()).getBytes(StandardCharsets.UTF_8);
	}

	@Attachment(value = "{attachName}", type = "text/plain")
	public static String attachAsText(String attachName, String message) {
		return message;
	}

	public static void browserConsoleLogs() {
		attachAsText(
				"Browser console logs",
				String.join(System.lineSeparator(), Selenide.getWebDriverLogs(BROWSER))
		);
	}

	@Attachment(value = "Video", type = "text/html", fileExtension = ".html")
	public static String addVideo() {
		return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
		       + getVideoUrl()
		       + "' type='video/mp4'></video></body></html>";
	}

	public static URL getVideoUrl() {
		String videoUrl = "https://selenoid.autotests.cloud/video/" + Selenide.sessionId() + ".mp4";
//        System.out.println(sessionId());
		try {
			return new URL(videoUrl);
		} catch (MalformedURLException e) {
			logger.error("Failed to construct URL for video: {}", videoUrl, e);
		}
		return null;
	}
}
