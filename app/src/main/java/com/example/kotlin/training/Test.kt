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

