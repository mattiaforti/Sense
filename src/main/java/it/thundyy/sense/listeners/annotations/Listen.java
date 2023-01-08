package it.thundyy.sense.listeners.annotations;

import it.thundyy.sense.listeners.annotations.priority.EventPriority;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Listen {
    EventPriority priority() default EventPriority.NORMAL;
    
    boolean ignoreCancelled() default false;
}
