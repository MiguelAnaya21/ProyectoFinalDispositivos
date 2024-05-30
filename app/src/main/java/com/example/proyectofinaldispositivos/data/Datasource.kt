package com.example.proyectofinaldispositivos.data

import com.example.proyectofinaldispositivos.R
import com.example.proyectofinaldispositivos.model.CartasAbdominales

class Datasource() {
    fun loadAbdominales(): List<CartasAbdominales> {
        return listOf<CartasAbdominales>(
            CartasAbdominales(R.string.descAbd1, R.string.tit_abd1, R.drawable.abd_1),
            CartasAbdominales(R.string.descAbd2, R.string.tit_abd2, R.drawable.abd_2),
            CartasAbdominales(R.string.descAbd3, R.string.tit_abd3, R.drawable.abd_3),
            CartasAbdominales(R.string.descAbd4, R.string.tit_abd4, R.drawable.abd_4),
            CartasAbdominales(R.string.descAbd5, R.string.tit_abd5, R.drawable.abd_5))
    }
}