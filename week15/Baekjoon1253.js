/*
 * BOJ 1253번 : 좋다
 * 메모리 : 9740kb
 * 시간 : 176ms
 */

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim().split("\n");

const N = parseInt(input[0]);
const arr = input[1].split(" ").map(Number);
const arrSort = arr.sort((a, b) => a - b);
let result = 0;

for (let i = 0; i < N; i++) {
  const target = arr[i];
  let left = 0;
  let right = N - 1;

  while (left < right) {
    if (arrSort[left] + arrSort[right] === target) {
      if (left !== i && right !== i) {
        result++;
        break;
      } else if (left === i) {
        left++;
      } else if (right === i) {
        right--;
      }
    } else if (arrSort[left] + arrSort[right] < target) {
      left++;
    } else {
      right--;
    }
  }
}

console.log(result);
