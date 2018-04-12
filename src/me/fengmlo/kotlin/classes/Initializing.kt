package me.fengmlo.kotlin.classes

class User(val nickname: String) // 最简洁的语法，形参和属性同字段，自动赋值给属性

class Player constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }

//    fun getName(): String = _nickname // 形参只能在初始化代码中能访问（属性的初始化器和初始化块）
}

class Gamer (_nickname: String) {
    val nickname = _nickname
}

fun main(args: Array<String>) {
    println(User("jim").nickname)
    println(Player("lily").nickname)
    println(Gamer("linda").nickname)
}