import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author YangRuiHong
 * @Create 2023-04-15 14:20
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer age;
    private String name;
    private String sex;
}
