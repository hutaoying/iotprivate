package com.hty.iotprivate.guava.future;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by hutaoying on 2019/11/30
 *
 * guava listenerFuture
 */

public class Demone {
    public static void main(String[] args) {
       ListeningExecutorService executorService =  MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

        ListenableFuture<Integer> explosion = executorService.submit(new Callable<Integer>() {
            public Integer call() {
                return 1;
            }
        });
    }
}


