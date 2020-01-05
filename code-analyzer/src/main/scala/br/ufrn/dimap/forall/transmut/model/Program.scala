package br.ufrn.dimap.forall.transmut.model

import scala.meta._
import scala.collection.mutable._

trait Program extends Element {
  
  def programSource: ProgramSource

  def name: String

  def tree: Tree

  def datasets: List[Dataset]

  def transformations: List[Transformation]

  def edges: List[Edge]

  def inputDatasets = datasets.filter(d => d.isInputDataset)

  def outputDatasets = datasets.filter(d => d.isOutputDataset)

  def datasetByReferenceName(name: String): Option[Dataset] = datasets.filter(d => d.reference.name == name).headOption

  def isDatasetByReferenceNameDefined(name: String) = datasetByReferenceName(name).isDefined
  
  def copy(id: Long = this.id, programSource : ProgramSource = this.programSource, name: String = this.name, tree: Tree = this.tree, datasets: List[Dataset] = this.datasets, transformations: List[Transformation] = this.transformations, edges : List[Edge] = this.edges) : Program

}