package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*

파일을 확장자 별로 정리해서 개수 파악
확장자들을 사전 순으로 정렬

group by로 묶은 뒤 key를 정렬, 개수 구하기

// 입력
8
sbrus.txt
spc.spc
acm.icpc
korea.icpc
sample.txt
hello.world
sogang.spc
example.txt
// 출력
icpc 2
spc 2
txt 3
world 1
 */
class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 파일의 개수 1 <= N <= 50,000
        int N = Integer.parseInt(br.readLine());
        // N회만큼 파일명 입력
        String[] fileNames = new String[N];
        for (int i = 0; i < N; i++) {
            // 파일명은 소문자와 점으로만 이루어짐.
            // 점은 정확히 한 번 들어가며 첫 글자 또는 마지막 글자로 오지 않음
            // 3 <= 파일명 <= 100
            fileNames[i] = br.readLine().trim();
        }
        
        Map<String, List<String>> fileNamesGroupByExt = Arrays.stream(fileNames)
                .collect(Collectors.groupingBy(it -> it.split("\\.")[1]));

        List<String> extentions = new ArrayList<>(fileNamesGroupByExt.keySet());
        extentions.sort(Comparator.naturalOrder());

        String resultString = extentions.stream()
                .map(it -> it + " " + fileNamesGroupByExt.get(it).size())
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(resultString);
    }
}

public class Boj20291_파일정리 {

}
