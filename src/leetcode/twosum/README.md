# two sum

https://leetcode.com/problems/two-sum/submissions/

합이 목표가 되는 두 개의 수 탐색

## 풀이

### 1차시

처음 번에는 약간 개선 된 brute force를 이용했다.

1. num과 index를 저장하는 노드를 생성한 뒤 정렬한다. 
```java
List<Node> nodes = new ArrayList<>();

for (int i = 0; i < nums.length; i++) {
    nodes.add(new Node(nums[i], i));
}
```

2. 순서대로 찾는다. 이 때, 정렬되어 있기 때문에 특정 수를 넘어가면 다음으로 넘어가도록 했다.
```java
for (int i = 0; i < nodes.size(); i++) {
    for (int j = i + 1; j < nodes.size(); j++) {
        Node a = nodes.get(i);
        Node b = nodes.get(j);

        if (a.num + b.num == target) {
            int[] result = new int[]{a.index, b.index};
            Arrays.sort(result);
            return result;
        }

        if (target < a.num + b.num) {
            break;
        }
    }
}
```

### 2차시 

통과는 했지만 심화된 풀이는 조금 달랐다. 풀이에서는 `map` 을 사용했다.
> 해쉬 맵과 해쉬 테이블은 같은 개념이다. Java에서는 구현이 다른 것이다. https://d2.naver.com/helloworld/831311

```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    // #1 
    int complement = target - nums[i];
    // #2
    if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
    }
    // #3    
    map.put(nums[i], i);
}
```

1. \#1에서는 보수를 찾는다.

예를 들어, 9에 대한 2의 보수는 7이며, 7의 보수는 2다.

현재 숫자가 2나 7일때 테이블에서 2 혹은 7을 찾아오면 되는 것이다.

2. \#2에서 맵에 계산한 보수가 존재하는지 판단한다.

이러한 동작이 가능한 이유는 두 개의 정답 중 선행되는 답은 \#3 에서 저장이 될 것이기 때문이다.

예를 들어, 2와 7이 정답이라면, `nums[i]` 가 2인 경우에서 2의 보수인 7이 맵에 저장되었을 것이다.

그리고 `nums[i]` 가 7인 경우에서 7의 보수인 2는 이미 맵에 들어있기 때문에 꺼내오면 된다.