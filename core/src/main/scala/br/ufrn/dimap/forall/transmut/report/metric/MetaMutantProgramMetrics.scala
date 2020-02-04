package br.ufrn.dimap.forall.transmut.report.metric

import br.ufrn.dimap.forall.transmut.mutation.model.MetaMutantProgram
import br.ufrn.dimap.forall.transmut.mutation.operator.MutationOperatorsEnum
import br.ufrn.dimap.forall.transmut.mutation.analyzer.MutantResult
import br.ufrn.dimap.forall.transmut.mutation.model.MutantProgramSource
import br.ufrn.dimap.forall.transmut.mutation.model.MutantProgram
import br.ufrn.dimap.forall.transmut.mutation.analyzer.MutantKilled
import br.ufrn.dimap.forall.transmut.mutation.analyzer.MutantError
import br.ufrn.dimap.forall.transmut.mutation.analyzer.MutantSurvived
import br.ufrn.dimap.forall.transmut.mutation.analyzer.MutantEquivalent

case class MetaMutantProgramMetrics(metaMutant: MetaMutantProgram, mutantsVerdicts: List[MutantResult[MutantProgram]]) {
  
  def id = metaMutant.id
  
  def originalProgram = metaMutant.original
  
  def name = originalProgram.name
  
  def code = originalProgram.tree.syntax
  
  def programSource = originalProgram.programSource
  
  def programSourceId = programSource.id
  
  def programSourceName = programSource.source.getFileName.toString().replaceFirst(".scala", "")
  
  def datasets = originalProgram.datasets
  
  def totalDatasets = datasets.size
  
  def transformations = originalProgram.transformations
  
  def totalTransformations = transformations.size
  
  def mutants = metaMutant.mutants
  
  def totalMutants = mutants.size
  
  def mutantsMetrics = mutants.map(m => MutantProgramMetrics(m, mutantsVerdicts.filter(mr => mr.mutant.id == m.id).head))
  
  def mutationOperatorsMetrics = MutationOperatorsMetrics(mutantsMetrics)
  
  def killedMutants = mutantsVerdicts.filter(r => r match {
    case MutantKilled(m) => true
    case _               => false
  }).map(mr => mr.mutant)

  def totalKilledMutants = killedMutants.size

  def survivedMutants = mutantsVerdicts.filter(r => r match {
    case MutantSurvived(m) => true
    case _                 => false
  }).map(mr => mr.mutant)

  def totalSurvivedMutants = survivedMutants.size

  def equivalentMutants = mutantsVerdicts.filter(r => r match {
    case MutantEquivalent(m) => true
    case _                   => false
  }).map(mr => mr.mutant)

  def totalEquivalentMutants = equivalentMutants.size

  def errorMutants = mutantsVerdicts.filter(r => r match {
    case MutantError(m) => true
    case _              => false
  }).map(mr => mr.mutant)

  def totalErrorMutants = errorMutants.size

  def mutationScore = totalKilledMutants.toFloat / (totalMutants - totalEquivalentMutants)
  
}