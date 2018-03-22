package com.example.khent.pokemonsearchapi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), Callback{

    private val pokemonUrl = "https://pokeapi.co/api/v2/pokemon/"
    private var mPokemons: ArrayList<PokemonItems>? = null
    private var mAdapter: PokemonAdapter? = null
    private lateinit var madapter: PokemonAdapter
    internal lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var name: EditText
    private lateinit var SearchBtn: ImageButton
    private lateinit var text: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findview()

        SearchBtn.setOnClickListener {
            fetchPokemons()
        }
    }



    fun findview(){
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        name = findViewById(R.id.etPokemonName)
        recyclerView.layoutManager = LinearLayoutManager(this)
        madapter = PokemonAdapter(this, ArrayList())
        SearchBtn = findViewById(R.id.ibSearch)
        text = findViewById(R.id.txtprogress)

        recyclerView.adapter = madapter
    }


    private fun fetchPokemons() {
        val name = name.text.toString()

        if (TextUtils.isEmpty(name))
            toast("Please input search name.")
        else {
            val request = Request.Builder().url("$pokemonUrl${name.toLowerCase()}/").build()
            val client = OkHttpClient()

            progressBar.visibility = View.VISIBLE
            text.visibility = View.VISIBLE
            client.newCall(request).enqueue(this)
        }
    }
    @SuppressLint("NewApi")
    override fun onFailure(call: Call?, e: IOException?) {
        runOnUiThread {
            toast("Failed to fetch Pokemon data!")
            progressBar.visibility = View.GONE
            text.visibility = View.GONE
        }
    }

    override fun onResponse(call: Call?, response: Response?) {

        val body = response?.body()?.string()
        val jsonObject = JSONObject(body)
        val pokemon = GsonBuilder().create().fromJson(body, PokemonItems::class.java)

        mPokemons = ArrayList()
        mAdapter = PokemonAdapter(this, mPokemons)

        runOnUiThread {
            if (jsonObject.isNull("name"))
                toast("Pokemon not found.")
            else {
                recyclerView.adapter = mAdapter
                mAdapter?.add(pokemon)
                }


            progressBar.visibility = View.GONE
            text.visibility = View.GONE
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (name.isFocused) {
                val outRect = Rect()
                name.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    name.clearFocus()
                    hideKeyboard(view)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard(view: View?) {
        val inputMethodManager = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }



    private fun toast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
