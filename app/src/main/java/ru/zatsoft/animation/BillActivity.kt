package ru.zatsoft.animation

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.zatsoft.animation.StartActivity.Companion.mBasket
import ru.zatsoft.animation.databinding.ActivityBillBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBillBinding
    private lateinit var basketAdapter: BillAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val formater = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val currentDate = LocalDateTime.now().format(formater)
        binding.billDate.text = currentDate

        var summ = 0.0
        mBasket.forEach { product -> summ += product.price }

        basketAdapter = BillAdapter(mBasket)
        binding.rvBill.adapter = basketAdapter
        binding.rvBill.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.tvSumm.text = "Итого: ${summ.toString()} руб."
    }
}