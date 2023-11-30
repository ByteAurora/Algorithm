import 'dart:io';
import 'dart:math';

enum Direction { up, down, right, left }

bool compare2DArray(List<List> array1, List<List> array2) {
  for (var r = 0; r < array1.length; r++) {
    for (var c = 0; c < array1.length; c++) {
      if (array1[r][c] != array2[r][c]) {
        return false;
      }
    }
  }
  return true;
}

int getMaxValueFrom2DArray(List<List> array) {
  return array
      .map((row) => row.reduce((e1, e2) => e1 > e2 ? e1 : e2))
      .reduce((e1, e2) => e1 > e2 ? e1 : e2);
}

void swap2DArrayValue(
    List<List> array, int v1Row, int v1Col, int v2Row, int v2Col) {
  int temp = array[v1Row][v1Col];
  array[v1Row][v1Col] = array[v2Row][v2Col];
  array[v2Row][v2Col] = temp;
}

int dfs(int n, List<List> gameMap, int currentRound) {
  if (currentRound == 5) {
    return getMaxValueFrom2DArray(gameMap);
  }

  var maxValue = 0;
  for (var direction in Direction.values) {
    var changedGameMap = gameMap.map((row) => List.from(row)).toList();
    switch (direction) {
      case Direction.up:
        for (var c = 0; c < n; c++) {
          var mergeableIdx = 0;
          for (var r = 0; r < n; r++) {
            if (mergeableIdx == r || changedGameMap[r][c] == 0) continue;

            if (changedGameMap[mergeableIdx][c] == 0) {
              swap2DArrayValue(changedGameMap, r, c, mergeableIdx, c);
              continue;
            }

            if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
              changedGameMap[mergeableIdx][c] *= 2;
              changedGameMap[r][c] = 0;
            } else {
              swap2DArrayValue(changedGameMap, r, c, mergeableIdx + 1, c);
            }
            mergeableIdx += 1;
          }
        }
        break;
      case Direction.down:
        for (var c = 0; c < n; c++) {
          var mergeableIdx = n - 1;
          for (var r = n - 1; r >= 0; r--) {
            if (mergeableIdx == r || changedGameMap[r][c] == 0) continue;

            if (changedGameMap[mergeableIdx][c] == 0) {
              swap2DArrayValue(changedGameMap, r, c, mergeableIdx, c);
              continue;
            }

            if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
              changedGameMap[mergeableIdx][c] *= 2;
              changedGameMap[r][c] = 0;
            } else {
              swap2DArrayValue(changedGameMap, r, c, mergeableIdx - 1, c);
            }
            mergeableIdx -= 1;
          }
        }
        break;
      case Direction.right:
        for (var r = 0; r < n; r++) {
          var mergeableIdx = n - 1;
          for (var c = n - 1; c >= 0; c--) {
            if (mergeableIdx == c || changedGameMap[r][c] == 0) continue;

            if (changedGameMap[r][mergeableIdx] == 0) {
              swap2DArrayValue(changedGameMap, r, c, r, mergeableIdx);
              continue;
            }

            if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
              changedGameMap[r][mergeableIdx] *= 2;
              changedGameMap[r][c] = 0;
            } else {
              swap2DArrayValue(changedGameMap, r, c, r, mergeableIdx - 1);
            }
            mergeableIdx -= 1;
          }
        }
        break;
      case Direction.left:
        for (var r = 0; r < n; r++) {
          var mergeableIdx = 0;
          for (var c = 0; c < n; c++) {
            if (mergeableIdx == c || changedGameMap[r][c] == 0) continue;

            if (changedGameMap[r][mergeableIdx] == 0) {
              swap2DArrayValue(changedGameMap, r, c, r, mergeableIdx);
              continue;
            }

            if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
              changedGameMap[r][mergeableIdx] *= 2;
              changedGameMap[r][c] = 0;
            } else {
              swap2DArrayValue(changedGameMap, r, c, r, mergeableIdx + 1);
            }
            mergeableIdx += 1;
          }
        }
        break;
    }

    if (compare2DArray(gameMap, changedGameMap)) {
      maxValue = max(maxValue, getMaxValueFrom2DArray(changedGameMap));
    } else {
      maxValue = max(maxValue, dfs(n, changedGameMap, currentRound + 1));
    }
  }

  return maxValue;
}

int solution(int n, List<List> gameMap) {
  return dfs(n, gameMap, 0);
}

void main() {
  var n = int.parse(stdin.readLineSync()!);

  List<List<int>> gameMap =
      List.generate(n, (index) => List.empty(growable: true));

  for (var r = 0; r < n; r++) {
    gameMap[r] = stdin.readLineSync()!.split(" ").map(int.parse).toList();
  }

  stdout.writeln(solution(n, gameMap));
}
