package me.fengmlo.kotlin.classes

fun getFacebookName(accountId: Int) = "fb:$accountId"

interface NamedUser {
    val nickname: String
}
class PrivateNamedUser(override val nickname: String) : NamedUser

class SubscribingNamedUser(val email: String) : NamedUser {
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookNamedUser(val accountId: Int) : NamedUser {
    override val nickname = getFacebookName(accountId)
}

// 覆写属性一般是为了给它自定义getter
fun main(args: Array<String>) {
    println(PrivateNamedUser("test@kotlinlang.org").nickname)
    println(SubscribingNamedUser("test@kotlinlang.org").nickname)
}