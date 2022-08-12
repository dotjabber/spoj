package priv.dotjabber.spoj.en.challenge;

import java.util.*;

/**
 * https://www.spoj.com/problems/KAMIL/
 */
public class KAMIL {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {
            String l = s.next();
            int v = 1;

            for (int i = 0; i < l.length(); i++) {
                v *= "DTLF".indexOf(l.charAt(i)) > -1 ? 2 : 1;
            }

            System.out.println(v);
        }
    }
}

// import java.util.*;public class Main{public static void main(String[]a){Scanner s=new Scanner(System.in);while(s.hasNext()){String l=s.next();int v=1;for(int i=0;i<l.length();i++){v*="DTLF".indexOf(l.charAt(i))>-1?2:1;}System.out.println(v);}}}
