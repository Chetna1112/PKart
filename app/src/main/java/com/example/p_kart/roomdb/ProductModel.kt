package com.example.p_kart.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.checkerframework.checker.index.qual.NonNegative
import org.checkerframework.checker.nullness.qual.NonNull

@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey
    val productID:String,
    @ColumnInfo(name = "productName")
    val productName:String?="",
    @ColumnInfo(name = "productImage")
    val productImage:String?="",
    @ColumnInfo(name = "productSP")
    val productSP:String?="",




)
