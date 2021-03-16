package listperformanceoptimalization.history;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListHistory implements History {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {

        text = text.replaceAll("\\s+", " ");
        String[] words = text.split(" ");
        wordsLinkedList.addAll(Arrays.asList(words));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {

        ListIterator<String> listIterator = wordsLinkedList.listIterator();

        while (listIterator.hasNext()) {
            if (listIterator.next().equals(wordToBeRemoved)) {
                listIterator.remove();
            }
        }
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {

        ListIterator<String> listIterator = wordsLinkedList.listIterator();

        while (listIterator.hasNext()) {
            if (listIterator.next().equals(from)) {
                listIterator.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {

        ListIterator<String> listIterator = wordsLinkedList.listIterator();

        while (listIterator.hasNext()) {

            boolean isAllInList = true;

            for (int i = 0; i < fromWords.length; i++) {

                if (listIterator.hasNext() && !(fromWords[i].equals(listIterator.next()))) {
                    isAllInList = false;
                    break;
                }

                if (i == fromWords.length - 1) {

                    for (int j = 0; j < fromWords.length; j++) {
                        listIterator.previous();
                    }
                }
            }

            if (isAllInList) {

                for (int i = 0; i < fromWords.length; i++) {
                    if (listIterator.next().equals(fromWords[i])) {
                        listIterator.remove();

                        if (i == fromWords.length - 1) {
                            for (String to : toWords) {
                                listIterator.add(to);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
