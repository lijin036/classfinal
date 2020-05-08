import java.io.File;
import java.util.ArrayList;

import net.roseboy.classfinal.JarDecryptor;
import net.roseboy.classfinal.JarEncryptor;
import net.roseboy.classfinal.util.IoUtils;

public class Test4 {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir");

        File path = new File(basePath + "/COMPARE/1");
        ArrayList<File> fileList = new ArrayList<>();
        getClassList(fileList, path);

        for (File file : fileList) {
            byte[] bytes1 = IoUtils.readFileToByte(file);
            byte[] bytes = null;
            try {
                byte[] result = JarEncryptor.encrypt(bytes1);
                bytes = JarDecryptor.decrypt(result);

                if (bytes != null && bytes[0] == -54 && bytes[1] == -2 && bytes[2] == -70 && bytes[3] == -66) {
//                    System.out.println(true);
                } else {
                    System.out.println(file.getAbsolutePath());
                    System.out.println(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void getClassList(ArrayList<File> fileList, File path) {
        if (path.isDirectory()) {
            File[] fileArr = path.listFiles();
            for (File file : fileArr) {
                getClassList(fileList, file);
            }
        } else {
            if (path.getName().endsWith(".class")) {
                fileList.add(path);
            }
        }
    }
}
