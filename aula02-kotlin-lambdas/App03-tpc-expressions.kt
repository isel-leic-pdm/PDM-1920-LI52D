fun main() {
    print("Insert an expression in prefix notation: ")
    val input = readLine()
    val expr : IntSupplier = parse(input!!) // String => Função 
    val result = expr()
    print("$input = $result")
}

fun parse(input: String) : () -> Int {
    return { 0 }
}

typealias IntSupplier = () -> Int
typealias Predicate<T> = (T) -> Boolean