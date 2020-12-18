package prgrms.단어변환;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(String begin, String target, String[] words) {
        return Words.create(words, begin)
                .convert(target)
                .getMoveCount();
    }
}

class Words {
    private List<Word> words;
    private int moveCount;

    private Words(List<Word> words) {
        this.words = words;
    }

    public static Words create(String[] inputWords, String begin) {
        Words words = new Words(Arrays.asList(inputWords).stream()
                .map(Word::new)
                .collect(Collectors.toList()));

        if (words.words.stream().noneMatch(word -> word.equals(new Word(begin)))) {
            words.words.add(0, new Word(begin));
        }

        for (Word word : words.words) {
            word.addLink(words);
        }

        return words;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public List<Word> asList() {
        return Collections.unmodifiableList(words);
    }

    public Words convert(String target) {
        if (words.stream()
                .noneMatch(word -> word.equals(new Word(target)))) {
            return this;
        }

        Word current = words.get(0);
        current.setLinked(false);
        Queue<Word> bfs = new ArrayDeque<>();

        bfs.offer(current);

        while (!current.equals(new Word(target))) {
            current = bfs.poll();
            current.setLinked(false);
            bfs.addAll(current.getLinkedWords());
        }

        moveCount = current.getMoveCount();

        return this;
    }
}

class Word {
    private String word;
    private List<Word> linkedWords = new ArrayList<>();
    private boolean linked = true;
    private int moveCount = 0;

    public Word(String word) {
        this.word = word;
    }

    public Word addLink(Words words) {
        for (Word word : words.asList()) {
            int cnt = 0;

            for (int i = 0; i < this.word.length(); i++) {
                if (this.word.charAt(i) == word.word.charAt(i)) {
                    cnt++;
                }
            }

            if (cnt == this.word.length() - 1) {
                linkedWords.add(word);
            }
        }

        return this;
    }

    public boolean isLinked() {
        return linked;
    }

    public void setLinked(boolean linked) {
        this.linked = linked;
    }

    public List<Word> getLinkedWords() {
        List<Word> result = linkedWords.stream().filter(Word::isLinked).collect(Collectors.toList());

        result.forEach(word -> {
            word.setLinked(false);
            word.moveCount = this.moveCount + 1;
        });

        return Collections.unmodifiableList(result);
    }

    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Object> inputs = new ArrayList<>(Arrays.asList(
                new ArrayList(Arrays.asList(
                        "hit",
                        "cog",
                        new String[]{"hot", "dot", "dog", "lot", "log", "cog"}
                )),
                new ArrayList(Arrays.asList(
                        "hit",
                        "cog",
                        new String[]{"hot", "dot", "dog", "lot", "log"}
                )),
                new ArrayList(Arrays.asList(
                        "hot",
                        "lot",
                        new String[]{"hot", "dot", "dog", "lot", "log"}
                ))
        ));

        for (Object inputObj : inputs) {
            List<Object> input = (List<Object>) inputObj;

            String begin = (String) input.get(0);
            String target = (String) input.get(1);
            String[] words = (String[]) input.get(2);

            int result = new Solution().solution(begin, target, words);

            System.out.println(result);
        }
    }
}

