import java.util.List;

import net.roseboy.classfinal.util.JarUtils;

public class Test2 {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir");
        String jarPath = basePath + "/JAR/core2-0.0.554.jar";
        String outPath = basePath + "/COMPARE/1/";
        List<String> allFile = JarUtils.unJar(jarPath, outPath);
    }

}
