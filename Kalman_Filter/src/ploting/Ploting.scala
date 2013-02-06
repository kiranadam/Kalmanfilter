package ploting
import scalala.tensor.dense.DenseVectorCol
import scalala.library.Plotting._
import kalman._

object Ploting {
  
  val fig = figure()
  hold(true)
  xlim(-400,400)
  ylim(-400,400)
  
  // sensor measurement plotting
  def Zplotter(Zlist: List[DenseVectorCol[Double]]) = {
    
    val x = DenseVectorCol[Double](Zlist.map(_(0)).toArray)
    val y = DenseVectorCol[Double](Zlist.map(_(1)).toArray)
    
    plot(x,y,'+',"r")
    
  }
  
  // state plotting
  def Xplotter(state: DenseVectorCol[Double], color: String) = {

    plot(Array(state(0)),Array(state(1)),'.',color)
  }

  
  // clear frame
   def clear() {
    clf()(fig)
    hold(true)
    xlim(-400,400)
    ylim(-400,400)

  }

}