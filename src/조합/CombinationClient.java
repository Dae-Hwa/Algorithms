package 조합;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CombinationClient {
    public static void main(String[] args) {
        Combination.createBy(4, 3).printCom();
    }
}

class Combination {
    private List<List<Integer>> combinations = new ArrayList<>();

    private Combination() {
    }

    public static Combination createBy(int n, int r) {
        Combination combination = new Combination();

        combination.init(n,r);

        return combination;
    }

    private void init(int n, int r) {
        Stack<Integer> combinationElements = new Stack<>();
        pick(combinationElements, n, r);
    }

    public void printCom() {
        System.out.println(combinations);
    }

    private void pick(Stack<Integer> st, int n, int r) {
        if (r == 0) {
            printPick(st);

            return;
        }

        for (int current = st.isEmpty() ? 0 : st.lastElement() + 1; current < n; current++) {
            st.push(current);
            pick(st, n, r - 1);
            st.pop();
        }

    }

    private void printPick(Stack<Integer> combinationElements) {
        List<Integer> combination = new ArrayList<>(combinationElements);

        combinations.add(Collections.unmodifiableList(combination));
    }
}