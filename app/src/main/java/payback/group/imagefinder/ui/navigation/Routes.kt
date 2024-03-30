package payback.group.imagefinder.ui.navigation

enum class Screen {
    SEARCH,
    DETAIL,
}

sealed class NavigationItem(val route: String) {
    object Search : NavigationItem(Screen.SEARCH.name)
    object Detail : NavigationItem(Screen.DETAIL.name)
}