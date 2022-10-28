# eMovie
Descubre las películas y visualiza su trailer

¿En qué consiste el principio de responsabilidad única?
- Este principio de lo que nos habla es que cada clase o metodo deberia tener una unica responsabilidad, pero para saber que se cuenta solo con una responsabilidad hay varias tecnicas, como el buen uso de los nombres tanto en las clases como en los metodos, por ejemplo si al nombrar un metodo que diga "validateDataAndGetInfo" se estaria incumpliendo con el principio ya que vemos que si separamos la función en sus responsabilidades unicas quedarian una función llamada "validateData" y otra "getInfo" también un sintoma de que una clase tiene mas de una responsabilidad esta en saber si tiene muchos metodos privados es un indicio de que una responsabilidad entre todos esos metodos privados pueden llevarse a una clase y dejar un solo metodo publico.

¿Cuál es su propósito?
- El proposito de el principio de responsabilidad unica es ayudar a tener una cohesión en el codigo, ayudar a evitar boiler code y asi tener claridad en la responsabilidad que tiene cada clase y cada metodo.

¿Qué características tiene, según su opinión, un “buen” código o código limpio?
Un codigo limpio deberia:
 - Tener la capacidad de ser entendido por cualquier programador
 - Tener nombres claros que digan lo que realmente esta haciendo la funcionalidad en el codigo
 - Debe tener un bajo acomplamiento
 - Las dependencias de terceros deben tener inversión de dependencias
 - Deben tener pruebas que documenten lo que realmente hace el codigo
 - No debe tener comentarios basura que engañen las funcionalidades
 - Deberian poder ser escalable sin la necesidad de retrabajar lo que se tiene
 - Deberia poder cumplir con algunos principios SOLID
 - Utilizar patrones de diseño para afrontar los problemas de comportamiento, estructurales o creacionales

Detalla cómo harías todo aquello que no hayas llegado a completar.
 - De los puntos importantes se llego a completar las funcionalidades, en funcionalidades para dejar el codigo con un tema de configuración hubiera agregado los tipos de secciones que iba a tener en la pantalla inicial, también los textos que se mostraran consumiendo un servicio web o almacenar localmente la configuración y que de esa manera al cargar los datos iniciales sea de manera dinamica la carga de secciones como en este caso lo fueron "Proximos estrenos", "Tendencia" y "Recomendados para ti".  

