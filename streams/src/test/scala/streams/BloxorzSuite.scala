package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) {
        case (block, move) => move match {
          case Left => block.left
          case Right => block.right
          case Up => block.up
          case Down => block.down
        }
      }
  }

  trait Level1 extends SolutionChecker {
    /* terrain for level 1*/

    val level =
      """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0, 0)), "0,0")
      assert(!terrain(Pos(4, 11)), "4,11")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1, 1))
    }
  }

  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }

  trait Level2 extends SolutionChecker {
    /* terrain for level 3*/

    val level =
      """ooo-------
      |oSooTo----
      |ooooooooo-
      |-ooooooooo
      |-----ooooo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right)
  }

  test("terrain function level 2") {
    new Level2 {
      assert(terrain(Pos(0, 0)), "0,0")
      assert(!terrain(Pos(4, 11)), "4,11")
    }
  }

  test("findChar level 2") {
    new Level2 {
      assert(startPos == Pos(1, 1))
    }
  }

  test("optimal solution for level 2") {
    new Level2 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 2") {
    new Level2 {
      assert(solution.length == optsolution.length)
    }
  }

  trait Level3 extends SolutionChecker {
    /* terrain for level 3*/

    val level =
      """ooo-------
      |ooooTo----
      |ooooooooo-
      |-oooSooooo
      |-----ooooo
      |------ooo-""".stripMargin

    val optsolution = List(Left, Up, Up, Right)
  }

  test("terrain function level 3") {
    new Level3 {
      assert(terrain(Pos(3, 4)), "3,4")
      assert(!terrain(Pos(4, 11)), "4,11")
    }
  }

  test("findChar level 3") {
    new Level3 {
      assert(startPos == Pos(3, 4))
    }
  }

  test("optimal solution for level 3") {
    new Level3 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 3") {
    new Level3 {
      assert(solution.length == optsolution.length)
    }
  }
}
