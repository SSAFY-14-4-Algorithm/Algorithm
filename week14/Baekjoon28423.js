/*
 * BOJ 28423 : 게임
 * 메모리 : 20,672kb
 * 시간 : 248ms
 */

const fs = require("fs");
const input = fs.readFileSync("../algorithm/input.txt").toString().trim().split(" ");

const L = Number(input[0]);
const R = Number(input[1]);

let result = 0;
for (let i = L; i <= R; i++) {
  const G = g(i);
  result += G;
}
console.log(result);

function f(x) {
  const str = x.toString();
  let sum = 0;
  let mult = 1;

  for (let i = 0; i < str.length; i++) {
    sum += Number(str[i]);
    mult *= Number(str[i]);
  }

  return Number(sum.toString() + mult.toString());
}

function g(x) {
  const vistied = new Set();

  while (true) {
    const F = f(x);

    if (F === x) return 1;
    if (F > 100000) return -1;
    if (vistied.has(F)) return 0;

    vistied.add(F);
    x = F;
  }
}
