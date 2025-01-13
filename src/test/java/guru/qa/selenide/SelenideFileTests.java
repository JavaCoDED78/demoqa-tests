package guru.qa.selenide;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class SelenideFileTests {

	@Test
	void selenideDownloadFile() throws IOException {
		open("https://github.com/junit-team/junit5/blob/main/README.md");
		File file = $("[data-testid='raw-button']").download();

		Stream<String> lines = Files.lines(file.toPath());
		try (lines) {
			String textContent = lines
					.skip(2)
					.findFirst().orElse("");
			Assertions.assertThat(textContent).contains("This repository is the home of _JUnit 5_.");
			System.out.println(textContent);
		}
	}

	@Test
	void selenideUploadFile() {
		open("https://demoqa.com/automation-practice-form");
		$("input[type='file']").uploadFromClasspath("Img.jpg");
		$("#uploadPicture").shouldHave(value("Img.jpg"));
	}
}
