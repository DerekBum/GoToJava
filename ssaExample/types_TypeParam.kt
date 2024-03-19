package GoToJava

import java.io.BufferedReader
import jacodbInst.*
import jacodbInst.GoType
class types_TypeParam : GoType {

	var check: types_Checker? = null
	var obj: types_TypeName? = null
	var index: Long? = null
	var bound: Any? = null

	override val typeName: String
        get() = obj!!.Object!!.name!!
}

fun read_types_TypeParam(buffReader: BufferedReader, id: Int): types_TypeParam {
	val res = types_TypeParam()
    if (id != -1) {
        if (ptrMap.containsKey(id)) {
            return ptrMap[id] as types_TypeParam
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
    res.check = mapDec[readType]?.invoke(buffReader, id) as types_Checker?

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
    res.obj = mapDec[readType]?.invoke(buffReader, id) as types_TypeName?

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
    res.index = mapDec[readType]?.invoke(buffReader, id) as Long?

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
    res.bound = mapDec[readType]?.invoke(buffReader, id) as Any?

	buffReader.readLine()
	return res
}
