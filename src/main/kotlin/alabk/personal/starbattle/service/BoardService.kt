package alabk.personal.starbattle.service

import alabk.personal.starbattle.entity.Colors

class BoardService {

    fun generateStarGrid(): Array<Array<String>> {
        // Initialize an empty 8x8 grid
        val grid = Array(8) { Array(8) { "" } }

        // List of colors to choose from
        val colors = Colors.entries
        val usedColors = mutableSetOf<Colors>()

        // Function to get a random color that hasn't been used yet
        fun getRandomColor(): Colors {
            val availableColors = colors.filter { it !in usedColors }
            return if (availableColors.isNotEmpty()) {
                val color = availableColors.random()
                usedColors.add(color)
                color
            } else {
                colors.random()
            }
        }

        // Check if the cell is available (not adjacent to another star)
        fun isCellAvailable(row: Int, col: Int): Boolean {
            // Check bounds
            if (row !in 0 until 8 || col !in 0 until 8) return false

            // Check adjacent cells (including diagonals)
            for (r in maxOf(row - 1, 0)..minOf(row + 1, 7)) {
                for (c in maxOf(col - 1, 0)..minOf(col + 1, 7)) {
                    if ((r != row || c != col) && grid[r][c].isNotEmpty()) {
                        return false
                    }
                }
            }
            return true
        }

        // Place one star in each row
        for (row in 0 until 8) {
            var placed = false
            while (!placed) {
                val col = (0..8).random() // Random column index for star placement
                if (isCellAvailable(row, col)) {
                    val color = getRandomColor()
                    grid[row][col] = "${color.colorCode}X" // Append 'X' to indicate a star
                    placed = true
                }
            }
        }

        return grid
    }

}