# Palindromne Number

## 나의 풀이
덱에 넣은 뒤 양쪽에서 뽑아줬다.
```java
while (1 < deque.size()) {
    if (!deque.pollFirst().equals(deque.pollLast())) {
        return false;
    }
}
```