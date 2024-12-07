package ru.zatsoft.animation

import android.app.AlertDialog
import android.app.Dialog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class RemoveDialog: DialogFragment() {
    lateinit var removable: Removable

        override fun onAttach(context: Context) {
            super.onAttach(context)
            removable = context as Removable
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val product = requireArguments().getParcelable<Product>("product")
            return activity?.let {
                val builder = AlertDialog.Builder(requireActivity())
                builder
                    .setTitle("Удаляем из корзины")
                    .setMessage("Удаляем \"${product?.name}\" ?")
                    .setIcon(R.drawable.remove_product)
                    .setPositiveButton("Да") { dialog, ind ->
                        product?.let { removable.remove(it)}
                    }
                    .setNegativeButton("Отмена", null)
                    .create()
            } ?: throw IllegalStateException("Activity null")
        }
}
