#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <queue>
#include <unordered_map>

using namespace std;

struct Node {
    int value = 0;
    Node* outNode = NULL;
    int enterNodeCount = 0;
};

int solution(int n, int k, vector<pair<string, string>> connections) {
    unordered_map<string, unique_ptr<Node>> nodes;

    for (auto& connection : connections) {
        string parent = connection.first, child = connection.second;
        nodes.emplace(parent, make_unique<Node>());
        nodes.emplace(child, make_unique<Node>());
        nodes[parent]->enterNodeCount += 1;
        nodes[child]->outNode = nodes[parent].get();
    }

    queue<Node*> q;

    for (auto& node : nodes) {
        if (node.second->enterNodeCount == 0) {
            node.second->value = 1;
            q.push(node.second.get());
        }
    }

    int totalWeight = 0;

    while (!q.empty()) {
        Node* currentNode = q.front();
        q.pop();
        totalWeight += currentNode->value;
        if (currentNode->outNode != NULL) {
            currentNode->outNode->value += currentNode->value;
            if (--(currentNode->outNode->enterNodeCount) == 0) {
                q.push(currentNode->outNode);
            }
        }
    }

    return k / totalWeight;
}

int main() {
    int t;
    cin >> t;

    for (int testNum = 1; testNum <= t; testNum++) {
        int n, k;
        cin >> n >> k;

        vector<pair<string, string>> connections(n);
        for (int i = 0; i < n; i++) {
            cin >> connections[i].first >> connections[i].second;
        }

        cout << "#" << testNum << ": " << solution(n, k, connections) << endl;
    }
}