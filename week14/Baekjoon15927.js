/*
 * BOJ 15922 : 회문은 회문아니야
 * 메모리 : 41,028kb
 * 시간 : 256ms
 */

const fs = require("fs");
const input = fs.readFileSync("input.txt").toString().trim();

const reversed = input.split("").reverse().join("");

if (input != reversed) {
  console.log(input.length);
} else {
  const same = input.split("").every((ch) => ch === input[0]);
  if (same) {
    console.log(-1);
  } else {
    console.log(input.length - 1);
  }
}
