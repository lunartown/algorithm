const input = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n')

//색종이
//https://www.acmicpc.net/problem/2563

function main(): void {
  const n: number = Number(input[0])
  const arr: number[][] = input
    .slice(1)
    .map((e: string) => e.split(' ').map(Number))
  const result: number = solution(n, arr)
  console.log(result)
}

function solution(n: number, arr: number[][]): number {
  const board: number[][] = Array.from(Array(100), () => Array(100).fill(0))
  let result: number = 0

  for (let i = 0; i < n; i++) {
    const [x, y] = arr[i]

    for (let j = x; j < x + 10; j++) {
      for (let k = y; k < y + 10; k++) {
        board[j][k] = 1
      }
    }
  }

  for (let i = 0; i < 100; i++) {
    for (let j = 0; j < 100; j++) {
      if (board[i][j] === 1) {
        result++
      }
    }
  }

  return result
}

main()