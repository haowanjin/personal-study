package com.ddup.thread.forkJoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FindDirsFiles extends RecursiveAction {

    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    public static void main(String[] args) throws InterruptedException {
        FindDirsFiles task = new FindDirsFiles(new File("D:/"));
        // 用一个 ForkJoinPool 实例调度总任务
        ForkJoinPool pool = new ForkJoinPool();

        pool.execute(task);

        System.out.println("Task is running .........");
        Thread.sleep(20);
        int otherWork = 0;
        for (int i = 0; i < 100; i++) {
            System.out.println("*** 主线程进行其他任务：" + otherWork++);
        }
        System.out.println("### 主线程其他任务执行完毕 ####");
        task.join();
        System.out.println("Task execute end");
        System.out.println("=== task 执行完毕，主线程继续其他事情 ===");
        Thread.sleep(2000);
        System.out.println("=== 主线程其他事情搞完了 ===");


    }


    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks = new ArrayList<>();
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                // 对每个子目录都新建一个子任务
                if (file.isDirectory()) {
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    // 遇到文件，检查。
                    if (file.getAbsolutePath().endsWith("sql")) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("文件：" + file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                for (FindDirsFiles subTask : invokeAll(subTasks)) {
                    subTask.join();
                }
            }
        }
    }

}
