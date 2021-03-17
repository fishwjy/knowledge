/**
 * Created by vincent
 * Date: 2021/3/17
 * Time: 10:56
 * 新生代 GC：这一区域的 GC 叫作 Minor GC。因为 Java 对象大多都具备朝生夕灭的特性，所以 Minor GC 非常频繁，一般回收速度也比较快。
 * 老年代 GC：发生在这一区域的 GC 也叫作 Major GC 或者 Full GC。当出现了 Major GC，经常会伴随至少一次的 Minor GC。
 *
 * -verbose:gc   显示GC的操作内容
 * -Xms20M       初始化堆大小为20MB
 * -Xmx20M       设置堆最大分配内存20MB
 * -Xmn10M       设置新生代的内存大小为10M
 * -XX:+PrintGCDetails   打印GC的详细log日志
 * -XX:SurvivorRatio=8   新生代中Eden区域与Survivor区域的大小比值为8:1:1
 *
 * VM agrs: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * a4为1MB时
 * Heap
 * PSYoungGen??????total?9216K,?used?8003K?[0x00000007bf600000,?0x00000007c0000000,?0x00000007c0000000)
 * ??eden?space?8192K,?97%?used?[0x00000007bf600000,0x00000007bfdd0ed8,0x00000007bfe00000)
 * ??from(S0)?space?1024K,?0%?used?[0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 * ??to(S1)???space?1024K,?0%?used?[0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 * ParOldGen???????total?10240K,?used?0K?[0x00000007bec00000,?0x00000007bf600000,?0x00000007bf600000)
 * ??object?space?10240K,?0%?used?[0x00000007bec00000,0x00000007bec00000,0x00000007bf600000)
 * Metaspace???????used?2631K,?capacity?4486K,?committed?4864K,?reserved?1056768K
 * ??class?space????used?286K,?capacity?386K,?committed?512K,?reserved?1048576K
 *
 *
 * a4为2MB时
 * [GC?(Allocation?Failure)?[PSYoungGen:?6815K->480K(9216K)]?6815K->6632K(19456K),?0.0067344?secs]?[Times:?user=0.04?sys=0.00,?real=0.01?secs]
 * Heap
 * PSYoungGen??????total?9216K,?used?2130K?[0x00000007bf600000,?0x00000007c0000000,?0x00000007c0000000)
 * ??eden?space?8192K,?26%?used?[0x00000007bf600000,0x00000007bf814930,0x00000007bfe00000)
 * ??from?space?1024K,?0%?used?[0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 * ??to???space?1024K,?0%?used?[0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 * ParOldGen???????total?10240K,?used?6420K?[0x00000007bec00000,?0x00000007bf600000,?0x00000007bf600000)
 * ??object?space?10240K,?62%?used?[0x00000007bec00000,0x00000007bf2450d0,0x00000007bf600000)
 * Metaspace???????used?2632K,?capacity?4486K,?committed?4864K,?reserved?1056768K
 * ??class?space????used?286K,?capacity?386K,?committed?512K,?reserved?1048576K
 */

public class MinorGCTest {
    private static final int _1MB = 1024 * 1024;
    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a4 = new byte[1 * _1MB];
    }

    public static void main(String[] agrs) {
        testAllocation();
    }
}
