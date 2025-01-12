package demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import demoqa.pages.componets.CalendarComponent;
import demoqa.pages.componets.RegistrationResultModal;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {

	private final CalendarComponent calendarComponent = new CalendarComponent();
	private final RegistrationResultModal registrationResultModal = new RegistrationResultModal();

	private static final String TITLE_TEXT = "Student Registration Form";
	private static final String TITLE_RESULT_MODAL = "Thanks for submitting the form";
	private static final String RELATIVE_URL = "/automation-practice-form";
	private final SelenideElement
			firstNameInput = $("#firstName"),
			lastNameInput = $("#lastName"),
			userEmailInput = $("#userEmail"),
			userNumberInput = $("#userNumber"),
			dateOfBirthInput = $("#dateOfBirthInput"),
			subjectsInput = $("#subjectsInput"),
			currentAddressInput = $("#currentAddress"),
			stateInput = $("#state"),
			cityInput = $("#city"),
			submitButton = $("#submit");


	public PracticeFormPage openPage() {
		open(RELATIVE_URL);
		$(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
		return this;
	}

	public PracticeFormPage closeAdvertisement() {
		executeJavaScript("$('#fixedban').remove()");
		executeJavaScript("$('footer').remove()");
		return this;
	}

	public PracticeFormPage setFirstName(String firstName) {
		firstNameInput.setValue(firstName);
		return this;
	}

	public PracticeFormPage setLastName(String lastName) {
		lastNameInput.setValue(lastName);
		return this;
	}

	public PracticeFormPage setUserEmail(String email) {
		userEmailInput.setValue(email);
		return this;
	}

	public PracticeFormPage setGender(String gender) {
		$("#genterWrapper").$(byText(gender)).click();
		return this;
	}

	public PracticeFormPage setUserNumber(String mobileNumber) {
		userNumberInput.setValue(mobileNumber);
		return this;
	}

	public PracticeFormPage setDateOfBirth(String day, String month, String year) {
		dateOfBirthInput.click();
		calendarComponent.setDate(day, month, year);
		return this;
	}

	public PracticeFormPage setSubject(String subject) {
		subjectsInput.sendKeys(subject.substring(0, 3));
		$(byText(subject)).click();
		return this;
	}

	public PracticeFormPage setSubjects(List<String> subjects) {
		subjects.forEach(this::setSubject);
		return this;
	}

	public PracticeFormPage setHobby(String hobby) {
		$("#hobbiesWrapper").$(byText(hobby)).click();
		return this;
	}

	public PracticeFormPage uploadPicture(String file) {
		$("#uploadPicture").uploadFromClasspath(file);
		return this;
	}

	public PracticeFormPage setAddress(String address, String state, String city) {
		currentAddressInput.setValue(address);
		stateInput.click();
		$("#stateCity-wrapper").$(byText(state)).click();
		cityInput.click();
		$("#stateCity-wrapper").$(byText(city)).click();
		return this;
	}

	public void submitForm() {
		submitButton.click();
	}

	public PracticeFormPage checkResultsModalAppears() {
		registrationResultModal.verifyModalAppears(TITLE_RESULT_MODAL);
		return this;
	}

	public PracticeFormPage checkResult(String key, String value) {
		registrationResultModal.verifyResult(key, value);
		return this;
	}
}
