import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreationType {
    // 해당 데이터빈이 프로토 타입인지 싱글톤인지
    String type() default "ProtoType";
}
