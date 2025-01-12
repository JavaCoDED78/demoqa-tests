package demoqa.pages.componets;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultModal {

	public void verifyModalAppears(String expectedText) {
		$(".modal-dialog").should(appear);
		$("#example-modal-sizes-title-lg").shouldHave(text(expectedText));
	}

	public void verifyResult(String key, String value) {
		$(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
	}
}
