# MJPI - The Missing Java Primitive Iterators library

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.plugatar/mjpi/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.plugatar/mjpi)
[![Javadoc](http://www.javadoc.io/badge/com.plugatar/mjpi.svg)](http://www.javadoc.io/doc/com.plugatar/mjpi)

---

This library contains Iterators and Iterables for all primitive types. It also replaces native primitive iterators: 
`java.util.PrimitiveIterator.OfInt`, `java.util.PrimitiveIterator.OfLong`, `java.util.PrimitiveIterator.OfDouble`.

## How to use

Maven:
```xml
<dependency>
  <groupId>com.plugatar</groupId>
  <artifactId>mjpi</artifactId>
  <version>1.0</version>
</dependency>
```

Gradle:
```groovy
dependencies {
    compile 'com.plugatar:mjpi:1.0'
}
```

## List of interfaces

| Item type | Iterator          | Iterable          |
| --------- | ----------------- | ----------------- |
| `byte`    | `ByteIterator`    | `ByteIterable`    |
| `short`   | `ShortIterator`   | `ShortIterable`   |
| `int`     | `IntIterator`     | `IntIterator`     |
| `long`    | `LongIterator`    | `LongIterable`    |
| `float`   | `FloatIterator`   | `FloatIterable`   |
| `double`  | `DoubleIterator`  | `DoubleIterable`  |
| `char`    | `CharIterator`    | `CharIterable`    |
| `boolean` | `BooleanIterator` | `BooleanIterable` |

# List of implementations

|           | Implementation                   | Interface                    | Based on                     |
| --------- | -------------------------------- | ---------------------------- | ---------------------------- |
| `byte`    | `ByteIteratorOf`                 | `ByteIterator`               | `byte[]`                     |
|           | `ByteIteratorOfIterator`         | `ByteIterator`               | `Iterator<T>`                |
|           | `IteratorOfByteIterator`         | `Iterator<T>`                | `ByteIterator`               |
|           | `ByteIterableOf`                 | `ByteIterable`               | `byte[]`                     |
| `short`   | `ShortIteratorOf`                | `ShortIterator`              | `short[]`                    |
|           | `ShortIteratorOfIterator`        | `ShortIterator`              | `Iterator<T>`                |
|           | `IteratorOfShortIterator`        | `Iterator<T>`                | `ShortIterator`              |
|           | `ShortIterableOf`                | `ShortIterable`              | `short[]`                    |
| `int`     | `IntIteratorOf`                  | `IntIterator`                | `int[]`                      |
|           | `IntIteratorOfIterator`          | `IntIterator`                | `Iterator<T>`                |
|           | `IteratorOfIntIterator`          | `Iterator<T>`                | `IntIterator`                |
|           | `IntIteratorOfNativeIterator`    | `IntIterator`                | `PrimitiveIterator.OfInt`    |
|           | `NativeIteratorOfIntIterator`    | `PrimitiveIterator.OfInt`    | `IntIterator`                |
|           | `IntIterableOf`                  | `IntIterable`                | `int[]`                      |
| `long`    | `LongIteratorOf`                 | `LongIterator`               | `long[]`                     |
|           | `LongIteratorOfIterator`         | `LongIterator`               | `Iterator<T>`                |
|           | `IteratorOfLongIterator`         | `Iterator<T>`                | `LongIterator`               |
|           | `LongIteratorOfNativeIterator`   | `LongIterator`               | `PrimitiveIterator.OfLong`   |
|           | `NativeIteratorOfLongIterator`   | `PrimitiveIterator.OfLong`   | `LongIterator`               |
|           | `LongIterableOf`                 | `LongIterable`               | `long[]`                     |
| `float`   | `FloatIteratorOf`                | `FloatIterator`              | `float[]`                    |
|           | `FloatIteratorOfIterator`        | `FloatIterator`              | `Iterator<T>`                |
|           | `IteratorOfFloatIterator`        | `Iterator<T>`                | `FloatIterator`              |
|           | `FloatIterableOf`                | `FloatIterable`              | `float[]`                    |
| `double`  | `DoubleIteratorOf`               | `DoubleIterator`             | `double[]`                   |
|           | `DoubleIteratorOfIterator`       | `DoubleIterator`             | `Iterator<T>`                |
|           | `IteratorOfDoubleIterator`       | `Iterator<T>`                | `DoubleIterator`             |
|           | `DoubleIteratorOfNativeIterator` | `DoubleIterator`             | `PrimitiveIterator.OfDouble` |
|           | `NativeIteratorOfDoubleIterator` | `PrimitiveIterator.OfDouble` | `DoubleIterator`             |
|           | `DoubleIterableOf`               | `DoubleIterable`             | `double[]`                   |
| `char`    | `CharIteratorOf`                 | `CharIterator`               | `char[]`                     |
|           | `CharIteratorOfIterator`         | `CharIterator`               | `Iterator<T>`                |
|           | `IteratorOfCharIterator`         | `Iterator<T>`                | `CharIterator`               |
|           | `CharIterableOf`                 | `CharIterable`               | `char[]`                     |
| `boolean` | `BooleanIteratorOf`              | `BooleanIterator`            | `boolean[]`                  |
|           | `BooleanIteratorOfIterator`      | `BooleanIterator`            | `Iterator<T>`                |
|           | `IteratorOfBooleanIterator`      | `Iterator<T>`                | `BooleanIterator`            |
|           | `BooleanIterableOf`              | `BooleanIterable`            | `boolean[]`                  |
