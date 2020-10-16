package boj.boj14891;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] gearsInput = new String[4];

        for (int i = 0; i < gearsInput.length; i++) {
            gearsInput[i] = br.readLine();
        }

        Gears gears = Gears.create(gearsInput);

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            int[] rotateStatus = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            gears.rotate(rotateStatus[0], rotateStatus[1]);
        }

        System.out.println(gears.getScore());
    }
}

class Gears {
    public final static int N = 0;
    public final static int S = 1;


    private final List<Gear> gears;

    private Gears(List<Gear> gears) {
        this.gears = gears;
    }

    public static Gears create(String[] inputs) {
        List<Gear> gears = new ArrayList<>();

        for (String input : inputs) {
            gears.add(Gear.create(input));
        }

        for (int i = 0; i < gears.size(); i++) {
            if (0 < i) {
                gears.get(i).setLeft(gears.get(i - 1));
            }

            if (i < gears.size() - 1) {
                gears.get(i).setRight(gears.get(i + 1));
            }
        }

        return new Gears(gears);
    }

    public void rotate(int index, int rotationDirection) {
        int currentIndex = index - 1;
        Gear current = gears.get(currentIndex);

        Queue<Gear> leftRotationGears = new ArrayDeque<>();
        Queue<Gear> rightRotationGears = new ArrayDeque<>();

        Gear left = current;

        while (left.canRotateLeft()) {
            leftRotationGears.add(left.getLeft());
            left = left.getLeft();
        }

        Gear right = current;

        while (right.canRotateRight()) {
            rightRotationGears.add(right.getRight());
            right = right.getRight();
        }

        current.rotate(rotationDirection);

        int leftRotationDirection = rotationDirection;

        while (!leftRotationGears.isEmpty()) {
            leftRotationDirection *= -1;
            leftRotationGears.poll().rotate(leftRotationDirection);
        }

        int rightRotationDirection = rotationDirection;

        while (!rightRotationGears.isEmpty()) {
            rightRotationDirection *= -1;
            rightRotationGears.poll().rotate(rightRotationDirection);
        }
    }

    public int getScore() {
        int score = 0;

        for (int i = 0; i < gears.size(); i++) {
            if (gears.get(i).getPoleOfTop() == S) {
                score += Math.pow(2, i);
            }
        }

        return score;
    }

    @Override
    public String toString() {
        return "Gears{" + System.lineSeparator() +
                "gears=" + gears +
                '}';
    }
}

class Gear {
    public final static int CLOCKWISE = 1;
    public final static int COUNTERCLOCKWISE = -1;

    private Gear left;
    private Gear right;

    private final int[] teeth;
    private int indexOfTop = 0;

    private Gear(int[] teeth) {
        this.teeth = teeth;
    }

    public static Gear create(String input) {
        return new Gear(Arrays.stream(input.split(""))
                .mapToInt(Integer::parseInt)
                .toArray());
    }

    public void rotate(int rotationDirection) {
        // 시계방향
        if (rotationDirection == CLOCKWISE) {
            // 반시계방향
            indexOfTop--;

            if (indexOfTop < 0) {
                indexOfTop = 7;
            }
        }

        if (rotationDirection == COUNTERCLOCKWISE) {
            indexOfTop++;

            if (7 < indexOfTop) {
                indexOfTop = 0;
            }
        }
    }

    public boolean canRotateLeft() {
        if (left == null) {
            return false;
        }

        return left.getRightPole() != getLeftPole();

    }

    public boolean canRotateRight() {
        if (right == null) {
            return false;
        }

        return right.getLeftPole() != getRightPole();
    }

    public int getRightPole() {
        int leftPole = indexOfTop + 2;

        return 7 < leftPole ? teeth[leftPole - 8] : teeth[leftPole];
    }

    public int getLeftPole() {
        int rightPole = indexOfTop + 6;

        return 7 < rightPole ? teeth[rightPole - 8] : teeth[rightPole];
    }

    public Gear getLeft() {
        return left;
    }

    public void setLeft(Gear left) {
        this.left = left;
    }

    public Gear getRight() {
        return right;
    }

    public void setRight(Gear right) {
        this.right = right;
    }

    public int getPoleOfTop() {
        return teeth[indexOfTop];
    }

    @Override
    public String toString() {
        String left = this.left != null ? Arrays.toString(this.left.teeth) : "null";
        String right = this.right != null ? Arrays.toString(this.right.teeth) : "null";

        return "Gear{" +
                "left=" + left +
                ", teeth=" + Arrays.toString(teeth) +
                ", right=" + right +
                ", indexOfTop=" + indexOfTop +
                '}' + System.lineSeparator();
    }
}