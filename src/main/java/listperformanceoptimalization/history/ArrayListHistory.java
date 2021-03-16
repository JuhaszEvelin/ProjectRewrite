package listperformanceoptimalization.history;

import java.util.*;

public class ArrayListHistory implements History {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        text = text.replaceAll("\\s+", " ");
        String[] words = text.split(" ");

        wordsArrayList.addAll(Arrays.asList(words));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {

        wordsArrayList.removeIf(s -> s.equals(wordToBeRemoved));
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {

        ListIterator<String> listIterator = wordsArrayList.listIterator();

        for (int i = 0; i < wordsArrayList.size(); i++) {
            if (listIterator.next().equals(from)) {
                listIterator.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {

        List<String> res = new LinkedList<>(wordsArrayList);
        ListIterator<String> listIterator = res.listIterator();
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

        wordsArrayList = res;

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
