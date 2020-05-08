import java.io.File;
import java.util.ArrayList;

import com.sunrisechina.SSJar;

import net.roseboy.classfinal.util.IoUtils;

public class Test {
    public static void main(String[] args) {
        ArrayList<File> fileList = getClassList();
        for (File classFile : fileList) {
            byte[] bytes1 = IoUtils.readFileToByte(classFile);
            byte[] bytes2 = null;;
            try {
                String content = new String(bytes1, "GBK");
                String result = SSJar.encrypt(content);
                result = SSJar.decrypt(result);
                bytes2 = result.getBytes("GBK");
                
                System.out.println(bytes1.length);
                System.out.println(bytes2.length);
                System.out.println(new String(bytes1, "UTF-8"));
                System.out.println(new String(bytes2, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static ArrayList<File> getClassList() {
        String basePath = System.getProperty("user.dir");
        File dir = new File(basePath + "/JAR/");
        dir.mkdirs();
        File[] fileArr = dir.listFiles();

        ArrayList<File> classList = new ArrayList<File>();
        for (File file : fileArr) {
            if (file.getName().endsWith(".class")) {
                classList.add(file);
            }
        }
        return classList;
    }
}
