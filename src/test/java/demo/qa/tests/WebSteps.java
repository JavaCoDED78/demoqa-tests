package demo.qa.tests;


import com.codeborne.selenide.WebDriverRunner;
import demo.qa.pages.PracticeFormPage;
import demo.qa.pages.dto.PracticeFormDto;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.List;

public class WebSteps {

	private final PracticeFormPage practiceFormPage = new PracticeFormPage();

	@Step("Open practice form")
	public void openPracticeForm() {
		practiceFormPage.openPage();
	}

	@Step("Close advertisement")
	public void closeAdvertisement() {
		practiceFormPage.closeAdvertisement();
	}

	@Step("Fill practice form")
	public void fillPracticeForm(PracticeFormDto dto) {
		practiceFormPage
				.setFirstName(dto.getFirstName())
				.setLastName(dto.getLastName())
				.setUserEmail(dto.getEmail())
				.setGender(dto.getGender())
				.setUserNumber(dto.getMobileNumber())
				.setDateOfBirth(dto.getDay(), dto.getMonth(), dto.getYear())
				.setSubject(dto.getSubject1())
				.setSubjects(List.of(dto.getSubject1(), dto.getSubject2()))
				.setHobby(dto.getHobby())
				.uploadPicture(dto.getPicture())
				.setAddress(dto.getCurrentAddress(), dto.getState(), dto.getCity());
	}

	@Step("Submit practice form")
	public void submitForm() {
		practiceFormPage.submitForm();
	}

	@Step("Check result modal")
	public void checkResultModal(PracticeFormDto dto) {
		practiceFormPage
				.checkResultsModalAppears()
				.checkResult("Student Name", dto.getFirstName() + " " + dto.getLastName())
				.checkResult("Student Email", dto.getEmail())
				.checkResult("Gender", dto.getGender())
				.checkResult("Mobile", dto.getMobileNumber())
				.checkResult("Date of Birth", dto.getDay() + " " + dto.getMonth() + "," + dto.getYear())
				.checkResult("Subjects", dto.getSubject1() + ", " + dto.getSubject2())
				.checkResult("Hobbies", dto.getHobby())
				.checkResult("Picture", dto.getPicture())
				.checkResult("Address", dto.getCurrentAddress())
				.checkResult("State and City", dto.getState()+ " " + dto.getCity());
	}

	@Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
	public byte[] takeScreenshot() {
		return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
	}
}
