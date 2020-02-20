# KotlinNN
## Description

A Neural Network Library for Kotlin made in Kotlin from Scratch

## Usage

Create instance of class NeuralNetwork: var nn: NeuralNetwork = NeuralNetwork(learningRate, ActivationFunction, layers)

ActivationFunctions supported: TanH, Sigmoid, Relu, Linear

All inputs and outputs are Double Arrays

For training use:
nn.trainSingle(input, output) or
nn.trainBatch(batchSize, input, output)