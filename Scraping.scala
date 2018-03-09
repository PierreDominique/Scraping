import scala.collection.mutable.ArrayBuffer

// Méthodes de Scraping ////////////////////////////////////////////////////////////////////////////////////////

object Scraping {

  // Méthode d'instanciation la Terre //////////////////////////////////////////////////////////////////////////
  def buildEarth(pageHtml: String): Unit = {

    // Split afin de séparer les continents dans un tableau
    var splitArticle: Array[String] = pageHtml.split("article")
    var splitArticle2 = splitArticle.apply(1)
    var splitConts: Array[String] = splitArticle2.split("continent/")

    var listNomConts = ArrayBuffer[String]()
    var listPays = ArrayBuffer[Pays]()
    var listConts = ArrayBuffer[Continent]()

    var nomCont: String = ""
    var endFor = splitConts.length - 1

    // Boucle pour récupérer l'ensemble des continents
    for (i <- 1 to endFor) {
      var start = splitConts.apply(i).indexOf(">") + 1
      var end = splitConts.apply(i).indexOf("<")
      nomCont = splitConts.apply(i).substring(start, end)

      //Recuperation de la liste de Pays par Continent
      listPays = scrapPays(splitConts.apply(i))

      // Liste des Continents (class)
      var continent = new Continent(nomCont,listPays)
      listConts += continent
    }
    // Declaration de la Terre
    Terre.conts = listConts
  }

  // Methode de récupération des Pays pour un Continent donné //////////////////////////////////////////////////
  def scrapPays(continent : String): ArrayBuffer[Pays] = {

    // Split afin de séparer les Pays dans un taleau
    var splitBody: Array[String] = continent.split("body")
    var splitTr: Array[String] = splitBody.apply(1).split("<tr>")
    var listPays = ArrayBuffer[Pays]()

    // Test pour l'Afrique (pub sur la page web séparant le tableau en deux)
    if(splitBody.length > 3){
      splitTr = splitBody.apply(1).concat(splitBody.apply(3)).split("<tr>")
    }

    //Boucle pour récupérer l'ensemble des Pays sur le Continent "continent"
    for (i <- 1 to splitTr.length - 1) {
      var tmp = splitTr.apply(i)
      var nomPays: String = ""
      var nomCapitale: String = ""
      var pop: String = ""
      var superficie: String = ""
      var start: Int = 0
      var end: Int = 0

      // Recuperation du nom du Pays
      var preStart = tmp.indexOf("td-country\">") + 12
      start = tmp.indexOf("\">", preStart) + 2
      end = tmp.indexOf("</a>", start)
      nomPays = tmp.substring(start, end)

      // Recuperation des Capitales
      start = tmp.indexOf("td-capital\">") + 12
      end = tmp.indexOf("</td>", start)
      nomCapitale = tmp.substring(start, end)

      // Recuperation de la Population
      start = tmp.indexOf("td-population\">") + 15
      end = tmp.indexOf("</td>", start)
      pop = tmp.substring(start, end)

      // Recuperation de la Superficie
      start = tmp.indexOf("td-area\">") + 9
      end = tmp.indexOf("&nbsp;km", start)
      superficie = tmp.substring(start, end)

      // Liste des pays (class)
      var pays = new Pays(nomPays, nomCapitale, pop, superficie)
      listPays += pays
    }
    return listPays
  }
}