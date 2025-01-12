package demoqa.tests;

import demoqa.pages.PracticeFormPage;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PracticeFormWithPageObjectsTests extends TestBase {

	private final PracticeFormPage practiceFormPage = new PracticeFormPage();

	@Test
	void fillPracticeForm() {
		String firstName = "androsor";
		String lastName = "androsor99";
		String email = "androsor99@gmail.com";
		String mobileNumber = "3291406808";
		String gender = "Male";
		String hobby = "Sports";
		String day = "04";
		String month = "January";
		String year = "1978";
		String subject1 = "English";
		String subject2 = "Physics";
		List<String> subjects = List.of(subject1, subject2);
		String currentAddress = "Skrypnikova 21-120";
		String nameFile = "Img.jpg";
		String state = "Haryana";
		String city = "Karnal";

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
