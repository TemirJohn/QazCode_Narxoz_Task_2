package org.Temirjohn.Task_1;

import java.util.*;
import java.util.concurrent.*;

public class Task_1 {
    public static void main(String[] args) {
        int size = 1_000_000;
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(1);
        }
        int numberOfThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<Long>> futures = new ArrayList<>();
        int batchSize = list.size() / numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            final int start = i * batchSize;
            final int end;
            if (i == numberOfThreads - 1) {
                end = list.size();
            } else {
                end = (i + 1) * batchSize;
            }
            List<Integer> subList = list.subList(start, end);

            Callable<Long> task = () -> {
                long partSum = 0;
                for (Integer num : subList) {
                    partSum += num;
                }
                System.out.println("Thread " +  Thread.currentThread().getName() + " sum from " + start + " to " + end);
                return partSum;
            };

            futures.add(executorService.submit(task));
        }

            long totalsum = 0;

            try {
                for (Future<Long> future : futures) {
                    totalsum += future.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }

            System.out.println("Total Sum " + totalsum);
    }
}