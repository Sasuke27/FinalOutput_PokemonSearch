package com.example.khent.pokemonsearchapi

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.apache.commons.lang3.StringUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import java.util.*


class PokemonAdapter(private val context: Context, private val pokemons: ArrayList<PokemonItems>?)
    : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder =
            MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_second, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val pokemon = pokemons!![position]

        holder?.tvPokemonHeight?.text = (" %.2f m".format(Locale.US, pokemon.height.toDouble() / 10))
        holder?.tvPokemonWeight?.text = (" %.1f kg".format(Locale.US, pokemon.weight.toDouble() / 10))
        holder!!.tvPokemonName?.text = StringUtils.capitalize(pokemon.name)
        holder.tvPokemonId?.text = String.format("#" + "%03d", pokemon.id)
        Picasso.with(context).load(pokemon.sprites.frontDefault).into(holder.ivPokemonImage)


        val pokeType = pokemons[position]

        when(pokeType.types.type.name) {
            "normal" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.normal))
            "fighting" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.fighting))
            "flying" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.flying))
            "poison" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.poison))
            "ground" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.ground))
            "rock" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.rock))
            "bug" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.bug))
            "ghost" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.ghost))
            "steel" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.steel))
            "fire" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.fire))
            "water" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.water))
            "grass" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.grass))
            "electric" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.electric))
            "psychic" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.psychic))
            "ice" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.ice))
            "dragon" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.dragon))
            "dark" -> holder.tvPokemonType?.setTextColor(ContextCompat.getColor(context, R.color.dark))


        }


        holder.tvPokemonType?.text = pokeType.types.type.name

        for (i in 0 until pokemon.stats.size) {
            when (i) {
                0 -> holder.tvPokemonSpeed?.append(" ${pokemon.stats[i].baseStat}")
                1 -> holder.tvPokemonSdef?.append(" ${pokemon.stats[i].baseStat}")
                2 -> holder.tvPokemonSAttack?.append(" ${pokemon.stats[i].baseStat}")
                3 -> holder.tvPokemonDefense?.append(" ${pokemon.stats[i].baseStat}")
                4 -> holder.tvPokemonAttack?.append(" ${pokemon.stats[i].baseStat}")
                5 -> holder.tvPokemonHp?.append(" ${pokemon.stats[i].baseStat}")
            }
        }
    }

    fun add(pokemon: PokemonItems?) {
        pokemons?.add(pokemon!!)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = pokemons!!.size

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val ivPokemonImage = itemView?.findViewById<ImageView>(R.id.ivPokemon)
        val tvPokemonName = itemView?.findViewById<TextView>(R.id.tvPokemonName)
        val tvPokemonId = itemView?.findViewById<TextView>(R.id.tvPokemonId)
        val tvPokemonHeight = itemView?.findViewById<TextView>(R.id.HeightNumber)
        val tvPokemonWeight = itemView?.findViewById<TextView>(R.id.WeightNumber)
        val tvPokemonAbility = itemView?.findViewById<TextView>(R.id.pokemon_ability)
        val tvPokemonType = itemView?.findViewById<TextView>(R.id.Types_type)
        val tvPokemonAttack = itemView?.findViewById<TextView>(R.id.pokemon_attack)
        val tvPokemonDefense = itemView?.findViewById<TextView>(R.id.pokemon_defense)
        val tvPokemonSdef = itemView?.findViewById<TextView>(R.id.pokemon_Sdefense)
        val tvPokemonSAttack = itemView?.findViewById<TextView>(R.id.pokemon_Sattack)
        val tvPokemonSpeed = itemView?.findViewById<TextView>(R.id.pokemon_Speed)
        val tvPokemonHp = itemView?.findViewById<TextView>(R.id.pokemon_hp)
        val tvPokemonStatsName_attack = itemView?.findViewById<TextView>(R.id.pokemonStatsName_attack)
        val tvpokemonStatsName_defense = itemView?.findViewById<TextView>(R.id.pokemonStatsName_defense)
        val tvpokemonStatsName_Sattack = itemView?.findViewById<TextView>(R.id.pokemonStatsName_Sattack)
        val tvpokemonStatsName_Sdef = itemView?.findViewById<TextView>(R.id.pokemonStatsName_Sdefense)
        val tvpokemonStatsName_hp = itemView?.findViewById<TextView>(R.id.pokemonStatsName_hp)
        val tvpokemonStatsName_speed = itemView?.findViewById<TextView>(R.id.pokemonStatsName_speed)


    }
}