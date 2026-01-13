package hytale.server.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark methods as event handlers.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    /**
     * The priority of this handler.
     */
    EventPriority priority() default EventPriority.NORMAL;

    /**
     * Whether to ignore cancelled events.
     */
    boolean ignoreCancelled() default false;
}

