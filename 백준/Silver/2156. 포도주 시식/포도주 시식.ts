const input = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n')

//포도주 시식
//https://www.acmicpc.net/problem/2156

function main(): void {
  const n: number = parseInt(input[0])
  const wines = input.slice(1).map((e: string) => parseInt(e))
  const result = solution(n, wines)
  console.log(result)
}

function solution(n: number, wines: number[]): number {
  const dp: number[] = new Array(n).fill(0)
  dp[0] = wines[0]
  dp[1] = wines[0] + wines[1]
  dp[2] = Math.max(wines[0] + wines[2], wines[1] + wines[2], dp[1])

  for (let i = 3; i < n; i++) {
    dp[i] = Math.max(
      dp[i - 3] + wines[i - 1] + wines[i],
      dp[i - 2] + wines[i],
      dp[i - 1]
    )
  }

  return dp[n - 1]
}

main()