package com.john.emovie.domain.entities

import com.john.emovie.domain.entities.MovieFilter.Companion.maxElementWithFilter
import com.john.emovie.domain.models.Movie
import com.john.emovie.domain.models.MovieDummies.getFiltersOfTenMoviesWithDifferentDateAndLanguage
import com.john.emovie.domain.models.MovieDummies.getMoviesWithReleaseDateNineteenHundredAndEight
import com.john.emovie.domain.models.MovieDummies.getTenMovies
import com.john.emovie.domain.models.MovieDummies.getTenMoviesWithDifferentDateAndLanguage
import com.john.emovie.domain.models.MovieDummies.releaseDateNineteenHundredAndEighty
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.Test

class MovieFilterTest {

    @Test
    fun `Given ten movies When get default movie Then must obtain six films`() {
        val movies = `given ten movies`()
        val defaultMovies = `when get default movies`(movies)
        `then must obtain six films`(defaultMovies)
    }

    private fun `given ten movies`() = getTenMovies()

    private fun `when get default movies`(movies: List<Movie>) =
        MovieFilter(movies).getDefaultMovies()

    private fun `then must obtain six films`(movies: List<Movie>) {
        withClue("the movies size should be six movies") {
            movies.size shouldBe maxElementWithFilter
        }
    }

    @Test
    fun `Given ten movies with different date and language When get filters movies Then must obtain six films`() {
        val movies = `given ten movies with different date and language`()
        val filtersMovies = `when get filters movies`(movies)
        `then the filters should be the filters of ten movies with different date and language`(
            filtersMovies
        )
    }

    private fun `given ten movies with different date and language`() =
        getTenMoviesWithDifferentDateAndLanguage()

    private fun `when get filters movies`(movies: List<Movie>) = MovieFilter(movies).getFilters()

    private fun `then the filters should be the filters of ten movies with different date and language`(
        filtersMovies: List<String>
    ) {
        withClue("the movies size should be the filters should be the filters of ten movies with different date and language") {
            filtersMovies shouldBe getFiltersOfTenMoviesWithDifferentDateAndLanguage()
        }
    }

    @Test
    fun `Given ten movies with different date and language When obtain filtered movies Then the filtered movies should be the movies with release date nineteen hundred and eighty`() {
        val movies = `given ten movies with different date and language`()
        val filteredMovies = `when obtain filtered movies`(movies)
        `then the filtered movies should be the movies with release date nineteen hundred and eighty`(
            filteredMovies
        )
    }

    private fun `when obtain filtered movies`(movies: List<Movie>) =
        MovieFilter(movies).obtainFilteredMovies(
            releaseDateNineteenHundredAndEighty
        )

    private fun `then the filtered movies should be the movies with release date nineteen hundred and eighty`(
        filteredMovies: List<Movie>
    ) {
        withClue("the filtered movies should be the movies with release date nineteen hundred and eighty") {
            filteredMovies shouldBe getMoviesWithReleaseDateNineteenHundredAndEight()
        }
    }
}
