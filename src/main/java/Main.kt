import ActivationFunctions.*
import Dataset.Dataset
import DatasetGenerator.XOR
import NeuralNetwork.NeuralNetwork
import kotlin.concurrent.thread


fun main(){
/*
  val af: ActivationFunction = TanH()
  val nn = NeuralNetwork(0.3, af, 2, 4, 2, 1)
  val dataset = Dataset()
  val xor = XOR()
  dataset.generate(xor, 1_000)
  repeat(20_000) {
    nn.trainBatch(128, dataset)
  }
  println(nn.feedForward(arrayOf(-1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(-1.0,1.0))[0])
  println(nn.feedForward(arrayOf(1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(1.0,1.0))[0])
*/
  for(i in 0..100_000_000)


}




