package demoqa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

	@BeforeAll
	static void beforeAll() {
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

	@Test
	void fillPracticeForm() {
		String firstName = "androsor";
		String lastName = "androsor99";
		String email = "androsor99@gmail.com";
		String mobileNumber = "3291406808";
		String gender = "Male";
		String hobby = "Sports";
		String day = "4";
		String month = "January";
		String year = "1978";
		String subject1 = "English";
		String subject2 = "Physics";
		String currentAddress = "Skrypnikova 21-120";
		String nameFile = "Img.jpg";
		String state = "Haryana";
		String city = "Karnal";

		open("/automation-practice-form");
		$(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
		executeJavaScript("$('#fixedban').remove()");
		executeJavaScript("$('footer').remove()");

		//add personal information
		$("#firstName").sendKeys(firstName);
		$("#lastName").sendKeys(lastName);
		$("#userEmail").sendKeys(email);
		$("#genterWrapper").$(byText(gender)).click();
		$("#userNumber").setValue(mobileNumber);

		// choose date of birth
		$("#dateOfBirthInput").click();
		$(".react-datepicker__month-select").selectOptionContainingText(month);
		$(".react-datepicker__year-select").selectOptionContainingText(year);
		$(".react-datepicker__day.react-datepicker__day--004:not(.react-datepicker__day--outside-month)").click();

		// choose subjects and hobbies
		$("#subjectsInput").sendKeys("eng");
		$(byText(subject1)).click();
		$("#subjectsInput").sendKeys("phy");
		$(byText(subject2)).click();
		$("#hobbiesWrapper").$(byText(hobby)).click();

		//upload file
		$("#uploadPicture").uploadFromClasspath(nameFile);
		$("#uploadPicture").shouldHave(value(nameFile));

		//choose address, state, city
		$("#currentAddress").sendKeys(currentAddress);
		$("#state").click();
		$("#stateCity-wrapper").$(byText(state)).click();
		$("#city").click();
		$("#stateCity-wrapper").$(byText(city)).click();
		$("#submit").click();

		// check results
		$(".modal-dialog").should(appear);
		$("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
		checkRowWithText("Student Name", firstName + " " + lastName);
		checkRowWithText("Student Email", email);
		checkRowWithText("Gender", gender);
		checkRowWithText("Mobile", mobileNumber);
		checkRowWithText("Date of Birth", day + " " + month + "," + year);
		checkRowWithText("Subjects", subject1 + ", " + subject2);
		checkRowWithText("Hobbies", hobby);
		checkRowWithText("Picture", nameFile);
		checkRowWithText("Address", currentAddress);
		checkRowWithText("State and City", state + " " + city);
	}

	private void checkRowWithText(String label, String expectedValue) {
		$(".table").$(byText(label)).sibling(0).shouldHave(text(expectedValue));
	}

}
