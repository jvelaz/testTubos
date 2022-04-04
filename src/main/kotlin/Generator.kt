
import csstype.NamedColor
import org.w3c.dom.HTMLOptionsCollection
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.get

//import org.w3c.dom.*
private val MAXITEMSPERCOLOUR = 4 //how many items in each colour
private val MAXITEMSPERCOLUMN = 4 //how many items in each column (tube)

enum class Colours (val id: Int) {
    ORANGE(0),
    GREEN(1),
    RED(2),
    BLUE(3),
    PURPLE(4),
    PINK(5)
}

var colorMap : MutableMap <Colours, NamedColor> = mutableMapOf(
Colours.ORANGE to NamedColor.orange,
Colours.GREEN to NamedColor.green,
Colours.RED to NamedColor.red,
Colours.BLUE to NamedColor.blue,
Colours.PURPLE to NamedColor.purple,
Colours.PINK to NamedColor.lightpink
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

fun getSelectValues (select : HTMLSelectElement) :MutableList<String>{
    var result:MutableList<String> = mutableListOf()
    var options: HTMLOptionsCollection = select.options

    for (i in 0 until options.length){
        var opts= options[i]?.getAttribute("selected")
        if (opts != null) {
            result.add(opts)
        }
    }
    return result
}