package me.vlasoff.diplombackend.parser

import me.vlasoff.diplombackend.models.parser.Program
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import me.vlasoff.diplombackend.utils.Constants
import me.vlasoff.diplombackend.utils.getNumber
import me.vlasoff.diplombackend.utils.pMap

class ProgramsParser(
    private val url: String
) {
    private val doc = Jsoup.connect(Constants.BASE_URL + url).get()

    fun parse(): List<Program> {
        val pagination = doc.select("ul.pagination > li")
        return if (pagination.isNotEmpty()) {
            val res = mutableListOf<Program>()
            val pages = pagination[pagination.size - 2].child(0).ownText().toInt()
            for (i in 1..pages) {
                res.addAll(parsePage(i))
            }
            res
        } else {
            parsePage(1)
        }
    }

    private fun parsePage(page: Int): List<Program> {
        val document = Jsoup.connect(Constants.BASE_URL + url + "?page=$page").get()
        return document.select("div.itemSpecAll").map { item ->
            var program: Program
            val title = item.select("a.spectittle")[0].ownText()
            val exams = if (item.select("div.egeInVuzProg > span")
                    .isNullOrEmpty()
            ) emptyList() else item.select("div.egeInVuzProg >  span")[0].ownText().split(",", "/")
            val numbersCard = item.select("div.col-md-5 > div.col-md-4.itemSpecAllParamWHide.newbl")

            if (numbersCard.isNullOrEmpty()) {
                program = Program.notActual()
            } else {

                val cost = getNumber(numbersCard[0].getElementsByClass("tooltipq")[0].ownText())

                val freeItem = numbersCard[1]
                val (examResultsForFreePlaces, freePlacesCount) = getPlacesInfo(freeItem)
                val paidItem = numbersCard[2]
                val (examResultsForPaidPlaces, paidPlacesCount) = getPlacesInfo(paidItem)

                program = Program(
                    title,
                    exams,
                    cost,
                    examResultsForFreePlaces,
                    freePlacesCount,
                    examResultsForPaidPlaces,
                    paidPlacesCount,
                )
            }
            program
        }
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