package me.vlasoff.diplombackend.parser

import me.vlasoff.diplombackend.models.parser.Speciality
import me.vlasoff.diplombackend.utils.Constants
import me.vlasoff.diplombackend.utils.getNumber
import me.vlasoff.diplombackend.utils.pMap
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class SpecialitiesParser(
    private val url: String
) {
    private val doc = Jsoup.connect(Constants.BASE_URL + url).get()

    fun parse(): List<Speciality> {
        val pagination = doc.select("ul.pagination > li")
        return if (pagination.isNotEmpty()) {
            val res = mutableListOf<Speciality>()
            val pages = pagination[pagination.size - 2].child(0).ownText().toInt()
            for (i in 1..pages) {
                res.addAll(parseSpecialitiesInPage(i))
            }
            res
        } else {
            parseSpecialitiesInPage(1)
        }
    }

    private fun parseSpecialitiesInPage(page: Int): List<Speciality> {
        val document = Jsoup.connect(Constants.BASE_URL + url + "?page=$page").get()
        return document.select("div.itemSpecAll").map { specialityItem ->
            var speciality: Speciality
            val title = specialityItem.select("a.spectittle")[0].ownText()
            val degreeAndForms = specialityItem.select("div.itemSpecAllinfo > div > i")[0].ownText().split("|")
            val degree = degreeAndForms[0]
            val forms = try {
                degreeAndForms[1].split(",")
            } catch (ex: Exception) {
                emptyList()
            }
            val exams = if (specialityItem.select("div.egeInVuzProg > span")
                    .isNullOrEmpty()
            ) emptyList() else specialityItem.select("div.egeInVuzProg >  span")[0].ownText().split(",", "/")
            val numbersCard = specialityItem.select("div.col-md-5 > div.col-md-4.itemSpecAllParamWHide.newbl")

            if (numbersCard.isNullOrEmpty()) {
                speciality = Speciality.notActual()
            } else {

                val cost = getNumber(numbersCard[0].getElementsByClass("tooltipq")[0].ownText())

                val freeItem = numbersCard[1]
                val (examResultsForFreePlaces, freePlacesCount) = getPlacesInfo(freeItem)
                val paidItem = numbersCard[2]
                val (examResultsForPaidPlaces, paidPlacesCount) = getPlacesInfo(paidItem)

                speciality = Speciality(
                    title,
                    degree,
                    forms,
                    exams,
                    cost,
                    examResultsForFreePlaces,
                    freePlacesCount,
                    examResultsForPaidPlaces,
                    paidPlacesCount,

                    )
            }
            speciality
        }.filter { it != Speciality.notActual() }
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