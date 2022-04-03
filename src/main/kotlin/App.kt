
import csstype.*
import react.*
import kotlinx.coroutines.*
import react.css.css
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr



private val scope = MainScope()

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
private val MAXITEMSPERCOLOUR = 4 //how many items in each colour
private val MAXITEMSPERCOLUMN = 4 //how many items in each column (tube)
private val maxColours= Colours.values().size
private var maxColumns = maxColours * MAXITEMSPERCOLOUR / MAXITEMSPERCOLUMN //the columns

//an array with how many items are remaining for each of the selected colors

val App = FC<Props> {io ->
    //final matrix with the final distribution
    var finalLabelConstruct: MutableList<MutableList<Colours>> by useState(mutableListOf())
    var colorList: MutableMap<Colours, Boolean> by useState(mutableMapOf())
    var remaining: MutableMap<Colours, Int> by useState(mutableMapOf())

    Colours.values().forEach { col ->
        colorList[col] = true
    }

    initialize(remaining, colorList)

    useEffectOnce {
        scope.launch {
            for (i in 0 until remaining.size) {
                var column: MutableList<Colours> = buildColumn(remaining)
                finalLabelConstruct.add(column)
            }
        }
    }
    h2 {
        +"Selecciona tus colores:"
    }
    div{
        css{
            padding = 25.px
        }


        h3{
            css{
                padding = 15.px
            }
            +"Colores"
        }
        Colours.values().forEach { col ->
            input{
                type=InputType.checkbox
                id="checkBox"+col.name


                this.onChange?.let {
                    colorList[col] = colorList[col] != true
                }
            }
            +col.name
            span{
                css{
                    padding = 5.px
                }
            }
        }
        button{
            css{
                padding = 15.px
            }
            onClick?.let {

            }
            +"Generar"
        }

    }

    p{
        div{
            id="tabla"
            css{
                padding = 25.px
            }
            initialize(remaining, colorList)

            maxColumns=remaining.size

            for (i in 0 until remaining.size) {
                var column: MutableList<Colours> = buildColumn(remaining)
                finalLabelConstruct.add(column)
            }
            table{
                css{
                    padding = 5.px
                    borderWidth=1.px
                    borderStyle=LineStyle.solid
                    color= NamedColor.black
                }
                tbody{
                    for (i in 0 until MAXITEMSPERCOLUMN) {
                        tr{
                            css{
                                padding = 5.px
                                borderWidth=1.px
                                borderStyle=LineStyle.solid
                                color= NamedColor.black
                            }
                            for (j in 0 until maxColumns) {
                                var colorLabel: Colours = finalLabelConstruct[j][i]
                                var colorCSS = colorMap[colorLabel]
                                td{
                                    css{
                                        padding = 5.px
                                        borderWidth=1.px
                                        borderStyle=LineStyle.solid
                                        color= NamedColor.black
                                        if(colorCSS!=null){
                                            backgroundColor=colorCSS
                                        }
                                    }
                                    +(finalLabelConstruct[j][i].toString()) }
                            }
                        }
                    }
                }
            }
        }
    }

}
