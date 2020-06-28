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
//abstract class Person(name: String, age: Int) {
//    // java way
//    private val name: String
//    private val age: Int
//
//    init {
//        this.name = name
//        this.age = age
//    }
//}

// optimization step 1
//abstract class Person(name: String, age: Int) {
//    private val name: String = name
//    private val age: Int = age
//}

// Optimization step 2
//abstract class Person(val name: String, val age: Int)

// Custom setter getter
abstract class Person(name: String, val age: Int) {
    var name = name
    get() = "Hello $field"
    set(value) {
        if(field != value) {
            field = value
        }
    }
}

class Developer(name: String): Person(name, 30)

fun testClass() {
//    val p = Person("John", 20)
    val d = Developer("Tom")
    val name = d.name

}
