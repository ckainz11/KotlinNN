class NeuralNetwork(vararg layers: Int){
    private var nodes: Array<Int> = Array(layers.size){i->layers[i]}
    private var weights: Array<Matrix> = Array(layers.size-1){i->Matrix(nodes[i+1],nodes[i])}
    private var biases: Array<Matrix> = Array(layers.size-1){i->Matrix(nodes[i+1], 1)}
    fun feedForward(vararg input: Double): Array<Double>{
        return if(input.size != nodes[0])
            null!!
        else{
            var inputs: Matrix = Matrix(input.size, 1)
            inputs.matrixFromArray(input)
            var hidden: Matrix = weights[0].multiply(inputs)!!
            hidden.add(biases[0])
            hidden.sigmoid()
            for(i in 1..weights.size){
                hidden = weights[i].multiply(hidden)!!
                hidden.add(biases[i])
                hidden.sigmoid()
            }
            hidden.arrayFromMatrix()
        }

    }
}