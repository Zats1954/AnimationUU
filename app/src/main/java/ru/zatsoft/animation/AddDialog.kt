package ru.zatsoft.animation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException


class AddDialog : DialogFragment() {
    lateinit var addBasket: AddBasket

    override fun onAttach(context: Context) {
        super.onAttach(context)
        addBasket = context as AddBasket
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val product = requireArguments().getParcelable<Product>("product")
        return activity?.let {
            val builder = AlertDialog.Builder(requireActivity())
            builder
                .setTitle("Добавление в корзину")
                .setMessage("Добавляем \"${product?.name}\" ?")
                .setIcon(R.drawable.add_product_48)
                .setPositiveButton("В корзину") { dialog, ind ->
                    product?.let { addBasket.addBasket(it) }
                }
                .setNegativeButton("Отмена", null)
                .create()
        } ?: throw IllegalStateException("Activity null")
    }
}