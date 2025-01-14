package demo.qa.tests;

import demo.qa.pages.dto.PracticeFormDto;
import demo.qa.pages.dto.factory.DtoFactory;
import demo.qa.pages.dto.factory.PracticeFormDtoFactory;
import org.junit.jupiter.api.Test;

public class PracticeFormWithWebStepsTests extends TestBase {

	private final DtoFactory<PracticeFormDto> dtoFactory = new PracticeFormDtoFactory();

	@Test
	void fillPracticeForm() {
		PracticeFormDto formDto = dtoFactory.create();
		WebSteps webSteps = new WebSteps();
		webSteps.openPracticeForm();
		webSteps.closeAdvertisement();
		webSteps.fillPracticeForm(formDto);
		webSteps.submitForm();
		webSteps.checkResultModal(formDto);
		webSteps.takeScreenshot();

	}

}
