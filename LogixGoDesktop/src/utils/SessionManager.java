package utils;

import java.io.*;
import java.util.Properties;

public class SessionManager {
    private static final String SESSION_FILE = "src/resource/session.properties"; // Adjust the path as needed
    private static final String USER_ID_KEY = "userId";

    // Save session ID as an integer
    public static void saveSession(int userId) {
        try (FileOutputStream fos = new FileOutputStream(SESSION_FILE)) {
            Properties properties = new Properties();
            properties.setProperty(USER_ID_KEY, String.valueOf(userId));
            properties.store(fos, "User Session Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Retrieve session ID as an integer
    public static Integer getSession() {
        try (FileInputStream fis = new FileInputStream(SESSION_FILE)) {
            Properties properties = new Properties();
            properties.load(fis);
            String userId = properties.getProperty(USER_ID_KEY);
            return userId != null ? Integer.parseInt(userId) : null;
        } catch (IOException | NumberFormatException e) {
            return null; // Return null if no session exists or an error occurs
        }
    }

    // Clear session data but keep the file
    public static void clearSession() {
        try (FileOutputStream fos = new FileOutputStream(SESSION_FILE)) {
            Properties properties = new Properties(); // Empty properties
            properties.store(fos, "Cleared Session Data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
