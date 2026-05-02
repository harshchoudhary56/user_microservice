package com.apple.inc.user.util;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public interface AsyncTaskExecutor {

    <T> void execute(Supplier<T> supplier);

    <T> void execute(Supplier<T> supplier, String poolId);

    <T> Future<T> queue(Supplier<T> supplier);

    <T> Future<T> queue(Supplier<T> supplier, String poolId);

    <T> Optional<T> get(Future<T> future, int timeOutInMillis);

    <T> Optional<T> get(Future<T> future, int timeOutInMillis, boolean isFailureSuppressed);
}
