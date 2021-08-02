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

import com.plugatar.mjfi.CharConsumer;
import com.plugatar.mjfi.ToCharFunction;
import com.plugatar.mjpi.CharIterator;

import java.util.Iterator;
import java.util.Objects;

/**
 * The {@link CharIterator} implementation based on {@link Iterator}.
 *
 * @param <T> the type of wrapped Iterator items
 */
public final class CharIteratorOfIterator<T> implements CharIterator {
    private final Iterator<? extends T> origin;
    private final ToCharFunction<? super T> conversionFunction;

    /**
     * Ctor.
     *
     * @param origin             the origin iterator
     * @param conversionFunction the function to convert items of type {@code T} to {@code char}
     * @throws NullPointerException if {@code origin} or {@code conversionFunction} is null
     */
    public CharIteratorOfIterator(final Iterator<? extends T> origin,
                                  final ToCharFunction<? super T> conversionFunction) {
        this.origin = Objects.requireNonNull(origin, "origin is null");
        this.conversionFunction = Objects.requireNonNull(conversionFunction, "conversionFunction is null");
    }

    @Override
    public boolean hasNext() {
        return this.origin.hasNext();
    }

    @Override
    public char next() {
        return this.conversionFunction.applyAsChar(this.origin.next());
    }

    @Override
    public void remove() {
        this.origin.remove();
    }

    @Override
    public void forEachRemaining(final CharConsumer action) {
        this.origin.forEachRemaining(obj -> action.accept(this.conversionFunction.applyAsChar(obj)));
    }
}
