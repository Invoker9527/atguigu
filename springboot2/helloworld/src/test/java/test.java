

/**
 * @author YangRuiHong
 * @create 2022-07-17 14:30
 * @description:
 */
public class test {
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("Hello");
        StringBuffer b = new StringBuffer("World");
        operator(a, b);
        System.out.println(a + "," + b);
    }

    private static void operator(StringBuffer a, StringBuffer b) {
        a.append(b);
        b = a;
        Throwable ad = new Exception();
        Throwable sdsd = new Error();
    }
}
