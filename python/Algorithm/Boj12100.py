UP = 0
DOWN = 1
LEFT = 2
RIGHT = 3
DIRECTIONS = [UP, DOWN, LEFT, RIGHT]


def dfs(N, game_map, current_round):
    if current_round == 5:
        return max(max(row) for row in game_map)
    max_value = 0
    for direction in DIRECTIONS:
        changed_map = [row[:] for row in game_map]
        if direction == UP:
            for c in range(N):
                mergeble_idx = 0
                for r in range(N):
                    if r != mergeble_idx and changed_map[r][c] != 0:
                        if changed_map[mergeble_idx][c] == 0:
                            changed_map[r][c], changed_map[mergeble_idx][c] = \
                                changed_map[mergeble_idx][c], changed_map[r][
                                    c]
                        else:
                            if changed_map[mergeble_idx][c] == changed_map[r][c]:
                                changed_map[mergeble_idx][c] *= 2
                                changed_map[r][c] = 0
                                mergeble_idx += 1
                            else:
                                mergeble_idx += 1
                                changed_map[r][c], changed_map[mergeble_idx][c] = \
                                    changed_map[mergeble_idx][c], \
                                        changed_map[r][c]
        elif direction == DOWN:
            for c in range(N):
                mergeble_idx = N - 1
                for r in range(N - 1, -1, -1):
                    if r != mergeble_idx and changed_map[r][c] != 0:
                        if changed_map[mergeble_idx][c] == 0:
                            changed_map[r][c], changed_map[mergeble_idx][c] = \
                                changed_map[mergeble_idx][c], changed_map[r][
                                    c]
                        else:
                            if changed_map[mergeble_idx][c] == changed_map[r][c]:
                                changed_map[mergeble_idx][c] *= 2
                                changed_map[r][c] = 0
                                mergeble_idx -= 1
                            else:
                                mergeble_idx -= 1
                                changed_map[r][c], changed_map[mergeble_idx][c] = \
                                    changed_map[mergeble_idx][c], \
                                        changed_map[r][c]
        elif direction == RIGHT:
            for r in range(N):
                mergeble_idx = N - 1
                for c in range(N - 1, -1, -1):
                    if c != mergeble_idx and changed_map[r][c] != 0:
                        if changed_map[r][mergeble_idx] == 0:
                            changed_map[r][c], changed_map[r][mergeble_idx] = changed_map[r][
                                mergeble_idx], changed_map[r][
                                c]
                        else:
                            if changed_map[r][mergeble_idx] == changed_map[r][c]:
                                changed_map[r][mergeble_idx] *= 2
                                changed_map[r][c] = 0
                                mergeble_idx -= 1
                            else:
                                mergeble_idx -= 1
                                changed_map[r][c], changed_map[r][mergeble_idx] = changed_map[r][
                                    mergeble_idx], \
                                    changed_map[r][c]
        else:
            for r in range(N):
                mergeble_idx = 0
                for c in range(N):
                    if c != mergeble_idx and changed_map[r][c] != 0:
                        if changed_map[r][mergeble_idx] == 0:
                            changed_map[r][c], changed_map[r][mergeble_idx] = changed_map[r][
                                mergeble_idx], changed_map[r][
                                c]
                        else:
                            if changed_map[r][mergeble_idx] == changed_map[r][c]:
                                changed_map[r][mergeble_idx] *= 2
                                changed_map[r][c] = 0
                                mergeble_idx += 1
                            else:
                                mergeble_idx += 1
                                changed_map[r][c], changed_map[r][mergeble_idx] = changed_map[r][
                                    mergeble_idx], \
                                    changed_map[r][c]
        if game_map != changed_map:
            max_value = max(max_value, dfs(N, changed_map, current_round + 1))
        else:
            max_value = max(max_value, max(max(row) for row in game_map))

    return max_value


def solution(N, game_map):
    return dfs(N, game_map[:], 0)


N = int(input())
game_map = []

for _ in range(N):
    game_map.append(list(map(int, input().split())))

print(solution(N, game_map))
