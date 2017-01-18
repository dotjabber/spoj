package com.spojen.challenge;
/**
 * ACCEPTED
 * - name must be changed to 'Main'
 * - s.close(); can be omitted.
 */
import java.util.*;public class KAMIL{public static void main(String[]a){Scanner s=new Scanner(System.in);while(s.hasNext()){String l=s.next();int v=1;for(int i=0;i<l.length();i++){v*="DTLF".indexOf(l.charAt(i))>-1?2:1;}System.out.println(v);s.close();}}}
