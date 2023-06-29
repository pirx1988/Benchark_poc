package org.example.Benchmark;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ExecutionPlan {

    @Param({"2", "3", "4", "5"})
    public int numOfElementsToTake;

    public String password;

    @Setup(Level.Invocation)
    public void Setup() {
        password = "adasdas";
    }


}
