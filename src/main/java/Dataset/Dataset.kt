package Dataset

import DatasetGenerator.DatasetGenerator
import kotlin.random.Random

class Dataset() {
    var labels: ArrayList<Array<Array<Double>>> = ArrayList()
    fun generate(datasetGenerator: DatasetGenerator, amountOfLabels: Int){
        for(i in 0 until amountOfLabels){
            labels.add(datasetGenerator.generate())
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