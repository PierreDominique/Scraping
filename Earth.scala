import scala.collection.mutable.ArrayBuffer

// Declaration des classes Terre-Continent-Pays //////////////////////////////////////////////////////////////////

class Pays(var nom: String, var capitale: String, var pop: String, var superficie : String) {
  def afficheCapitale = println("La capitale du pays est " + capitale)
  def affichePop = println("La population du pays est de " + pop + " habitants")
  def afficheSuperficie = println("La superficie du pays est de " + superficie + " km2")
}

class Continent(var nom: String, var pays: ArrayBuffer[Pays]) {
  def listPays = for (i <- 0 to pays.length - 1) println("-" + (i+1) + "- " + pays.apply(i).nom)
}

object Terre{
  val nom : String = "Terre"
  var conts : ArrayBuffer[Continent] = ArrayBuffer()
  def listConts = for (i <- 0 to conts.length - 1) println("-" + (i+1) + "- " + conts.apply(i).nom)
}