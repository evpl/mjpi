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

import com.plugatar.mjpi.IntIterable;
import com.plugatar.mjpi.IntIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link IntIterableOf}.
 */
final class IntIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new IntIterableOf((int[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachIntMethodThrowsNPEForNullArg() {
        final IntIterable iterable = new IntIterableOf();
        assertThatCode(() -> iterable.forEachInt(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachIntMethodIterateOver0Items() {
        final IntIterable iterable = new IntIterableOf();
        final List<Integer> forEachIntProcessedItems = new ArrayList<>();
        iterable.forEachInt(forEachIntProcessedItems::add);
        assertThat(forEachIntProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachIntMethodIterateOverMultipleItems() {
        final IntIterable iterable = new IntIterableOf(1, 2, 3);
        final List<Integer> forEachIntProcessedItems = new ArrayList<>();
        iterable.forEachInt(forEachIntProcessedItems::add);
        assertThat(forEachIntProcessedItems)
                .containsExactly(1, 2, 3);
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final IntIterator iterator = new IntIterableOf().intIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final IntIterator iterator = new IntIterableOf().intIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Integer> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final IntIterator iterator = new IntIterableOf(1, 2, 3, 4, 5).intIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(1);

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(2);

        final List<Integer> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly(3, 4, 5);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Integer> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
