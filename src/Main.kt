import ActivationFunctions.*
import Dataset.Dataset
import DatasetGenerator.XOR
import NeuralNetwork.NeuralNetwork


fun main(){
  var af: ActivationFunction = TanH()
  var nn = NeuralNetwork(0.3, af, 2, 4, 2, 1)

  println()
  var dataset = Dataset()
  var xor = XOR()
  dataset.generate(xor, 1000)
  for(i in 0 until 10000){
    nn.trainBatch(256, dataset)
  }
  println(nn.feedForward(arrayOf(-1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(-1.0,1.0))[0])
  println(nn.feedForward(arrayOf(1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(1.0,1.0))[0])
}
