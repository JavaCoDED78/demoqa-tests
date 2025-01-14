package demo.qa.pages.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PracticeFormDto {

	private final String firstName;
	private final String lastName;
	private final String email;
	private final String gender;
	private final String mobileNumber;
	private final String day;
	private final String month;
	private final String year;
	private final String subject1;
	private String subject2;
	private final String hobby;
	private final String currentAddress;
	private final String state;
	private String city;
	private final String picture;
}
