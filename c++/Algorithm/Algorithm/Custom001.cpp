#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, int k, vector<int> interests) {
    int maxGroup = 0, startIdx = 0, endIdx = 0;

    sort(interests.begin(), interests.end());

    while (endIdx < n) {
        while (endIdx < n && interests[endIdx] - interests[startIdx] <= k) endIdx++;

        maxGroup = max(maxGroup, endIdx - startIdx);

        while (endIdx < n && interests[endIdx] - interests[startIdx] > k) startIdx++;
    }

    return maxGroup;
}

int main() {
    int t;
    cin >> t;

    for (int testNum = 0; testNum < t; testNum++) {
        int n, k;
        cin >> n >> k;

        vector<int> interests(n);
        for (int i = 0; i < n; i++) {
            cin >> interests[i];
        }

        cout << "#" << testNum + 1 << ": " << solution(n, k, interests) << endl;
    }
}