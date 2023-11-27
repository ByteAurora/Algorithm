#include <iostream>

int main()
{
    int dp[41][2] = { 0, };
    dp[0][0] = 1; dp[0][1] = 0;
    dp[1][0] = 0; dp[1][1] = 1;

    int T;
    std::cin >> T;

    for (int i = 0; i < T; i++) {
        int N;
        std::cin >> N;

        for (int f_num_idx = 2; f_num_idx < N + 1; f_num_idx++) {
            dp[f_num_idx][0] = dp[f_num_idx - 1][0] + dp[f_num_idx - 2][0];
            dp[f_num_idx][1] = dp[f_num_idx - 1][1] + dp[f_num_idx - 2][1];
        }

        std::cout << dp[N][0] << " " << dp[N][1] << std::endl;
    }
}
