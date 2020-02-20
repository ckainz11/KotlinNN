package ActivationFunctions

import kotlin.math.*

class Relu: ActivationFunction {
    override fun applyActivation(x: Double): Double {
        return max(0.0, x)
    }

    override fun derivative(x: Double): Double {
        if(x>0)
            return 1.0
        return 0.0


    }
}