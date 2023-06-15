package forsyde.io.java.core.annotations;

import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.Trait;

import javax.annotation.processing.SupportedOptions;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface WithEdgeTrait {

    Class<? extends Trait> value();
}
