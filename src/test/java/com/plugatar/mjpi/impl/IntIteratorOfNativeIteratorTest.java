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

import com.plugatar.mjpi.IntIterator;
import com.plugatar.mjpi.TestException1;
import com.plugatar.mjpi.TestException2;
import com.plugatar.mjpi.TestException3;
import com.plugatar.mjpi.TestException4;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.function.IntConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link IntIteratorOfNativeIterator}.
 */
final class IntIteratorOfNativeIteratorTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> new IntIteratorOfNativeIterator(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void allMethodsUseOriginMethods() {
        final IntIterator iterator = new IntIteratorOfNativeIterator(
                new PrimitiveIterator.OfInt() {
                    @Override
                    public boolean hasNext() {
                        throw new TestException1();
                    }

                    @Override
                    public int nextInt() {
                        throw new TestException2();
                    }

                    @Override
                    public void remove() {
                        throw new TestException3();
                    }

                    @Override
                    public void forEachRemaining(final IntConsumer action) {
                        throw new TestException4();
                    }
                }
        );
        assertThatCode(() -> iterator.hasNext())
                .isInstanceOf(TestException1.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(TestException2.class);
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(TestException3.class);
        assertThatCode(() -> iterator.forEachRemaining(item -> {}))
                .isInstanceOf(TestException4.class);
    }

    @Test
    void iterations() {
        final List<Integer> removedItems = new ArrayList<>();
        final IntIterator iterator = new IntIteratorOfNativeIterator(
                new PrimitiveIterator.OfInt() {
                    private int pointer = 1;

                    @Override
                    public boolean hasNext() {
                        return this.pointer < 6;
                    }

                    @Override
                    public int nextInt() {
                        if (this.hasNext()) {
                            return this.pointer++;
                        }
                        throw new NoSuchElementException();
                    }

                    @Override
                    public void remove() {
                        removedItems.add(this.pointer - 1);
                    }
                }
        );

        assertThat(iterator.hasNext())
                .isTrue();
        assertThat(iterator.next())
                .isEqualTo(1);

        assertThat(iterator.hasNext())
                .isTrue();
        iterator.remove();
        assertThat(removedItems)
                .containsExactly(1);
        assertThat(iterator.next())
                .isEqualTo(2);

        final List<Integer> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .containsExactly(3, 4, 5);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        iterator.remove();
        assertThat(removedItems)
                .containsExactly(1, 5);
    }
}
