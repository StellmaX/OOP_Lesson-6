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
            // Проверка введённых данных
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

            // Запись в файл
            writeToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            System.out.println("Данные успешно записаны.");
        } catch (IllegalArgumentException | ParseException e) {
            System.err.println("Ошибка в данных: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: ");
            e.printStackTrace();
        }
    }
