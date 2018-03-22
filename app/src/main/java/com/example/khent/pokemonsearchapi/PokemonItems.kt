package com.example.khent.pokemonsearchapi

/**
 * Created by khent on 3/20/2018.
 */
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pokemons (
        val id: Int,
        val name: String,
        val sprites: Sprites
)

data class Ability(
        var url : String
)

data class PokemonItems(val id: Int,
                        val name: String,
                        val sprites: Sprites,
                        val weight: Int,
                        val height: Int,
                        val types: Types,
                        val stats: ArrayList<Stat>,
                        val abilities: ArrayList<Abilities>
                        )
    : Serializable

data class Abilities(val ability: PokeAbility)

data class Types(val type: Type)

data class PokeAbility(@SerializedName("name") val name: String)

data class Sprites(@SerializedName("front_default") val frontDefault: String)

data class Type(@SerializedName("name") val name: String)

data class Stat(@SerializedName("base_stat") val baseStat: Int)