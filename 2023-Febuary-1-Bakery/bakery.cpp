#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef pair<int, int> pii;
#define pb push_back

ll T;

bool check(ll N, ll C, ll M, vector<vector<ll>>& customers, ll w) {
  ll d = C+M-w;
  ll low = max((ll)1, C-w);
  ll high = min(d-1, C);

  for (ll i = 0; i < N; i++) {
    ll a = customers[i][0];
    ll b = customers[i][1];
    ll c = customers[i][2];

    if (b-a > 0) {
      low = max(low, (-c + b* d + (b - a - 1)) / (b - a));
    } else if (a - b > 0) {
      high = min(high, (c-b*d) / (a - b));
    } else {
      if (a * d > c) {
        return false;
      }
    }
  }
  return low <= high;
}

ll solve(ll N, ll C, ll M, vector<vector<ll>>& customers) {
  ll low = 0;
  ll high = C + M - 2;
  
  while (low < high) { // notice we do not compare element at mid with our target
    ll mid = low + (high - low) / 2;
    if (check(N, C, M, customers, mid))
      high = mid;
    else
      low = mid + 1;
  }
  return low;
}

int main() {
  // freopen("bakery.in", "r", stdin);
  // freopen("bakery.out", "w", stdout);

  cin >> T;
  for (ll i = 0; i < T; i++) {
    ll N, C, M;
    cin >> N >> C >> M;
    vector<vector<ll>> customers(N, vector<ll>(3));
    for (ll j = 0; j < N; j++) {
      cin >> customers[j][0] >> customers[j][1] >> customers[j][2];
    }

    cout << solve(N, C, M, customers) << endl;
  }

  return 0;
}