package boj.생태학4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> species = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            String cur = br.readLine();
            if (cur == null || cur.isEmpty()) break;
            species.add(cur);
        }

        System.out.println(solution(species).entrySet().stream()
                                   .map(entry -> String.join(" ", entry.getKey(), String.format("%.4f", entry.getValue())))
                                   .collect(Collectors.joining(System.lineSeparator()))
        );
    }

    public static Map<String, Double> solution(List<String> species) {
        Map<String, Double> speciesMap = new LinkedHashMap<>();

        species = species.stream().sorted().collect(Collectors.toList());

        for (String each : species) {
            double curCount = speciesMap.getOrDefault(each, 0.0);
            speciesMap.put(each, curCount + 1.0);
        }

        for (Map.Entry<String, Double> entry : speciesMap.entrySet()) {
            double cur = BigDecimal.valueOf(entry.getValue() * 100.0).divide(BigDecimal.valueOf(species.size()), 4, RoundingMode.HALF_UP).doubleValue();

            entry.setValue(cur);
        }

        return speciesMap;
    }
}

public class 생태학 {

}
