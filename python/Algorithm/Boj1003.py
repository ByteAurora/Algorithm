T = int(input())

dp = [[0, 0] for _ in range(41)]
dp[0] = [1, 0]
dp[1] = [0, 1]

for _ in range(T):
    N = int(input())

    if N == 0:
        print(dp[0][0], dp[0][1])
        continue

    if N == 1:
        print(dp[1][0], dp[1][1])
        continue

    for f_num_idx in range(2, N + 1, 1):
        if dp[f_num_idx] == [0, 0, 0]:
            dp[f_num_idx][0] = dp[f_num_idx - 1][0] + dp[f_num_idx - 2][0]
            dp[f_num_idx][1] = dp[f_num_idx - 1][1] + dp[f_num_idx - 2][1]

    print(dp[N][0], dp[N][1])
