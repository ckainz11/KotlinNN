package ActivationFunctions

import kotlin.math.*

class TanH: ActivationFunction{
    override fun applyActivation(x: Double): Double {
        return tanh(x)
    }

    override fun derivative(x: Double): Double {
        return 1.0-x*x
    }


}