package com.sunrisechina;

import net.roseboy.classfinal.JarDecryptor;
import net.roseboy.classfinal.util.JarUtils;
import net.roseboy.classfinal.util.StrUtils;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * AgentTransformer jvm加载class时回调
 *
 * @author roseboy
 */
public class SSTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain domain,
            byte[] classBuffer) {
        if (className == null || domain == null || loader == null) {
            return classBuffer;
        }

        //获取类所在的项目运行路径
        String projectPath = domain.getCodeSource().getLocation().getPath();
        projectPath = JarUtils.getRootPath(projectPath);
        if (StrUtils.isEmpty(projectPath)) {
            return classBuffer;
        }
        className = className.replace("/", ".").replace("\\", ".");
        if (className.startsWith("com.sunrisechina")) {
            System.out.println(className);
        }
        byte[] bytes = JarDecryptor.getInstance().doDecrypt(projectPath, className);
        //CAFEBABE,表示解密成功
        if (bytes != null) {
            System.out.println(bytes.length);
            if (bytes[0] == -54 && bytes[1] == -2 && bytes[2] == -70 && bytes[3] == -66) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
            return bytes;
        }
        return classBuffer;

    }
}
