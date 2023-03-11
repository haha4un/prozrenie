package com.example.prozrenie

class fb {
        var sur: String? = null
        var mid: String? = null
        var name: String? = null
        var id: String? = null
        var date: String? = null

        fun fb() {}
        fun fb(
            name: String?,
            sur: String?,
            mid: String?,
            date: String?,
            ids: String?
        ) {
            this.sur = sur
            this.name = name
            this.mid = mid
            this.date = date
            this.id = ids
        }
        fun getFullName(): String? {
            return "$name $sur $mid"
        }
        fun getIds(): String? {
            return id
        }
        fun getData(): String? {
        return date
        }


}