# Palindromne Number

## 풀이

### 1차시

덱에 넣은 뒤 양쪽에서 뽑아줬다.
```java
while (1 < deque.size()) {
    if (!deque.pollFirst().equals(deque.pollLast())) {
        return false;
    }
}
```

### 2차시

답은 나왔지만 스트링을 사용해 속도가 나오지 않은 것 같아 모범 답안을 참고했다.

```java
if(x < 0) {
    return false;
}

Deque<Integer> deque = new ArrayDeque<>();

while (x != 0) {
    deque.offerLast(x % 10);
    x /= 10;
}

while (1 < deque.size()) {
    if (deque.pollFirst() != (deque.pollLast())) {
        return false;
    }
}

return true;
```

위와 같이 개선했는데, 모범답안은 `deque` 에 넣어주는 대신 계속해서 판단한다. 저렇게하면 1.5배정도 느리지만 구현속도와 성능 사이의 적당한 지점인듯하다.

> 성능이 궁금해 char를 덱에 넣어봤는데 스트링 보다는 성능이 좋았다. 하지만 String으로 한번 바꿔주고 spilt하는 과정이 들어가면 느려질 수 밖에 없는 듯 하다. 