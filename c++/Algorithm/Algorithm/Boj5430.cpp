#include <iostream>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

void printArray(vector<string>& numberData, int startIdx, int endIdx, bool isReversed) {
    cout << '[';

    if (isReversed) {
        for (int idx = endIdx; idx >= startIdx; idx--) {
            cout << numberData[idx];
            if (idx > startIdx) cout << ',';
        }
    }
    else {
        for (int idx = startIdx; idx <= endIdx; idx++) {
            cout << numberData[idx];
            if (idx < endIdx) cout << ',';
        }
    }

    cout << ']' << endl;
}

int main() {
    int T;
    cin >> T;

    for (int testIdx = 0; testIdx < T; testIdx++) {
        string commands, inputData;
        int dataLength;
        cin >> commands;
        cin >> dataLength;
        cin >> inputData;

        if (dataLength == 0 && commands.find('D') != string::npos) {
            cout << "error" << endl;
            continue;
        }

        bool isError = false;
        bool isReversed = false;
        int startIdx = 0, endIdx = dataLength - 1;

        vector<string> numberData;
        inputData = inputData.substr(1, inputData.length() - 2);

        stringstream ss(inputData);
        string item;

        while (getline(ss, item, ',')) {
            numberData.push_back(item);
        }

        for (int commandIdx = 0; commandIdx < commands.length(); commandIdx++) {
            if (commands[commandIdx] == 'R') {
                isReversed = !isReversed;
            }
            else if (commands[commandIdx] == 'D') {
                if (startIdx > endIdx) {
                    isError = true;
                    break;
                }

                if (isReversed) {
                    endIdx--;
                }
                else {
                    startIdx++;
                }
            }
        }

        if (isError) {
            cout << "error" << endl;
        }
        else {
            printArray(numberData, startIdx, endIdx, isReversed);
        }
    }
}