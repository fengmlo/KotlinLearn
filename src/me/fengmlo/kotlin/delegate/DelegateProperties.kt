package me.fengmlo.kotlin.delegate

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

//region 属性委托
class Example {
    var p: String by DelegateExample() // 要委托的类必须有setValue、getValue
}

class DelegateExample {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating ${property.name} to me!"
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}
//endregion

//region lazy
val lazyValue: String by lazy(/*LazyThreadSafetyMode.PUBLICATION*/ /*多线程同时调用*/
        /*LazyThreadSafetyMode.NONE*//*不保证线程安全*/) {
    println("this will print on the first call")
    "value" // 第一次调用结束后，最终值会被缓存，以后再调用直接使用该值，不会调用初始化器
}
//endregion

//region 可观察属性 Observable
class User {
    var name: String by Delegates.observable("<no name>") { property, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }

    var nickname: String by Delegates.vetoable("no nickname") { property, oldValue, newValue ->
        println("$oldValue -> $newValue")
        return@vetoable true // 返回true表示确定更改，返回false表示拦截该次更改
    }
}
//endregion

//region 把属性储存在映射中
class MapUser(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int by map
}
//endregion

fun main(args: Array<String>) {

    // 属性委托
    println("////////// 属性委托")
    val example = Example()
    println(example.p)
    example.p = "new"

    // lazy
    println("/////////// lazy")
    println(lazyValue)
    println(lazyValue)

    // 可观察属性
    println("////////// 可观察属性")
    val user = User()
    println(user.name)
    user.name = "first"
    user.name = "second"

    println(user.nickname)
    user.nickname = "first nickname"

    println("////////// 把属性储存在映射中")
    val mapUser = MapUser(mutableMapOf(
            "name" to "John Doe",
            "age" to 25
    ))
    println(mapUser.name)
    println(mapUser.age)
}