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

import java.util.Objects;
import java.util.function.LongConsumer;

/**
 * An Iterable specialized for {@code long} values.
 *
 * @see Iterable
 * @see LongIterator
 */
public interface LongIterable {

    /**
     * Returns an LongIterator over elements.
     *
     * @return an LongIterator
     */
    LongIterator longIterator();

    /**
     * Performs the given action for each element of the {@code LongIterable}
     * until all elements have been processed or the action throws an
     * exception. Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @param action the action to be performed for each element
     * @throws NullPointerException if {@code action} is null
     */
    default void forEachLong(final LongConsumer action) {
        Objects.requireNonNull(action);
        final LongIterator iterator = this.longIterator();
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }
}
