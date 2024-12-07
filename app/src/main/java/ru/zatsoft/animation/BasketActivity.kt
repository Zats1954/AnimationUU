package ru.zatsoft.animation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import ru.zatsoft.animation.StartActivity.Companion.mBasket
import ru.zatsoft.animation.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity(), Removable {
    private lateinit var binding: ActivityBasketBinding
    private lateinit var basketAdapter: ProductAdapter
    private lateinit var toolBar: Toolbar

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolBar = binding.toolbarMain
        setSupportActionBar(toolBar)
        title = " "
        basketAdapter = ProductAdapter(mBasket)
        binding.rvBasket.adapter = basketAdapter
        binding.rvBasket.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvBasket.addItemDecoration(MyItemDecoration(this, R.drawable.divider))
        binding.rvBasket.setHasFixedSize(true)
        basketAdapter.setOnProductClickListener(
            object : ProductAdapter.OnProductClickListener {
                override fun onProductClick(product: Product, position: Int) {
                    val dialog = RemoveDialog()
                    val args = Bundle()
                    args.putParcelable("product",product)
                    dialog.arguments = args
                    dialog.show(supportFragmentManager,"basket")
                    basketAdapter.notifyDataSetChanged()
                }
            }

        )

        binding.fab.setOnClickListener {
            val intent = Intent(this, BillActivity::class.java)
            startActivity(intent)
        }
    }

    override fun remove(product: Product) {
        mBasket.remove(product)
        basketAdapter.notifyDataSetChanged()

    }
}