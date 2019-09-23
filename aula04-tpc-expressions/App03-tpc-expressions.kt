fun main() {
    print("Insert an expression in prefix notation: ")
    val input = readLine()
    val expr : Expression = parse(input!!) // String => Função 
    val result = expr()
    print("$input = $result")
}

typealias Expression = () -> Int 

fun parse(input: String) : Expression {
    val tokens = input.split(' ').iterator()
    return eval(tokens)
}

fun eval(tokens: Iterator<String>) : Expression {
    val curr = tokens.next()
    val nr = curr.toIntOrNull()
    return if(nr != null) constant(nr)
    else binaryExpr(curr, eval(tokens), eval(tokens))
}

fun constant(nr: Int) : Expression = {nr}

fun binaryExpr(opr: String, l: Expression, r: Expression) : Expression {
    when(opr) {
        "+" -> return { l() + r() }
        "-" -> return { l() - r() }
        "*" -> return { l() * r() }
        "/" -> return { l() / r() }
    }
    throw IllegalArgumentException("No operator for input: " + opr)
}
