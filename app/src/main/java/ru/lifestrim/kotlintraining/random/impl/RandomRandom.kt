package ru.lifestrim.kotlintraining.random.impl

import ru.lifestrim.kotlintraining.random.RandomNumberGenerator
import java.util.*

class RandomRandom : RandomNumberGenerator {
    val rnd: Random = Random()

    override fun rnd(minInt: Int, maxInt: Int): Int {
        val span = maxInt - minInt + 1
        return minInt+rnd.nextInt(span)
    }
}