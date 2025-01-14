package demo.qa.util;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class DataUtils {

	private final static List<String> HOBBIES = List.of("Sports", "Reading", "Music");
	private final static List<String> SUBJECTS = List.of(
			"Maths", "Physics", "Chemistry", "Computer Science", "English", "History", "Social Studies", "Biology", "Arts"
	);
	private final static List<String> MONTHS = List.of(
			"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
	);
	private final static Map<String, List<String>> STATES_AND_CITIES = Map.of(
			"NCR", List.of("Delhi", "Gurgaon", "Noida"),
			"Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
			"Haryana", List.of("Karnal", "Panipat"),
			"Rajasthan", List.of("Jaipur", "Jaiselmer")
	);

	private final static Faker faker = new Faker();

	public static String generateRandomFirstName() {
		return faker.name().firstName();
	}

	public static String generateRandomLastName() {
		return faker.name().lastName();
	}

	public static String generateRandomEmail() {
		return faker.internet().emailAddress();
	}

	public static String generateRandomPhone() {
		return faker.phoneNumber().subscriberNumber(10);
	}

	public static String generateRandomGender() {
		return faker.demographic().sex();
	}

	public static String generateRandomHobby() {
		return HOBBIES.get(ThreadLocalRandom.current().nextInt(HOBBIES.size()));
	}

	public static String generateRandomDay() {
		int day = faker.number().numberBetween(1, 28);
		return day < 10 ? "0" + day : Integer.toString(day);
	}

	public static String generateRandomMonth() {
		return MONTHS.get(ThreadLocalRandom.current().nextInt(MONTHS.size()));
	}

	public static String generateRandomYear() {
		int year = faker.number().numberBetween(1970, 2022);
		return Integer.toString(year);
	}

	public static String generateRandomSubject() {
		return SUBJECTS.get(ThreadLocalRandom.current().nextInt(SUBJECTS.size()));
	}

	public static String generateAnotherSubject(String ... subjects) {
		List<String> availableSubjects = new ArrayList<>(SUBJECTS);
		availableSubjects.removeAll(Arrays.asList(subjects));
		return availableSubjects.get(ThreadLocalRandom.current().nextInt(availableSubjects.size()));
	}

	public static String generateRandomAddress() {
		return faker.address().streetAddress();
	}

	public static String generateRandomState() {
		List<String> states = new ArrayList<>(STATES_AND_CITIES.keySet());
		return states.get(ThreadLocalRandom.current().nextInt(states.size()));
	}

	public static String generateRandomCity(String state) {
		List<String> cities = STATES_AND_CITIES.get(state);
		return cities.get(ThreadLocalRandom.current().nextInt(cities.size()));
	}
}
