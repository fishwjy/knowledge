import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vincent
 * Date: 2021/3/17
 * Time: 14:26
 *
 * VM args: -Xms4M -Xmx4M -Xmn2M SoftReferenceTest$SoftObject
 *
 * size of cache: 1
 * size of cache: 10001
 * size of cache: 20001
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 *         at SoftReferenceTest$SoftObject.<init>(SoftReferenceTest.java:13)
 *         at SoftReferenceTest$SoftObject.main(SoftReferenceTest.java:20)
 *
 */

public class SoftReferenceTest {
    public static class SoftObject {
        byte[] data = new byte[1024];

        public static int CACHE_INITIAL_CAPACITY = 100 * 1024; //100M
        public static Set<SoftReference<SoftObject>> cache = new HashSet<>(CACHE_INITIAL_CAPACITY);

        public static void main(String[] args) {
            for (int i = 0; i < CACHE_INITIAL_CAPACITY; i++) {
                SoftObject obj = new SoftObject();
                cache.add(new SoftReference<>(obj));
                if (i % 10000 == 0) {
                    System.out.println("size of cache: " + cache.size());
                }
            }

            System.out.println("End!");
        }
    }

//    public static class SoftObject {
//        byte[] data = new byte[1024];
//
//        public static int removedSoftRefs = 0;
//        public static int CACHE_INITIAL_CAPACITY = 100 * 1024; //100M
//        public static Set<SoftReference<SoftObject>> cache = new HashSet<>(CACHE_INITIAL_CAPACITY);
//        public static ReferenceQueue<SoftObject> referenceQueue = new ReferenceQueue<>();
//
//        public static void main(String[] args) {
//            for (int i = 0; i < CACHE_INITIAL_CAPACITY; i++) {
//                SoftObject obj = new SoftObject();
//                cache.add(new SoftReference<>(obj, referenceQueue));
//                clearUselessReferences();
//                if (i % 10000 == 0) {
//                    System.out.println("size of cache: " + cache.size());
//                }
//            }
//
//            System.out.println("End, removed soft reference = " + removedSoftRefs);
//        }
//
//        public static void clearUselessReferences() {
//            Reference<? extends SoftObject> ref = referenceQueue.poll();
//            while (ref != null) {
//                if (cache.remove(ref)) {
//                    removedSoftRefs++;
//                }
//                ref = referenceQueue.poll();
//            }
//        }
//    }
}
