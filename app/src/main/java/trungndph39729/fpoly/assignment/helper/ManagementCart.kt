package com.example.project1762.Helper

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import trungndph39729.fpoly.assignment.Models.Product
import trungndph39729.fpoly.assignment.helper.ChangeNumberItemsListener
import trungndph39729.fpoly.assignment.helper.TinyDB


class ManagmentCart(val context: Context, private val onCartUpdated: () -> Unit) {

    private val tinyDB = TinyDB(context)

    fun insertItem(item: Product) {
        var listFood = getListCart()
        val existAlready = listFood.any { it.title == item.title }
        val index = listFood.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listFood[index].numberInCart!! + 1
        } else {
            item.numberInCart = 1 // Set numberInCart to 1 for new items
            listFood.add(item)
        }
        tinyDB.putListObject("CartList", listFood)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()

        // Notify UI about the change
        onCartUpdated()
    }

    fun removeItem(itemToRemove: Product, listener: ChangeNumberItemsListener) {
        var listFood = getListCart()
        listFood.removeIf { it.id == itemToRemove.id }
        tinyDB.putListObject("CartList", listFood)
        listener.changed()
        Toast.makeText(context, "Removed from your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): SnapshotStateList<Product> {
        val list = tinyDB.getListObject("CartList") ?: listOf()
        val stateList = mutableStateListOf<Product>()
        stateList.addAll(list)
        return stateList
    }

    fun minusItem(
        listFood: SnapshotStateList<Product>,
        position: Int,

        ) {
        if (listFood[position].numberInCart == 1) {
            listFood.removeAt(position)
        } else {
            listFood[position].numberInCart = listFood[position].numberInCart!! - 1
        }
        tinyDB.putListObject("CartList", listFood)
        onCartUpdated()
    }

    fun plusItem(listFood: SnapshotStateList<Product>, position: Int) {
        listFood[position].numberInCart = listFood[position].numberInCart!! + 1
        tinyDB.putListObject("CartList", listFood)
        onCartUpdated()
    }

    fun getTotalFee(): Double {
        val listFood = getListCart()
        var fee = 0.0
        for (item in listFood) {
            fee += item.price * item.numberInCart!!
        }
        onCartUpdated()
        return fee
    }
    fun clearCart() {
        tinyDB.remove("CartList")
        onCartUpdated()
    }
}