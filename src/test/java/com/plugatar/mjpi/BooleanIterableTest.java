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

import com.plugatar.mjpi.impl.BooleanIteratorOf;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Test case for {@link BooleanIterable}.
 * <br/>Also used: {@link BooleanIteratorOf}.
 */
final class BooleanIterableTest {

    @Test
    void forEachBooleanMethodThrowsNPEForNullArg() {
        final BooleanIterable iterable = new BooleanIterable() {
            @Override
            public BooleanIterator booleanIterator() {
                return new BooleanIteratorOf();
            }
        };
        assertThatCode(() -> iterable.forEachBoolean(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void forEachBooleanMethodIterateOver0Items() {
        final BooleanIterable iterable = new BooleanIterable() {
            @Override
            public BooleanIterator booleanIterator() {
                return new BooleanIteratorOf();
            }
        };
        final AtomicBoolean isExecuted = new AtomicBoolean();
        iterable.forEachBoolean(item -> isExecuted.set(true));
        assertThat(isExecuted.get())
                .isFalse();
    }

    @Test
    void forEachBooleanMethodIterateOverMultipleItems() {
        final BooleanIterable iterable = new BooleanIterable() {
            @Override
            public BooleanIterator booleanIterator() {
                return new BooleanIteratorOf(false, true);
            }
        };
        final List<Boolean> items = new ArrayList<>();
        iterable.forEachBoolean(items::add);
        assertThat(items)
                .containsExactly(false, true);
    }
}
