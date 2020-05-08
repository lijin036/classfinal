package net.roseboy.classfinal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.roseboy.classfinal.util.IoUtils;
import net.roseboy.classfinal.util.JarUtils;
import net.roseboy.classfinal.util.Log;
import net.roseboy.classfinal.util.StrUtils;
import net.roseboy.classfinal.util.SysUtils;

public class Main {
    /**
     * 入口方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        Const.pringInfo();
        
        // 输出基础目录
        String basePath = System.getProperty("user.dir");
        System.out.println(basePath);

        // 清空目标文件夹
        File encryptJarDir = new File(basePath + "/ENCRYPT/");
        ArrayList<File> deleteList = getJarList(encryptJarDir);
        deleteList.forEach(f -> f.delete());

        // 获取来源文件夹
        File jarDir = new File(basePath + "/JAR/");
        ArrayList<File> jarList = getJarList(jarDir);
        for (File jarFile : jarList) {
            Log.println("处理中..." + jarFile.getName());
            List<String> includeJarList = StrUtils.toList(null);
            List<String> packageList = StrUtils.toList("com.sunrisechina");
            List<String> excludeClassList = StrUtils.toList(null);
            List<String> classPathList = StrUtils.toList(null);
            List<String> cfgFileList = StrUtils.toList(null);
            includeJarList.add("-");
            // 组织目标文件夹名称
            File encryptJarFile = new File(encryptJarDir.getAbsolutePath() + "/" + jarFile.getName());
            JarEncryptor encryptor = new JarEncryptor(jarFile.getAbsolutePath(), encryptJarFile.getAbsolutePath());
            encryptor.setCode(null);
            encryptor.setPackages(packageList);
            encryptor.setIncludeJars(includeJarList);
            encryptor.setExcludeClass(excludeClassList);
            encryptor.setClassPath(classPathList);
            encryptor.setCfgfiles(cfgFileList);
            try {
                String result = encryptor.doEncryptJar();
                Log.println("加密完成==>" + result);
            } catch (Exception e) {
                Log.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static ArrayList<File> getJarList(File dir) {
        dir.mkdirs();

        File[] fileArr = dir.listFiles();
        ArrayList<File> jarList = new ArrayList<File>();
        for (File file : fileArr) {
            if (file.getName().endsWith(".jar")) {
                jarList.add(file);
            } else if (file.isDirectory()) {
                deleteDir(file);
            }
        }
        return jarList;
    }

    /**
     * 迭代删除文件夹
     * 
     * @param dirPath 文件夹路径
     */
    public static void deleteDir(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i]);
                }
                file.delete();
            }
        }
    }

    /**
     * 生成机器码
     */
    public static void makeCode() {
        String path = JarUtils.getRootPath(null);
        path = path.substring(0, path.lastIndexOf("/") + 1);

        String code = new String(SysUtils.makeMarchinCode());
        File file = new File(path, "classfinal-code.txt");
        IoUtils.writeTxtFile(file, code);
        Log.println("Server code is: " + code);
        Log.println("==>" + file.getAbsolutePath());
        Log.println();
    }
}
