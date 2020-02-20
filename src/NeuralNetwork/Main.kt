package NeuralNetwork

import ActivationFunctions.*
import Dataset.Dataset
import DatasetGenerator.XOR


fun main(){
  var af: ActivationFunction = TanH()
  var nn = NeuralNetwork(0.03,af,2,4,2,1)

  println()
  var dataset = Dataset()
  for(i in 0 until 1000){
      var data: Array<Array<Double>> = XOR.generateXOR()
      dataset.addLabel(data[0],data[1])

  }
  for(i in 0 until 10000){
    nn.trainBatch(100, dataset)
  }
  println(nn.feedForward(arrayOf(-1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(-1.0,1.0))[0])
  println(nn.feedForward(arrayOf(1.0,-1.0))[0])
  println(nn.feedForward(arrayOf(1.0,1.0))[0])
}
