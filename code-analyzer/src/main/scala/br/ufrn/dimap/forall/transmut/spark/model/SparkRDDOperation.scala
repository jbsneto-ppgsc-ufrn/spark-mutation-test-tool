package br.ufrn.dimap.forall.transmut.spark.model

import br.ufrn.dimap.forall.transmut.model.Dataset
import br.ufrn.dimap.forall.transmut.model.Edge
import scala.meta.Tree
import scala.meta.contrib._

// Class created to represent other not supported RDD transformations or actions
case class SparkRDDOperation(override val id: Long) extends SparkRDDTransformation(id) {

  def this(id: Long, _name: String, _params: List[Tree], _source: Tree) {
    this(id)
    name = _name
    params = _params
    source = _source
  }

  override def copy(id: Long = this.id, name: String = this.name, source: Tree = this.source, params: List[Tree] = this.params, edges: List[Edge] = this.edges) = {
    var copyTransformation = SparkRDDOperation(id, name, params, source)
    copyTransformation.edges = edges
    copyTransformation
  }

  override def equals(that: Any): Boolean = that match {
    case that: SparkRDDOperation => {
      that.id == id &&
        that.name == name &&
        that.source.isEqual(source) &&
        that.params == params &&
        that.edges == edges
    }
    case _ => false
  }

}

object SparkRDDOperation {
  def apply(id: Long, name: String, params: List[Tree], source: Tree) = new SparkRDDOperation(id, name, params, source)
  def apply(id: Long, name: String, source: Tree) = new SparkRDDOperation(id, name, List(), source)
}