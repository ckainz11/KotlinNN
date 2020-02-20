package NeuralNetwork

import ActivationFunctions.ActivationFunction
import kotlin.random.Random
import kotlin.math.*
class Matrix(val rows: Int, val cols: Int){
    private var matrix = Array(rows) { Array(cols) { Random.nextDouble(-1.0, 1.0) } }

    companion object{
        fun subtract(m: Matrix, m1: Matrix): Matrix {
            if(m.rows != m1.rows)
                println("Matrices do not match in size")
            else{
                for(i in 0 until m.rows){
                    m.matrix[i][0] -=m1.matrix[i][0]
                }
            }
            return m
        }
        fun squareSub(m1: Matrix, m2: Matrix): Matrix?{

            if(m1.rows != m2.rows || m1.cols != m2.cols)
                println("Matrices do not match in size")
            else{
                var m: Matrix = Matrix(m1.rows, m1.cols)
                for(i in 0 until m1.rows){
                    for(j in 0 until m1.cols){
                        m.matrix[i][j] = (m1.matrix[i][j]-m2.matrix[i][j]).pow(2);
                    }
                }
                return m
            }
            return null
        }
        fun transpose(m: Matrix): Matrix {
            var m1: Matrix = Matrix(m.cols, m.rows)
            for(i in 0 until m.rows){
                for(j in 0 until m.cols){
                    m1.matrix[j][i] = m.matrix[i][j]
                }
            }
            return m1
        }
        fun arrayFromMatrix(m: Matrix): Array<Double>{
            return Array<Double>(m.rows ){i->m.matrix[i][0]}
        }
        fun matrixFromArray(arr: Array<Double>?): Matrix {
            var m: Matrix = Matrix(arr!!.size, 1)


                for(i in 0 until  arr.size){
                    m.matrix[i][0] = arr[i]
                }
            return m

        }
        fun multiply(m1: Matrix, m2: Matrix): Matrix {
            var m: Matrix = Matrix(m1.rows, m2.cols)
            if(m1.rows!=m2.rows||m1.cols!=m2.cols)
                throw IllegalArgumentException("Matrices do not match in size: m1, ${m1.rows}, ${m1.cols}, m2, ${m2.rows}, ${m2.cols}")
            else{
                for(i in 0 until m1.rows){
                    for (j in 0 until m1.cols){
                        m.matrix[i][j] = m1.matrix[i][j] * m2.matrix[i][j]
                    }
                }
            }
            return m
        }

    }
    fun dotProduct(m: Matrix): Matrix? {
        if(this.cols != m.rows)
            return null
        var result: Matrix = Matrix(rows, m.cols)
        for(i in 0 until rows){
            for(j in 0 until m.cols){
                var sum: Double = 0.0;
                for(k in 0 until cols){
                    sum += matrix[i][k] * m.matrix[k][j]
                }
                result.matrix[i][j] = sum
            }
        }
        return result
    }
    fun applyScalar(num: Double){
        for(i in 0 until this.rows){
            for(j in 0 until this.cols){
                matrix[i][j]*=num
            }
        }
    }

    fun add(m: Matrix){
        if(this.rows != m.rows && this.cols != m.cols)
            throw java.lang.IllegalArgumentException("Matrices do not match in size: m1, ${this.rows}, ${this.cols}, m2, ${m.rows}, ${m.cols}");
        else{
            for(i in 0 until this.rows){
                for(j in 0 until this.cols){
                    matrix[i][j]+=m.matrix[i][j]
                }
            }
        }
    }
    fun divideBy(divisor: Double){
        for(i in 0 until this.rows){
            for(j in 0 until this.cols){
                matrix[i][j] /= divisor;
            }
        }
    }
    fun subtract(m: Matrix){
        if(this.rows != m.rows)
            println("Matrices do not match in size");
        else{
            for(i in 0 until this.rows){
                for(j in 0 until this.cols){
                    matrix[i][j]-=m.matrix[i][j]
                }
            }
        }
    }
    fun applyActivation(af: ActivationFunction){
        for(i in 0 until this.rows){
            for(j in 0 until this.cols){
                matrix[i][j] = af.applyActivation(matrix[i][j])
            }
        }
    }

    fun derivative(af: ActivationFunction): Matrix {
        for(i in 0 until this.rows){
            for(j in 0 until this.cols){
                matrix[i][j] = af.derivative(matrix[i][j])
            }
        }
        return this.clone()
    }
    fun print(){
        for(i in 0 until this.rows){
            for(j in 0 until this.cols){
                println(matrix[i][j]);
            }
        }
    }
    fun clone(): Matrix {
        var m: Matrix = Matrix(this.rows, this.cols)
        for (i in 0 until this.rows) {
            for (j in 0 until this.cols) {
                m.matrix[i][j] = matrix[i][j]
            }
        }
        return m;
    }




}