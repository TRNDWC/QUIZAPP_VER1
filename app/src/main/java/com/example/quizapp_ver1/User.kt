package com.example.quizapp_ver1

object User : Player("",0) {

    fun memsetUser(){
        User.Name=""
        User.Score=0
    }
    fun setUserName(name: String) {
        User.Name=name
    }
    fun incScore() {
        User.Score = User.Score?.plus(1)
    }
    fun getUserScore(): Int? {
        return User.Score
    }
    fun getUserName():String?{
        return User.Name
    }
}