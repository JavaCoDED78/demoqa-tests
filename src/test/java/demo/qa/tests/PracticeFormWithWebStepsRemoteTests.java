package demo.qa.tests;

import demo.qa.pages.dto.PracticeFormDto;
import demo.qa.pages.dto.factory.DtoFactory;
import demo.qa.pages.dto.factory.PracticeFormDtoFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("remote")
public class PracticeFormWithWebStepsRemoteTests extends TestBaseRemote {

	private final DtoFactory<PracticeFormDto> dtoFactory = PracticeFormDtoFactory.getInstance();

	@Test
	void fillPracticeForm() {
		PracticeFormDto formDto = dtoFactory.create();
		WebStepsRemote webSteps = new WebStepsRemote();
		webSteps.openPracticeForm();
		webSteps.closeAdvertisement();
		webSteps.fillPracticeForm(formDto);
		webSteps.submitForm();
		webSteps.checkResultModal(formDto);
	}

}
