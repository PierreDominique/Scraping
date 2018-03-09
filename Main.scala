

// Main du projet /////////////////////////////////////////////////////////////////////////////////////////////

object Main {
  def main(args: Array[String]): Unit = {
    // Recuperation de la page html
    var url = "http://www.drapeauxdespays.fr/continent"
    var result = scala.io.Source.fromURL(url).mkString

    // Instanciation de la Terre
    Scraping.buildEarth(result)

    // Appel de l'interface Utilisateur-Machine
    interfaceUI.init()
  }
}