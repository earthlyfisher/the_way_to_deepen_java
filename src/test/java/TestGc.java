import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc  -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * <p>
 * Created by inspur on 2017/5/28.
 */
public class TestGc {

    static class OOMObject {
        private byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
       /* List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }

        System.gc();*/

        Thread.sleep(1000000L);
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
