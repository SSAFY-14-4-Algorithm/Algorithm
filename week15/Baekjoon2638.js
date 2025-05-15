/*
 * BOJ 2638번 : 치즈
 * 메모리 : 19,180kb
 * 시간 : 292ms
 */

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
let arr = input.slice(1).map((line) => line.split(" ").map(Number));

const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];

let time = 0;

while (true) {
  const check = checkCheese();
  const melt = meltCheese(check);
  if (!melt) break;
  time++;
}

function checkCheese() {
  const visited = Array.from({ length: N }, () => Array(M).fill(false));
  const queue = [[0, 0]];
  visited[0][0] = true;

  while (queue.length) {
    const [x, y] = queue.shift();

    for (let d = 0; d < 4; d++) {
      const nx = x + dx[d];
      const ny = y + dy[d];

      if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && arr[nx][ny] === 0) {
        visited[nx][ny] = true;
        queue.push([nx, ny]);
      }
    }
  }

  return visited; // 외부 공기인 영역 표시
}

function meltCheese(check) {
  const disappear = Array.from({ length: N }, () => Array(M).fill(false));
  let melted = false;

  for (let n = 0; n < N; n++) {
    for (let m = 0; m < M; m++) {
      if (arr[n][m] === 1) {
        let cnt = 0;
        for (let d = 0; d < 4; d++) {
          const nx = n + dx[d];
          const ny = m + dy[d];

          if (nx >= 0 && nx < N && ny >= 0 && ny < M && check[nx][ny]) {
            cnt++;
          }
        }
        if (cnt >= 2) {
          disappear[n][m] = true;
          melted = true;
        }
      }
    }
  }

  for (let n = 0; n < N; n++) {
    for (let m = 0; m < M; m++) {
      if (disappear[n][m]) {
        arr[n][m] = 0;
      }
    }
  }

  return melted;
}

console.log(time);
