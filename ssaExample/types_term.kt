package GoToJava

import java.io.BufferedReader
import jacodbInst.*
class types_term {

	var tilde: Boolean? = null
	var typ: Any? = null
}

fun read_types_term(buffReader: BufferedReader, id: Int): types_term {
	val res = types_term()
    if (id != -1) {
        if (ptrMap.containsKey(id)) {
            return ptrMap[id] as types_term
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
    res.tilde = mapDec[readType]?.invoke(buffReader, id) as Boolean?

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
    res.typ = mapDec[readType]?.invoke(buffReader, id) as Any?

	buffReader.readLine()
	return res
}
