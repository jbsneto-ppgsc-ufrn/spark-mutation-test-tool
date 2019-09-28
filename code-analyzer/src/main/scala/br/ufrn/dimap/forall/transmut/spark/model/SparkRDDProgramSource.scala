package br.ufrn.dimap.forall.transmut.spark.model

import scala.collection.mutable.ListBuffer

import br.ufrn.dimap.forall.transmut.model.ProgramSource
import br.ufrn.dimap.forall.transmut.model.Program
import scala.meta.Tree
import br.ufrn.dimap.forall.transmut.model.Element

case class SparkRDDProgramSource(override val id: Long) extends ProgramSource {

  def this(id: Long, t: Tree) {
    this(id)
    _tree = t
  }

  private var _programs: ListBuffer[SparkRDDProgram] = scala.collection.mutable.ListBuffer.empty[SparkRDDProgram]

  private var _tree: Tree = _

  override def tree = _tree

  def tree_=(t: Tree) {
    _tree = t
  }

  override def programs = _programs.toList

  def addProgram(p: SparkRDDProgram) {
    _programs += p
  }

}

object SparkRDDProgramSource {
  def apply(id: Long, tree: Tree) = new SparkRDDProgramSource(id, tree)
}