package demo.qa.tests;

import demo.qa.pages.PracticeFormPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.webdriver;
import static demo.qa.util.DataUtils.generateAnotherSubject;
import static demo.qa.util.DataUtils.generateRandomAddress;
import static demo.qa.util.DataUtils.generateRandomCity;
import static demo.qa.util.DataUtils.generateRandomDay;
import static demo.qa.util.DataUtils.generateRandomEmail;
import static demo.qa.util.DataUtils.generateRandomFirstName;
import static demo.qa.util.DataUtils.generateRandomGender;
import static demo.qa.util.DataUtils.generateRandomHobby;
import static demo.qa.util.DataUtils.generateRandomLastName;
import static demo.qa.util.DataUtils.generateRandomMonth;
import static demo.qa.util.DataUtils.generateRandomPhone;
import static demo.qa.util.DataUtils.generateRandomState;
import static demo.qa.util.DataUtils.generateRandomSubject;
import static demo.qa.util.DataUtils.generateRandomYear;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class PracticeFormWithPageObjectsTests extends TestBase {

	private final PracticeFormPage practiceFormPage = new PracticeFormPage();

	@Test
	void fillPracticeForm() {
		var firstName = generateRandomFirstName();
		var lastName = generateRandomLastName();
		var email = generateRandomEmail();
		var mobileNumber = generateRandomPhone();
		var gender = generateRandomGender();
		var hobby = generateRandomHobby();
		var day = generateRandomDay();
		var month = generateRandomMonth();
		var year = generateRandomYear();
		var subject1 = generateRandomSubject();
		var subject2 = generateAnotherSubject(subject1);
		var currentAddress = generateRandomAddress();
		var state = generateRandomState();
		var city = generateRandomCity(state);
		var nameFile = "Img.jpg";
		var subjects = List.of(subject1, subject2);

		step("Open practice form", () -> {
			practiceFormPage.openPage();
		});
		step("Close advertisement", () -> {
			practiceFormPage.closeAdvertisement();
		});
		step("Fill practice form", () -> {
			practiceFormPage
					.setFirstName(firstName)
					.setLastName(lastName)
					.setUserEmail(email)
					.setGender(gender)
					.setUserNumber(mobileNumber)
					.setDateOfBirth(day, month, year)
					.setSubjects(subjects)
					.setHobby(hobby)
					.uploadPicture(nameFile)
					.setAddress(currentAddress, state, city);
		});
		step("Submit practice form", practiceFormPage::submitForm);
		step("Check results modal", () -> {
			practiceFormPage
					.checkResultsModalAppears()
					.checkResult("Student Email", email)
					.checkResult("Gender", gender)
					.checkResult("Mobile", mobileNumber)
					.checkResult("Date of Birth", day + " " + month + "," + year)
					.checkResult("Subjects", subject1 + ", " + subject2)
					.checkResult("Hobbies", hobby)
					.checkResult("Picture", nameFile)
					.checkResult("Address", currentAddress)
					.checkResult("State and City", state + " " + city);
			attachment("Source", webdriver().driver().source());
		});
	}

}
