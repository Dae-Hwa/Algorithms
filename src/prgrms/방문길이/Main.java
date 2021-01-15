package prgrms.방문길이;

import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;

        Point cur = new Point();
        Point prev = cur.copy();

        Set<Set<Point>> edges = new HashSet<>();

        for (String dir : dirs.split("")) {
            Set<Point> edge = new HashSet<>();

            if (!MoveCommand.valueOf(dir).execute(cur)) {
                continue;
            }
            edge.add(cur.copy());
            edge.add(prev);

            prev = cur.copy();

            if (!edges.contains(edge)) {
                answer++;
            }
            edges.add(edge);
        }


        return answer;
    }
}

enum MoveCommand {
    U {
        public boolean execute(Point point) {
            return point.setY(point.y + 1);
        }
    },
    D {
        public boolean execute(Point point) {
            return point.setY(point.y - 1);
        }
    },
    R {
        public boolean execute(Point point) {
            return point.setX(point.x + 1);
        }
    },
    L {
        public boolean execute(Point point) {
            return point.setX(point.x - 1);
        }
    };

    abstract boolean execute(Point point);
}

class Point {
    int x = 0;
    int y = 0;

    public Point() {
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point copy() {
        return new Point(this);
    }

    public boolean setX(int x) {
        this.x = x;

        if (5 < this.x) {
            this.x = 5;
            return false;
        }
        if (x < -5) {
            this.x = -5;
            return false;
        }

        return true;
    }

    public boolean setY(int y) {
        this.y = y;

        if (5 < this.y) {
            this.y = 5;
            return false;
        }
        if (this.y < -5) {
            this.y = -5;
            return false;
        }

        return true;
    }

    public boolean equals(Object o) {
        Point target = (Point) o;

        return this.x == target.x && this.y == target.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}