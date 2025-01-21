package view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import model.Mission;

public class WordFileGenerator {

	public static String generateMissionDocument(ArrayList<Mission> missions, String filePath) {
		// Create a new document
		XWPFDocument document = new XWPFDocument();

		// Convert and format the date
		String formattedDate = formatDate(missions.get(0).getDate());

		// Add the date as the title
		XWPFParagraph dateParagraph = document.createParagraph();
		dateParagraph.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun dateRun = dateParagraph.createRun();
		dateRun.setBold(true);
		dateRun.setFontSize(24);
		dateRun.setText(formattedDate);

		try {
			for (Mission mission : missions) {
				// Add a blank line
				document.createParagraph();

				// Add mission details
				XWPFParagraph missionParagraph = document.createParagraph();
				missionParagraph.setAlignment(ParagraphAlignment.CENTER);

				XWPFRun missionRun = missionParagraph.createRun();
				missionRun.setFontSize(18);
				missionRun.setBold(true);
				missionRun.setText("Driver: " + mission.getDriverName());
				missionRun.addBreak();
				missionRun.addBreak();

				XWPFRun routeRun = missionParagraph.createRun();
				routeRun.setFontSize(18);
				routeRun.setBold(false);
				routeRun.setText("(Start) Warehouse: " + mission.getWarehouseAddress());
				routeRun.addBreak();
				routeRun.setText("↓");
				routeRun.addBreak();
				// Add delivery addresses with arrows
				ArrayList<String> addresses = mission.getDeliveryAddresses();
				for (int i = 0; i < addresses.size(); i++) {
					routeRun.setText("(Stop " + (i + 1) + ") " + addresses.get(i));
					if (i < addresses.size() - 1) {
						routeRun.addBreak();
						routeRun.setText("↓");
						routeRun.addBreak();
					}
				}

				routeRun.addBreak();
				routeRun.setText("↓");
				routeRun.addBreak();
				routeRun.setText("(End) Warehouse: " + mission.getWarehouseAddress());
				routeRun.addBreak();

				// Add a blank line after each mission
				document.createParagraph();
			}

			// Write the document to the specified file path
			try (FileOutputStream out = new FileOutputStream(filePath)) {
				document.write(out);
			}

			System.out.println("Document generated successfully at: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				document.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File file = new File(filePath);
		return file.getAbsolutePath();
	}

	private static String formatDate(String date) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
			Date parsedDate = inputFormat.parse(date);
			return outputFormat.format(parsedDate);
		} catch (Exception e) {
			e.printStackTrace();
			return date;
		}
	}
}
