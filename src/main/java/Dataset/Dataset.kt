package Dataset

import DatasetGenerator.DatasetGenerator
import java.io.File
import kotlin.random.Random

class Dataset() {
    var labels: ArrayList<Array<Array<Double>>> = ArrayList()
    fun generate(datasetGenerator: DatasetGenerator, amountOfLabels: Int){
        for(i in 0 until amountOfLabels){
            labels.add(datasetGenerator.generate())
        }
    }
    fun generateFromFile(fileName: String, numOfInputs: Int){
        if(fileName.contains(".csv")) {
            val f = File(fileName)
            f.forEachLine() {
                divideIntoInAndOutputs(readCSV(it), numOfInputs)
            }
        }
    }
    private fun divideIntoInAndOutputs(values: List<Double>, numOfInputs: Int){
        var inputs: Array<Double> = Array(numOfInputs){i->values[i]}
        var targets: Array<Double> = Array(values.size-numOfInputs){
            i->values[i+numOfInputs]
        }
        labels.add(arrayOf(inputs,targets))
    }
    private fun readCSV(line: String): List<Double>{
        return  List(line.split(";").size){
            index -> (line.split(";")[index]).toDouble()
        }
    }
    fun addLabel(input: Array<Double>, target: Array<Double>){
        var label: Array<Array<Double>> = arrayOf(input, target)
        labels.add(label)
    }
    fun getRandomLabel(): Array<Array<Double>>{
        return labels[Random.nextInt(0,labels.size)]
    }



}