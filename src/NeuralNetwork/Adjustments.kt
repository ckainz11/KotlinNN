package NeuralNetwork

class Adjustments {
    var biasAdjustments: ArrayList<Matrix> = ArrayList()
    var weightAdjustments: ArrayList<Matrix> = ArrayList()

    fun addWeightAdjustment(adj: Matrix) = weightAdjustments.add(adj)
    fun addBiasAdjustment(adj: Matrix) = biasAdjustments.add(adj)
    fun getBiasAdjustment(layer: Int): Matrix = biasAdjustments[layer]
    fun getWeightAdjustment(layer: Int): Matrix = weightAdjustments[layer]



}