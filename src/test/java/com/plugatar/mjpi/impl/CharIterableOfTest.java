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

import com.plugatar.mjpi.CharIterable;
import com.plugatar.mjpi.CharIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link CharIterableOf}.
 */
final class CharIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new CharIterableOf((char[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachCharMethodThrowsNPEForNullArg() {
        final CharIterable iterable = new CharIterableOf();
        assertThatCode(() -> iterable.forEachChar(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachCharMethodIterateOver0Items() {
        final CharIterable iterable = new CharIterableOf();
        final List<Character> forEachCharProcessedItems = new ArrayList<>();
        iterable.forEachChar(forEachCharProcessedItems::add);
        assertThat(forEachCharProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachCharMethodIterateOverMultipleItems() {
        final CharIterable iterable = new CharIterableOf('1', '2', '3');
        final List<Character> forEachCharProcessedItems = new ArrayList<>();
        iterable.forEachChar(forEachCharProcessedItems::add);
        assertThat(forEachCharProcessedItems)
                .containsExactly('1', '2', '3');
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final CharIterator iterator = new CharIterableOf().charIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final CharIterator iterator = new CharIterableOf().charIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Character> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final CharIterator iterator = new CharIterableOf('1', '2', '3', '4', '5').charIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo('1');

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo('2');

        final List<Character> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly('3', '4', '5');

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Character> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
