package br.ufrn.dimap.forall.transmut.spark.model

import br.ufrn.dimap.forall.transmut.model._
import scala.collection.mutable.ListBuffer
import scala.meta.Tree

class SparkRDDTransformation(override val id: Long) extends Transformation {
  
  def this(id: Long, name: String, params: List[Tree], source: Tree) {
    this(id)
    _name = name
    _params = params
    _source = source
  }

  private var _name: String = _
  private var _params: List[Tree] = _
  private var _source: Tree = _
  private var _edges: ListBuffer[Edge] = scala.collection.mutable.ListBuffer.empty[Edge]
  
  override def name = _name
  def name_=(n: String) {
    _name = n
  }
  
  override def params = _params
  
  def params_=(p : List[Tree]) {
    _params = p
  }
  
  override def source = _source
  
  def source_=(s : Tree) {
    _source = s
  }
  
  override def edges = _edges.toList
  
  def addEdge(edge: Edge) {
    _edges += edge
  }
  
}

object SparkRDDTransformation {
  def apply(id: Long, name: String, params: List[Tree], source: Tree) = new SparkRDDTransformation(id, name, params, source)
  def apply(id: Long, name: String, source: Tree) = new SparkRDDTransformation(id, name, List(), source)
}