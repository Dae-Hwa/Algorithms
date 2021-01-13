# Reverse Integer

https://leetcode.com/problems/reverse-integer/submissions/

숫자 역순으로 배열

## 풀이

### 1차시

deque에 넣은 뒤 뒤집어서 꺼냈다.

```java
while (!deque.isEmpty()) {
    sb.append(deque.pollLast());
}
```
짚어볼 수 있는 문제점은 2가지다.

1. 궂이 새로운 자료구조를 만들어 공간을 사용하지 않아도 된다. 숫자 조작 만으로 스택과 같은 효과를 낼 수 있기 때문에
2. 스트링으로 바꿨다가 다시 파싱하는 비용이 비교적 크다.

### 2차시
```java
while (x != 0) {
    int modular = x % 10;
    sb.append(modular < 0 ? modular * -1 : modular);
    x /= 10;
}
```
> 참고로 부호는 따로 저장해놨다. 그렇지 않으면 음수로 저장되어 파싱이 어렵기 때문이다.

문제점 1번에 착안하여 덱 없이 진행했다.

하지만 속도 개선은 크지 않았다. 메모리도 마찬가지였는데, 결국 스트링 빌더에 넣어서 처리하기 때문으로 생각된다.

개선을 위해 모법답안을 참고 했다.

### 3차시
```java
while (x != 0) {
    int modular = x % 10;
    x /= 10;

    if (Integer.MAX_VALUE / 10 < result) {
        return 0;
    }
    if (result < Integer.MIN_VALUE / 10) {
        return 0;
    }

    if (modular < 0) {
        modular *= -1;
    }

    result *= 10;
    result += modular;
}

return result * sign;
```

속도는 훨씬 개선되었다. int 만으로 풀이했기 때문이다. 확실히 가능한 String을 쓰지 않는 것이 기본적인 속도향상에 큰 도움이 되는 것 같다.

생각하기 까다로운 부분은 최대, 최소값을 판단하는 부분이다. 조건은 다음과 같다.

- `result * 10 + modular` 를 진행하기 전의 `result` 의 값이 int의 최대 및 최소값의 `/10` 보다 큰지 판단해야 한다.

답안에는 모듈러를 포함해서 조건 판단을 해줬는데, 모듈러가 해당 범위를 초과하려면 입력할 때 부터 값이 초과되기 때문에 해당 경우는 없을 듯 하다.
> 참고 - Integer.MAX_VALUE = 2147483647 (2^31 -1)