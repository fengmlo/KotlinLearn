package me.fengmlo.kotlin.function

import java.util.concurrent.locks.Lock

fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}
// 如果函数的最后一个参数是一个函数，并且你传递一个 lambda 表达式作为相应的参数，你可以在圆括号之外指定它
//lock (lock) {
//    sharedResource.operation()
//}

/*
lambda 表达式总是括在花括号中；
其参数（如果有的话）在 -> 之前声明（参数类型可以省略）；
函数体（如果存在的话）在 -> 后面。
*/

fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this) {
        result.add(transform(item))
    }
    return result
}
// 如果 lambda 是该调用的唯一参数，则调用中的圆括号可以完全省略
// val doubled = ints.map { value -> value * 2 }

// 如果函数字面值只有一个参数， 那么它的声明可以省略（连同 ->），其名称是 it
// ints.map { it * 2 }

// 申明函数类型
/*
val compare: (x: T, y: T) -> Int = ……
var sum: ((Int, Int) -> Int)? = null // 可空类型
*/

// 完整语法
/*
val sum = { x: Int, y: Int -> x + y }
val sum: (Int, Int) -> Int = { x, y -> x + y }
*/

// 匿名函数
/*
fun(x: Int, y: Int): Int = x + y

fun(x: Int, y: Int): Int {
    return x + y
}*/

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)

    list.filter(fun(it: Int) = it > 0)
    list.filter(fun(it) = it > 0)
    list.filter(fun(it: Int): Boolean {
        return it > 0
    })

    list.filter { it > 0 }
    list.filter { it -> it > 0 }
    list.filter {
        val shouldFilter = it > 0
        shouldFilter
    }
    list.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter
    }

    val filter: (Int) -> Boolean = { it -> it > 0 }
    list.filter(filter)

    val filter2 = { it: Int -> it > 0 }
    list.filter(filter2)

    val sum = fun Int.(other: Int): Int = this + other
    println(1.sum(2))

    fun test(op: (a: Int, b: Int) -> Int, a: Int, b: Int) {
        println(op(a, b))
    }

    val sum2 = fun(a: Int, b:Int) = a + b

    // 类型兼容，Int.(Int) -> Int 与 (Int, Int) -> Int 兼容
    test(sum, 1, 2)
    test(sum2, 1, 2)
}
