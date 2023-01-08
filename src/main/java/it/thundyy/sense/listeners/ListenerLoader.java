package it.thundyy.sense.listeners;

import it.thundyy.sense.listeners.impl.PlayerListener;
import it.thundyy.sense.listeners.annotations.Listen;
import lombok.RequiredArgsConstructor;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.GlobalEventHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

@RequiredArgsConstructor
public class ListenerLoader {
    private final GlobalEventHandler eventHandler;
    private final Set<Listener> listeners = Set.of(
            new PlayerListener()
    );
    
    public void boot() {
        listeners.forEach(listener -> {
            Class<?> clazz = listener.getClass();
            
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println(method.getName());
                if (!Modifier.isPublic(method.getModifiers()) ||
                        !method.isAnnotationPresent(Listen.class)) continue;
                
                var parameter = method.getParameterTypes()[0];
                if (!net.minestom.server.event.Event.class.isAssignableFrom(parameter)) continue;
                
                var event = parameter.asSubclass(net.minestom.server.event.Event.class);
                Listen annotation = method.getAnnotation(Listen.class);
                
                int priority = annotation.priority().getPriority();
                boolean ignoreCancelled = annotation.ignoreCancelled();
                EventListener<?> aListener = EventListener.builder(event)
                        .handler(capture -> {
                            try {
                                method.invoke(clazz.newInstance(),
                                        capture);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        })
                        .ignoreCancelled(ignoreCancelled)
                        .build();
                
                eventHandler.addListener(aListener)
                        .setPriority(priority);
            }
        });
    }
}
