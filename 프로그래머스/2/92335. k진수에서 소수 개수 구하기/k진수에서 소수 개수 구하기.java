class Solution {
    public int solution(int n, int k) {
        String input = Integer.toString(n, k);
        String[] numbers = input.split("0+");
        
        int cnt = 0;
        for (String num : numbers) {
            if (!num.isEmpty()) {
                long val = Long.parseLong(num);
                if (val > 1) {
                    boolean primeNumber = true;
                    for (long i = 2; i * i <= val; i++) {
                        if (val % i == 0) {
                            primeNumber = false;
                            break;
                        }
                    }
                    if (primeNumber) cnt++;
                }
            }
        }
        return cnt;
    }
}