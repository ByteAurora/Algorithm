import 'dart:collection';
import 'dart:io';

class Node {
  int value = 0;
  Node? outNode;
  int enterNodeCount = 0;
}

int solution(int n, int k, List<List<String>> connections) {
  var nodes = <String, Node>{};

  for (var connection in connections) {
    var parent = connection[0];
    var child = connection[1];

    nodes.putIfAbsent(parent, () => Node()).enterNodeCount += 1;
    nodes.putIfAbsent(child, () => Node()).outNode = nodes[parent];
  }

  var queue = Queue<Node>();
  for (var node in nodes.values) {
    if (node.enterNodeCount == 0) {
      node.value = 1;
      queue.add(node);
    }
  }

  var totalWeight = 0;
  while (queue.isNotEmpty) {
    var currentNode = queue.removeFirst();

    totalWeight += currentNode.value;

    if (currentNode.outNode != null) {
      var outNode = currentNode.outNode!;
      outNode.value += currentNode.value;
      if (--outNode.enterNodeCount == 0) {
        queue.add(currentNode.outNode!);
      }
    }
  }

  return k ~/ totalWeight;
}

void main() {
  int t = int.parse(stdin.readLineSync()!);

  for (int testNum = 0; testNum < t; testNum++) {
    var inputData = stdin.readLineSync()!.split(" ").map(int.parse).toList();
    var n = inputData[0];
    var k = inputData[1];

    var connections = <List<String>>[];
    for (int i = 0; i < n; i++) {
      connections.add(stdin.readLineSync()!.split(" "));
    }

    stdout.writeln("#${testNum + 1}: ${solution(n, k, connections)}");
  }
}
