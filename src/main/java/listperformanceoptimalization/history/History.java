package listperformanceoptimalization.history;

public interface History {

    /**
     * This method add new element to the end of the array.
     *
     * @param text: the sentence or word what needs to be add. You can give text with separators as well.
     * */
    void add(String text);

    /**
     * This method remove the @param element and shifts any subsequent elements to the left.
     *
     * @param wordToBeRemoved: the sentence or word that needs to be removed.
     * */
    void removeWord(String wordToBeRemoved);

    /**
     * This returns the number of elements in this list.
     * If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return number of elements in array.
     * */
    int size();

    /**
     * This method removes all of the elements from this list. The list will be empty after this call returns.
     *
     * */
    void clear();

    /**
     * This method replace one word in the list with the given word.
     *
     * @param from: word to be removed and changed.
     * @param to: word to add to the place of removed word (@param to).
     * */
    void replaceOneWord(String from, String to);

    /**
     * This method replace more words in the list with the given words.
     *
     * @param fromWords  : words to be removed.
     * @param toWords  : words to add to the place of removed words (@param fromWords).
     * */
    void replaceMoreWords(String[] fromWords, String[] toWords);

    /**
     * DON'T rewrite this method!
     * replaces all occurrences of sentences or words with sentences or words.
     * The tests are using this method instead of replaceOneWord() or replaceMoreWords().
     * In tests we can use the same method signature for all classes which implements this interface. (E)
     *
     * @param from: the sentence or word what needs to be replaced
     * @param to:   the sentence or word which replaces the sentence found in 'from'
     */
    default void replace(String from, String to) {
        String[] fromWords = from.split("\\s+");
        String[] toWords = to.split("\\s+");
        if (fromWords.length == 1 && toWords.length == 1) {
            replaceOneWord(from, to);
        } else {
            replaceMoreWords(fromWords, toWords);
        }
    }
}
