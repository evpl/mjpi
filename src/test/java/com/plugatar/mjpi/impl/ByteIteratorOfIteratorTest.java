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

import com.plugatar.mjfi.ToByteFunction;
import com.plugatar.mjpi.ByteIterator;
import com.plugatar.mjpi.TestException1;
import com.plugatar.mjpi.TestException2;
import com.plugatar.mjpi.TestException3;
import com.plugatar.mjpi.TestException4;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link ByteIteratorOfIterator}.
 */
final class ByteIteratorOfIteratorTest {

    @Test
    void ctorThrowsNPEForNullOriginArg() {
        assertThatCode(() -> new ByteIteratorOfIterator<Byte>(null, i -> i))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void ctorThrowsNPEForNullConversionFunctionArg() {
        final Iterator<Byte> objIterator = Arrays.asList((byte) 1, (byte) 2, (byte) 3).iterator();
        assertThatCode(() -> new ByteIteratorOfIterator<>(objIterator, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void pecsForCtor() {
        final Iterator<Byte> objIterator = Arrays.asList((byte) 1, (byte) 2, (byte) 3).iterator();
        final ToByteFunction<Object> function = i -> (byte) 0;
        final ByteIteratorOfIterator<Number> iterator = new ByteIteratorOfIterator<>(objIterator, function);
    }

    @Test
    void allMethodsUseOriginMethods() {
        final ByteIterator iterator = new ByteIteratorOfIterator<>(
                new Iterator<Byte>() {
                    @Override
                    public boolean hasNext() {
                        throw new TestException1();
                    }

                    @Override
                    public Byte next() {
                        throw new TestException2();
                    }

                    @Override
                    public void remove() {
                        throw new TestException3();
                    }

                    @Override
                    public void forEachRemaining(final Consumer<? super Byte> action) {
                        throw new TestException4();
                    }
                },
                i -> i
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
        final List<String> items = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        final ByteIterator iterator = new ByteIteratorOfIterator<>(
                items.iterator(),
                Byte::valueOf
        );

        assertThat(iterator.hasNext())
                .isTrue();
        assertThat(iterator.next())
                .isEqualTo((byte) 1);
        assertThat(iterator.hasNext())
                .isTrue();
        assertThat(iterator.next())
                .isEqualTo((byte) 2);

        iterator.remove();
        assertThat(items)
                .containsExactly("1", "3", "4", "5");

        final List<Byte> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .containsExactly((byte) 3, (byte) 4, (byte) 5);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
    }
}
