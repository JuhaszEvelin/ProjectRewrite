package listperformanceoptimalization.history;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHistoryPerformance {
    private History history;
    private static final int repeatCount = 250_000;
    private static final int iliadRepetition = 10;

    public TestHistoryPerformance(String name) throws IOException {
        switch (name) {
            case "ArrayList":
                history = new ArrayListHistory();
                history.add(repeat("foo bar", repeatCount));
                break;
            case "LinkedList":
                history = new LinkedListHistory();
                history.add(repeat("foo bar", repeatCount));
                break;
        }
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        return Arrays.asList( "ArrayList", "LinkedList");
    }

    private String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

    String readIliad() throws IOException {
        Path iliadPath = FileSystems.getDefault().getPath("src/main/resources/Iliad.txt");
        String text = new String(Files.readAllBytes(iliadPath));
        // Strip everything except for letters
        return text.replaceAll("[^a-zA-Z \n]", "");
    }

    @Test(timeout = 5000)
    public void add() {
        history.add(repeat("foo bar", repeatCount));
        assertEquals(repeatCount * 4, history.size());
    }

    @Test(timeout = 5000)
    public void remove() {
        history.removeWord("bar");
        assertEquals(repeatCount, history.size());
    }

    @Test(timeout = 5000)
    public void replaceOneWord() {
        history.replace("bar", "baz");
        assertEquals(repeatCount * 2, history.size());
    }

    @Test(timeout = 5000)
    public void replaceMultipleWords_toIsShorterThanFrom() {
        history.replace("foo bar", "beat");
        assertEquals(repeatCount, history.size());
    }

    @Test(timeout = 5000)
    public void replaceMultipleWords_toIsLongerThanFrom() {
        history.replace("foo bar", "1 2 3 4");
        assertEquals(repeatCount * 4, history.size());
    }


    @Test(timeout = 5000)
    public void replaceMultipleWords_equalLengthOfFromAndTo() {
        history.replace("foo bar", "baz beat");
        assertEquals(repeatCount * 2, history.size());
    }

    @Test(timeout = 5000)
    public void removeInIliad() throws IOException {
        history.clear();
        history.add(repeat(readIliad(), iliadRepetition));
        history.removeWord("king");
        history.removeWord("Zeus");
        history.removeWord("Apollo");
        history.removeWord("it");
        assertEquals(151969 * iliadRepetition, history.size());
    }

    @Test(timeout = 5000)
    public void replaceOneWordInIliad() throws IOException {
        history.clear();
        history.add(repeat(readIliad(), iliadRepetition));
        history.replace("Achilles", "Il");
        history.replace("Agamemnon", "Il");
        history.replace("Priam", "Trumm");
        history.replace("chariot", "tank");
        history.replace("bow", "missile");
        history.replace("arrow", "nuke");
        history.replace("the", "the");
        assertEquals(153268 * iliadRepetition, history.size());
    }
}