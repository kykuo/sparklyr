package sparklyr


import org.apache.spark.ml.feature.ElementwiseProduct
import org.apache.spark.ml.linalg._
import org.apache.spark.ml.PipelineStage

object MLUtils2 {
  def sparkVector(v: Array[Double]): Vector = {
    Vectors.dense(v)
  }

  def setScalingVec(elementwiseProduct: ElementwiseProduct,
    v: Array[Double]): ElementwiseProduct = {
    elementwiseProduct.setScalingVec(sparkVector(v))
  }

  def getParamMap(pipelineStage: PipelineStage): Map[String, Any] = {
    Map(pipelineStage.extractParamMap.toSeq map {
      pair => pair.param.name -> (pair.value match {
        case v: DenseVector => v.toArray
        case _ => pair.value
      })
    }: _*)
  }
}