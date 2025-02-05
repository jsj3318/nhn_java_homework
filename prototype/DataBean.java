import java.lang.annotation.Annotation;

public interface DataBean extends Cloneable{

    DataBean clone();

    private CreationType getDataBeanInfo() {
        Annotation[] annotations = this.getClass().getAnnotations();
        for(Annotation annotation : annotations) {
            if (annotation instanceof CreationType) {
                return  (CreationType) annotation;
            }
        }
        throw new UnsupportedOperationException("No Creation Type");
    }

    public default String getCreationType() {
        try {
            return this.getDataBeanInfo().type();
        }
        catch(Exception e) {
            return "unknown";
        }
    }
}