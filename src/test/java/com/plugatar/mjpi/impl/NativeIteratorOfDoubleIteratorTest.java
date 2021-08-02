/*
 * Copyright (c) 2021 Evgenii Plugatar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.plugatar.mjpi.impl;

import com.plugatar.mjpi.DoubleIterator;
import com.plugatar.mjpi.TestException1;
import com.plugatar.mjpi.TestException2;
import com.plugatar.mjpi.TestException3;
import com.plugatar.mjpi.TestException4;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link NativeIteratorOfDoubleIterator}.
 * <br/>Also used: {@link DoubleIteratorOf}.
 */
final class NativeIteratorOfDoubleIteratorTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> new NativeIteratorOfDoubleIterator(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void allMethodsUseOriginMethods() {
        final PrimitiveIterator.OfDouble iterator = new NativeIteratorOfDoubleIterator(
                new DoubleIterator() {
                    @Override
                    public boolean hasNext() {
                        throw new TestException1();
                    }

                    @Override
                    public double next() {
                        throw new TestException2();
                    }

                    @Override
                    public void remove() {
                        throw new TestException3();
                    }

                    @Override
                    public void forEachRemaining(final DoubleConsumer action) {
                        throw new TestException4();
                    }
                }
        );
        assertThatCode(() -> iterator.hasNext())
                .isInstanceOf(TestException1.class);
        assertThatCode(() -> iterator.nextDouble())
                .isInstanceOf(TestException2.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(TestException2.class);
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(TestException3.class);
        assertThatCode(() -> iterator.forEachRemaining((DoubleConsumer) item -> {}))
                .isInstanceOf(TestException4.class);
        assertThatCode(() -> iterator.forEachRemaining((Consumer<? super Double>) item -> {}))
                .isInstanceOf(TestException4.class);
    }

    @Test
    void hasNextAndNextIntAndNextMethodsIterations() {
        final List<Double> removedItems = new ArrayList<>();
        final PrimitiveIterator.OfDouble nativeIterator = new NativeIteratorOfDoubleIterator(
                new DoubleIterator() {
                    private int pointer = 1;

                    @Override
                    public boolean hasNext() {
                        return this.pointer < 3;
                    }

                    @Override
                    public double next() {
                        if (this.hasNext()) {
                            return (double) this.pointer++;
                        }
                        throw new NoSuchElementException();
                    }

                    @Override
                    public void remove() {
                        removedItems.add((double) this.pointer - 1);
                    }
                }
        );

        assertThat(nativeIterator.hasNext())
                .isTrue();
        assertThat(nativeIterator.next())
                .isEqualTo(1.0);

        assertThat(nativeIterator.hasNext())
                .isTrue();
        nativeIterator.remove();
        assertThat(removedItems)
                .containsExactly(1.0);
        assertThat(nativeIterator.next())
                .isEqualTo(2.0);

        assertThat(nativeIterator.hasNext())
                .isFalse();
        assertThatCode(() -> nativeIterator.next())
                .isInstanceOf(NoSuchElementException.class);
        nativeIterator.remove();
        assertThat(removedItems)
                .containsExactly(1.0, 2.0);
    }

    @Test
    void forEachRemainingMethodsForPrimitivesIterations() {
        final PrimitiveIterator.OfDouble iterator = new NativeIteratorOfDoubleIterator(
                new DoubleIteratorOf(1.0, 2.0, 3.0)
        );
        final List<Double> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining((DoubleConsumer) forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .containsExactly(1.0, 2.0, 3.0);
    }

    @Test
    void forEachRemainingMethodsForObjectsIterations() {
        final PrimitiveIterator.OfDouble iterator = new NativeIteratorOfDoubleIterator(
                new DoubleIteratorOf(1.0, 2.0, 3.0)
        );
        final List<Double> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining((Consumer<? super Double>) forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .containsExactly(1.0, 2.0, 3.0);
    }
}
