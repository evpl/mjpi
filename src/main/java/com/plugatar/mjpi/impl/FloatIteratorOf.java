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

import com.plugatar.mjpi.FloatIterator;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The {@link FloatIterator} implementation based on {@code float} array.
 */
public final class FloatIteratorOf implements FloatIterator {
    private final float[] items;
    private final int length;
    private int pointer;

    /**
     * Ctor.
     *
     * @param items items to iterate
     * @throws NullPointerException if {@code items} is null
     */
    public FloatIteratorOf(final float... items) {
        this.items = Objects.requireNonNull(items, "items is null");
        this.length = items.length;
        this.pointer = 0;
    }

    @Override
    public boolean hasNext() {
        return this.pointer < this.length;
    }

    @Override
    public float next() {
        if (this.hasNext()) {
            return this.items[this.pointer++];
        }
        throw new NoSuchElementException();
    }
}
