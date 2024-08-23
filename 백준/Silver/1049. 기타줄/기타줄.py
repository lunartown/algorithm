import sys
input = sys.stdin.readline

N, M = map(int, input().split())
min_package = float('inf')
min_single = float('inf')

for _ in range(M):
    package, single = map(int, input().split())
    min_package = min(min_package, package)
    min_single = min(min_single, single)

# 패키지 가격이 6개 낱개보다 비싸면 패키지 가격을 6개 낱개 가격으로 조정
min_package = min(min_package, min_single * 6)

# 세 가지 경우를 모두 고려하여 최소 비용 계산
total_cost = min(
    ((N // 6) + 1) * min_package,  # 모두 패키지로 구매
    (N // 6) * min_package + (N % 6) * min_single,  # 패키지와 낱개 혼합
    N * min_single  # 모두 낱개로 구매
)

print(total_cost)