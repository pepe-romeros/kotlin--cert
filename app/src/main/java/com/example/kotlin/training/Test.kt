package com.example.kotlin.training

// Functions don't need to be inside a class -> First class citizens

fun test() {
    // this returns Unit = Void in Java
    println("This is a test")
}

fun test2(): Int { // with returning value
    return 0
}

// Expression function
fun test2Expression(x: Int, y: Int): Int = x + y

// Variables

fun variables() {
    var x = 20
    val l: Long = x.toLong()
}

/// Classes

//class Person
//open class Person(name: String, age: Int)
abstract class Person(name: String, age: Int)

class Developer(name: String): Person(name, 30)

fun testClass() {
//    val p = Person("John", 20)
    val d = Developer("Tom")

}
