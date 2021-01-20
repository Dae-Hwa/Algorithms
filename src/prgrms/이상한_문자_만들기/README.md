# 이상한 문자 만들기

문자열을 잘라 인덱스별로 다르게 출력하는 문제
https://programmers.co.kr/learn/courses/30/lessons/12930?language=java

## 풀이

`StringTokenizer` 로는 풀리는데 `split` 으로는 이상하게 풀리지 않았다.

이유는 `split` 메소드의 공백 생략때문이었다.

https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#split(java.lang.String,int)

`split(" ", -1)` 이와 같이 두 번째 인자로 음수를 넣어주면 공백 생략없이 모든 단어를 잘라준다. 
