package com.example.kotlin.training

import android.content.Context
import android.widget.TextView
import com.example.kotlin.training.model.MediaItem

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

fun test3(context: Context) {
    val textView: TextView = TextView(context).apply2 {
        text = "Hello"
        hint = "GoodBye"
        textSize = 20f
    }

}

//fun TextView.apply2(body: TextView.() -> Unit): TextView {
//    body()
//    return this
//}
inline fun <T>T.apply2(body: T.() -> Unit): T {
    body()
    return this
}

// run
//fun <T, U>T.run2(body: T.() -> U): U {
//    return body()
//}
inline fun <T, U>T.run2(body: T.() -> U): U = body()

// let
//fun <T, U>T.let2(body: (T) -> U): U {
//    return body(this)
//}
inline fun <T, U>T.let2(body: (T) -> U): U = body(this)

// property delegates
// lazy, observable, vetoable

// Collections
fun test4(context: Context) {
    val listOfInt: List<Int> = listOf(1, 3, 5, 6, 7, 9) // is immutable
    val mutableListOfInt:MutableList<Int> = mutableListOf(1, 3, 5, 6, 7, 9)

    val evenValues:List<String> = listOfInt.filter { it % 2 == 0 }.map { it.toString() }
//
//    setOf<>()
//    mapOf<>()
//    sequenceOf()
}

fun test5(items: List<MediaItem>) {
    val listOfUrls = items
        .asSequence()
        .filter { it.type == MediaItem.Type.PHOTO }
        .map { it.url }
        .toList()

//    val myMap = mapOf(Pair(1, "a"), Pair(2, "b"))
    val myMap = mapOf(1 to "a", 2 to "b")

    for((key, value) in myMap) {
        print("Key is $key and Value is $value")
    }
}