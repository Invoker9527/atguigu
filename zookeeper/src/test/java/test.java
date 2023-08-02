/**
 * @Author YangRuiHong
 * @Create 2023-04-15 14:12
 * @Description
 */
public class test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("s");
        sb.append(1);
        StringBuffer sf = new StringBuffer("s");
        sf.append(1);
        new test().watch();
    }

    void watch() {
        Student student = new Student(1, "Tom", "男");
        System.out.println(student.toString());
        student.setAge(20);
        int a = 0;
        int b = 0;
        int r = 0;
        try {
            r = student.getAge() / a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(r);
        student.setAge(0);

    }
}
