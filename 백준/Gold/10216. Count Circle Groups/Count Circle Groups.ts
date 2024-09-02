const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');

interface Point {
    x: number;
    y: number;
    r: number;
}

function distance(p1: Point, p2: Point): number {
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
}

function canCommunicate(p1: Point, p2: Point): boolean {
    return distance(p1, p2) <= p1.r + p2.r;
}

function dfs(graph: number[][], visited: boolean[], current: number): void {
    visited[current] = true;
    for (const neighbor of graph[current]) {
        if (!visited[neighbor]) {
            dfs(graph, visited, neighbor);
        }
    }
}

function countGroups(points: Point[]): number {
    const n = points.length;
    const graph: number[][] = Array(n).fill(null).map(() => []);

    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            if (canCommunicate(points[i], points[j])) {
                graph[i].push(j);
                graph[j].push(i);
            }
        }
    }

    const visited: boolean[] = Array(n).fill(false);
    let groupCount = 0;

    for (let i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(graph, visited, i);
            groupCount++;
        }
    }

    return groupCount;
}

function main(input: string[]): void {
    const T = parseInt(input[0]);
    let lineIndex = 1;

    for (let t = 0; t < T; t++) {
        const N = parseInt(input[lineIndex++]);
        const points: Point[] = [];

        for (let i = 0; i < N; i++) {
            const [x, y, r] = input[lineIndex++].split(' ').map(Number);
            points.push({ x, y, r });
        }

        console.log(countGroups(points));
    }
}

main(input);