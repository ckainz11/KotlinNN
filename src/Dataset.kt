import kotlin.random.Random

class Dataset(vararg data: DoubleArray) {
    var labels: Array<DoubleArray> = Array<DoubleArray>(data.size){i-> data[i] }
    fun get(): DoubleArray{
        return labels[Random.nextInt()]
    }
}