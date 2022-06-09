package me.vlasoff.diplombackend.parser

import me.vlasoff.diplombackend.models.parser.University
import me.vlasoff.diplombackend.utils.Constants
import me.vlasoff.diplombackend.utils.getNumber
import me.vlasoff.diplombackend.utils.pMap
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class UniversitiesParser(
    private val city: String? = null,
    private val vuz: String? = null
) {
    fun parse(): List<University> {
        val allUniversities = mutableListOf<University>()
        var currentPage = 1
        while (true) {
            val parsedUniversities = parsePage(currentPage)
            if (!parsedUniversities.isNullOrEmpty()) {
                allUniversities.addAll(parsedUniversities)
                currentPage++
            } else {
                break
            }
        }
        println(allUniversities)
        return allUniversities
    }

    private fun parsePage(page: Int): List<University>? {
        val url = buildUrl(page)
        println(url)
        val document = Jsoup.connect(url).get()
        val universities = document.select("div.vuzesfullnorm").map { universityCard ->
            val title = universityCard.select("div.itemVuzTitle").text()
            val city = universityCard.select("h6.fitemVv > i").text()

            val infoLinkElement = universityCard.select("div.col-md-7 > a")[0]
            val infoLink = infoLinkElement.attr("href")
            val logoUrl = infoLinkElement.child(0).attr("src")

            val specialitiesShortDescription = universityCard.select("div.clearfix.opisItemVV").text()

            val numbersCard = universityCard.select("div.col-md-5 > div.col-md-4.info")

            val cost = getNumber(numbersCard[0].getElementsByClass("tooltipq")[0].ownText())

            val freeItem = numbersCard[1]
            val (examResultsForFreePlaces, freePlacesCount) = getPlacesInfo(freeItem)
            val paidItem = numbersCard[2]
            val (examResultsForPaidPlaces, paidPlacesCount) = getPlacesInfo(paidItem)

            University(
                title = title,
                specialitiesShortDescription = specialitiesShortDescription,
                city = city,
                logoUrl = logoUrl,
                cost = cost,
                examResultsForFreePlaces = examResultsForFreePlaces,
                freePlaces = freePlacesCount,
                examResultsForPaidPlaces = examResultsForPaidPlaces,
                paidPlaces = paidPlacesCount,
                infoLink = infoLink,
                info = UniversityInfoParser(infoLink).parse(),
                specialities = SpecialitiesParser(infoLink).parse(),
                programs = ProgramsParser(infoLink).parse(),
//                professions = ProfessionsParser(infoLink).parse()
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

    private fun buildUrl(page: Int): String {
        return if (city != null && vuz == null) {
            Constants.BASE_URL + city + Constants.PAGE + page
        } else if (city == null && vuz != null) {
            Constants.BASE_URL + Constants.UNIVERSITY + vuz + Constants.PAGE + page
        } else {
            ""
        }
    }
}