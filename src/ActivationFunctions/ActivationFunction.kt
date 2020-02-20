package ActivationFunctions

interface ActivationFunction {
    fun applyActivation(x: Double): Double
    fun derivative(x: Double): Double
}