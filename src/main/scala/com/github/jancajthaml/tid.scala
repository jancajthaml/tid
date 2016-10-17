package com.github.jancajthaml

/**
  * Helpers without uneccessary roundtrips
  *
  * @author jan.cajthaml
  */
private[jancajthaml] object x {
  val last: java.util.concurrent.ConcurrentHashMap[Long, Long]
    = new java.util.concurrent.ConcurrentHashMap[Long, Long]()
}

/**
  * @author jan.cajthaml
  */
object tid extends (() => String) {

  /**
   * @return time id
   */
  def apply(): String = {
    val now: Long = java.time.LocalDateTime.now(java.time.ZoneOffset.UTC).toInstant(java.time.ZoneOffset.UTC).toEpochMilli
    x.last.putIfAbsent(now, 0l)
    var r: java.lang.Long = null
    do { r = x.last.get(now) } while (!x.last.replace(now, r, r + 1));
    ((now * 10000) + r).toString
  }

}
