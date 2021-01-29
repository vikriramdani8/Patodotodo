package com.example.patodotodo.model

class Model_User {
    var id_user: Int = 0
    var name_user: String? = null
    var email_user: String? = null
    var username_user: String? = null
    var password_user: String? = null

    constructor(){}
    constructor(id_user: Int){
        this.id_user = id_user
    }

    constructor(username_user: String, password_user: String){
        this.username_user = username_user
        this.password_user = password_user
    }

    constructor(id_user: Int, name_user: String, email_user: String, username_user: String, password_user: String){
        this.id_user = id_user
        this.name_user = name_user
        this.email_user = email_user
        this.username_user = username_user
        this.password_user = password_user
    }

}