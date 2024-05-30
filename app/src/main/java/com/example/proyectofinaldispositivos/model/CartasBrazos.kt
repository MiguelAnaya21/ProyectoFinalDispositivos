package com.example.proyectofinaldispositivos.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CartasBrazos(
    @StringRes val stringResourceId: Int,
    @StringRes val stringResourceId2: Int,
    @DrawableRes val imageResourceId: Int
)
