import me.vlasoff.diplombackend.models.parser.UniversityInfo
import me.vlasoff.diplombackend.utils.Constants
import org.jsoup.Jsoup

class UniversityInfoParser(
    private val univerId: Int
) {
    private val document = Jsoup.connect(Constants.BASE_URL + univerId).get()
    fun parse(): UniversityInfo {
        val title = document.select("h1.mainTitle.fc-white")[0].ownText()
        val vuzOption = document.select("div.vuzOpiton > i").map { element ->
            element.attr("class") == "material-icons vuzok"
        }
        val description = document.select("div.midVuztext")[0].ownText()
        val contacts = document.select("div.col-lg-8.col-md-8.col-xs-8.col-sm-8").map { it.ownText() }
        return UniversityInfo(
            title,
            vuzOption[0],
            vuzOption[1],
            vuzOption[2],
            vuzOption[3],
            vuzOption[4],
            description,
            contacts[0],
            contacts[1],
            contacts[2],
            contacts[3],
            univerId
        )
    }
}