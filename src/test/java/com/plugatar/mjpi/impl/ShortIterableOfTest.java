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

import com.plugatar.mjpi.ShortIterable;
import com.plugatar.mjpi.ShortIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link ShortIterableOf}.
 */
final class ShortIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new ShortIterableOf((short[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachShortMethodThrowsNPEForNullArg() {
        final ShortIterable iterable = new ShortIterableOf();
        assertThatCode(() -> iterable.forEachShort(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachShortMethodIterateOver0Items() {
        final ShortIterable iterable = new ShortIterableOf();
        final List<Short> forEachShortProcessedItems = new ArrayList<>();
        iterable.forEachShort(forEachShortProcessedItems::add);
        assertThat(forEachShortProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachShortMethodIterateOverMultipleItems() {
        final ShortIterable iterable = new ShortIterableOf((short) 1, (short) 2, (short) 3);
        final List<Short> forEachShortProcessedItems = new ArrayList<>();
        iterable.forEachShort(forEachShortProcessedItems::add);
        assertThat(forEachShortProcessedItems)
                .containsExactly((short) 1, (short) 2, (short) 3);
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final ShortIterator iterator = new ShortIterableOf().shortIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final ShortIterator iterator = new ShortIterableOf().shortIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Short> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final ShortIterator iterator = new ShortIterableOf(
                (short) 1, (short) 2, (short) 3, (short) 4, (short) 5
        ).shortIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo((short) 1);

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo((short) 2);

        final List<Short> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly((short) 3, (short) 4, (short) 5);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Short> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
