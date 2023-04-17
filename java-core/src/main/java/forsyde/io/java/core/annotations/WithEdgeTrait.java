package forsyde.io.java.core.annotations;

import forsyde.io.java.core.Trait;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface WithEdgeTrait {
    String[] traitNames = null;
    Trait[] traits = null;
}
