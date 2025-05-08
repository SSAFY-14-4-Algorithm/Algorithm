/*
 * BOJ 15919 : 사자는 여행왕이야야
 * 메모리 : 11,560kb
 * 시간 : 168ms
 * dp[i] = i일에 여행을 시작했을 때, i일 이전까지의 최대 공백
 */

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");

const N = Number(input[0]);
const M = Number(input[1]);
const plans = input.slice(2).map((line) => line.split(" ").map(Number));

plans.sort((a, b) => a[1] - b[1]);

const dp = new Array(N + 1).fill(Infinity);
dp[0] = 0;

for (let [start, end] of plans) {
  for (let i = 0; i < start; i++) {
    const diff = start - i - 1; // i+1일 ~ start-1일 전까지 공백
    const maxDiff = Math.max(dp[i], diff);
    dp[end] = Math.min(dp[end], maxDiff);
  }
}

// 마지막 날 이후 남은 공백 고려
let answer = Infinity;
for (let i = 0; i <= N; i++) {
  const diff = N - i; // i일 이후로 여행 없는 공백
  answer = Math.min(answer, Math.max(dp[i], diff));
}

console.log(answer);
