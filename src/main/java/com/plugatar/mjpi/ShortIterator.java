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

import com.plugatar.mjfi.ShortConsumer;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * An Iterator specialized for {@code short} values.
 *
 * @see java.util.Iterator
 * @see ShortIterable
 */
public interface ShortIterator {

    /**
     * Returns {@code true} if the iteration has more elements.
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    short next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation). This method can be called
     * only once per call to {@link #next}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this iterator
     * @throws IllegalStateException         if the {@code next} method has not yet been called,
     *                                       or the {@code remove} method has already been called
     *                                       after the last call to the {@code next} method
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * Performs the given action for each remaining element until all elements
     * have been processed or the action throws an exception. Exceptions thrown
     * by the action are relayed to the caller.
     *
     * @param action the action to be performed for each element
     * @throws NullPointerException if {@code action} is null
     */
    default void forEachRemaining(final ShortConsumer action) {
        Objects.requireNonNull(action);
        while (this.hasNext()) {
            action.accept(this.next());
        }
    }
}
