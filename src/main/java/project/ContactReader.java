package project;

import project.searchable.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class ContactReader {

    public static Contact[] readFileToContactArray(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(ContactReader::getContactFromString)
                    .toArray(Contact[]::new);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new Contact[0];
    }

    private static Contact getContactFromString(String string) {
        String[] values = string.split(" ");
        if (values[0].matches("\\d+")) {
            return new Contact(values[0], extractName(1, values));
        } else {
            return new Contact(extractName(0, values));
        }
    }

    private static String extractName(int skip, String[] string) {
        return Arrays.stream(string)
                .skip(skip)
                .collect(joining(" "));
    }
}
