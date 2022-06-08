package me.vlasoff.diplombackend.parser

import UniversitiesParser
import me.vlasoff.diplombackend.models.parser.University
import me.vlasoff.diplombackend.utils.Constants
import org.jsoup.Jsoup

class CitiesParser {

    private val document = Jsoup.connect(Constants.BASE_URL).get()

    suspend fun parse(): Set<University> {
        val citiesElements = document.select("div.col-md-12.itemCityLeft > a")
        val citiesHrefs = citiesElements.map { it.attr("href") }
        val cities = citiesElements.map { it.ownText() }
        val universities = mutableSetOf<University>()
        citiesHrefs.forEachIndexed { index, city ->
            val parser = UniversitiesParser()
            universities.addAll(parser.parse().map { it.copy(city = cities[index]) })
        }
        println()
        println(universities.size)
        return universities
    }
}