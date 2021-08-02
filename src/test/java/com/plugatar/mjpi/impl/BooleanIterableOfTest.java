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

import com.plugatar.mjpi.BooleanIterable;
import com.plugatar.mjpi.BooleanIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link BooleanIterableOf}.
 */
final class BooleanIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new BooleanIterableOf((boolean[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachBooleanMethodThrowsNPEForNullArg() {
        final BooleanIterable iterable = new BooleanIterableOf();
        assertThatCode(() -> iterable.forEachBoolean(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachBooleanMethodIterateOver0Items() {
        final BooleanIterable iterable = new BooleanIterableOf();
        final List<Boolean> forEachBooleanProcessedItems = new ArrayList<>();
        iterable.forEachBoolean(forEachBooleanProcessedItems::add);
        assertThat(forEachBooleanProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachBooleanMethodIterateOverMultipleItems() {
        final BooleanIterable iterable = new BooleanIterableOf(true, false, false);
        final List<Boolean> forEachBooleanProcessedItems = new ArrayList<>();
        iterable.forEachBoolean(forEachBooleanProcessedItems::add);
        assertThat(forEachBooleanProcessedItems)
                .containsExactly(true, false, false);
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final BooleanIterator iterator = new BooleanIterableOf().booleanIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final BooleanIterator iterator = new BooleanIterableOf().booleanIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Boolean> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final BooleanIterator iterator = new BooleanIterableOf(
                true, false, false, true, true
        ).booleanIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isTrue();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isFalse();

        final List<Boolean> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly(false, true, true);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Boolean> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
