#include <bits/stdc++.h>
using namespace std;

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int num = 0;
    cin >> num;
    int set = 0;

    int numSet[10] = {};

    while(num != 0) {
        int a = num % 10;
        num = num / 10;
        numSet[a]++;
        for(int i = 0; i < 9; i == 5? i+=2: i++) {
            set = max(set, numSet[i]);
        }

        int sixNine = numSet[6] + numSet[9];
        set = max(set, (int) ceil((double)sixNine / 2));
    }

    cout << set;


    return 0;
}