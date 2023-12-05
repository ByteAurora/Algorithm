import heapq
import sys

input = sys.stdin.readline
N, K = map(int, input().split())

jewelry_list = []
for _ in range(N):
    weight, value = map(int, input().split())
    heapq.heappush(jewelry_list, (weight, value))

bags = [int(input()) for _ in range(K)]
bags.sort()

total_value = 0
searched_jewelry_list = []
for bag in bags:
    while jewelry_list and bag >= jewelry_list[0][0]:
        heapq.heappush(searched_jewelry_list, -1 * heapq.heappop(jewelry_list)[1])

    if searched_jewelry_list:
        total_value += -1 * heapq.heappop(searched_jewelry_list)
    elif not jewelry_list:
        break

print(total_value)
