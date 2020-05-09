package cn.novalue.blog.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工具类，提供诸如Bean属性复制等功能。
 *
 * @author Wu Yangjie
 * @date 2020-03-06
 */
@Slf4j
public class MyBeanUtils {
    private static final ConcurrentHashMap<String, BeanCopier> MAP_CACHE = new ConcurrentHashMap<>();

    public static void clear() {
        MAP_CACHE.clear();
    }

    /**
     * 属性拷贝
     * @param source 原对象
     * @param target 目标对象
     */
    public static <O, T> void copy(O source, T target) {
        BeanCopier copier = getCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
    }

    /**
     * 属性拷贝
     * @param source 原对象
     * @param target 目标类Class
     * @return 目标对象
     */
    public static <O, T> T copy(O source, Class<T> target) {
        BeanCopier copier = getCopier(source.getClass(), target);
        T instance;
        try {
            instance = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("{}: failed to create a instance of {}!", e.getMessage(), target.toString());
            return null;
        }
        copier.copy(source, instance, null);
        return instance;
    }

    public static <O, T> List<T> copyByList(List<O> source, Class<T> target) {
        List<T> result = new ArrayList<>(source.size());
        try {
            for (O s: source) {
                T t = target.getDeclaredConstructor().newInstance();
                copy(s, t);
                result.add(t);
            }
        } catch (Exception e) {
            log.error("{}: failed to create a instance of {}!", e.getMessage(), target.toString());
            return null;
        }
        return result;
    }

    private static BeanCopier getCopier(Class<?> class1, Class<?> class2) {
        String key = generateKey(class1, class2);
        if (MAP_CACHE.containsKey(key)) {
            return MAP_CACHE.get(key);
        }
        BeanCopier copier = BeanCopier.create(class1, class2, false);
        MAP_CACHE.put(key, copier);
        return copier;
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}
