package br.ufrn.dimap.forall.transmut.spark.model

import scala.collection.mutable.ListBuffer

import br.ufrn.dimap.forall.transmut.model.ProgramSource
import br.ufrn.dimap.forall.transmut.model.Program
import scala.meta.Tree
import br.ufrn.dimap.forall.transmut.model.Element
import scala.meta.contrib._
import java.nio.file.Path

case class SparkRDDProgramSource(override val id: Long) extends ProgramSource {

  def this(id: Long, t: Tree, s: Path) {
    this(id)
    tree = t
    source = s
  }

  private var _programs: ListBuffer[SparkRDDProgram] = scala.collection.mutable.ListBuffer.empty[SparkRDDProgram]

  private var _tree: Tree = _

  private var _source: Path = _

  override def source = _source

  def source_=(s: Path) {
    _source = s
  }

  override def tree = _tree

  def tree_=(t: Tree) {
    _tree = t
  }

  override def programs = _programs.toList

  def addProgram(p: SparkRDDProgram) {
    _programs += p
  }

  def removeProgram(p: SparkRDDProgram) {
    val index = _programs.indexOf(p)
    _programs.remove(index)
  }

  override def copy(id: Long = this.id, tree: Tree = this.tree, source: Path = this.source, programs: List[Program] = this.programs): ProgramSource = {
    val copyProgramSoucer = SparkRDDProgramSource(id)
    copyProgramSoucer.source = source
    copyProgramSoucer.tree = tree
    programs.foreach(p => copyProgramSoucer.addProgram(p.copy().asInstanceOf[SparkRDDProgram]))
    copyProgramSoucer
  }

  override def equals(that: Any): Boolean = that match {
    case that: SparkRDDProgramSource => {
      that.id == id &&
        that.source.equals(source) &&
        that.tree.isEqual(tree) &&
        that.programs == programs
    }
    case _ => false
  }

}

object SparkRDDProgramSource {
  def apply(id: Long, tree: Tree, source: Path) = new SparkRDDProgramSource(id, tree, source)
}