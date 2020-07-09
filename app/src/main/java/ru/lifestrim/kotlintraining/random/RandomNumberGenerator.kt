package ru.lifestrim.kotlintraining.random

interface RandomNumberGenerator {
    fun rnd(minInt:Int, maxInt:Int):Int
}