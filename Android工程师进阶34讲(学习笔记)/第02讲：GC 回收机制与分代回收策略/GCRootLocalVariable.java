/**
 * Created by vincent
 * Date: 2021/3/16
 * Time: 4:37 PM
 *
 * 验证虚拟机栈（栈帧中的局部变量）中引用的对象作为 GC Root
 *
 *
 * 运行结果：
 *
 * 开始时:
 * free is 241 M, total is 245 M,
 * 第一次GC完成
 * free is 163 M, total is 245 M,
 * 第二次GC完成
 * free is 243 M, total is 245 M,
 */

public class GCRootLocalVariable {
    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args){
        System.out.println("开始时:");
        printMemory();
        method();
        System.gc();
        System.out.println("第二次GC完成");
        printMemory();
    }

    public static void method() {
        GCRootLocalVariable g = new GCRootLocalVariable();
        System.gc();
        System.out.println("第一次GC完成");
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
