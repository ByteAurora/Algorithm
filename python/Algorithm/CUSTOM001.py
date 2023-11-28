def solution(N, K, interests):
    max_group = 0
    start_idx = 0
    end_idx = 0

    interests.sort()

    while end_idx < len(interests):
        while end_idx < len(interests) and interests[end_idx] - interests[start_idx] <= K:
            end_idx += 1

        max_group = max(max_group, interests[end_idx] - interests[start_idx])

        while end_idx < len(interests) and interests[end_idx] - interests[start_idx] > K:
            start_idx += 1

    return max_group


for _ in range(int(input())):
    N, K = list(map(int, input().split()))
    interests = list(map(int, input().split()))

    print('#%d: %d' % (_ + 1, solution(N, K, interests)))
