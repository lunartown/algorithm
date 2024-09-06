import * as fs from 'fs';

class Point {
  constructor(public x: number, public y: number) {}
}

function vectorProduct(A: Point, B: Point, C: Point): number {
  return (B.x - A.x) * (C.y - A.y) - (B.y - A.y) * (C.x - A.x);
}

function ccw(A: Point, B: Point, C: Point): boolean {
  return vectorProduct(A, B, C) > 0;
}

function distanceSquared(A: Point, B: Point): number {
  return (A.x - B.x) ** 2 + (A.y - B.y) ** 2;
}

function grahamScan(points: Point[]): Point[] {
  // 가장 y좌표가 작은 점을 찾고, y좌표가 같다면 x좌표가 작은 점을 선택
  let pivot = points.reduce((min, p) => 
    p.y < min.y || (p.y === min.y && p.x < min.x) ? p : min
  );

  // 나머지 점들을 pivot을 기준으로 반시계 방향으로 정렬
  points.sort((a, b) => {
    let order = vectorProduct(pivot, a, b);
    if (order === 0) {
      return distanceSquared(pivot, a) - distanceSquared(pivot, b);
    }
    return -order;
  });

  let stack: Point[] = [points[0], points[1]];

  for (let i = 2; i < points.length; i++) {
    while (
      stack.length >= 2 &&
      !ccw(stack[stack.length - 2], stack[stack.length - 1], points[i])
    ) {
      stack.pop();
    }
    stack.push(points[i]);
  }

  return stack;
}

const input: string[] = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const N = Number(input[0]);
const points: Point[] = [];

for (let i = 1; i <= N; i++) {
  const [x, y] = input[i].split(' ').map(Number);
  points.push(new Point(x, y));
}

const convexHull = grahamScan(points);
console.log(convexHull.length);