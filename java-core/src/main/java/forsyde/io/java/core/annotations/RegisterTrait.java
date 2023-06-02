package forsyde.io.java.core.annotations;

import forsyde.io.java.core.TraitHierarchy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface RegisterTrait {

    Class<? extends TraitHierarchy> value();
}
