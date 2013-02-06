package kalman

import scalala._
import scalala.tensor.mutable._
import scalala.tensor.dense.DenseMatrix
import scalala.tensor.dense.DenseVectorCol
import scalala.tensor.mutable.Matrix
import scalala.library.LinearAlgebra._
import scalala.operators.Implicits._

class Kalman(init: Initialization,sensor: SensorModel) extends Dynamics {
  
  val R = init.R  // Measurement Covariance
  val H = init.H  // Observation Matrix 
  
  
  // kalman filter implementation 
  def kalmanfilter(x: DenseVectorCol[Double],P: DenseMatrix[Double], deltaT: Double): State = {
   
    // Prediction cycle 
    var xkk_1 = F(deltaT) * x
    var Pkk_1 = F(deltaT) * P * F(deltaT).t + Q(deltaT)
    
    // Correction cycle
    var vkk_1 = sensor.Z.last - H * xkk_1
    var Skk_1 = H * Pkk_1 * H.t + R
    var Wkk_1 = Pkk_1 * H.t * inv(Skk_1)
    
    var xkk = xkk_1 + Wkk_1 * vkk_1
    var Pkk = Pkk_1 - Wkk_1 * Skk_1 * Wkk_1.t
    
    
    var state = Array(xkk_1,xkk)
    var covar = Array(Pkk_1,Pkk)
  
    new State(state,covar)
  }
  

}