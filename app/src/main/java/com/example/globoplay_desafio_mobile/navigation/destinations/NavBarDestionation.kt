package com.example.globoplay_desafio_mobile.navigation.destinations

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.globoplay_desafio_mobile.R
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.HOME_NAV_ITEM
import com.example.globoplay_desafio_mobile.util.TestingTagsConstants.MY_LIST_NAV_ITEM

enum class NavBarDestionation(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val testTag: String,
) {
    HOME(
        label = R.string.inicio,
        icon = R.drawable.ic_home,
        contentDescription = R.string.inicio,
        testTag = HOME_NAV_ITEM,
    ),
    MY_LIST(
        label = R.string.minha_lista,
        icon = R.drawable.ic_star,
        contentDescription = R.string.minha_lista,
        testTag = MY_LIST_NAV_ITEM,
    )
}