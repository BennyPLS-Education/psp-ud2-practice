## Execució

Aquest programa fa ús de caràcters ASCII especials, la terminal del intellj no accepta la majoria utilitzats per aquesta
TUI.

Per executar s'haurà d'obrir a una terminal a part (alerta amb el mida), amb IntelliJ hauràs de compilar a un executable
.jar, aquest es localitzara a la base del projecte i no a la carpeta out.

Finalment, tenim 4 paràmetres opcionals dels quals els 2 primers són dependents.

**Arguments:**

1. Durada de cada actualització
    * **int**
2. Llargària del camí
    * **double**
3. Eliminar la pregunta de top 3
    * **bool**
4. Repetir Infinitament
    * **bool**

Exemple:** java -jar practice.jar 500 0.1 true true**