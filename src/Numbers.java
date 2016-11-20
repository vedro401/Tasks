/**
 * Created by someone on 19.11.16.
 */
public class Numbers {
    static int [] degs; // for first method
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        seek1(5);
        System.out.println("seek 1 time = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        seek2(5);
        System.out.println("seek 2 time = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        seek3();
        System.out.println("seek 3 time = " + (System.currentTimeMillis() - time));


    }

    /**
     *  That was first method and I was sure I need to use recursion
     *  for making it adaptive. But it's the slowest version.
     *  lead time ~ 138
     */

    private static void seek1(int x){
        degs = new int[10];
        for (int i = 0; i < degs.length; i++) {
            degs[i] = (int) Math.pow(i, x);
        }

        long lim;
        int degN = (int)Math.pow(9,x);
        int n = 2;
        int degT = 100;
        while(n*degN > degT){
            n++;
            degT *= 10;
        }
        lim = n*degN;
        System.out.println("lim " + lim);

        for (int i = 1; i < 10; i++) {
            calc(i*10,degs[i],i, lim);
        }
    }
    private static void calc(int c1, int c2, int ans, long lim){
        for (int i = 0; i < 10; i++) {
            if(c1 + i == c2 + degs[i]){
                System.out.println(ans*10 + i);
            }
        }
        if(ans < lim)
        for (int i = 0; i < 10; i++) {
                calc((c1 + i)*10,(c2 + degs[i]),ans*10 + i, lim);
        }
    }

    /**
     * Second version. Faster then first and it disappoints me,
     * I thought recursive method will giv me advantage
     * lead time ~ 30
     */
    private static void seek2(int x){

        int[] deg = new int[10];
        for (int i = 0; i < deg.length; i++) {
            deg[i] = (int) Math.pow(i, x);
        }
        long lim;
        int degN = (int)Math.pow(9,x);
        int n = 2;
        int degT = 100;
        while(n*degN > degT){
            n++;
            degT *= 10;
        }
        lim = n*degN;
        degT /= 10;

        for (int i = 10; i < lim; i++) {
            int res = 0;
            for (int j = 1; j <= degT; j *= 10) {
                res += deg[digit(i, j)];
            }

            if (res == i) {
                System.out.println(res);
            }
        }

    }
    static int digit(int x, int i) {
        return (x / i) % 10;
    }

    /**
     * Hardcode version.
     * Ugly, but the fastest of all.
     * lead time ~ 15
     */

    private static void seek3(){
        int[] deg = new int[10];
        for (int i = 0; i < deg.length; i++) {
            deg[i] = (int) Math.pow(i, 5);
        }

        int sum = 0;
        int num = 0;

        for (int i = 0; i < 4; i++) {
            num += i * 100000;
            sum += deg[i];
            for (int j = 0; j < deg.length; j++) {
                num += j * 10000;
                sum += deg[j];
                for (int j2 = 0; j2 < deg.length; j2++) {
                    num += j2 * 1000;
                    sum += deg[j2];
                    for (int k = 0; k < deg.length; k++) {
                        num += k * 100;
                        sum += deg[k];
                        for (int k2 = 1; k2 < deg.length; k2++) {
                            num += k2 * 10;
                            sum += deg[k2];
                            for (int l = 0; l < deg.length; l++) {
                                num += l * 1;
                                sum += deg[l];
                                if (num == sum)
                                    System.out.println(num);
                                num -= l * 1;
                                sum -= deg[l];
                            }
                            num -= k2 * 10;
                            sum -= deg[k2];
                        }
                        num -= k * 100;
                        sum -= deg[k];
                    }
                    num -= j2 * 1000;
                    sum -= deg[j2];
                }
                num -= j * 10000;
                sum -= deg[j];
            }
            num -= i * 100000;
            sum -= deg[i];
        }
    }

}
