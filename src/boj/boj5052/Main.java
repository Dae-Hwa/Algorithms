package boj.boj5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        String[] results = new String[t];


        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[n];

            for (int j = 0; j < n; j++) {
                phoneNumbers[j] = br.readLine();
            }

            results[i] = solution(phoneNumbers);
        }

        for (String each : results) {
            System.out.println(each);
        }
    }

    public static String solution(String[] phoneNumbers) {
        Trie trie = new Trie();

        for (String phoneNumber : phoneNumbers) {
            if(!trie.add(phoneNumber.split(""))) {
                return "NO";
            }
        }

//        for (String phoneNumber : phoneNumbers) {
//            if (trie.findDuplicated(phoneNumber.split(""))) {
//                return "NO";
//            }
//        }

        return "YES";
    }
}

class Trie {
    private TrieNode root = new TrieNode();

    public boolean add(String[] number) {
        return root.add(number, 0);
    }

    public boolean findDuplicated(String[] number) {
        return root.findDuplicated(number, 0);
    }
}

class TrieNode {
    private Map<String, TrieNode> children = new HashMap<>();
    private boolean hasEndNumber;

    public boolean add(String[] number, int index) {
        if (hasEndNumber) {
            return false;
        }

        if (index < number.length) {
            TrieNode child = children.getOrDefault(number[index], new TrieNode());
            children.put(number[index], child);
            if (!child.add(number, index + 1)) {
                return false;
            }
        } else {
            hasEndNumber = true;
            return children.size() == 0;
        }

        return true;
    }

    private boolean hasChildren(String key) {
        if (children.containsKey(key)) {
            return children.get(key).children.size() != 0;
        }

        return false;
    }

    public boolean findDuplicated(String[] number, int index) {
        if (index == number.length) {
            return false;
        }

        if (children.containsKey(number[index])) {
            if (number.length == index + 1 && hasChildren(number[index])) {
                return true;
            }

            return children.get(number[index]).findDuplicated(number, index + 1);
        }

        return false;
    }
}
