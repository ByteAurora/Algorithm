import 'dart:io';
import 'dart:math';

void main() {
  int T = int.parse(stdin.readLineSync()!);

  for (int testNum = 0; testNum < T; testNum++) {
    var inputData = stdin.readLineSync()!.split(' ');
    var n = int.parse(inputData[0]), k = int.parse(inputData[1]);
    var interests =
        stdin.readLineSync()!.split(' ').map((s) => int.parse(s)).toList();

    stdout.writeln('#${testNum + 1}: ${solution(n, k, interests)}');
  }
}

int solution(int n, int k, List<int> interests) {
  var maxGroup = 0;
  var startIdx = 0;
  var endIdx = 0;

  interests.sort();

  while (endIdx < n) {
    while (endIdx < n && interests[endIdx] - interests[startIdx] <= k) {
      endIdx++;
    }

    maxGroup = max(maxGroup, endIdx - startIdx);

    while (endIdx < n && interests[endIdx] - interests[startIdx] > k) {
      startIdx++;
    }
  }

  return maxGroup;
}
