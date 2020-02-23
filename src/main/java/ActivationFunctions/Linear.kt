package ActivationFunctions

class Linear: ActivationFunction{
    override fun applyActivation(x: Double): Double {
        return x
    }

    override fun derivative(x: Double): Double {
        return 1.0
    }
}