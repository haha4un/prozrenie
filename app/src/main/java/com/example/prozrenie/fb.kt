package com.example.prozrenie

class fb {
        var surname: String? = null
        var middlename: String? = null
        var name: String? = null
        var id: String? = null
        var date: String? = null
        var diagnose: String? = null

        fun fb() {}
        fun fb(
            name: String?,
            surname: String?,
            middlename: String?,
            date: String?,
            ids: String?,
            diagnos: String?
        ) {
            this.surname = surname
            this.name = name
            this.middlename = middlename
            this.date = date
            this.id = ids
            this.diagnose = diagnos
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
        fun getNames(): String? {
            return name
        }
        fun getSurnames(): String? {
            return surname
        }
        fun getMiddlenames(): String? {
            return middlename
        }
        fun getDiagnoses():String? {
            return diagnose
        }


}