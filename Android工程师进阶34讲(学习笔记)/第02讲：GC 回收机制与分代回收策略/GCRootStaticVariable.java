/**
 * Created by vincent
 * Date: 2021/3/16
 * Time: 4:54 PM
 *
 * 验证方法区中的静态变量引用的对象作为 GC Root
 *
 *
 * 运行结果：
 * 
 * 程序开始:
 * free is 241 M, total is 245 M,
 * free is 120 M, total is 245 M,
 * GC完成
 * free is 163 M, total is 245 M,
 */

public class GCRootStaticVariable {
    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;
    private static GCRootStaticVariable staticVariable;

    public GCRootStaticVariable(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args){
        System.out.println("程序开始:");
        printMemory();
        GCRootStaticVariable g = new GCRootStaticVariable(4 * _10MB);
        g.staticVariable = new GCRootStaticVariable(8 * _10MB);
        // 将g置为null, 调用GC时可以回收此对象内存
        g = null;
        printMemory();
        System.gc();
        System.out.println("GC完成");
        printMemory();
    }

    /**
     * 打印出当前JVM剩余空间和总的空间大小
     */
    public static void printMemory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory()/1024/1024 + " M, ");
        System.out.println("total is " + Runtime.getRuntime().totalMemory()/1024/1024 + " M, ");
    }
}