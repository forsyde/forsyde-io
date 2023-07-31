package forsyde.io.core.annotations;

import forsyde.io.core.Trait;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface WithEdgeTrait {

    Class<? extends Trait> value();
}
