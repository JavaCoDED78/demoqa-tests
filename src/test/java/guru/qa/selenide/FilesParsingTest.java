package guru.qa.selenide;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import guru.qa.selenide.model.Glossary;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {

	private final ClassLoader cl = FilesParsingTest.class.getClassLoader();

	@Test
	void pdfParseTest() throws Exception {
		open("https://junit.org/junit5/docs/current/user-guide/");
		File downloadedPdf = $("a[href='junit-user-guide-5.11.4.pdf']").download();
		PDF content = new PDF(downloadedPdf);
		assertThat(content.text).contains("JUnit 5 User Guide");
	}

	@Test
	void xlsParseTest() throws Exception {
		try (InputStream resourceAsStream = cl.getResourceAsStream("sample-xlsx-file.xlsx")) {
			XLS content = new XLS(resourceAsStream);
			assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("Dulce");
		}
	}
	@Test
	void csvParseTest() throws Exception {
		try (
				InputStream resource = cl.getResourceAsStream("qa_guru.csv");
				CSVReader reader = new CSVReader(new InputStreamReader(resource))
		) {
			List<String[]> content = reader.readAll();
			assertThat(content.get(0)[1]).contains("lesson");
		}
	}

	@Test
	void zipParseTest() throws Exception {
		try (
				InputStream resource = cl.getResourceAsStream("sample.txt.zip");
				ZipInputStream zis = new ZipInputStream(resource)
		) {
			ZipEntry entry;
			while((entry = zis.getNextEntry()) != null) {
				assertThat(entry.getName()).contains("sample.txt");
			}
		}
	}

	@Test
	void jsonParseTest() throws Exception {
		Gson gson = new Gson();
		try (
				InputStream resource = cl.getResourceAsStream("glossary.json");
				InputStreamReader reader = new InputStreamReader(resource)
		) {
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
			assertThat(jsonObject.get("gloss_div").getAsJsonObject().get("title").getAsString()).isEqualTo("S");
			assertThat(jsonObject.get("gloss_div").getAsJsonObject().get("flag").getAsBoolean()).isTrue();
		}
	}

	@Test
	void jsonParseImprovedTest() throws Exception {
		Gson gson = new Gson();
		try (
				InputStream resource = cl.getResourceAsStream("glossary.json");
				InputStreamReader reader = new InputStreamReader(resource)
		) {
			Glossary glossary = gson.fromJson(reader, Glossary.class);
			assertThat(glossary.title).isEqualTo("example glossary");
			assertThat(glossary.glossDiv.title).isEqualTo("S");
			assertThat(glossary.glossDiv.flag).isTrue();
		}
	}
}
