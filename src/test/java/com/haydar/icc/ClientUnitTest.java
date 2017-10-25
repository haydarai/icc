package test.java.com.haydar.icc;

import main.java.com.haydar.icc.Client;
import main.java.com.haydar.icc.ICC;
import org.junit.Before;
import org.junit.Test;

public class ClientUnitTest {

    private Client client[];
    private ICC icc;

    @Before
    public void setUp() {
        icc = new ICC();
        client = new Client[10];
        for (int i = 0; i < client.length; i++) {
            client[i] = new Client(icc, i + 1);
        }
    }

    @Test
    public void test() {
        client[1].start();
        client[3].start();
        client[5].start();
        client[4].start();
    }
}
