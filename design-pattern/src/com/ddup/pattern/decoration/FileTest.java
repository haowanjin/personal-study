package com.ddup.pattern.decoration;

import java.io.*;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File f = new File("./file.txt");
        if (!f.exists()) {
            f.createNewFile();
        }

        String msg = "引言：\n" +
                "　　注：本人一直都是用的git bash窗口完成日常的开发工作。\n" +
                "\n" +
                "　　事情是这样的，切换分支的时候命令打错了，git checkout 后面没有跟分支名，结果git status，很多delete的文件，直接冒冷汗，git add ,commit 之后发现本地与远程确实是删除了很多文件，我本地没有修改的代码，于是选择直接删除本地的分支，然后重新从远程拉分支。\n" +
                "\n" +
                "具体操作：\n" +
                "　　我现在在dev20181018分支上，想删除dev20181018分支\n" +
                "\n" +
                "　　1 先切换到别的分支: git checkout dev20180927\n" +
                "\n" +
                "　　2 删除本地分支： git branch -d dev20181018\n" +
                "\n" +
                "　　3 如果删除不了可以强制删除，git branch -D dev20181018\n" +
                "\n" +
                "　　4 有必要的情况下，删除远程分支(慎用)：git push origin --delete dev20181018\n" +
                "\n" +
                "　　5 在从公用的仓库fetch代码：git fetch origin dev20181018:dev20181018\n" +
                "\n" +
                "　　6 然后切换分支即可：git checkout dev20181018\n" +
                "\n" +
                "　　注：上述操作是删除个人本地和个人远程分支，如果只删除个人本地，请忽略第4步\n" +
                "\n" +
                "关于http协议";
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(msg, 5, msg.length()-13);
        bw.flush();
    }
}
