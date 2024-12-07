package ru.zatsoft.animation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import ru.zatsoft.animation.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity(), AddBasket {
    companion object{
        var mBasket = mutableListOf<Product>()
    }
    private lateinit var binding: ActivityStartBinding
    private lateinit var products: MutableList<Product>
    private var basket = mutableListOf<Product>()
    private lateinit var adapter: ProductAdapter
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolBar = binding.toolbarMain
        setSupportActionBar(toolBar)
        title = " "
        products = mutableListOf<Product>(
            Product("Молоко", R.raw.moloko, 30.50),
            Product("Кефир",  R.raw.kefir, 45.00),
            Product("Сметана",R.raw.smetana, 68.20),
            Product("Творог", R.raw.tvorog, 78.80),
            Product("Сыр",    R.raw.syr,   250.35),
            Product("Хлеб",   R.raw.bread, 45.60),
            Product("Сардины",R.raw.sardiny, 145.60)
        )
        adapter = ProductAdapter(products)
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.addItemDecoration(MyItemDecoration(this, R.drawable.divider))
        binding.rvList.setHasFixedSize(true)
        adapter.setOnProductClickListener(
            object : ProductAdapter.OnProductClickListener {
                override fun onProductClick(product: Product, position: Int) {
                    val dialog = AddDialog()
                    val args = Bundle()
                    args.putParcelable("product",product)
                    dialog.arguments = args
                    dialog.show(supportFragmentManager,"custom")
                }
            }
        )

        binding.fab.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exit)
            finishAffinity()
        return super.onOptionsItemSelected(item)
    }

    override fun addBasket(product: Product) {
        println("------- ${product.name}")
        mBasket.add(product)
        basket.add(product)
    }
}