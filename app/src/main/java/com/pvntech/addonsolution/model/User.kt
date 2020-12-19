package com.pvntech.addonsolution.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user", indices = [Index(value = ["userId"], unique = true)])
class User {

    @PrimaryKey(autoGenerate = true)
    var uId:Int = 0

    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    var userId:String?=null

    @SerializedName("userName")
    @ColumnInfo(name = "userName")
    var userName:String?=null

}