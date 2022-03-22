package tubeGenerator

import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.Color.black
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.jetbrains.compose.web.renderComposableInBody

///////////////////////
//  Colours - colores
//  0: Orange - Naranja
//  1: Green - Verde
//  2: Red - Rojo
//  3: Blue - Azul
//  4: Purple - Morado
//  5: Pink - Rosa
// si se quieren mas o menos colores, simplemente agregarlos al enum
///////////////////////


// static
val MAXITEMSPERCOLOUR = 4 //how many items in each colour
val MAXITEMSPERCOLUMN = 4 //how many items in each column (tube)
val maxColours= Colours.values().size
val maxColumns = maxColours * MAXITEMSPERCOLOUR / MAXITEMSPERCOLUMN //the columns

fun main() {

    //an array with how many items are remaining for each of the selected colors
    var remaining:MutableMap<Colours, Int> = mutableMapOf()

    //final matrix with the final distribution
    var finalLabelConstruct : MutableList<MutableList<Colours>> = mutableListOf()



    var colorList : MutableMap<Colours,Boolean> = mutableMapOf()

    renderComposable(rootElementId = "root") {
        Div({ style { padding(25.px) } }) {

            Span({ style { padding(15.px) } }) {
                Text("Colores")
            }

            Colours.values().forEach { col ->
                Button(attrs = {
                    onClick { colorList[col] = true }
                }) {
                    Text(col.name)
                }

                Span({ style { padding(5.px) } })
            }
        }
        Div({ style { padding(25.px) } }) {

            Button(attrs = {
                onClick {  }
            }) {
                Text("Generar")
            }
        }
    }

    //val maxColours=colorList.size
    //val maxColumns = maxColours * MAXITEMSPERCOLOUR / MAXITEMSPERCOLUMN //the columns
    //initialize(remaining, colorList)
    initialize(remaining)

    for (i in 0 until maxColumns){
        var column :MutableList<Colours> = buildColumn(remaining)
        finalLabelConstruct.add(column)
    }

    renderComposableInBody() {

        Table({style{   padding(5.px)
                        border{width(1.px)
                                style(LineStyle.Solid)
                                color(black)}
                    }   }){
            Tbody {
                for (i in 0 until MAXITEMSPERCOLUMN) {
                    Tr ({style {
                        padding(5.px)
                        border{width(1.px)
                        style(LineStyle.Solid)
                        color(black)}
                        }}){
                        for (j in 0 until maxColumns) {
                            var colorLabel: Colours = finalLabelConstruct[j][i]
                            var colorCSS = colorMap[colorLabel]
                            Td ({style {
                                padding(5.px)
                                border{width(1.px)
                                style(LineStyle.Solid)
                                color(black)}
                                if (colorCSS != null) {
                                    backgroundColor(colorCSS)
                                }
                                }})
                                { Text (finalLabelConstruct[j][i].toString())}
                        }
                    }
                }
            }
        }
    }
}

