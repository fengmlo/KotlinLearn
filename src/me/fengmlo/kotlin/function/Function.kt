package me.fengmlo.kotlin.function

import com.sun.corba.se.impl.orbutil.graph.Graph
import sun.security.provider.certpath.Vertex

fun double(x: Int): Int { // 具有代码块体的函数必须显式指定返回类型
    return 2 * x
}

fun double2(x: Int) = 2 * x // 单表达式函数返回值类型可推断时可省略

fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) { // 函数参数必须有显式类型
    // 函数返回类型为Unit
}

open class A {
    open fun foo(i: Int = 10) {}
}

class B : A() {
    override fun foo(i: Int/* = 20*/) {} // 覆盖方法只能用与基类相同的默认值
}

fun foo(bar: Int = 0, baz: Int) {} // 要使用默认值，只能用命名参数调用 foo(baz = 1)

fun foo2(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { // 若lambda表达式参数从括号外传入，允许默认参数不传值
    println("bar: $bar, baz: $baz")
}
/*
foo(1) { println("hello") } // 使用默认值 baz = 1
foo { println("hello") }    // 使用两个默认值 bar = 0 与 baz = 1
*/

infix fun Int.shl(x: Int): Int { // 中缀表示法
    // ……
    return 0
}

fun <T> asList(vararg ts: T): List<T> { // 可变参数
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

// 命名参数
fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {
}

// 局部函数和闭包
/*fun dfs(graph: Graph) {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v)
    }
    dfs(graph.vertices[0])
}*/

// 尾递归函数，编译器优化为循环，不会栈溢出，函数必须将其自身调用作为它执行的最后一个操作
tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

fun main(args: Array<String>) {
    foo2(1) { println("hello") }
    foo2 { println("hello") }

    reformat("123",
            normalizeCase = true,
            upperCaseFirstLetter = true,
            divideByCamelHumps = false,
            wordSeparator = '_'
    ) // 命名参数，非命名参数必须放在命名参数前，命名参数之间的位置可以随意

}