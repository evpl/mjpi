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

import com.plugatar.mjpi.ByteIterable;
import com.plugatar.mjpi.ByteIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link ByteIterableOf}.
 */
final class ByteIterableOfTest {

    @Test
    void ctorThrowsNPEForNullArg() {
        assertThatCode(() -> {
            new ByteIterableOf((byte[]) null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachByteMethodThrowsNPEForNullArg() {
        final ByteIterable iterable = new ByteIterableOf();
        assertThatCode(() -> iterable.forEachByte(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachByteMethodIterateOver0Items() {
        final ByteIterable iterable = new ByteIterableOf();
        final List<Byte> forEachByteProcessedItems = new ArrayList<>();
        iterable.forEachByte(forEachByteProcessedItems::add);
        assertThat(forEachByteProcessedItems)
                .isEmpty();
    }

    @Test
    void forEachByteMethodIterateOverMultipleItems() {
        final ByteIterable iterable = new ByteIterableOf((byte) 1, (byte) 2, (byte) 3);
        final List<Byte> forEachByteProcessedItems = new ArrayList<>();
        iterable.forEachByte(forEachByteProcessedItems::add);
        assertThat(forEachByteProcessedItems)
                .containsExactly((byte) 1, (byte) 2, (byte) 3);
    }

    @Test
    void iteratorForEachRemainingMethodThrowsNPEForNullArg() {
        final ByteIterator iterator = new ByteIterableOf().byteIterator();
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void iterateOver0Items() {
        final ByteIterator iterator = new ByteIterableOf().byteIterator();
        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);
        final List<Byte> forEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(forEachRemainingProcessedItems::add);
        assertThat(forEachRemainingProcessedItems)
                .isEmpty();
    }

    @Test
    void iterateOverMultipleItems() {
        final ByteIterator iterator = new ByteIterableOf(
                (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5
        ).byteIterator();

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo((byte) 1);

        assertThat(iterator.hasNext())
                .isTrue();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(iterator.next())
                .isEqualTo((byte) 2);

        final List<Byte> firstForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(firstForEachRemainingProcessedItems::add);
        assertThat(firstForEachRemainingProcessedItems)
                .containsExactly((byte) 3, (byte) 4, (byte) 5);

        assertThat(iterator.hasNext())
                .isFalse();
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatCode(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class);

        final List<Byte> secondForEachRemainingProcessedItems = new ArrayList<>();
        iterator.forEachRemaining(secondForEachRemainingProcessedItems::add);
        assertThat(secondForEachRemainingProcessedItems)
                .isEmpty();
    }
}
