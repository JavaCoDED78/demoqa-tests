package demo.qa.tests;

import demo.qa.pages.PracticeFormPage;
import demo.qa.util.DataUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PracticeFormWithPageObjectsTests extends TestBase {

	private final PracticeFormPage practiceFormPage = new PracticeFormPage();

	@Test
	void fillPracticeForm() {
		var firstName = DataUtils.generateRandomFirstName();
		var lastName = DataUtils.generateRandomLastName();
		var email = DataUtils.generateRandomEmail();
		var mobileNumber = DataUtils.generateRandomPhone();
		var gender = DataUtils.generateRandomGender();
		var hobby = DataUtils.generateRandomHobby();
		var day = DataUtils.generateRandomDay();
		var month = DataUtils.generateRandomMonth();
		var year = DataUtils.generateRandomYear();
		var subject1 = DataUtils.generateRandomSubject();
		var subject2 = DataUtils.generateAnotherSubject(subject1);
		var currentAddress = DataUtils.generateRandomAddress();
		var state = DataUtils.generateRandomState();
		var city = DataUtils.generateRandomCity(state);
		var nameFile = "Img.jpg";
		var subjects = List.of(subject1, subject2);

		practiceFormPage
				.openPage()
				.closeAdvertisement()
				.setFirstName(firstName)
				.setLastName(lastName)
				.setUserEmail(email)
				.setGender(gender)
				.setUserNumber(mobileNumber)
				.setDateOfBirth(day, month, year)
				.setSubjects(subjects)
				.setHobby(hobby)
				.uploadPicture(nameFile)
				.setAddress(currentAddress, state, city)
				.submitForm();

		practiceFormPage.checkResultsModalAppears()
				.checkResult("Student Name", firstName + " " + lastName)
				.checkResult("Student Email", email)
				.checkResult("Gender", gender)
				.checkResult("Mobile", mobileNumber)
				.checkResult("Date of Birth", day + " " + month + "," + year)
				.checkResult("Subjects", subject1 + ", " + subject2)
				.checkResult("Hobbies", hobby)
				.checkResult("Picture", nameFile)
				.checkResult("Address", currentAddress)
				.checkResult("State and City", state + " " + city);
	}

}
