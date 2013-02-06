package kalman

import scalala._
import scalala.tensor.mutable._
import scalala.tensor.dense.DenseMatrix
import scalala.tensor.dense.DenseVectorCol
import scalala.tensor.mutable.Matrix
import scalala.library.LinearAlgebra._
import scalala.operators.Implicits._

case class Initialization extends Dynamics {
  
  // R is measurement Covariance Matrix 
  def R : DenseMatrix[Double] = DenseMatrix.eye[Double](2) * sigma:^2;
  
  // initial State x(0|0)
  def init_state: DenseVectorCol[Double] = DenseVectorCol[Double](0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
  
  // initial Covariance P(0|0)
  def init_covar: DenseMatrix[Double] = {
    DenseMatrix.vertcat(
    DenseMatrix.horzcat( R, DenseMatrix.zeros[Double](2, 4)),
    DenseMatrix.horzcat( DenseMatrix.zeros[Double](2, 2), DenseMatrix.eye[Double](2, 2) * v_max:^2, DenseMatrix.zeros(2, 2)),
    DenseMatrix.horzcat( DenseMatrix.zeros[Double](2, 4), DenseMatrix.eye[Double](2, 2) * q_max:^2)
  )
  }
  
  // number of iterations
  def iter: Int = 100
  
  // Field of View
  def FOV: ((Int,Int),(Int,Int)) = ((-400,400),(-400,400)) 
  
  // Observabilty Matrix
  
  def H: DenseMatrix[Double] = DenseMatrix.horzcat( DenseMatrix.eye[Double](2, 2), DenseMatrix.zeros[Double](2, 2), DenseMatrix.zeros[Double](2, 2))

}