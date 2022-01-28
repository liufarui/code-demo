package com.github.liufarui.demo;

import java.io.File;

/**
 * @author liufarui
 * @date 2021/12/26 15:04
 */
public class FileDemo {
    public static void main(String[] args) {
        File dir = new File("/Users/liufarui/OneDrive/图片/Photo/Processed");
        filesDirs(dir);
    }

    public static void filesDirs(File dir) {
        //File对象是文件或文件夹的路径，第一层判断路径是否为空
        if (dir != null) {
            //第二层路径不为空，判断是文件夹还是文件
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File file : files) {
                    filesDirs(file);
                }
            } else {
                String absolutePath = dir.getAbsolutePath();
                String dirName = absolutePath.split("/")[absolutePath.split("/").length - 2];
                if (dir.getName().substring(2).contains(dirName)) {
                    System.out.println(dir.getParentFile().getAbsolutePath() + "/" + dir.getName().replace(dirName, "_"));
                    File newNameFile = new File(dir.getParentFile().getAbsolutePath() + "/" + dir.getName().replace(dirName, "_"));
                    dir.renameTo(newNameFile);
                }

            }
        } else {
            System.out.println("文件不存在");
        }
    }
}
