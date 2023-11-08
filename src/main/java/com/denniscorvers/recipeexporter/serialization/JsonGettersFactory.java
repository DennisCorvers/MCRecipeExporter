package com.denniscorvers.recipeexporter.serialization;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonGettersFactory {
    private final static HashMap<Class<?>, List<JsonGetterTargets>> m_jsonGetterCache = new HashMap<>();

    public static List<JsonGetterTargets> getTargets(Object obj) {
        return getTargets(obj.getClass());
    }

    public static List<JsonGetterTargets> getTargets(Class<?> type) {
        synchronized (m_jsonGetterCache) {
            List<JsonGetterTargets> output = m_jsonGetterCache.getOrDefault(type, null);

            if (output == null) {
                output = getJsonGetters(type);
                m_jsonGetterCache.put(type, output);
            }

            return output;
        }
    }

    private static List<JsonGetterTargets> getJsonGetters(Class<?> type) {
        final List<JsonGetterTargets> targets = new ArrayList<>();

        for (final Method getter : type.getDeclaredMethods()) {
            if (!isGetter(getter))
                continue;

            if (getter.isAnnotationPresent(JsonGetter.class)) {
                JsonGetter annotation = getter.getAnnotation(JsonGetter.class);
                targets.add(new JsonGetterTargets(getter, annotation));
            }
        }

        return targets;
    }

    private static boolean isGetter(Method method) {
        return method.getReturnType() != void.class &&
                method.getParameterCount() == 0;
    }

    public static class JsonGetterTargets {
        private final Method m_getter;
        private final JsonGetter m_annotation;

        public JsonGetterTargets(Method target, JsonGetter annotation) {
            m_getter = target;
            m_annotation = annotation;
        }

        public Object getJsonPropertyValue(Object parent) {
            try {
                return m_getter.invoke(parent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public String getJsonPropertyName() {
            return m_annotation.value();
        }
    }

}
