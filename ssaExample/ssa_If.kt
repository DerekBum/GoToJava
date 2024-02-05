package GoToJava

import java.io.BufferedReader
class ssa_If {

	var anInstruction: generatedInlineStruct_000? = null
	var Cond: Any? = null
}

fun read_ssa_If(buffReader: BufferedReader, id: Int): ssa_If {
	val res = ssa_If()
    if (id != -1) {
        if (ptrMap.containsKey(id)) {
            return ptrMap[id] as ssa_If
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
    res.anInstruction = mapDec[readType]?.invoke(buffReader, id) as generatedInlineStruct_000?

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
    res.Cond = mapDec[readType]?.invoke(buffReader, id) as Any?

	buffReader.readLine()
	return res
}