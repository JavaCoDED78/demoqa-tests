package demo.qa.tests;

import demo.qa.pages.PracticeFormPage;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PracticeFormWithPageObjectsTests extends TestBase {

	private final PracticeFormPage practiceFormPage = new PracticeFormPage();

	@Test
	void fillPracticeForm() {
		var firstName = "androsor";
		var lastName = "androsor99";
		var email = "androsor99@gmail.com";
		var mobileNumber = "3291406808";
		var gender = "Male";
		var hobby = "Sports";
		var day = "04";
		var month = "January";
		var year = "1978";
		var subject1 = "English";
		var subject2 = "Physics";
		var currentAddress = "Skrypnikova 21-120";
		var nameFile = "Img.jpg";
		var state = "Haryana";
		var city = "Karnal";
		List<String> subjects = List.of(subject1, subject2);

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
