package com.ex.study;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.interrupted;

public class ConcurrentTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //中断
        FutureTask<Integer> task = new FutureTask<>(()->{
            while (!interrupted()) {
                System.out.println("thread break");
            }
            return 123;
        });
        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();
        System.out.println(task.get());
        //加锁
//        SynchronizedTest test = new SynchronizedTest();
////        ExecutorService pool = Executors.newCachedThreadPool();
//        LinkedBlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>(5);
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,10,60,
//                TimeUnit.SECONDS, blockingDeque, handler);
//        pool.execute(()->test.fun());
//        pool.execute(()->test.fun());
        // 异步线程
//        FutureTask<Integer> task = new FutureTask<Integer>(() -> {
//            int result=0;
//            for (int i = 0; i < 100; i++) {
//                Thread.sleep(10);
//                result+=i;
//            }
//            return result;
//        });
//        Thread thread = new Thread(task);
//        thread.start();
//        Thread otherThread = new Thread(() -> {
//            System.out.println("other thread running");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        otherThread.start();
//        System.out.println(task.get());
        //线程不安全
//        final int threadSize=10000;
//        threadUnsafe threadUnsafe = new threadUnsafe();
//        CountDownLatch latch = new CountDownLatch(threadSize);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < threadSize; i++) {
//            executorService.execute(() -> {
//                threadUnsafe.add();
//                latch.countDown();
//            });
//        }
//        latch.await();
//        executorService.shutdown();
//        System.out.println(threadUnsafe.get());
        // threadLocal不同线程互不干扰
//        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
//        Thread thread1 = new Thread(() -> {
//            threadLocal.set(1);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(threadLocal.get());
//            threadLocal.remove();
//        });
//        Thread thread2 = new Thread(() -> {
//            System.out.println(threadLocal.get());
//            threadLocal.set(2);
//            threadLocal.remove();
//        });
//        thread1.start();
//        thread2.start();
    }

    static class threadUnsafe{
        //        public static AtomicInteger cnt = new AtomicInteger(0);
        private int cnt=0;

        public void add(){
//            cnt.incrementAndGet();
            cnt++;
        }
        public int get(){
//            return cnt.get();
            return cnt;
        }
    }

    static class SynchronizedTest{
        public void fun() {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName()+" "+i);
                }
            }
        }
    }
}
