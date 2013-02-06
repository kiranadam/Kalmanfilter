package kalman

import scalala.tensor.mutable._
import scalala.tensor.dense.DenseMatrix
import scalala.tensor.dense.DenseVectorCol
import scalala.tensor.mutable.Matrix
import scalala.library.LinearAlgebra._
import scalala.operators.Implicits._
/*
 * 
 */

trait Dynamics {
  
  
  
  
  def sigma: Double = 50;
  def v_max: Double = 335.41;
  def q_max: Double = 9.14;
  def theta: Double = 60;
  
  // F is the Dynamic/Evolution/State Transition Matrix
  def F(deltaT: Double): DenseMatrix[Double] = {
    DenseMatrix.vertcat(
    DenseMatrix.horzcat(DenseMatrix.eye[Double](2), DenseMatrix.eye[Double](2,2) * deltaT , DenseMatrix.eye[Double](2,2) * (deltaT:^2/2)),
    DenseMatrix.horzcat(DenseMatrix.zeros[Double](2,2), DenseMatrix.eye[Double](2,2), DenseMatrix.eye[Double](2,2) * deltaT),
    DenseMatrix.horzcat(DenseMatrix.zeros[Double](2,2), DenseMatrix.zeros[Double](2,2), DenseMatrix.eye[Double](2,2) * java.lang.Math.exp(-deltaT/theta)))
  }
  
  
  // G is control vector as per von Keuk model
  def G(deltaT: Double): DenseMatrix[Double] = {
     DenseMatrix.vertcat(DenseMatrix.zeros[Double](4, 2), DenseMatrix.eye[Double](2, 2)) * (q_max * java.lang.Math.sqrt(1 - java.lang.Math.exp(-2 * deltaT/theta)))
  
  }
  
  
  // Q is the Dynamic Covariance Matrix
  
  def Q(deltaT: Double): DenseMatrix[Double] = {
    DenseMatrix.vertcat(
    DenseMatrix.zeros[Double](4, 6), DenseMatrix.horzcat(DenseMatrix.zeros[Double](2, 4),DenseMatrix.eye[Double](2,2))    
    ) * (q_max:^2 *( 1 - java.lang.Math.exp(-2 * deltaT/theta)))
  }
  

}