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

import com.plugatar.mjpi.DoubleIterable;
import com.plugatar.mjpi.DoubleIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link DoubleIterableOf}.
 */
final class DoubleIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new DoubleIterableOf((double[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachDoubleMethodThrowsNPEForNullArg() {
        final DoubleIterable iterable = new DoubleIterableOf();
        assertThatCode(() -> iterable.forEachDouble(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachDoubleMethodIterateOver0Items() {
        final DoubleIterable iterable = new DoubleIterableOf();
        final List<Double> forEachDoubleProcessedItems = new ArrayList<>();
        iterable.forEachDouble(forEachDoubleProcessedItems::add);
        assertThat(forEachDoubleProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachDoubleMethodIterateOverMultipleItems() {
        final DoubleIterable iterable = new DoubleIterableOf(1.0, 2.0, 3.0);
        final List<Double> forEachDoubleProcessedItems = new ArrayList<>();
        iterable.forEachDouble(forEachDoubleProcessedItems::add);
        assertThat(forEachDoubleProcessedItems)
                .containsExactly(1.0, 2.0, 3.0);
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final DoubleIterator iterator = new DoubleIterableOf().doubleIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final DoubleIterator iterator = new DoubleIterableOf().doubleIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Double> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final DoubleIterator iterator = new DoubleIterableOf(1.0, 2.0, 3.0, 4.0, 5.0).doubleIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(1.0);

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(2.0);

        final List<Double> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly(3.0, 4.0, 5.0);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Double> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
