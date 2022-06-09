package me.vlasoff.diplombackend.parser

import me.vlasoff.diplombackend.models.parser.UniversityInfo
import me.vlasoff.diplombackend.utils.Constants
import org.jsoup.Jsoup

class UniversityInfoParser(
    private val url: String
) {
    private val document = Jsoup.connect(Constants.BASE_URL + url).get()
    fun parse(): UniversityInfo {
        val title = document.select("h1.mainTitle.fc-white")[0].ownText()
        val vuzOpiton = document.select("div.vuzOpiton > i").map { element ->
            element.attr("class") == "material-icons vuzok"
        }
        val description = document.select("div.midVuztext")[0].ownText()
        val contacts = document.select("div.col-lg-8.col-md-8.col-xs-8.col-sm-8").map { it.ownText() }
        return UniversityInfo(
            title,
            vuzOpiton[0],
            vuzOpiton[1],
            vuzOpiton[2],
            vuzOpiton[3],
            vuzOpiton[4],
            description,
            contacts[0],
            contacts[1],
            contacts[2],
            contacts[3],
        )
    }
}