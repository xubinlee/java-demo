package com.ex.study;

//import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DoTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
//        Path path = Paths.get("F:\\Doc\\Iovoice");
//        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
//            @Override
//            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println(file);
//                return FileVisitResult.CONTINUE;
//            }
//        });
        int i=123;
        int first=i;
        int second=i;
        int i1 = first << 8;
        int i2 = (first << 8) + second;
    }

    static class MyRunnable implements Runnable{
        @Override
        public synchronized void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }
}

