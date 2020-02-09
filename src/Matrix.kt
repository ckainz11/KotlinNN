import kotlin.random.Random
import kotlin.math.*
class Matrix(rows: Int, cols: Int){

    private val rows: Int = rows
    private val cols: Int = cols
    private var matrix = Array(rows) { Array(cols) { Random.nextDouble(-1.0,1.0) } }

    fun multiply(m: Matrix): Matrix? {
        if(this.cols != m.rows)
            return null;
        var result: Matrix = Matrix(rows, m.cols)
        for(i in 0..rows){
            for(j in 0..m.cols){
                var sum: Double = 0.0;
                for(k in 0..cols){
                    sum += matrix[i][k] * m.matrix[k][j]
                }
                result.matrix[i][j] = sum
            }
        }
        return result
    }
    fun matrixFromArray(arr: DoubleArray){
        if(this.cols != 1 && this.rows != arr.size)
            println("Matrix has been created falsely");
        else{
            for(i in 0..arr.size){
                matrix[i][0] = arr[i]
            }
        }
    }
    fun arrayFromMatrix(): Array<Double>{
        return Array<Double>(rows){i->matrix[i][0]}
    }
    fun add(m: Matrix){
        if(this.rows != m.rows && this.cols != m.cols)
            println("Matrices do not match in size");
        else{
            for(i in 0..this.rows){
                for(j in 0..this.cols){
                    matrix[i][j]+=m.matrix[i][j]
                }
            }
        }
    }
    fun sigmoid(){
        for(i in 0..this.rows){
            for(j in 0..this.cols){
                matrix[i][j] = 1.0 / (1.0+exp(-matrix[i][j]))
            }
        }
    }

}