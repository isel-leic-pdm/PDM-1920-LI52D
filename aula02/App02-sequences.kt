typealias IntSupplier = () -> Int
typealias StringConsumer = (String) -> Unit

fun sum(nrs: Iterable<Int>) : Int {
    var acc = 0
    for(n in nrs) acc += n
    return acc
}

fun <T> traverse(src: Iterable<T>, cons: (T) -> Unit) {
    for(item in src) cons(item)
}


fun main() {
    val const5 : IntSupplier = { 5 }
    // val foo : StringConsumer = { msg -> println(msg) }
    val foo : StringConsumer = ::println
    
    println(const5())
    foo("Ola Mundo")
    
    val arr = listOf(1, 2, 3, 4, 5)
    println(sum(arr))
    traverse(arr, ::println)
}

