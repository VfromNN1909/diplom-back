import me.vlasoff.diplombackend.models.parser.University
import me.vlasoff.diplombackend.utils.Constants
import me.vlasoff.diplombackend.utils.getNumber
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class UniversitiesParser {
    fun parse(): List<University> {
        val allUniversities = mutableListOf<University>()
        (1..50).forEach { page ->
            allUniversities.addAll(parsePage(page))
        }
        println(allUniversities.size)
        return allUniversities
    }

    private fun parsePage(page: Int): List<University> {
        val document = Jsoup.connect(Constants.BASE_URL + "?page=$page").get()
        val universities = document.select("div.vuzesfullnorm").map { universityCard ->
            val title = universityCard.select("div.itemVuzTitle").text()
            val city = universityCard.select("h6.fitemVv > i").text()

            val infoLinkElement = universityCard.select("div.col-md-7 > a")[0]
            val infoLink = getNumber(infoLinkElement.attr("href"))
            val logoUrl = infoLinkElement.child(0).attr("src")

            val numbersCard = universityCard.select("div.col-md-5 > div.col-md-4.info")

            val cost = getNumber(numbersCard[0].getElementsByClass("tooltipq")[0].ownText())

            val freeItem = numbersCard[1]
            val (examResultsForFreePlaces, freePlacesCount) = getPlacesInfo(freeItem)
            val paidItem = numbersCard[2]
            val (examResultsForPaidPlaces, paidPlacesCount) = getPlacesInfo(paidItem)

            University(
                id = infoLink!!.toLong(),
                title = title,
                city = city,
                logoUrl = logoUrl,
                cost = cost,
                examResultsForFreePlaces = examResultsForFreePlaces,
                freePlaces = freePlacesCount,
                examResultsForPaidPlaces = examResultsForPaidPlaces,
                paidPlaces = paidPlacesCount
            )
        }
        return universities
    }


    private fun getPlacesInfo(budgetItem: Element): Pair<Int?, Int?> {
        return try {
            val placesInfo = budgetItem.select("center > a.tooltipq").map { getNumber(it.ownText()) }
            Pair(placesInfo[0], placesInfo[1])
        } catch (ex: Exception) {
            return Pair(null, null)
        }
    }

}