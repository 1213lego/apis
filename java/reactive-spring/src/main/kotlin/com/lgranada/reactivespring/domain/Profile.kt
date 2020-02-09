package com.lgranada.reactivespring.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Profile(@Id val id: String?, val email: String){
    constructor(email: String): this(null,email);
}
