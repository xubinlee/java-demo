package com.ex.study;

import java.util.concurrent.*;

public class ForkJoin extends RecursiveTask<Integer> {
    private final int threshold=5;
    private int first;
    private int last;

    public ForkJoin(int first, int last){
        this.first=first;
        this.last=last;
    }

    @Override
    protected Integer compute() {
        int result=0;
        if (last-first<=threshold){
            for (int i = first; i <= last; i++) {
                result+=i;
            }
        }else{
            int middle=first+(last-first)/2;
            ForkJoin leftTask = new ForkJoin(first, middle);
            ForkJoin rightTask = new ForkJoin(middle + 1, last);
            leftTask.fork();
            rightTask.fork();
            result=leftTask.join()+rightTask.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoin forkJoin = new ForkJoin(1, 1000);
        ForkJoinPool joinPool = new ForkJoinPool();
        Future result = joinPool.submit(forkJoin);
        System.out.println(result.get());
    }
}
