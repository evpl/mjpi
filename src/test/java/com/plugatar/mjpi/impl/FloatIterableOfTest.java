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

import com.plugatar.mjpi.FloatIterable;
import com.plugatar.mjpi.FloatIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link FloatIterableOf}.
 */
final class FloatIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new FloatIterableOf((float[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachFloatMethodThrowsNPEForNullArg() {
        final FloatIterable iterable = new FloatIterableOf();
        assertThatCode(() -> iterable.forEachFloat(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachFloatMethodIterateOver0Items() {
        final FloatIterable iterable = new FloatIterableOf();
        final List<Float> forEachFloatProcessedItems = new ArrayList<>();
        iterable.forEachFloat(forEachFloatProcessedItems::add);
        assertThat(forEachFloatProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachFloatMethodIterateOverMultipleItems() {
        final FloatIterable iterable = new FloatIterableOf(1.0f, 2.0f, 3.0f);
        final List<Float> forEachFloatProcessedItems = new ArrayList<>();
        iterable.forEachFloat(forEachFloatProcessedItems::add);
        assertThat(forEachFloatProcessedItems)
                .containsExactly(1.0f, 2.0f, 3.0f);
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final FloatIterator iterator = new FloatIterableOf().floatIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final FloatIterator iterator = new FloatIterableOf().floatIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Float> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final FloatIterator iterator = new FloatIterableOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f).floatIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(1.0f);

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo(2.0f);

        final List<Float> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly(3.0f, 4.0f, 5.0f);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Float> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
