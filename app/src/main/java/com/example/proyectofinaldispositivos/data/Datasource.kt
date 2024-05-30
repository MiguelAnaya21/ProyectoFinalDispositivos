package com.example.proyectofinaldispositivos.data

import com.example.proyectofinaldispositivos.R
import com.example.proyectofinaldispositivos.model.CartasAbdominales
import com.example.proyectofinaldispositivos.model.CartasBrazos
import com.example.proyectofinaldispositivos.model.CartasEspalda

class Datasource() {
    fun loadAbdominales(): List<CartasAbdominales> {
        return listOf<CartasAbdominales>(
            CartasAbdominales(R.string.descAbd1, R.string.tit_abd1, R.drawable.abd_1),
            CartasAbdominales(R.string.descAbd2, R.string.tit_abd2, R.drawable.abd_2),
            CartasAbdominales(R.string.descAbd3, R.string.tit_abd3, R.drawable.abd_3),
            CartasAbdominales(R.string.descAbd4, R.string.tit_abd4, R.drawable.abd_4),
            CartasAbdominales(R.string.descAbd5, R.string.tit_abd5, R.drawable.abd_5))
    }
    fun loadBrazos(): List<CartasBrazos> {
        return listOf<CartasBrazos>(
            CartasBrazos(R.string.descBra1, R.string.tit_bra1, R.drawable.bra_1),
            CartasBrazos(R.string.descBra2, R.string.tit_bra2, R.drawable.bra_2),
            CartasBrazos(R.string.descBra3, R.string.tit_bra3, R.drawable.bra_3),
            CartasBrazos(R.string.descBra4, R.string.tit_bra4, R.drawable.bra_4),
            CartasBrazos(R.string.descBra5, R.string.tit_bra5, R.drawable.bra_5))
    }

    fun loadEspalda(): List<CartasEspalda> {
        return listOf<CartasEspalda>(
            CartasEspalda(R.string.descEsp1, R.string.tit_esp1, R.drawable.esp_1),
            CartasEspalda(R.string.descEsp2, R.string.tit_esp2, R.drawable.esp_2),
            CartasEspalda(R.string.descEsp3, R.string.tit_esp3, R.drawable.esp_3),
            CartasEspalda(R.string.descEsp4, R.string.tit_esp4, R.drawable.esp_4),
            CartasEspalda(R.string.descEsp5, R.string.tit_esp5, R.drawable.esp_5))
    }

}