T = int(input())

for _ in range(T):
    commands = input()
    data_length = int(input())

    if data_length == 0:
        input()
        if 'D' in commands:
            print('error')
        else:
            print('[]')
    else:
        datas = list(map(int, input()[1:-1].split(',')))
        start_idx = 0
        end_idx = data_length - 1
        is_reversed = False
        is_error = False

        for command in commands:
            if command == 'R':
                is_reversed = not is_reversed
            elif command == 'D':
                if start_idx > end_idx:
                    is_error = True
                    break

                if is_reversed:
                    end_idx -= 1
                else:
                    start_idx += 1

        if is_error:
            print('error')
        else:
            if start_idx > end_idx:
                print('[]')
            else:
                print('[', end='')
                if is_reversed:
                    for idx in range(end_idx, start_idx, -1):
                        print(datas[idx], end=',')
                    print(datas[start_idx], end='')
                else:
                    for idx in range(start_idx, end_idx, 1):
                        print(datas[idx], end=',')
                    print(datas[end_idx], end='')
                print(']')
