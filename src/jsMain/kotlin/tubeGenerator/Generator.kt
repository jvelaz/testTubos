package tubeGenerator

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color.blue
import org.jetbrains.compose.web.css.Color.green
import org.jetbrains.compose.web.css.Color.lightpink
import org.jetbrains.compose.web.css.Color.orange
import org.jetbrains.compose.web.css.Color.purple
import org.jetbrains.compose.web.css.Color.red


enum class Colours (val id: Int) {
    ORANGE(0),
    GREEN(1),
    RED(2),
    BLUE(3),
    PURPLE(4),
    PINK(5)
}

var colorMap : MutableMap <Colours, CSSColorValue> = mutableMapOf(
Colours.ORANGE to orange,
Colours.GREEN to green,
Colours.RED to red,
Colours.BLUE to blue,
Colours.PURPLE to purple,
Colours.PINK to lightpink
)

fun initialize (remaining : MutableMap<Colours,Int>){
    if (remaining.isNotEmpty()){
        remaining.clear()
    }

    Colours.values().forEach {
        remaining[it] = MAXITEMSPERCOLOUR
    }
}

fun initialize (remaining : MutableMap<Colours,Int>, colorList : MutableMap<Colours,Boolean>){
    if (remaining.isNotEmpty()){
        remaining.clear()
    }

    colorList.forEach {
        if (it.value){
            remaining[it.key] = MAXITEMSPERCOLOUR
        }else{
            remaining.remove(it.key)
        }
    }
}

fun buildColumn (remaining : MutableMap<Colours,Int>)  : MutableList<Colours>{
    var column :MutableList<Colours> = mutableListOf()

    for (i in 0 until MAXITEMSPERCOLUMN){
        var randomElement = remaining.entries.random()

        column.add(randomElement.key)
        if (remaining.getValue(randomElement.key) > 1){
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
