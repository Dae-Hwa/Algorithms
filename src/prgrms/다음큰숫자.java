import java.util.*;

class Solution {
    public boolean solution(int x) {        
        int sumOfUnits = String.valueOf(x).chars()
            .map(Character::getNumericValue)
            .sum();
        
        return (x % sumOfUnits) == 0;
    }
}
