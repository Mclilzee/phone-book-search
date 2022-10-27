import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void checkIfElementIsInArray() {
        String[] strings = new String[]{"test", "java", "main"};
        String element = "java";

        assertTrue(Main.isElementInArray(element, strings));
    }

    @Test
    void elementIsNotInArray() {
        String[] strings = new String[]{"test", "java", "main"};
        String element = "ruby";

        assertFalse(Main.isElementInArray(element, strings));
        assertFalse(Main.isElementInArray(element, new String[0]));
    }

    @Test
    void countElementsOfSubArray() {
        String[] array = new String[]{"java", "C++", "C", "Python", "Ruby"};
        String[] subArray = new String[]{"java", "C", "Rust"};
        assertEquals(2, Main.countSubArrayElements(subArray, array));

        String[] secondSubArray = new String[]{"C++", "Wizard", "Mage"};
        assertEquals(1, Main.countSubArrayElements(secondSubArray, array));
    }

    @Test
    void zeroIfArraysAreEmptyOrNull() {
        assertEquals(0, Main.countSubArrayElements(new String[0], new String[0]));
        assertEquals(0, Main.countSubArrayElements(new String[]{"test"}, new String[0]));
        assertEquals(0, Main.countSubArrayElements(null, new String[0]));
        assertEquals(0, Main.countSubArrayElements(new String[0], null));
        assertEquals(0, Main.countSubArrayElements(null, null));
    }

    @Test
    void getStringArrayFromFile() {
        String[] expected = new String[]{"Mark zergberg", "John Doe", "Gly marksman"};
        assertArrayEquals(expected, Main.readFileIntoStringArray("./src/test/java/sample.txt"));
        assertArrayEquals(new String[0], Main.readFileIntoStringArray("imaginaryfile"));
    }
}