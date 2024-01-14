package com.idz.lecture4_demo3.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val name: String,
    val id: String,
    val avatarUrl: String,
    var isChecked: Boolean) {

    companion object {

        const val NAME_KEY = "name"
        const val ID_KEY = "id"
        const val AVATAR_URL_KEY = "avatarUrl"
        const val IS_CHECKED_KEY = "isChecked"

        fun fromJSON(json: Map<String, Any>): Student {
            val id = json[ID_KEY] as? String ?: ""
            val name = json[NAME_KEY] as? String ?: ""
            val avatarUrl = json[AVATAR_URL_KEY] as? String ?: ""
            val isChecked = json[IS_CHECKED_KEY] as? Boolean ?: false
            return Student(name, id, avatarUrl, isChecked)
        }
    }

    val json: Map<String, Any>
        get() {
            return hashMapOf(
                ID_KEY to id,
                NAME_KEY to name,
                AVATAR_URL_KEY to avatarUrl,
                IS_CHECKED_KEY to isChecked
            )
        }
}
