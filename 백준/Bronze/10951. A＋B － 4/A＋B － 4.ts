import * as readLine from "readline";

interface TestCase {
  a: number;
  b: number;
}

async function getInput(): Promise<string> {
  const rl = readLine.createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  let input = "";

  for await (const line of rl) {
    input += line + "\n";
    if (line === "") break;
  }

  rl.close();
  return input.trim();
}

function parseInt(input: string): TestCase[] {
  const lines = input.split("\n");
  const testCases: TestCase[] = [];

  for (let line of lines) {
    const [a, b] = line.split(" ").map(Number);
    testCases.push({ a, b });
  }

  return testCases;
}

function solve(testCases: TestCase[]): number[] {
  return testCases.map((testCase) => testCase.a + testCase.b);
}

function printOutput(output: number[]) {
  output.forEach((value) => console.log(value));
}

async function main() {
  const input = await getInput();
  const testCases = parseInt(input);
  const output = solve(testCases);
  printOutput(output);
}

main().catch((error) => console.error(error));