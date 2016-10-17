package com.github.jancajthaml

import org.scalatest.{FlatSpec, Matchers}

class TimeIDSpecs extends FlatSpec with Matchers {

  "tid" should "render in valid format" in {
    tid() should fullyMatch regex """[0-9]{17}"""
  }

  it should "not generate duplicate keys" in {
    val ids = (0 to 1000).map(_ => tid())
    ids.size should === (ids.toSet.size)
  }

}