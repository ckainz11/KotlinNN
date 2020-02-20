package NeuralNetwork

import ActivationFunctions.ActivationFunction
import Dataset.Dataset
import kotlin.IllegalArgumentException

class NeuralNetwork(private val learningRate: Double, private val activationFunction: ActivationFunction, vararg layers: Int){
    private var nodes: Array<Int> = Array(layers.size){i->layers[i]}
    private var weights: Array<Matrix> = Array(layers.size-1){ i-> Matrix(nodes[i + 1], nodes[i]) }
    private var biases: Array<Matrix> = Array(layers.size-1){ i-> Matrix(nodes[i + 1], 1) }

    fun feedForward(data: Array<Double>): Array<Double> {


        if(data.size != nodes[0])
            throw IllegalArgumentException("Input-Array size has to match input nodes")
        else{
            val inputs: Matrix = Matrix.matrixFromArray(data);

            var hidden: Matrix = weights[0].dotProduct(inputs)!!
            hidden.add(biases[0])
            hidden.applyActivation(activationFunction)
            for(i in 1 until weights.size){
                hidden = weights[i].dotProduct(hidden)!!
                hidden.add(biases[i])
                hidden.applyActivation(activationFunction)
            }
            return Matrix.arrayFromMatrix(hidden)
        }

    }
    fun trainBatch(batchSize: Int, dataset: Dataset){
        var adjustments: ArrayList<Adjustments> = ArrayList()
        for(i in 0 until batchSize){
            var label: Array<Array<Double>> = dataset.getRandomLabel()
            adjustments.add(train(label[0], label[1]))
        }
        applyAdjustments(average(adjustments))
    }
    fun applyAdjustments(adj: Adjustments){
        for(i in 0 until weights.size){
            biases[i].add(adj.getBiasAdjustment(i))
            weights[i].add(adj.getWeightAdjustment(i))
        }
    }
    fun train(data: Array<Double>, target: Array<Double>): Adjustments{
        val outputs: ArrayList<Matrix> = ArrayList()
        val targets: Matrix = Matrix.matrixFromArray(target)
        val inputs: Matrix = Matrix.matrixFromArray(data);

        outputs.add(inputs.clone());

        var hidden: Matrix = weights[0].dotProduct(inputs)!!
        for(i in weights.indices){
            if(i == 0){
                hidden.add(biases[0])
                hidden.applyActivation(activationFunction)
            }
            else {
                hidden = weights[i].dotProduct(hidden)!!
                hidden.add(biases[i])
                hidden.applyActivation(activationFunction)
            }
            outputs.add(hidden.clone())
        }

        var cost: Matrix = Matrix.subtract(targets, outputs[outputs.size-1])
        var weightAdjustments: ArrayList<Matrix> = ArrayList()
        var biasAdjustments: ArrayList<Matrix> = ArrayList()
        var adj = Adjustments()
        for(i in weights.size-1 downTo 0 ) {
            if (i == weights.size - 1) {
                var gradient: Matrix = outputs[i + 1].derivative(activationFunction)
                gradient = Matrix.multiply(cost, gradient)

                gradient.applyScalar(learningRate)
                biasAdjustments.add(gradient.clone())

                gradient = gradient.dotProduct(Matrix.transpose(outputs[i]))!!

                weightAdjustments.add(gradient.clone())

            } else {
                cost = Matrix.transpose(weights[i + 1]).dotProduct(cost)!!
                var gradient: Matrix = outputs[i + 1].derivative(activationFunction)
                gradient = Matrix.multiply(cost, gradient)
                gradient.applyScalar(learningRate)
                biasAdjustments.add(gradient.clone())
                gradient = outputs[i].dotProduct(Matrix.transpose(gradient))!!
                gradient = Matrix.transpose(gradient)
                weightAdjustments.add(gradient.clone())


            }
        }
        biasAdjustments.reverse()
        weightAdjustments.reverse()
        for(i in 0 until weights.size){
            adj.addBiasAdjustment(biasAdjustments[i])
            adj.addWeightAdjustment(weightAdjustments[i])
        }
        return adj
    }
    fun average(batchAdjustments: ArrayList<Adjustments>): Adjustments {
        var final = Adjustments()
        for(i in 0 until weights.size){
            var weightSum: Matrix = batchAdjustments[0].getWeightAdjustment(i)
            var biasSum: Matrix = batchAdjustments[0].getBiasAdjustment(i)
            for(j in 1 until batchAdjustments.size){
                weightSum.add(batchAdjustments[j].getWeightAdjustment(i))
                biasSum.add(batchAdjustments[j].getBiasAdjustment(i))
            }
            weightSum.divideBy(batchAdjustments.size.toDouble())
            final.addWeightAdjustment(weightSum)
            biasSum.divideBy(batchAdjustments.size.toDouble())
            final.addBiasAdjustment(biasSum)
        }
        return final
    }

}