public class Calculation {
    public static int combination(int n, int r) {
        if (r > n)
            return 0;
        if (r == 0 || r == n)
            return 1;
        return combination(n - 1, r - 1) + combination(n - 1, r);
    }
}
