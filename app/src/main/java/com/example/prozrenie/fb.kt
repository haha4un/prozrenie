package com.example.prozrenie

class fb {
        var surname: String? = null
        var middlename: String? = null
        var name: String? = null
        var id: String? = null
        var date: String? = null

        fun fb() {}
        fun fb(
            name: String?,
            surname: String?,
            middlename: String?,
            date: String?,
            ids: String?
        ) {
            this.surname = surname
            this.name = name
            this.middlename = middlename
            this.date = date
            this.id = ids
        }
        fun getFullName(): String? {
            return "$surname $name $middlename"
        }
        fun getIds(): String? {
            return id
        }
        fun getData(): String? {
        return date
        }


}