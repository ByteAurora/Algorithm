#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

enum Direction
{
    UP, DOWN, LEFT, RIGHT
};


int findMaxValue(vector<vector<int>>& arr) {
    int maxValue = 0;
    for (const auto& row : arr) {
        for (int value : row) {
            maxValue = max(maxValue, value);
        }
    }
    return maxValue;
}

bool arrayEqual(vector<vector<int>>& arr1, vector<vector<int>>& arr2) {
    for (int r = 0; r < arr1.size(); r++) {
        for (int c = 0; c < arr1.size(); c++) {
            if (arr1[r][c] != arr2[r][c]) return false;
        }
    }

    return true;
}

vector<vector<int>> deepCopy(vector<vector<int>>& arr) {
    vector<vector<int>> newArr(arr.size(), vector<int>(arr.size()));

    for (int r = 0; r < arr.size(); r++) {
        for (int c = 0; c < arr.size(); c++) newArr[r][c] = arr[r][c];
    }

    return newArr;
}

inline void swap(vector<vector<int>>& arr, int v1Row, int v1Col, int v2Row, int v2Col) {
    int temp = arr[v1Row][v1Col];
    arr[v1Row][v1Col] = arr[v2Row][v2Col];
    arr[v2Row][v2Col] = temp;
}

int dfs(int n, vector<vector<int>>& gameMap, int currentRound) {
    if (currentRound == 5) {
        return findMaxValue(gameMap);
    }

    int maxValue = 0;
    for (auto directionIdx = 0; directionIdx < 4; directionIdx++) {
        auto direction = static_cast<Direction>(directionIdx);

        vector<vector<int>> changedGameMap = deepCopy(gameMap);

        switch (direction) {
        case Direction::UP:
            for (int c = 0; c < n; c++) {
                int mergeableIdx = 0;
                for (int r = 0; r < n; r++) {
                    if (r == mergeableIdx || changedGameMap[r][c] == 0) continue;

                    if (changedGameMap[mergeableIdx][c] == 0) {
                        swap(changedGameMap, mergeableIdx, c, r, c);
                    }
                    else {
                        if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
                            changedGameMap[mergeableIdx][c] *= 2;
                            changedGameMap[r][c] = 0;
                        }
                        else {
                            swap(changedGameMap, mergeableIdx + 1, c, r, c);
                        }
                        mergeableIdx++;
                    }
                }
            }
            break;
        case Direction::DOWN:
            for (int c = 0; c < n; c++) {
                int mergeableIdx = n - 1;
                for (int r = n - 1; r >= 0; r--) {
                    if (r == mergeableIdx || changedGameMap[r][c] == 0) continue;

                    if (changedGameMap[mergeableIdx][c] == 0) {
                        swap(changedGameMap, mergeableIdx, c, r, c);
                    }
                    else {
                        if (changedGameMap[mergeableIdx][c] == changedGameMap[r][c]) {
                            changedGameMap[mergeableIdx][c] *= 2;
                            changedGameMap[r][c] = 0;
                        }
                        else {
                            swap(changedGameMap, mergeableIdx - 1, c, r, c);
                        }
                        mergeableIdx--;
                    }
                }
            }
            break;
        case Direction::RIGHT:
            for (int r = 0; r < n; r++) {
                int mergeableIdx = n - 1;
                for (int c = n - 1; c >= 0; c--) {
                    if (c == mergeableIdx || changedGameMap[r][c] == 0) continue;

                    if (changedGameMap[r][mergeableIdx] == 0) {
                        swap(changedGameMap, r, mergeableIdx, r, c);
                    }
                    else {
                        if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
                            changedGameMap[r][mergeableIdx] *= 2;
                            changedGameMap[r][c] = 0;
                        }
                        else {
                            swap(changedGameMap, r, mergeableIdx - 1, r, c);
                        }
                        mergeableIdx--;
                    }
                }
            }
            break;
        case Direction::LEFT:
            for (int r = 0; r < n; r++) {
                int mergeableIdx = 0;
                for (int c = 0; c < n; c++) {
                    if (c == mergeableIdx || changedGameMap[r][c] == 0) continue;

                    if (changedGameMap[r][mergeableIdx] == 0) {
                        swap(changedGameMap, r, mergeableIdx, r, c);
                    }
                    else {
                        if (changedGameMap[r][mergeableIdx] == changedGameMap[r][c]) {
                            changedGameMap[r][mergeableIdx] *= 2;
                            changedGameMap[r][c] = 0;
                        }
                        else {
                            swap(changedGameMap, r, mergeableIdx + 1, r, c);
                        }
                        mergeableIdx++;
                    }
                }
            }
            break;
        }

        if (arrayEqual(gameMap, changedGameMap)) {
            maxValue = max(maxValue, findMaxValue(changedGameMap));
        }
        else {
            maxValue = max(maxValue, dfs(n, changedGameMap, currentRound + 1));
        }
    }
    return maxValue;
}

int main() {
    int n = 0;
    cin >> n;

    vector<vector<int>> gameMap(n, vector<int>(n));

    for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) cin >> gameMap[r][c];
    }

    cout << dfs(n, gameMap, 0) << endl;
}