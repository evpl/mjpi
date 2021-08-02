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

import com.plugatar.mjpi.impl.ByteIteratorOf;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link ByteIterable}.
 * <br/>Also used: {@link ByteIteratorOf}.
 */
final class ByteIterableTest {

    @Test
    void forEachByteMethodThrowsNPEForNullArg() {
        final ByteIterable iterable = new ByteIterable() {
            @Override
            public ByteIterator byteIterator() {
                return new ByteIteratorOf();
            }
        };
        assertThatCode(() -> iterable.forEachByte(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachByteMethodIterateOver0Items() {
        final ByteIterable iterable = new ByteIterable() {
            @Override
            public ByteIterator byteIterator() {
                return new ByteIteratorOf();
            }
        };
        final AtomicBoolean isExecuted = new AtomicBoolean();
        iterable.forEachByte(item -> isExecuted.set(true));
        assertThat(isExecuted.get())
                .isFalse();
    }

    @Test
    void forEachByteMethodIterateOverMultipleItems() {
        final ByteIterable iterable = new ByteIterable() {
            @Override
            public ByteIterator byteIterator() {
                return new ByteIteratorOf((byte) 1, (byte) 2);
            }
        };
        final List<Byte> items = new ArrayList<>();
        iterable.forEachByte(items::add);
        assertThat(items)
                .containsExactly((byte) 1, (byte) 2);
    }
}
