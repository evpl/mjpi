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
package com.plugatar.mjpi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link LongIterator}.
 */
final class LongIteratorTest {

    @Test
    void removeMethodThrowsUnsupportedOperationException() {
        final LongIterator iterator = new LongIterator() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public long next() {
                return 1L;
            }
        };
        assertThatCode(() -> iterator.remove())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void forEachRemainingMethodThrowsNPEForNullArg() {
        final LongIterator iterator = new LongIterator() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public long next() {
                return 1L;
            }
        };
        assertThatCode(() -> iterator.forEachRemaining(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachRemainingMethodIterateOver0Items() {
        final LongIterator iterator = new LongIterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public long next() {
                return 1L;
            }
        };
        final AtomicBoolean isExecuted = new AtomicBoolean();
        iterator.forEachRemaining(item -> isExecuted.set(true));
        assertThat(isExecuted.get())
                .isFalse();
    }

    @Test
    void forEachRemainingMethodIterateOverMultipleItems() {
        final LongIterator iterator = new LongIterator() {
            private int pointer = 1;

            @Override
            public boolean hasNext() {
                return this.pointer == 1 || this.pointer == 2;
            }

            @Override
            public long next() {
                return (long) this.pointer++;
            }
        };
        final List<Long> items = new ArrayList<>();
        iterator.forEachRemaining(items::add);
        assertThat(items)
                .containsExactly(1L, 2L);
    }
}
