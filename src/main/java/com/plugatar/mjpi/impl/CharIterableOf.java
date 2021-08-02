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

import java.util.Objects;

/**
 * The {@link CharIterable} implementation based on {@code char} array.
 */
public final class CharIterableOf implements CharIterable {
    private final char[] items;

    /**
     * Ctor.
     *
     * @param items the array
     * @throws NullPointerException if {@code items} is null
     */
    public CharIterableOf(final char... items) {
        this.items = Objects.requireNonNull(items, "items is null");
    }

    @Override
    public CharIterator charIterator() {
        return new CharIteratorOf(this.items);
    }
}
