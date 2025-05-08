/*
 * BOJ 15922 : 아우으 우아으이야
 * 메모리 : 46,168kb
 * 시간 : 324ms
 */

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");
// const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = input[0];
const line = input.slice(1).map((line) => line.split(" ").map(Number));

// 1. 시작점 기준으로 정렬
line.sort((a, b) => a[0] - b[0]);

let [start, end] = line[0];
let total = 0;

for (let i = 1; i < N; i++) {
  const [nextStart, nextEnd] = line[i];

  if (nextStart <= end) {
    // 2. 겹치면, 현재 선분 확장장
    end = Math.max(end, nextEnd);
  } else {
    // 3. 안겹치면, 지금까지 길이 더하고 다음 선분 시작
    total += end - start;
    start = nextStart;
    end = nextEnd;
  }
}

// 마지막 선분도 누적
total += end - start;

console.log(total);
