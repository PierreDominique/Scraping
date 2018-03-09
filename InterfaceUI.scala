

// Interface Utilisateur-Machine ///////////////////////////////////////////////////////////////////////////////

object interfaceUI {
  def init(): Unit = {

    println("\n---------")
    println("Bienvenue")
    println("---------\n")

    val erreur = "!!!! Commande invalide - Entrez un nombre valide !!!!\n"

    var boucle1 = true

    // Boucler sur le menu principal
    while (boucle1) {
      println("Liste des continents :")
      Terre.listConts
      println("-0- QUITTER")
      println("Veuillez choisir un continent :")
      print(">>> ")

      var input1 = -1

      try {
        input1 = scala.io.StdIn.readInt()
      }
      catch {
        case _: Throwable => input1 = -1
      }

      print("\n")

      if (input1 == 0) {
        println("---------")
        println("Au Revoir")
        println("---------")
        System.exit(1)
        boucle1 = false
      }
      if (input1 < 0 || input1 > Terre.conts.length) {
        println(erreur)
      }
      else {
        var c = Terre.conts.apply(input1 - 1)
        var boucle2 = true

        // Boucler sur le menu des Pays
        while (boucle2) {
          println("Continent " + c.nom + " - Selectionnez une option :")
          println("-1- Nombre de Pays en " + c.nom)
          println("-2- Liste des Pays en " + c.nom)
          println("-3- Retour")
          print(">>> ")

          // Try-Catch permettant de gérer les erreurs de saisie
          try {
            input1 = scala.io.StdIn.readInt()
          }
          catch {
            case _: Throwable => input1 = -1
          }

          print("\n")

          if (input1 == 1) {
            println("Il y a " + c.pays.length + " Pays sur le continent " + c.nom + "\n")
          }
          if (input1 == 2 || input1 == 3) {
            boucle2 = false
          }
          if (input1 < 1 || input1 > 3) {
            println(erreur)
          }
        }
        var boucle3 = true

        if (input1 == 2) {
          println("Continent " + c.nom + " - Liste des Pays :")
          c.listPays
          println("Selectionnez un Pays")
          print(">>> ")
          var input2 = -1

          // Try-Catch permettant de gérer les erreurs de saisie
          try {
            input2 = scala.io.StdIn.readInt()
          }
          catch {
            case _: Throwable => input2 = -1
          }

          print("\n")

          if (input2 < 0 || input2 > c.pays.length) {
            println(erreur)
          }

          else {
            var p = c.pays.apply(input2 - 1)

            // Boucler sur le menu d'un Pays
            while (boucle3) {
              println("Pays " + p.nom + " - Selectionnez une option :")
              println("-1- Capitale du pays")
              println("-2- Population du pays")
              println("-3- Superficie du pays")
              println("-4- Retour")
              print(">>> ")

              // Try-Catch permettant de gérer les erreurs de saisie
              try {
                input2 = scala.io.StdIn.readInt()
              }
              catch {
                case _: Throwable => input2 = -1
              }
              print("\n")

              if (input2 == 1) {
                p.afficheCapitale
              }
              if (input2 == 2) {
                p.affichePop
              }
              if (input2 == 3) {
                p.afficheSuperficie
              }
              if (input2 == 4) {
                boucle3 = false
              }
              if (input2 < 1 || input2 > 4) {
                println(erreur)
              }
            }
          }
        }
      }
    }
  }
}