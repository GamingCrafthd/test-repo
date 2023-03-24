import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ClassFinder {

    private static final int THREAD_POOL_SIZE = 4;

    public static List<Class<?>> findAnnotatedClasses(String packageName, Class<? extends Annotation> annotation) throws Exception {
        List<Class<?>> annotatedClasses = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<List<Class<?>>>> futures = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        for (java.net.URL resource : java.util.Collections.list(classLoader.getResources(path))) {
            for (String file : new java.io.File(resource.toURI()).list()) {
                if (file.endsWith(".class")) {
                    String className = packageName + "." + file.substring(0, file.length() - 6);
                    futures.add(executor.submit(() -> {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (clazz.isAnnotationPresent(annotation)) {
                            return List.of(clazz);
                        } else {
                            return List.of();
                        }
                    }));
                }
            }
        }

        for (Future<List<Class<?>>> future : futures) {
            annotatedClasses.addAll(future.get());
        }

        executor.shutdown();
        return annotatedClasses;
    }

    // example usage
    public static void main(String[] args) throws Exception {
        List<Class<?>> annotatedClasses = findAnnotatedClasses("org.example", org.example.Annotation.class);
        for (Class<?> clazz : annotatedClasses) {
            System.out.println(clazz.getName());
        }
    }
}
