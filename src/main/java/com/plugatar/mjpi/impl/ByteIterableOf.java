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

import java.util.Objects;

/**
 * The {@link ByteIterable} implementation based on {@code byte} array.
 */
public final class ByteIterableOf implements ByteIterable {
    private final byte[] items;

    /**
     * Ctor.
     *
     * @param items the array
     * @throws NullPointerException if {@code items} is null
     */
    public ByteIterableOf(final byte... items) {
        this.items = Objects.requireNonNull(items, "items is null");
    }

    @Override
    public ByteIterator byteIterator() {
        return new ByteIteratorOf(this.items);
    }
}
