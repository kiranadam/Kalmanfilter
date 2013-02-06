package kalman

import scalala._
import scalala.tensor.mutable._
import scalala.tensor.dense.DenseMatrix
import scalala.tensor.dense.DenseVectorCol
import scalala.tensor.mutable.Matrix
import scalala.library.LinearAlgebra._
import scalala.operators.Implicits._

class Transition(init: Initialization) extends Dynamics {
  
  var state = init.init_state
  
  // state transition 
  def state_trans(deltaT: Double) = {
    state = F(deltaT) * state 
  }

}