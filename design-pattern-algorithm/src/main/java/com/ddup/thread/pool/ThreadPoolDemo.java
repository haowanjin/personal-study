package com.ddup.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * corePoolSize – 要保留在池中的线程数，即使它们处于空闲状态，除非设置了 allowCoreThreadTimeOut
         * maximumPoolSize – 池中允许的最大线程数
         * keepAliveTime – 当线程数大于核心数时，这是多余空闲线程在终止前等待新任务的最长时间。
         * unit – keepAliveTime 参数的时间单位
         * workQueue – 用于在执行任务之前保存任务的队列。这个队列将只保存 execute 方法提交的 Runnable 任务。
         * threadFactory – 执行程序创建新线程时使用的工厂
         * handler – 执行被阻塞时使用的处理程序，因为达到了线程边界和队列容量
         */
        //threadPool1 core size = 2 threadPool1 pool size = 0
        ExecutorService threadPool1 = new ThreadPoolExecutor(2,
                4, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        /**
         * 单个后台线程
         *
         * 创建一个 Executor，它使用单个工作线程在无界队列中运行。 （但是请注意，如果这个单线程在关闭之前由于执行失败而终止
         * ，如果需要执行后续任务，一个新线程将取代它。）保证任务按顺序执行，并且不会超过一个任务处于活动状态在任何给定的时间。
         * 与其他等效的 newFixedThreadPool(1) 不同，返回的执行器保证不可重新配置以使用其他线程。
         */
        // ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        /**
         * 无界线程池，具有自动线程回收
         *
         * 创建一个线程池，根据需要创建新线程，但在可用时将重用先前构造的线程。这些池通常会提高执行许多短期异步任务的程序的性能。
         * 如果可用，调用 execute 将重用先前构造的线程。如果没有可用的现有线程，则会创建一个新线程并将其添加到池中。 60 秒内
         * 未使用的线程将被终止并从缓存中删除。因此，保持空闲足够长时间的池不会消耗任何资源。请注意，可以使用 ThreadPoolExecutor
         * 构造函数创建具有相似属性但细节不同（例如，超时参数）的池。
         * 可在需要时使用提供的 ThreadFactory 创建新线程
         */
        // ExecutorService threadPool3 = Executors.newCachedThreadPool(/*ThreadFactory threadFactory*/);
        /**
         * 固定大小线程池
         *
         * 创建一个线程池，该线程池重用固定数量的线程在共享的无界队列中运行。在任何时候，最多 nThreads 个线程将是活动的处理任务。
         * 如果在所有线程都处于活动状态时提交了其他任务，它们将在队列中等待，直到有线程可用。如果任何线程在关闭前的执行过程中因失败
         * 而终止，则在需要执行后续任务时，将有一个新线程取而代之。池中的线程将一直存在，直到它被明确关闭
         */
        // ExecutorService threadPool4 = Executors.newFixedThreadPool(2/*,ThreadFactory threadFactory*/);
        //ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        for (int i = 0; i < 6; i++) {
            threadPool1.execute(new ThreadTask("任务" + i));
        }


        /**
         * 启动有序关闭，其中执行先前提交的任务，但不会接受新任务。 如果已经关闭，调用没有额外的效果。
         * 此方法不等待先前提交的任务完成执行。 使用awaitTermination来做到这一点。
         */
        threadPool1.shutdown();
        System.out.println(threadPool1.isShutdown());

        /**
         * 尝试停止所有正在执行的任务，停止等待任务的处理，并返回等待执行的任务列表。此方法不会等待主动执行的任务终止。 使用awaitTermination来做到这一点。
         * 除了尽力尝试停止处理正在执行的任务之外，没有任何保证。 例如，典型的实现将通过Thread.interrupt取消，因此任何未能响应中断的任务可能永远不会终止
         */
        //System.out.println(threadPool1.shutdownNow());
        /**
         * 阻塞直到所有任务在关闭请求后完成执行，或发生超时，或当前线程被中断，以先发生者为准。
         *
         * awaitTermination方法：接收timeout和unit两个参数，用于设定超时时间及单位。当等待超过设定时间时，会监测ExecutorService是否已经关闭，
         * 若关闭则返回true，否则返回false。一般情况下会和shutdown方法组合使用。
         */
        while (!threadPool1.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程池没有关闭");
        }
        System.out.println("线程池关闭");

    }

    static class ThreadTask implements Runnable {
        private String taskName;
        public ThreadTask(String name) {
            this.taskName = name;
        }

        public String getName() {
            return this.taskName;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "- > " + getName() + " *** 开始执行");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "- > " + getName() + " 执行完毕 ***");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "- > " + getName() + " 被中断");
            }
        }
    }
}
