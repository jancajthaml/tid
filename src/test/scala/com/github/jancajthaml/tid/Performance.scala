package com.github.jancajthaml

import org.scalameter.api.{Measurer, Bench, Gen, exec}

object RegressionResources extends Bench.OfflineReport {

  override def measurer = new Measurer.MemoryFootprint

  val times = Gen.range("times")(0, 100000, 20000)

  performance of "com.github.jancajthaml" in {
    measure method "tid" in {
      using(times) config (
        exec.minWarmupRuns -> 2,
        exec.maxWarmupRuns -> 5,
        exec.benchRuns -> 5,
        exec.independentSamples -> 1
      ) in { sz => { (0 to sz).foreach { x => { tid() } } } }
    }
  }
}

object RegressionPerformance extends Bench.OfflineReport {

  val times = Gen.range("times")(0, 100000, 20000)

  performance of "com.github.jancajthaml" in {
    measure method "tid" in {
      using(times) config (
        exec.benchRuns -> 20,
        exec.independentSamples -> 1,
        exec.outliers.covMultiplier -> 1.5,
        exec.outliers.suspectPercent -> 40
      ) in { sz => { (0 to sz).foreach { x => { tid() } } } }
    }
  }

}