import kalman._
import ploting.Ploting._

object main extends App{

  override def main(args: Array[String]) {
    
    simulation()
    
  }
  
  def simulation() {
    
    val init = new Initialization()
    val trans = new Transition(init)
    val sensor = new SensorModel(init,trans)
    val kalman = new Kalman(init,sensor)
    
    var state = new State(Array(init.init_state,init.init_state), Array(init.init_covar,init.init_covar))
    
    val deltaT: Double = 5.0
    
    for(iter <- 0 until init.iter)
    {
      clear()
      
      trans.state_trans(deltaT)
      val Z = sensor.Z
      
      state = kalman.kalmanfilter(state.xkk, state.Pkk, deltaT)
      Zplotter(Z)
      Xplotter(state.xkk_1,"g")
      Xplotter(state.xkk,"c")
      
      Thread.sleep(100)
      
      
    }
    
  }
  
}