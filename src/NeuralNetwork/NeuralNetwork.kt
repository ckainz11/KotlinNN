package NeuralNetwork

class NeuralNetwork(private var learningRate: Double, vararg layers: Int){
    private var nodes: Array<Int> = Array(layers.size){i->layers[i]}
    private var weights: Array<Matrix> = Array(layers.size-1){ i-> Matrix(nodes[i + 1], nodes[i]) }
    private var biases: Array<Matrix> = Array(layers.size-1){ i-> Matrix(nodes[i + 1], 1) }
    fun feedForward(data: Array<Double>): Array<Double>? {


        return if(data.size != nodes[0])
            null
        else{
            val inputs: Matrix = Matrix.matrixFromArray(data);

            var hidden: Matrix = weights[0].dotProduct(inputs)!!
            hidden.add(biases[0])
            hidden.sigmoid()
            for(i in 1 until weights.size){
                hidden = weights[i].dotProduct(hidden)!!
                hidden.add(biases[i])
                hidden.sigmoid()
            }
            return Matrix.arrayFromMatrix(hidden)
        }

    }
    fun train(data: Array<Double>, target: Array<Double>){
        val outputs: ArrayList<Matrix> = ArrayList()
        val targets: Matrix = Matrix.matrixFromArray(target)
        val inputs: Matrix = Matrix.matrixFromArray(data);

        outputs.add(inputs.clone());

        var hidden: Matrix = weights[0].dotProduct(inputs)!!
        for(i in weights.indices){
            if(i == 0){
                hidden.add(biases[0])
                hidden.sigmoid()
            }
            else {
                hidden = weights[i].dotProduct(hidden)!!
                hidden.add(biases[i])
                hidden.sigmoid()
            }
            outputs.add(hidden.clone())
        }


        var cost: Matrix = Matrix.subtract(targets, outputs.get(outputs.size-1))
        for(i in weights.size-1 downTo 0 ){
            //println("${cost.cols}, ${cost.rows}")
            if(i == weights.size-1){
                var gradient: Matrix = outputs.get(i+1).derivative()
                gradient = Matrix.multiply(cost, gradient)

                gradient.applyScalar(learningRate)
                biases[i].add(gradient.clone())
                gradient = gradient.dotProduct(Matrix.transpose(outputs.get(i)))!!

                weights[i].add(gradient.clone())

            }
            else {
                cost = Matrix.transpose(weights[i+1]).dotProduct(cost)!!
                var gradient: Matrix = outputs.get(i+1).derivative()
                gradient = Matrix.multiply(cost, gradient)
                gradient.applyScalar(learningRate)
                biases[i].add(gradient.clone())
                gradient = outputs[i].dotProduct(Matrix.transpose(gradient))!!
                weights[i].add(Matrix.transpose(gradient))


            }




        }
    }
}