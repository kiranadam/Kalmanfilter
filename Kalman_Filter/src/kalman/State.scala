package kalman

import scalala.tensor.dense.{DenseMatrix,DenseVectorCol}

class State(var x: Array[DenseVectorCol[Double]], var P: Array[DenseMatrix[Double]]) {
  
 
  // Constructor for State
  def this(state: State) = this(state.x, state.P)
  
  // filtering state
  def xkk(): DenseVectorCol[Double] = {
    var xk = x(1)
    xk
  }
  
  // filtering covariance
  def Pkk(): DenseMatrix[Double] = {
    var Pk = P(1)
    Pk
  }
  
  // prediction state
  def xkk_1(): DenseVectorCol[Double] = {
    var xk = x(0)
    xk
  }
  
  // predition covariance
  def Pkk_1(): DenseMatrix[Double] = {
    var Pk = P(0)
    Pk
  }

}