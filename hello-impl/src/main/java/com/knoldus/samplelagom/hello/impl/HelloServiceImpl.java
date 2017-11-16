/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package com.knoldus.samplelagom.hello.impl;

import akka.japi.Pair;
import com.knoldus.samplelagom.hello.api.HelloService;
import com.knoldus.samplelagom.hello.api.MarksPojo;
import com.knoldus.samplelagom.hello.api.ResultPojo;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the HelloService.
 */
public class HelloServiceImpl implements HelloService {
    
    private final Manipulator manipulator;
    
    @Inject
    public HelloServiceImpl(Manipulator manipulator) {
        this.manipulator = manipulator;
    }
    
    @Override
    public HeaderServiceCall<MarksPojo, ResultPojo> getResult(String id) {
        return (requestHeader,request) -> {
            return CompletableFuture.completedFuture(new Pair(ResponseHeader.OK, getResult(manipulator.retieveData(request))));
        };
    }
    
    private ResultPojo getResult(Float avg) {
        ResultPojo resultPojo = new ResultPojo();
        if (avg > 33.0) {
            resultPojo.setResult("pass");
        } else {
            resultPojo.setResult("fail");
        }
        return resultPojo;
    }
}
