package prgrms.방문길이;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("LULLLLLLU"));
    }
}


class Solution {
    public int solution(String dirs) {
        GameCharacter gameCharacter = new GameCharacter();

        for (String dir : dirs.split("")) {
            gameCharacter.move(dir);
        }

        return gameCharacter.getDistance();
    }
}

enum GameOperator {
    U("U") {
        public Place move(Place place) {
            return new Place(place.getX(), place.getY() + 1);
        }
    }, D("D") {
        public Place move(Place place) {
            return new Place(place.getX(), place.getY() - 1);
        }
    }, R("R") {
        public Place move(Place place) {
            return new Place(place.getX() + 1, place.getY());
        }
    }, L("L") {
        public Place move(Place place) {
            return new Place(place.getX() - 1, place.getY());
        }
    };

    private final String symbol;

    GameOperator(String symbol) {
        this.symbol = symbol;
    }

    public abstract Place move(Place place);

    public static Place moveBy(Place place, String symbol) {
        for (GameOperator gameOperator : values()) {
            if (gameOperator.symbol.equals(symbol)) {
                return gameOperator.move(place);
            }
        }

        throw new IllegalArgumentException(symbol + "과 일치하는 명령을 찾지 못했습니다.");
    }
}

class GameCharacter {
    private final Set<Path> movedPlaces = new HashSet<>();
    private Place currentPlace = new Place(0, 0);
    private Place nextPlace = new Place(0, 0);

    public void move(String symbol) {
        nextPlace = currentPlace.copy();

        Place movedPlace = GameOperator.moveBy(nextPlace, symbol);

        if (!currentPlace.equals(movedPlace)) {
            movedPlaces.add(new Path(currentPlace, movedPlace));
            currentPlace = movedPlace;
        }
    }

    public int getDistance() {
        return movedPlaces.size();
    }
}

class Path {
    private final Place startPlace;
    private final Place endPlace;

    public Path(Place startPlace, Place endPlace) {
        this.startPlace = startPlace;
        this.endPlace = endPlace;
    }

    @Override
    public boolean equals(Object o) {
        Path target = (Path) o;

        if (this.startPlace.equals(target.startPlace) && this.endPlace.equals(target.endPlace)) {
            return true;
        }
        return this.endPlace.equals(target.startPlace) && this.startPlace.equals(target.endPlace);

    }

    @Override
    public int hashCode() {
        if (startPlace.hashCode() <= endPlace.hashCode()) {
            return Objects.hash(endPlace, startPlace);
        }

        return Objects.hash(startPlace, endPlace);
    }
}

class Place {
    private final static int MIN_VALUE_OF_Y = -5;
    private final static int MAX_VALUE_OF_Y = 5;
    private final static int MIN_VALUE_OF_X = -5;
    private final static int MAX_VALUE_OF_X = 5;

    private final int x;
    private final int y;

    public Place(int x, int y) {
        if (MAX_VALUE_OF_X < x) {
            x = MAX_VALUE_OF_X;
        }
        if (x < MIN_VALUE_OF_X) {
            x = MIN_VALUE_OF_X;
        }
        if (MAX_VALUE_OF_Y < y) {
            y = MAX_VALUE_OF_Y;
        }
        if (y < MIN_VALUE_OF_Y) {
            y = MIN_VALUE_OF_Y;
        }

        this.x = x;
        this.y = y;
    }

    public Place copy() {
        return new Place(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        return this.x == ((Place) o).x && this.y == ((Place) o).y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}