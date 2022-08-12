package priv.dotjabber.spoj.en.challenge;

/**
 * https://www.spoj.com/problems/EVAL/
 **/
public class EVAL {
    public static long[] fix(long[] val, long shifter) {
        for(int i = val.length - 1; i > 0; i--) {
            if(val[i] > shifter) {
                val[i - 1] += val[i] / shifter;
                val[i] = val[i] % shifter;
            }
        }
        return val;
    }

    public static void div(long[] in, long divisor, long shifter) {
        long count = in[0];
        for (int i = 0; i < in.length; i++) {
            if(count > 0) {
                in[i] = count / divisor;
                count -= in[i] * divisor;
            }

            if(i + 1 < in.length) {
                count *= shifter;
                count += in[i + 1];
            }
        }
    }

    public static void add(long[] val, long[] param) {
        for(int i = 0; i < val.length; i++) {
            val[i] += param[i];
        }
    }

    public static void print(long[] val) {
        System.out.print("2.");
        for(int i = 1; i < val.length; i++) {
            if(val[i] < 10000000000000L) {
                System.out.printf("%014d", val[i]);

            } else {
                System.out.print(val[i]);
            }
        }
    }

    public static void main(String[] args) {
        long[] in = new long[7000];
        long[] out = new long[7000];

        // set '1' in 'in' and 'out'
        in[0] = 1;
        out[0] = 1;
        long shifter = 100000000000000L;

        for(long divisor = 2; divisor < 16500; divisor++) {
            div(in, divisor, shifter);
            add(out, in);
        }
        print(fix(out, shifter));
    }
}
