package me.fengmlo.kotlin.delegate

interface Base {
    fun print()
}

class BaseImpl(private val x: Int) : Base {
    override fun print() {
        println(x)
    }
}

class Derived(var b: Base) : Base by b {
    override fun print() { // 重写print
        b.print()
        println("Derived")
    }
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print()
}