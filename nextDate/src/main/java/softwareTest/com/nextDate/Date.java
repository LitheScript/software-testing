package softwareTest.com.nextDate;
/**
 * @author Lori
 * @date 2022/4/27 23:12
 * @description:
 */

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Date {
    private final int Y;
    private final int M;
    private final int D;
}
