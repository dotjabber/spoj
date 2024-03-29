package priv.dotjabber.spoj.classical;

import org.junit.jupiter.api.Test;
import priv.dotjabber.spoj.en.classical.PALIN;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PALINTest {
    
    @Test
    public void testPalindrome() {
        String[] in = { "808", "2133", "9999999", "999999", "899998", "3423355356", "100001", "46887767", "9", "99",
                "0", "1991", "1239400", "123999500", "56442", "56471", "123456", "1234567", "77777777777", "2991", "3994",
                "9999", "5448", "653434", "101", "199", "0012100", "0003", "123456", "1234567", "9999", "99999", "1",
                "2991", "3994", "9999", "5448", "65343454", "100", "4111" };
        String[] out = { "818", "2222", "10000001", "1000001", "900009", "3423443243", "101101", "46888864", "11", "101",
                "1", "2002", "1240421", "124000421", "56465", "56565", "124421", "1235321", "77777877777", "2992", "4004",
                "10001", "5555", "654456", "111", "202", "0013100", "0110", "124421", "1235321", "10001", "100001", "2",
                "2992", "4004", "10001", "5555", "65344356", "101", "4114" };

        for (int i = 0; i < in.length; i++) {
           assertEquals(PALIN.next(in[i]), out[i]);
        }
    }
}
