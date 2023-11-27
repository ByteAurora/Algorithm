import 'dart:io';

void main(List<String> arguments) {
  List<List<int>> dp = [
    [1, 0],
    [0, 1]
  ];

  for (int i = 0; i < 41; i++) {
    dp.add([0, 0]);
  }

  int T = int.parse(stdin.readLineSync()!);

  for (int i = 0; i < T; i++) {
    int N = int.parse(stdin.readLineSync()!);

    if (N < 2) {
      print("${dp[N][0]} ${dp[N][1]}");
      continue;
    }

    for (int i = 2; i < N + 1; i++) {
      dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
      dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
    }

    print("${dp[N][0]} ${dp[N][1]}");
  }
}
