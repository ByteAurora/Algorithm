from collections import deque


class Node:
    def __init__(self):
        self.value = 0
        self.out_node = None
        self.enter_node_count = 0


T = int(input())

for test_num in range(T):
    N, K = list(map(int, input().split()))
    nodes = {}

    for _ in range(N):
        parent, child = input().split()

        if parent not in nodes:
            nodes[parent] = Node()

        if child not in nodes:
            nodes[child] = Node()

        nodes[parent].enter_node_count += 1
        nodes[child].out_node = nodes[parent]

    queue = deque([])

    for node_name, node in nodes.items():
        if node.enter_node_count == 0:
            node.value = 1
            queue.append(node)

    total_weight = 0

    while queue:
        current_node = queue.popleft()

        total_weight += current_node.value

        if current_node.out_node:
            current_node.out_node.value += current_node.value
            current_node.out_node.enter_node_count -= 1

            if current_node.out_node.enter_node_count == 0:
                queue.append(current_node.out_node)

    print('#%d: %d' % (test_num + 1, K // total_weight))
