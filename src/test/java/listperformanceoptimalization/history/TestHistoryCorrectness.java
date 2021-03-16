package listperformanceoptimalization.history;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHistoryCorrectness {
    private History history;

    public TestHistoryCorrectness(String name) {
        switch (name) {
            case "ArrayList":
                history = new ArrayListHistory();
                break;
            case "LinkedList":
                history = new LinkedListHistory();
                break;
        }
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection data() {
        return Arrays.asList(  "ArrayList", "LinkedList");
    }

    @Test
    public void add_spaceSeparatedString_representedInToString() {
        history.add("abc def ghi");
        assertEquals("abc def ghi", history.toString());
        history.add("x y z w");
        assertEquals("abc def ghi x y z w", history.toString());
    }

    @Test
    public void add_stringWithMultiSpaces_stringsAreTrimmed() {
        history.add("multiple    spaces");
        assertEquals("multiple spaces", history.toString());
    }

    @Test
    public void add_wordsSeparatedByTab_addOnlyWordsWithoutTabs() {
        history.add("newlines\tand tabs\ttoo");
        assertEquals("newlines and tabs too", history.toString());
    }

    @Test
    public void add_wordsSeparatedByNewLine_addOnlyWordsWithoutTabs() {
        history.add("newlines\nand tabs\ntoo");
        assertEquals("newlines and tabs too", history.toString());
    }

    @Test
    public void add_wordsSeparatedByVariousWhitespaceChar_addOnlyWordsWithoutWhitespace() {
        history.add("newlines\nand tabs\ttoo");
        assertEquals("newlines and tabs too", history.toString());
    }

    @Test
    public void size_beforeAnyChange_returnZeroAsDefault() {
        assertEquals(0, history.size());
    }

    @Test
    public void size_afterAddingWords_returnWithCountOfWords(){
        history.add("abc def ghi");
        assertEquals(3, history.size());
    }

    @Test
    public void clear_removesAllWordsFromHistory() {
        history.add("abc def ghi");
        assertNotEquals("", history.toString());

        history.clear();
        assertEquals("", history.toString());
    }

    @Test
    public void replaceOneWord_fullMatch_replaceAllOccurrences() {
        history.add("abc def ghi abc def ghi");
        history.replace("abc", "ABC");
        history.replace("def", "DEF");
        assertEquals("ABC DEF ghi ABC DEF ghi", history.toString());
    }

    @Test
    public void replaceOneWord_partOfWord_replacesOnlyFullMatches() {
        history.add("carpet car motorcar Nicaragua");
        history.replace("car", "AUTOMOBILE");
        assertEquals("carpet AUTOMOBILE motorcar Nicaragua", history.toString());
    }

    @Test
    public void removeWord_found_eliminateAllOccurrences() {
        history.add("hello world hello hello world");
        history.removeWord("hello");
        assertEquals("world world", history.toString());
    }

    @Test
    public void removeWord_wordNotFound_noChangeInHistory() {
        history.add("hello world hello hello world");
        history.removeWord("x");
        assertEquals("hello world hello hello world", history.toString());
    }

    @Test
    public void replaceMoreWords_equalLengthOfFromAndTo_replacesEveryWord() {
        history.add("replace me replace me me replace me");
        history.replace("replace me", "HAPPY FUN");
        assertEquals("HAPPY FUN HAPPY FUN me HAPPY FUN", history.toString());
    }

    @Test
    public void replaceMoreWords_toIsLongerThanFrom_insertWordsCorrectly() {
        history.add("x y z x y z w");
        history.replace("x y", "X X Y Y");
        assertEquals("X X Y Y z X X Y Y z w", history.toString());
    }

    @Test
    public void replaceMoreWords_toIsShorterThanFrom_deleteExtraElementsAndReplacesRest() {
        history.add("x y z x y z w");
        history.replace("x y", "XY");
        assertEquals("XY z XY z w", history.toString());
    }

    @Test
    public void replaceMoreWords_fromNotFound_noChangeInHistory() {
        history.add("replace me replace me me replace me");
        history.replace("replace replace me", "HAPPY FUN");
        assertEquals("replace me replace me me replace me", history.toString());
    }

    @Test
    public void replaceMoreWords_fromWithConsecutiveOccurrences_replaceAllOccurrences() {
        history.add("x x x y x x x x y x x");
        history.replace("x x", "XX");
        assertEquals("XX x y XX XX y XX", history.toString());
    }

    @Test
    public void replaceMoreWords_fromIsEqualToHistory_replaceFullHistory() {
        String text = "this is the text that I would like to be replaced";
        history.add(text);
        history.replace(text, "everything");
        assertEquals("everything", history.toString());
    }

    @Test
    public void replaceMoreWords_partialMatchAtTheEndOfString_replacesOnlyFullMatches() {
        history.add("end of string end of");
        history.replace("end of string", "END OF STRING");
        assertEquals("END OF STRING end of", history.toString());
    }

    @Test
    public void replaceMoreWords_toExtendsFrom_replacesOnlyInOriginalHistory() {
        history.add("Il Pet Il Pet");
        history.replace("Il", "Pet Il");
        assertEquals("Pet Il Pet Pet Il Pet", history.toString());
    }

    @Test
    public void replaceMoreWords_fromHasOnlyPartialMatch_noChangeHistory() {
        history.add("foo bar baz");

        history.replace("o b", "X");
        assertEquals("foo bar baz", history.toString());

        history.replace("foo b", "X");
        assertEquals("foo bar baz", history.toString());

        history.replace("o bar", "X");
        assertEquals("foo bar baz", history.toString());

        history.replace("o bar b", "X");
        assertEquals("foo bar baz", history.toString());
    }
}