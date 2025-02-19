package com.trycatch.prography.navigation

import androidx.annotation.DrawableRes
import com.trycatch.prography.ui.PrographyIcons
import com.trycatch.prography.presentation.main.MainRoute
import com.trycatch.prography.presentation.photo.PhotoRoute

enum class TopLevelDestination(
    @DrawableRes val icon: Int,
    val route: Any,
) {
    MAIN(
        icon = PrographyIcons.House,
        route = MainRoute
    ),
    PHOTO(
        icon = PrographyIcons.Cards,
        route = PhotoRoute
    ),
}