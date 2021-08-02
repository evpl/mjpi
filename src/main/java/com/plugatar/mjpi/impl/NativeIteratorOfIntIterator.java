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

import com.plugatar.mjpi.IntIterator;

import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.IntConsumer;

/**
 * The {@link PrimitiveIterator.OfInt} implementation based on {@link IntIterator}.
 */
public final class NativeIteratorOfIntIterator implements PrimitiveIterator.OfInt {
    private final IntIterator origin;

    /**
     * Ctor.
     *
     * @param origin the origin iterator
     * @throws NullPointerException if {@code origin} is null
     */
    public NativeIteratorOfIntIterator(final IntIterator origin) {
        this.origin = Objects.requireNonNull(origin, "origin is null");
    }

    @Override
    public boolean hasNext() {
        return this.origin.hasNext();
    }

    @Override
    public int nextInt() {
        return this.origin.next();
    }

    @Override
    public void remove() {
        this.origin.remove();
    }

    @Override
    public void forEachRemaining(final IntConsumer action) {
        this.origin.forEachRemaining(action);
    }
}
