/*
 * Copyright (C) 2016-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package com.knoldus.samplelagom.hello.impl;

import akka.japi.Pair;
import com.knoldus.samplelagom.hello.api.MarksPojo;
import com.knoldus.samplelagom.hello.api.ResultPojo;
import com.lightbend.lagom.javadsl.api.transport.RequestHeader;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.Assert.assertEquals;

public class HelloServiceImplTest {
    static MarksPojo marksPojo = new MarksPojo();
    @Injectable
    Manipulator manipulator;
    @Tested
    HelloServiceImpl helloServiceImpl;
    
    @Test
    public void getResultSuccessCase() throws Exception {
        new Expectations() {
            {
                
                marksPojo.setComputer(40);
                marksPojo.setMaths(40);
                marksPojo.setScience(40);
                marksPojo.setEnglish(40);
                marksPojo.setHindi(40);
                
                manipulator.retieveData(marksPojo);
                returns(Float.valueOf(40));
            }
        };
        HeaderServiceCall headerServiceCall = helloServiceImpl.getResult("mukesh");
        CompletionStage stage = headerServiceCall.invokeWithHeaders(RequestHeader.DEFAULT, marksPojo);
        CompletableFuture future = stage.toCompletableFuture();
        Pair<ResponseHeader, ResultPojo> pair = (Pair<ResponseHeader, ResultPojo>) future.get();
        ResultPojo resultPojo = pair.second();
        String result1 = resultPojo.getResult();
        
        
        //Pair<ResponseHeader, ResultPojo> result = helloServiceImpl.getResult("mukesh").invokeWithHeaders(RequestHeader.DEFAULT, marksPojo).toCompletableFuture().get();
        assertEquals("this should be =fail", "pass", result1);
    }
    
}
