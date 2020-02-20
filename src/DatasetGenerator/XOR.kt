package DatasetGenerator

import kotlin.random.Random



class XOR :DatasetGenerator{
    override fun generate(): Array<Array<Double>> {
        var d1: Double = Random.nextDouble(-1.0, 1.0)
        var d2: Double = Random.nextDouble(-1.0, 1.0)

        var in1: Boolean = d1 > 0
        var in2: Boolean = d2 > 0

        var outBool: Boolean = in1.xor(in2);
        var output: Double = if (outBool) 1.0 else -1.0
        return arrayOf(arrayOf(d1, d2), arrayOf(output))
    }


}



