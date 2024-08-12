package alabk.personal.starbattle.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.math.absoluteValue

class BoardServiceTest {

    @Test
    fun `generateStarGrid should generate a grid with no 2 adjacent stars horizontally vertically or diagonally`() {
        // arrange
        val service = BoardService()
        repeat(1000) {
            // act
            val result = service.generateStarGrid()

            // assert
            assertThat(result.isValidGrid()).isTrue
        }
    }

    // This function will check if we have two adjacent stars
    private fun Array<Array<String>>.isValidGrid(): Boolean {
        return this
            .flatMapIndexed { _, array ->
                array.mapIndexedNotNull { index, element ->
                    if (element.isNotBlank()) index else null
                }
            }
            .zipWithNext { a, b -> (b - a).absoluteValue }
            .all { it in 2..7 }
    }
}