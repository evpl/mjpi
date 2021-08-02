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

/**
 * Iterators and Iterables implementations.
 *
 * <br><table>
 * <caption>List of implementations</caption>
 * <tr><th> </th><th> Implementation </th><th> Interface </th><th> Based on </th></tr>
 *
 * <tr><td> byte </td><td> {@link com.plugatar.mjpi.impl.ByteIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.ByteIterator} </td><td> {@code byte[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.ByteIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.ByteIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfByteIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.ByteIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.ByteIterableOf} </td><td>
 * {@link com.plugatar.mjpi.ByteIterable} </td><td> {@code byte[]} </td></tr>
 *
 * <tr><td> short </td><td> {@link com.plugatar.mjpi.impl.ShortIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.ShortIterator} </td><td> {@code short[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.ShortIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.ShortIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfShortIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.ShortIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.ShortIterableOf} </td><td>
 * {@link com.plugatar.mjpi.ShortIterable} </td><td> {@code short[]} </td></tr>
 *
 * <tr><td> int </td><td> {@link com.plugatar.mjpi.impl.IntIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.IntIterator} </td><td> {@code int[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IntIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.IntIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfIntIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.IntIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IntIteratorOfNativeIterator} </td><td>
 * {@link com.plugatar.mjpi.IntIterator} </td><td> {@link java.util.PrimitiveIterator.OfInt} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.NativeIteratorOfIntIterator} </td><td>
 * {@link java.util.PrimitiveIterator.OfInt} </td><td> {@link com.plugatar.mjpi.IntIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IntIterableOf} </td><td>
 * {@link com.plugatar.mjpi.IntIterable} </td><td> {@code int[]} </td></tr>
 *
 * <tr><td> long </td><td> {@link com.plugatar.mjpi.impl.LongIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.LongIterator} </td><td> {@code long[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.LongIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.LongIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfLongIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.LongIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.LongIteratorOfNativeIterator} </td><td>
 * {@link com.plugatar.mjpi.LongIterator} </td><td> {@link java.util.PrimitiveIterator.OfLong} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.NativeIteratorOfLongIterator} </td><td>
 * {@link java.util.PrimitiveIterator.OfLong} </td><td> {@link com.plugatar.mjpi.LongIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.LongIterableOf} </td><td>
 * {@link com.plugatar.mjpi.LongIterable} </td><td> {@code long[]} </td></tr>
 *
 * <tr><td> float </td><td> {@link com.plugatar.mjpi.impl.FloatIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.FloatIterator} </td><td> {@code float[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.FloatIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.FloatIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfFloatIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.FloatIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.FloatIterableOf} </td><td>
 * {@link com.plugatar.mjpi.FloatIterable} </td><td> {@code float[]} </td></tr>
 *
 * <tr><td> double </td><td> {@link com.plugatar.mjpi.impl.DoubleIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.DoubleIterator} </td><td> {@code double[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.DoubleIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.DoubleIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfDoubleIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.DoubleIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.DoubleIteratorOfNativeIterator} </td><td>
 * {@link com.plugatar.mjpi.DoubleIterator} </td><td> {@link java.util.PrimitiveIterator.OfDouble} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.NativeIteratorOfDoubleIterator} </td><td>
 * {@link java.util.PrimitiveIterator.OfDouble} </td><td> {@link com.plugatar.mjpi.DoubleIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.DoubleIterableOf} </td><td>
 * {@link com.plugatar.mjpi.DoubleIterable} </td><td> {@code double[]} </td></tr>
 *
 * <tr><td> char </td><td> {@link com.plugatar.mjpi.impl.CharIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.CharIterator} </td><td> {@code char[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.CharIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.CharIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfCharIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.CharIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.CharIterableOf} </td><td>
 * {@link com.plugatar.mjpi.CharIterable} </td><td> {@code char[]} </td></tr>
 *
 * <tr><td> boolean </td><td> {@link com.plugatar.mjpi.impl.BooleanIteratorOf} </td><td>
 * {@link com.plugatar.mjpi.BooleanIterator} </td><td> {@code boolean[]} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.BooleanIteratorOfIterator} </td><td>
 * {@link com.plugatar.mjpi.BooleanIterator} </td><td> {@link java.util.Iterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.IteratorOfBooleanIterator} </td><td>
 * {@link java.util.Iterator} </td><td> {@link com.plugatar.mjpi.BooleanIterator} </td></tr>
 * <tr><td> </td><td> {@link com.plugatar.mjpi.impl.BooleanIterableOf} </td><td>
 * {@link com.plugatar.mjpi.BooleanIterable} </td><td> {@code boolean[]} </td></tr>
 * </table>
 */
package com.plugatar.mjpi.impl;
