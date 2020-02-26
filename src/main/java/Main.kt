import ActivationFunctions.*
import Dataset.Dataset
import DatasetGenerator.XOR
import NeuralNetwork.NeuralNetwork
import kotlin.concurrent.thread
import kotlin.coroutines.*;
import kotlin.system.measureTimeMillis

fun main(){


  val af: ActivationFunction = TanH()
  val nn = NeuralNetwork(0.3, af, 2, 4, 2, 1)
  val dataset = Dataset()
  val xor = XOR()
  val timeNeeded = measureTimeMillis {
  dataset.generate(xor, 500)
  repeat(10_000) {
    nn.trainBatch(128, dataset)
  }
  }
  println(nn.feedForward(arrayOf(-1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(-1.0,1.0))[0])
  println(nn.feedForward(arrayOf(1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(1.0,1.0))[0])
  println("${timeNeeded/1000.0} seconds needed")

}





