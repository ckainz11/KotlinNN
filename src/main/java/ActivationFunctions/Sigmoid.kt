package ActivationFunctions

import kotlin.math.*
class Sigmoid: ActivationFunction {
    override fun applyActivation(x: Double): Double {
        return 1.0/(1 + exp(-x))
    }

    override fun derivative(x: Double): Double {
        return x * (x - 1)
    }

}