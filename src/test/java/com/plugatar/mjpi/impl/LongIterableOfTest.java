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

import com.plugatar.mjpi.LongIterable;
import com.plugatar.mjpi.LongIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link LongIterableOf}.
 */
final class LongIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new LongIterableOf((long[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachLongMethodThrowsNPEForNullArg() {
        final LongIterable iterable = new LongIterableOf();
        assertThatCode(() -> iterable.forEachLong(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachLongMethodIterateOver0Items() {
        final LongIterable iterable = new LongIterableOf();
        final List<Long> forEachLongProcessedItems = new ArrayList<>();
        iterable.forEachLong(forEachLongProcessedItems::add);
        assertThat(forEachLongProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachLongMethodIterateOverMultipleItems() {
        final LongIterable iterable = new LongIterableOf(1L, 2L, 3L);
        final List<Long> forEachLongProcessedItems = new ArrayList<>();
        iterable.forEachLong(forEachLongProcessedItems::add);
        assertThat(forEachLongProcessedItems)
                .containsExactly(1L, 2L, 3L);
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final LongIterator iterator = new LongIterableOf().longIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final LongIterator iterator = new LongIterableOf().longIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Long> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final LongIterator iterator = new LongIterableOf(1L, 2L, 3L, 4L, 5L).longIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(1L);

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(2L);

        final List<Long> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly(3L, 4L, 5L);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Long> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
