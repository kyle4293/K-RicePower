package com.example.familyalbum.chat

data class Message(
    var message: String?,
    var sendId: String?,
    var sendTime: String?
    ){
    constructor(): this("","","")
}
