package payback.group.imagefinder.ui.screen.search

sealed class SearchAction  {
    data class DoingSearch(val term:String): SearchAction()
}