package demo.qa.pages.dto.factory;

import demo.qa.pages.dto.PracticeFormDto;

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

public class PracticeFormDtoFactory implements DtoFactory<PracticeFormDto> {

	private PracticeFormDtoFactory() {
	}

	@Override
	public PracticeFormDto create() {
		PracticeFormDto practiceFormDto = PracticeFormDto.builder()
				.firstName(generateRandomFirstName())
				.lastName(generateRandomLastName())
				.email(generateRandomEmail())
				.gender(generateRandomGender())
				.mobileNumber(generateRandomPhone())
				.day(generateRandomDay())
				.month(generateRandomMonth())
				.year(generateRandomYear())
				.subject1(generateRandomSubject())
				.subject2(generateAnotherSubject())
				.hobby(generateRandomHobby())
				.currentAddress(generateRandomAddress())
				.state(generateRandomState())
				.picture("Img.jpg")
				.build();
		practiceFormDto.setSubject2(generateAnotherSubject(practiceFormDto.getSubject1()));
		practiceFormDto.setCity(generateRandomCity(practiceFormDto.getState()));
		return practiceFormDto;
	}

	private static class Holder {
		private static final PracticeFormDtoFactory INSTANCE = new PracticeFormDtoFactory();
	}

	public static DtoFactory<PracticeFormDto> getInstance() {
		return Holder.INSTANCE;
	}
}
