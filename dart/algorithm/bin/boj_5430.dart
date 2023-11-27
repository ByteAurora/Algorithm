import 'dart:io';

void main(List<String> arguments) {
  int T = int.parse(stdin.readLineSync()!);

  for (int i = 0; i < T; i++) {
    String commands = stdin.readLineSync()!;
    int dataLength = int.parse(stdin.readLineSync()!);

    bool isError = false;
    bool isReversed = false;
    int startIdx = 0;
    int endIdx = dataLength - 1;

    String inputData = stdin.readLineSync()!;

    if (dataLength == 0) {
      if (commands.contains('D')) {
        print('error');
        return;
      }
    }

    List<int> data = inputData
        .replaceAll(RegExp(r'[\[\]]'), '')
        .split(',')
        .map((str) => int.parse(str))
        .toList();

    for (var command in commands.runes) {
      if (command == 'R'.runes.single) {
        isReversed = !isReversed;
      } else if (command == 'D'.runes.single) {
        if (startIdx > endIdx) {
          isError = true;
          break;
        } else {
          if (isReversed) {
            endIdx -= 1;
          } else {
            startIdx += 1;
          }
        }
      }
    }

    if (isError) {
      stdout.write("error\n");
    } else if (startIdx > endIdx) {
      stdout.write("[]\n");
    } else if (isReversed) {
      stdout.write("[");
      for (int idx = endIdx; idx > startIdx; idx--) {
        stdout.write("${data[idx]},");
      }
      stdout.write("${data[startIdx]}]\n");
    } else {
      stdout.write("[");
      for (int idx = startIdx; idx < endIdx; idx++) {
        stdout.write("${data[idx]},");
      }
      stdout.write("${data[endIdx]}]\n");
    }
  }
}
