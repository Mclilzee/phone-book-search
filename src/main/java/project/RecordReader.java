package project;

import project.searchable.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class RecordReader {

    public static Record[] readFileToRecordArray(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(RecordReader::getRecordFromString)
                    .toArray(Record[]::new);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new Record[0];
    }

    private static Record getRecordFromString(String string) {
        String[] values = string.split(" ");
        if (values[0].matches("\\d+")) {
            return new Record(values[0], extractName(1, values));
        } else {
            return new Record(extractName(0, values));
        }
    }

    private static String extractName(int skip, String[] string) {
        return Arrays.stream(string)
                .skip(skip)
                .collect(joining(" "));
    }
}
