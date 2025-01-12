package demoqa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBoxTests {

	@BeforeAll
	static void beforeAll() {
		Configuration.holdBrowserOpen = true;
		Configuration.browserSize = "1920x1080";
		Configuration.baseUrl = "https://demoqa.com";
	}

	@Test
	void fillFormTest() {
		String username = "androsor99";
		String email = "androsor99@gmail.com";
		String currentAddress = "Some address 1";
		String permanentAddress = "Other address 1";

		open("/text-box");
		$(".text-center").shouldHave(text("Text Box"));
		$("#userName").setValue(username);
		$("#userEmail").setValue(email);
		$("#currentAddress").setValue(currentAddress);
		$("#permanentAddress").setValue(permanentAddress);
		$("#submit").click();

		$("#output").shouldBe(visible);
		$("#output #name").shouldHave(text("Name:" + username));
		$("#output #email").shouldHave(text("Email:" + email));
		$("#output #currentAddress").shouldHave(text("Current Address :" + currentAddress));
		$("#output #permanentAddress").shouldHave(text("Permananet Address :" + permanentAddress));
	}
}
