package searchable;

public class RecordSorter {

    public static void bubbleSort(Record[] array) {
        boolean quit = false;
        while (!quit) {
            quit = true;

            for (int i = 0; i < array.length - 1; i++) {
                Record previous = array[i];
                Record next = array[i + 1];
                int comparingResult = previous.compareTo(next);

                if (comparingResult > 0) {
                    array[i] = next;
                    array[i + 1] = previous;
                    quit = false;
                }
            }
        }
    }
}
