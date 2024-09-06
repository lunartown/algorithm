import * as fs from 'fs';

class Point {
    constructor(public x: number, public y: number) {}
}

function dist(p1: Point, p2: Point): number {
    return (p1.x - p2.x) ** 2 + (p1.y - p2.y) ** 2;
}

function bruteForce(points: Point[], start: number, end: number): number {
    let minDist = Infinity;
    for (let i = start; i < end; i++) {
        for (let j = i + 1; j < end; j++) {
            minDist = Math.min(minDist, dist(points[i], points[j]));
        }
    }
    return minDist;
}

function stripClosest(strip: Point[], size: number, d: number): number {
    let min = d;
    strip.sort((a, b) => a.y - b.y);

    for (let i = 0; i < size; i++) {
        for (let j = i + 1; j < size && (strip[j].y - strip[i].y) ** 2 < min; j++) {
            min = Math.min(min, dist(strip[i], strip[j]));
        }
    }
    return min;
}

function closestUtil(points: Point[], start: number, end: number): number {
    if (end - start <= 3) {
        return bruteForce(points, start, end);
    }

    const mid = Math.floor((start + end) / 2);
    const midPoint = points[mid];
    const dl = closestUtil(points, start, mid);
    const dr = closestUtil(points, mid, end);
    let d = Math.min(dl, dr);

    const strip: Point[] = [];
    for (let i = start; i < end; i++) {
        if ((points[i].x - midPoint.x) ** 2 < d) {
            strip.push(points[i]);
        }
    }

    return Math.min(d, stripClosest(strip, strip.length, d));
}

function closest(points: Point[]): number {
    points.sort((a, b) => a.x - b.x);
    return closestUtil(points, 0, points.length);
}

const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const n = parseInt(input[0], 10);
const points: Point[] = [];

for (let i = 1; i <= n; i++) {
    const [x, y] = input[i].split(' ').map(Number);
    points.push(new Point(x, y));
}

console.log(closest(points));