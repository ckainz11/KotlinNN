

fun main(){

  var nn: NeuralNetwork = NeuralNetwork(1.0,2,2,1)
  println(nn.feedForward(arrayOf(1.0,1.0))!![0])
  println()
  println()
  println(nn.feedForward(arrayOf(0.0,0.0))!![0])
  println()
  for(i in 0 until 10000){
    nn.train(arrayOf(1.0,1.0), arrayOf(0.0))
    nn.train(arrayOf(1.0,0.0), arrayOf(1.0))
    nn.train(arrayOf(0.0,1.0), arrayOf(1.0))
    nn.train(arrayOf(0.0,0.0), arrayOf(0.0))
  }
  println(nn.feedForward(arrayOf(0.0,1.0))!![0])
  println(nn.feedForward(arrayOf(0.0,0.0))!![0])


}
