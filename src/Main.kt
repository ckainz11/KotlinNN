import kotlin.random.Random

fun main(args: Array<String>){
  var dataset: Array<Array<Double>> = arrayOf(arrayOf(1.0,1.0), arrayOf(1.0,0.0), arrayOf(0.0,1.0), arrayOf(0.0,0.0))
  var targets: Array<Array<Double>> = arrayOf(arrayOf(1.0), arrayOf(0.0), arrayOf(0.0), arrayOf(0.0))
  var nn: NeuralNetwork = NeuralNetwork(0.03,2,2,1)
  println(nn.feedForward(arrayOf(1.0,1.0))!!.get(0))
  println(nn.feedForward(arrayOf(0.0,0.0))!!.get(0))
  println()
  for(i in 0 until 10000){
    val random = Random.nextInt(0, dataset.size)
    nn.train(dataset[random], targets[random])
  }
  println(nn.feedForward(arrayOf(1.0,1.0))!!.get(0))
  println(nn.feedForward(arrayOf(0.0,0.0))!!.get(0))


}
