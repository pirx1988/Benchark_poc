package org.example.Benchmark;

import org.example.utils.Util;
import org.openjdk.jmh.annotations.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class EmployeeService {
//    @BenchmarkMode(Mode.AverageTime)
    @BenchmarkMode(Mode.SingleShotTime)
    @Benchmark
    @Fork(value = 1)
    @Warmup(iterations = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void init(ExecutionPlan plan) {
        getEmployees()
                .take(plan.numOfElementsToTake)
//                .doOnNext((el -> System.out.println(el)))
//                .doOnComplete(() -> System.out.println("completed"))
                .blockLast();
    }

    private static Flux<String> getEmployeeItems(String id) {
        List<String> employees = switch (id) {
            case "1" -> List.of( "item1", "item2", "item3");
            case "2" -> List.of("item4", "item5");
            case "3" -> List.of("item6", "item7");
            case "4" -> List.of("item8", "item9", "item10");
            default -> List.of("Not found");
        };
        Util.sleepSeconds(1);
        return Flux.create(employeesSink -> {
                    employees.forEach(employeesSink::next);
                    employeesSink.complete();
                }
        );
    }

    public Flux<String> getEmployees() {
        List<String> employeeIds = List.of("1", "2", "3", "4", "5");
        return Flux
                .fromIterable(employeeIds).parallel()
                .runOn(Schedulers.newParallel("test"))
                .groups()
                .flatMapSequential(identifiers -> identifiers.flatMap(EmployeeService::getEmployeeItems));
//        .flatMap(identifiers -> identifiers.flatMap(EmployeeService::getEmployeeItems));
    }
}
