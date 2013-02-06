package kalman

import scalala._
import scalala.tensor.mutable._
import scalala.tensor.dense.DenseMatrix
import scalala.tensor.dense.DenseVectorCol
import scalala.tensor.mutable.Matrix
import scalala.library.LinearAlgebra._
import scalala.operators.Implicits._

class SensorModel(val init:Initialization, val trans: Transition) extends Dynamics {
  
  val H = init.H
  val R = init.R 
  
  // Sensor measurement
  def Z = List( H * trans.state + noise )
  
  // White Noise for measurement
  def noise = {
     DenseVectorCol.randn(R.numCols) * sigma
  
  }

}