import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

///////////////////////
//  Colours - colores
//  0: Orange - Naranja
//  1: Green - Verde
//  2: Red - Rojo
//  3: Blue - Azul
//  4: Purple - Morado
// si se quieren mas o menos colores, simplemente agregarlos al enum
///////////////////////

enum class colours (val id: Int) {
    ORANGE(0),
    GREEN(1),
    RED(2),
    BLUE(3),
    PURPLE(4)
}

val MAXITEMSPERCOLOUR = 4 //how many items in each colour
val MAXITEMSPERCOLUMN = 4 //how many items in each column (tube)

val MAXCOLOURS=colours.values().size
val MAXCOLUMNS = MAXCOLOURS * MAXITEMSPERCOLOUR / MAXITEMSPERCOLUMN //the columns

fun main() {

    var remaining:MutableMap<colours,Int> = mutableMapOf<colours, Int>()
    var finalConstruct : MutableList<MutableList<colours>> = mutableListOf()

    initialize(remaining)

    for (i in 0 until MAXCOLUMNS){
        var column :MutableList<colours> = buildColumn(remaining)
        finalConstruct.add(column)
    }

    finalConstruct.forEach{
        print("${it}\n")
    }

    var count: Int by mutableStateOf(0)
    renderComposable(rootElementId = "root") {
        /*Div({ style { padding(25.px) } }) {
            Button(attrs = {
                onClick { count -= 1 }
            }) {
                Text("-")
            }

            Span({ style { padding(15.px) } }) {
                Text("$count")
            }

            Button(attrs = {
                onClick { count += 1 }
            }) {
                Text("+")
            }
        }*/

        Table({style{   border(3.px)
                        padding(5.px)
                        width(5.px)
                    }   }){
            Tbody {
                Tr {
                    Td{Text("Lane1")}
                    Td{Text("Lane2")}
                    Td{Text("Lane3")}
                }
            }
        }
    }
}

fun initialize (remaining : MutableMap<colours,Int>){
    if (remaining.isNotEmpty()){
        remaining.clear()
    }

    colours.values().forEach {
        remaining[it] = MAXITEMSPERCOLOUR
    }
}

fun buildColumn (remaining : MutableMap<colours,Int>)  : MutableList<colours>{
    var column :MutableList<colours> = mutableListOf()

    for (i in 0 until MAXITEMSPERCOLUMN){
        var randomElement = remaining.entries.random()

        column.add(randomElement.key)
        if (remaining.getValue(randomElement.key) > 1){
            //thank you kotlin/idea/whatever for not letting me use replace... sigh...

            //remaining.replace(randomElement.key,remaining.getValue(randomElement.key) - 1)

            var modifiedKey=randomElement.key
            var modifiedValue = remaining.getValue(randomElement.key) - 1
            remaining.remove(modifiedKey)
            remaining[modifiedKey] = modifiedValue
        }else{
            remaining.remove(randomElement.key)
        }
    }

    return column
}