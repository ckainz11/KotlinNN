package ActivationFunctions

interface ActivationFunction {
    fun apply(x: Double)
    fun derivative(x: Double)
}