package GoToJava

import java.io.BufferedReader
import jacodbInst.*
class types_TypeName {

	var Object: types_object? = null
}

fun read_types_TypeName(buffReader: BufferedReader, id: Int): types_TypeName {
	val res = types_TypeName()
    if (id != -1) {
        if (ptrMap.containsKey(id)) {
            return ptrMap[id] as types_TypeName
        }
        ptrMap[id] = res
    }
    var line: String
    var split: List<String>
    var id: Int
    var readType: String

	line = buffReader.readLine()
	if (line == "end") {
        return res
    }
    split = line.split(" ")
    readType = split[1]
    id = -1
    if (split.size > 2) {
        id = split[2].toInt()
    }
    res.Object = mapDec[readType]?.invoke(buffReader, id) as types_object?

	buffReader.readLine()
	return res
}
