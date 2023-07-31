package forsyde.io.core.annotations;

import forsyde.io.core.TraitHierarchy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface RegisterTrait {

    Class<? extends TraitHierarchy> value();
}
