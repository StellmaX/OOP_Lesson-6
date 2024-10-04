import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию, имя, отчество, дату рождения (dd.mm.yyyy), номер телефона, пол (m/f), разделенные пробелами:");

        String input = scanner.nextLine();
        String[] userData = input.split(" ");

        try {
            // Проверка количества данных
            if (userData.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Ожидалось 6, получено: " + userData.length);
            }

            // Распознавание данных
            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String birthDate = userData[3];
            String phoneNumber = userData[4];
            String gender = userData[5];

            // Проверка форматов
            checkBirthDate(birthDate);
            checkPhoneNumber(phoneNumber);
            checkGender(gender);

            // Создание или запись в файл
            writeToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            System.out.println("Данные успешно записаны.");
        } catch (IllegalArgumentException | ParseException e) {
            System.err.println("Ошибка в данных: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: ");
            e.printStackTrace();
        }
    }

    // Проверка даты
    private static void checkBirthDate(String birthDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        dateFormat.parse(birthDate);
    }

    // Проверка телефона
    private static void checkPhoneNumber(String phoneNumber) {
        try {
            Long.parseUnsignedLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат телефона: " + phoneNumber);
        }
    }

    // Проверка пола
    private static void checkGender(String gender) {
        if (!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f")) {
            throw new IllegalArgumentException("Неверный формат пола: " + gender + ". Ожидается 'm' или 'f'.");
        }
    }

    // Запись в файл
    private static void writeToFile(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, String gender) throws IOException {
        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender);
            writer.newLine();
        }
    }
}
