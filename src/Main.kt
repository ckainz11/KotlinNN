

fun main(args: Array<String>){

  var nn: NeuralNetwork = NeuralNetwork(0.03,2,1)
  println(nn.feedForward(arrayOf(1.0,1.0))!!.get(0))
  println(nn.feedForward(arrayOf(0.0,0.0))!!.get(0))
  println()
  for(i in 0 until 10000){
    nn.train(arrayOf(1.0,1.0), arrayOf(1.0))
    nn.train(arrayOf(1.0,0.0), arrayOf(0.0))
    nn.train(arrayOf(0.0,1.0), arrayOf(0.0))
    nn.train(arrayOf(0.0,0.0), arrayOf(0.0))
  }
  println(nn.feedForward(arrayOf(1.0,1.0))!!.get(0))
  println(nn.feedForward(arrayOf(0.0,0.0))!!.get(0))


}
