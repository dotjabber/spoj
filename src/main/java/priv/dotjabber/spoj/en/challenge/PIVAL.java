package priv.dotjabber.spoj.en.challenge;

/**
 * https://www.spoj.com/problems/PIVAL/
 * https://en.wikipedia.org/wiki/List_of_formulae_involving_π
 */
public class PIVAL {
    public static long[] fix(long[] val, long shifter) {
        for(int i = val.length - 1; i > 0; i--) {
            if(val[i] > shifter) {
                val[i - 1] += val[i] / shifter;
                val[i] = val[i] % shifter;
            }
        }


        return val;
    }

    public static void mul(long[] val, long param) {
        for(int i = 0; i < val.length; i++) {
            val[i] *= param;
        }
    }

    public static void print(long[] val) {
        System.out.print("3.");
        for(int i = 1; i < val.length; i++) {
            if(val[i] < 1000000000000L) {
                System.out.printf("%013d", val[i]);

            } else {
                System.out.print(val[i]);
            }
        }
    }

    public static void muldiv(long[] in, long multiplicator, long divisor, long shifter) {
        long count = in[0];
        long val;
        for (int i = 0; i < in.length; i++) {
            if(count > 0) {
                val = count / divisor;
                in[i] = val * multiplicator;
                count -= val * divisor;
            }

            if(i + 1 < in.length) {
                if(count > 0) {
                    count *= shifter;
                }
                count += in[i + 1];
            }
        }
    }

    public static void main(String[] args) {
        long[] in = new long[2400];

        // set '1' in 'in' and 'out'
        long shifter = 10000000000000L;
        in[0] = 0;

        for (long n = 101000, d = 2 * n + 1; n > 0; n--, d-=2) {
            muldiv(in, n, d, shifter);
            in[0] += 1;
        }
        mul(in, 2);
        print(fix(in, shifter));
    }
}
