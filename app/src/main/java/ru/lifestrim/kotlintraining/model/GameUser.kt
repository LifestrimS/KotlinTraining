package ru.lifestrim.kotlintraining.model

class GameUser(val firstName:String,
               val lastName:String,
               val userName:String,
               val registrationNumber:Int,
               val birthday:String = "",
               val userRank:Double = 0.0) {
    val fullName:String
    val initials:String
    init {
        fullName = firstName + " " + lastName
        initials = firstName.toUpperCase() +
                lastName.toUpperCase()
    }
}